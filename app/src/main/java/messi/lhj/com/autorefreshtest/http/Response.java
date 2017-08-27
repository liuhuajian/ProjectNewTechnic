package messi.lhj.com.autorefreshtest.http;

import java.util.List;

import messi.lhj.com.autorefreshtest.entity.Picture;

/**
 * Created by messi on 2017/8/27.
 */

public class Response {

    public DayPic data;

    public String server_time;

    public class DayPic{
        public String time;
        public List<Picture> list;

        @Override
        public String toString() {
            return "DayPic{" +
                    "time='" + time + '\'' +
                    ", list=" + list +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "Response{" +
                "data=" + data +
                ", server_time='" + server_time + '\'' +
                '}';
    }
}
