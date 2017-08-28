package messi.lhj.com.projectnewtechnic.util.http;


/**
 * Created by Sunflower on 2016/1/11.
 */
public class ResponseDetail<T> {

    public String code;
    public String result;
    public String message;
    public String totalcount;
    public String pushdata;
    public T detail;

    public boolean isSuccess() {
        return result.equals("ok");
    }

    @Override
    public String toString() {
        return "ResponseDetail{" +
                "code='" + code + '\'' +
                ", result='" + result + '\'' +
                ", message='" + message + '\'' +
                ", totalcount='" + totalcount + '\'' +
                ", pushdata='" + pushdata + '\'' +
                ", detail=" + detail +
                '}';
    }
}