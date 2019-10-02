package erik.study.net.netty.doc.demo.time.pojo;

import java.util.Date;

/**
 * @author erik.wang
 * @Date 2019-10-02
 */
public class UnixTime {

    private final long value;
    public UnixTime(){
        this(System.currentTimeMillis()/1000L + 2208988800L);
    }
    public  UnixTime(long value){
        this.value = value;
    }

    public long getValue() {
        return value;
    }

    @Override
    public String toString() {
        return new Date((getValue() - 2208988800L)*1000L).toString();
    }
}
