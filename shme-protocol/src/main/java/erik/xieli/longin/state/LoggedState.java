package erik.xieli.longin.state;

/**
 * describe:
 *
 * @author helloworldyu
 * @data 2018/3/27
 */
public class LoggedState extends AbstractState{
	public LoggedState(DeviceStateContext stateCtx) {
		super(stateCtx);
	}

	@Override
	public void onDisconnect(String describe) {
		//直接关闭链接
		this.stateCtx.closeChannle(describe);
	}

	@Override
	public void onHeartbeat(long lastUpdateTime, String describe) {
		//把当前状态放进去
		this.stateCtx.setState(this, describe );
		//状态不变更新 lastUpdateTime
		this.stateCtx.setLastUpdateTime(lastUpdateTime);
	}

	@Override
	public void onTimeout(String describe) {
		//状态模式设置为超时状态
		this.stateCtx.setState( new TimeoutState(this.stateCtx),describe );
	}

	@Override
	public String getStateName() {
		return "logged";
	}
}