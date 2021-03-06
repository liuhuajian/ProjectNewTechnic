package messi.lhj.com.projectnewtechnic.http;


/**
 * Created by Sunflower on 2016/1/11.
 */
public class ResponseIterms<T> extends Response{

    public String totalcount;
    public String pushdata;
    public T items;

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
                ", items=" + items +
                '}';
    }
}
