package erik.xieli.longin.state;

/**
 * describe:设备各种状态下的行为总和
 *
 * @author helloworldyu
 * @data 2018/3/27
 */
public interface IDeviceState {
	/**
	 * 设备新建立链接
	 * @param connectedTime 建立链接的时间
	 * @param describe 描述在什么时候进行的此动作
	 */
	void onConnect(long connectedTime, String describe);

	/**
	 * 断开链接
	 * @param describe 描述在什么时候进行的此动作
	 */
	void onDisconnect(String describe);

	/**
	 * 登录动作
	 * @param deviceId 设备 id
	 * @param lastUpdateTime 设备上行数据的时间
	 * @param heartRate
	 * @param describe  描述在什么时候进行的此动作
	 */
	void onLoginSucc(String deviceId, long lastUpdateTime, long heartRate, String describe);

	/**
	 * 登录失败
	 * @param describe 描述在什么时候进行的此动作
	 */
	void onLoginFailed(String describe);

	/**
	 * 只要有数据上报，都属于心跳
	 * @param lastUpdateTime  最新更新时间
	 * @param describe 描述在什么时候进行的此动作
	 */
	void onHeartbeat(long lastUpdateTime, String describe);

	/**
	 * 进入超时
	 * @param describe
	 */
	void onTimeout(String describe);

	/**
	 * 返回当前状态的名字
	 */
	String getStateName();
}
