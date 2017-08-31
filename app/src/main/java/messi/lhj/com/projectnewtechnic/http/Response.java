package messi.lhj.com.projectnewtechnic.http;

/**
 * Created by messi on 2017/8/31.
 */

public class Response {
    public String code;
    public String result;
    public String message;

    public boolean isSuccess() {
        return result.equals("ok");
    }

    @Override
    public String toString() {
        return "Response{" +
                "code='" + code + '\'' +
                ", result='" + result + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
