package erik.xieli.longin;

import erik.xieli.longin.attri.ChannelAttribute;
import erik.xieli.longin.state.DeviceStateContext;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleStateEvent;


public class DeviceStateHandler extends SimpleChannelInboundHandler<String> {
    public static final ChannelAttribute<DeviceStateContext> session = new ChannelAttribute<>("state");

    //有数据可读的时候触发
    //登录数据的格式 LOGIN:name,pass
    @Override
    public void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        if (0 == msg.length()) {
            return;
        }
        //处理消息
        System.out.println(getClass().getSimpleName() + "." + "channelRead0" + ctx.channel().remoteAddress() + ":" + msg);
        DeviceStateContext deviceStateContext = session.getAttributeValue(ctx);

        //是否是认证操作
        if (msg.startsWith("LOGIN")) {
            //登录操作
            boolean result = login(ctx, msg);
            if (result) {
                //===========login ok,切换到已登录状态===============
                deviceStateContext.onLoginSucc("device-123", System.currentTimeMillis(), 10, "设备认证通过");
                ctx.writeAndFlush("login ok\n");
            } else {
                //===========login false,切换到登录失败状态==========
                deviceStateContext.onLoginFailed("设备认证失败");
            }
        } else {
            //============状态为上行数据=============
            deviceStateContext.onHeartbeat(System.currentTimeMillis(), "设备上行了数据");
            //==============处理数据================
            System.out.println("收到数据:" + msg);
            //============返回消息==================
            ctx.writeAndFlush("recvData ok\n");
        }
        System.out.println("channelRead0:" + deviceStateContext.toString());
    }


    /**
     * 空闲一段时间，就进行检查 (当前时间-上次上行数据的时间) 如果大于设定的超时时间 设备状态就就行一次 onTimeout
     *
     * @param ctx
     * @param evt
     * @throws Exception
     */
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        System.out.println(getClass().getSimpleName() + "." + "userEventTriggered" + ctx.channel().remoteAddress());
        if (evt instanceof IdleStateEvent) {
            DeviceStateContext deviceStateContext = session.getAttributeValue(ctx);
            long lastUpdateTime = deviceStateContext.getLastUpdateTime();
            long currentTimeMillis = System.currentTimeMillis();
            long intervalTime = currentTimeMillis - lastUpdateTime;

            if (intervalTime > deviceStateContext.getHeartRate()) {
                //==============发生超时，进入超时状态==============
                deviceStateContext.onTimeout("设备发生超时");
                System.out.println("userEventTriggered:" + deviceStateContext.toString());
            }
        } else {
            //不是超时事件，进行传递
            super.userEventTriggered(ctx, evt);
        }
    }

    //客户端链接上来的时候触发
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //链接成功
        DeviceStateContext deviceStateContext = new DeviceStateContext(ctx.channel(), true);
        //===========设置设备状态为 未登录=================
        deviceStateContext.onConnect(System.currentTimeMillis(), "设备 active");
        //更新添加 state 属性
        session.setAttribute(ctx, deviceStateContext);
        System.out.println("channelActive:" + deviceStateContext.toString());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        //================设置为断开================
        DeviceStateContext deviceStateContext = session.getAttributeValue(ctx);
        deviceStateContext.onDisconnect("设备 inactive");
        System.out.println("channelInactive:" + deviceStateContext.toString());
    }

    //异常的时候触发
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        //==============发生异常切换到断开模式===============
        System.out.println("exceptionCaught:" + cause.getMessage());
        DeviceStateContext deviceStateContext = session.getAttributeValue(ctx);
        deviceStateContext.onDisconnect(cause.getMessage());
        System.out.println("exceptionCaught:" + deviceStateContext.toString());
    }


    private boolean login(ChannelHandlerContext ctx, String msg) {
        //获取用户名密码 LOGIN:name,pass
        String info[] = msg.split(":");
        if (2 != info.length) {
            return false;
        }
        String userAndPass = info[1];
        String info2[] = userAndPass.split(",");

        if (2 != info2.length) {
            return false;
        }

        String user = info2[0];
        String pass = info2[1];

        //核对用户名密码
        if (!user.equals("yhy") || !pass.equals("123")) {
            return false;
        } else {
            return true;
        }
    }
}
