package erik.study.net.netty.action.chapter13.server;

import java.net.InetSocketAddress;

/**
 * @author erik.wang
 * @Date 2019-10-06
 */
public class LogEvent {

    public static final byte SEPARATOR = ((byte) ':');
    private final InetSocketAddress source;
    private final String logfile;
    private final String msg;
    private final long received;

    public LogEvent(InetSocketAddress source, long received, String logfile, String msg) {
        this.source = source;
        this.logfile = logfile;
        this.msg = msg;
        this.received = received;
    }

    public LogEvent(String logfile, String msg) {
        this(null, -1, logfile, msg);
    }

    public InetSocketAddress getSource() {
        return source;
    }

    public String getLogfile() {
        return logfile;
    }

    public String getMsg() {
        return msg;
    }

    public long getReceivedTimestamp() {
        return received;
    }
}
