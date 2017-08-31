package messi.lhj.com.projectnewtechnic.http;

import java.util.List;

import messi.lhj.com.projectnewtechnic.refreshAndreloadmore.Picture;

/**
 * Created by messi on 2017/8/27.
 */

public class ResponsePic {

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
        return "ResponsePic{" +
                "data=" + data +
                ", server_time='" + server_time + '\'' +
                '}';
    }
}
