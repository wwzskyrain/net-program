package erik.xieli.longin.state;

import io.netty.channel.Channel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * describe:设备状态切换类
 *
 * @author helloworldyu
 * @data 2018/3/27
 */
public class DeviceStateContext implements IDeviceState {
	/**
	 * 是否开启记录所有的状态转变
	 */
	boolean history;
	/**
	 * 记录状态转换的历史
	 */
	private static class HistoryInfoDTO{
		private String describe;
		private String state;

		public HistoryInfoDTO(String describe, String state) {
			this.describe = describe;
			this.state = state;
		}

		@Override
		public String toString() {
			return "HistoryInfoDTO{" +
					"describe='" + describe + '\'' +
					", state='" + state + '\'' +
					'}';
		}
	}
	List<HistoryInfoDTO> historyState = new ArrayList<>();
	/**
	 *  防止竞争的读写锁
	 */
	ReentrantReadWriteLock lock = new ReentrantReadWriteLock();


	/**
	 * 设备的上下文信息
	 */
	private Channel channel;

	/**
	 * 设备的 deviceId
	 */
	private String deviceId;

	/**
	 * 链接时间
	 */

	private long connectTime;

	/**
	 * 设备的上次更新时间
	 */
	private long lastUpdateTime;

	/**
	 * 心跳周期 单位 s
	 */
	private long heartRate;

	/**
	 * 设备当前状态
	 */
	private IDeviceState state;

	/**
	 * @param channel 管理的 channel 信息
	 */
	public DeviceStateContext(Channel channel) {
		this.channel = channel;
		setState(new NoConnectedState(this), "初始化");
	}

	/**
	 * @param channel 管理的 channel 信息
	 * @param history true 开始记录历史状态
	 */
	public DeviceStateContext(Channel channel, boolean history) {
		this.history = history;
		this.channel = channel;
		setState(new NoConnectedState(this),"初始化" );
	}

	///////////////////////////get/set////////////////////////

	public Channel getChannel() {
		return channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public long getConnectTime() {
		return connectTime;
	}

	public void setConnectTime(long connectTime) {
		this.connectTime = connectTime;
	}

	public long getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(long lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public long getHeartRate() {
		return heartRate;
	}

	public void setHeartRate(long heartRate) {
		this.heartRate = heartRate;
	}

	public IDeviceState getState() {
		return state;
	}

	public void setState(IDeviceState state, String describe) {
		this.state = state;
		//把每次切换的状态加入到历史状态中
		historyState.add(new HistoryInfoDTO(describe,state.getStateName()));
	}


	///////////////////////////状态切换////////////////////////


	@Override
	public void onConnect(long connectTime, String describe) {
		lock.writeLock().lock();
		try {
			state.onConnect( connectTime,describe );
		}finally {
			lock.writeLock().unlock();
		}
	}

	@Override
	public void onDisconnect(String describe) {
		lock.writeLock().lock();
		try {
			state.onDisconnect(describe);
		}finally {
			lock.writeLock().unlock();
		}
	}

	@Override
	public void onLoginSucc(String deviceId, long lastUpdateTime, long heartRate, String describe) throws IllegalStateException{
		lock.writeLock().lock();
		try {
			state.onLoginSucc( deviceId, lastUpdateTime, heartRate, describe);
		}finally {
			lock.writeLock().unlock();
		}
	}

	@Override
	public void onLoginFailed(String describe) {
		lock.writeLock().lock();
		try {
			state.onLoginFailed(describe);
		}finally {
			lock.writeLock().unlock();
		}
	}

	@Override
	public void onHeartbeat(long lastUpdateTime, String describe) {
		lock.writeLock().lock();
		try {
			state.onHeartbeat(lastUpdateTime,describe );
		}finally {
			lock.writeLock().unlock();
		}
	}


	@Override
	public void onTimeout(String describe) {
		lock.writeLock().lock();
		try {
			state.onTimeout(describe);
		}finally {
			lock.writeLock().unlock();
		}
	}

	@Override
	public String getStateName() {
		return null;
	}

	/**
	 * 关闭链接
	 */
	protected void closeChannle( String describe ){
		setState(new NoConnectedState(this),describe );
		//关闭此 channel
		this.channel.close();
	}


	@Override
	public String toString() {
		return "DeviceStateContext{" +
				" state=" + state.getStateName()  +
				", channel=" + channel +
				", deviceId='" + deviceId + '\'' +
				", connectTime=" + connectTime +
				", lastUpdateTime=" + lastUpdateTime +
				", lock=" + lock +
				", \nhistory=" + historyState +
				'}';
	}
}