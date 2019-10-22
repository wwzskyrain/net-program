package erik.xieli.longin.state;

/**
 * describe:未连接状态
 *
 * @author helloworldyu
 * @data 2018/3/27
 */
public class NoConnectedState extends AbstractState{
	public NoConnectedState(DeviceStateContext ctx) {
		super(ctx);
	}

	@Override
	public void onConnect(long connectedTime, String describe) {
		stateCtx.setConnectTime(connectedTime);
		stateCtx.setState(new NoLoginState(this.stateCtx), describe);
	}

	@Override
	public void onDisconnect(String describe) {
		this.stateCtx.closeChannle(describe);
	}

	@Override
	public String getStateName() {
		return "noConnected";
	}
}