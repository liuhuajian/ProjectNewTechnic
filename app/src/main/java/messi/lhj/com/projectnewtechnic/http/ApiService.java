package messi.lhj.com.projectnewtechnic.http;


//import com.wdb007.app.wordbang.bean.BookList;
//import com.wdb007.app.wordbang.bean.BookShelf;
//import com.wdb007.app.wordbang.bean.User;

import messi.lhj.com.projectnewtechnic.bean.User;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2017/8/18.
 */

public interface ApiService {


    @GET("hhh")
    Observable<Response> getTestResponse();
//    @GET("api_demo/image-list.php")
//    Observable<ResponsePic> getPictures();
//    @GET("wdb007/user/getRegisterCode")
//    Observable<ResponseDetail> getMessagePass(@Query("mobile_no") String mobile);
//
//    @FormUrlEncoded
//    @POST("wdb007/user/login")
//    Observable<ResponseDetail<User>> getUserLogin(@Field("mobile_no") String mobile, @Field("verify_code") String verifyCode);
//
//    /**
//     * 获取地图书架
//     * @param longitude
//     * @param latitude
//     * @return
//     */
//    @GET("wdb007/bookshelf/getCoordinates")
//    Observable<ResponseIterms<List<BookShelf>>> getBookShelfLocation(@Query("user_longitude") String longitude, @Query("user_latitude") String latitude);
//
//    @GET("wdb007/user/getUserInfo")
//    Observable<ResponseDetail<User>> getUserInfo(@Query("userid") String userid, @Query("usertoken") String userToken);
//
//    @GET("wdb007/user/getBorrowList")
//    Observable<ResponseIterms<List<BookList>>> getBookList(@Query("userid") String userid, @Query("usertoken") String userToken,
//                                                           @Query("pagesize") String pageSize, @Query("pageno") String pageNo);
//    @GET("top250")
//    Observable<Movie> getMovie(@Query("start") String start, @Query("count") String count);
}
