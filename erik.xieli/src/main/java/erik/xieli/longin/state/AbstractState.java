package erik.xieli.longin.state;

/**
 * describe:所有状态类的基类
 *
 * @author helloworldyu
 * @data 2018/3/27
 */
public abstract class AbstractState implements IDeviceState{
	protected DeviceStateContext stateCtx;

	public AbstractState( DeviceStateContext stateCtx) {
		this.stateCtx = stateCtx;
	}


	@Override
	public void onConnect(long connectedTime, String describe) {
		throw new IllegalStateException(getStateName()+" 此状态不应该进行链接动作");
	}

	@Override
	public void onDisconnect(String describe) {
		throw new IllegalStateException(getStateName()+" 此状态不应该进行断开链接动作");
	}

	@Override
	public void onLoginSucc(String deviceId, long lastUpdateTime, long heartRate, String describe) {
		throw new IllegalStateException(getStateName()+" 此状态不应该进行登录动作");
	}

	@Override
	public void onLoginFailed(String describe) {
		throw new IllegalStateException(getStateName()+" 此状态不应该进行登录失败动作");
	}

	@Override
	public void onHeartbeat(long lastUpdateTime, String describe) {
		throw new IllegalStateException(getStateName()+" 此状态不应该进行心跳动作");
	}

	@Override
	public void onTimeout(String describe) {
		throw new IllegalStateException(getStateName()+" 此状态不应该进行进入超时动作");
	}

}