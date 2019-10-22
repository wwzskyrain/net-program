package erik.xieli.longin.state;

/**
 * describe:未登录状态
 *
 * @author helloworldyu
 * @data 2018/3/27
 */
public class NoLoginState extends AbstractState {
    public NoLoginState(DeviceStateContext ctx) {
        super(ctx);
    }

    @Override
    public void onDisconnect(String describe) {
        this.stateCtx.closeChannle(describe);
    }

    @Override
    public void onLoginSucc(String deviceId, long lastUpdateTime, long heartRate, String describe) {
        //设置数据
        this.stateCtx.setDeviceId(deviceId);
        this.stateCtx.setLastUpdateTime(lastUpdateTime);
        this.stateCtx.setHeartRate(heartRate);
        //状态转移
        this.stateCtx.setState(new LoggedState(this.stateCtx), describe);
    }
//
//	@Override
//	public void onLoginFailed(String describe) {
//		//为登录模式下,登录失败,直接断开链接。
//		this.stateCtx.closeChannle(describe);
//	}
//
//	@Override
//	public void onTimeout(String describe) {
//		//在未登录状态下,超时无数据,直接断开链接
//		this.stateCtx.closeChannle(describe);
//	}

    @Override
    public String getStateName() {
        return "noLogin";
    }
}