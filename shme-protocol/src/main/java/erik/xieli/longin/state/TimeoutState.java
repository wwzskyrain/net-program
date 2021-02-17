package erik.xieli.longin.state;

/**
 * describe:超时无数据状态
 *
 * @author helloworldyu
 * @data 2018/3/27
 */
public class TimeoutState extends AbstractState{
	public static final int MAX_TIMEOUT = 3;


	/**
	 * 进入超时状态的次数，如果超过 3 次则断开链接
	 */
	private int count;

	public TimeoutState(DeviceStateContext stateCtx) {
		super(stateCtx);
		this.count=1;
	}


	@Override
	public void onTimeout(String describe) {
		//把当前状态放进去
		this.stateCtx.setState(this, describe);
		this.count++;
		//连续 timeout 到一定次数就关闭连接，切换到 断开链接状态
		if( this.count >= MAX_TIMEOUT ){
			//断开链接
			this.stateCtx.closeChannle(describe);
		}
	}

	@Override
	public void onHeartbeat(long lastUpdateTime, String describe) {
		//=======更新最后更新时间=========
		this.stateCtx.setLastUpdateTime(lastUpdateTime);
		//=======状态转换为已登录=========
		this.stateCtx.setState(new LoggedState(this.stateCtx), describe);
	}

	@Override
	public String getStateName() {
		return "timeout";
	}
}