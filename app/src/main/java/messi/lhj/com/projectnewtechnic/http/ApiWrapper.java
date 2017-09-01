package messi.lhj.com.projectnewtechnic.http;


//import com.wdb007.app.wordbang.app.AppInstance;
//import com.wdb007.app.wordbang.bean.BookList;
//import com.wdb007.app.wordbang.bean.BookShelf;
//import com.wdb007.app.wordbang.bean.User;

import messi.lhj.com.projectnewtechnic.bean.User;
import rx.Observable;

/**
 * Created by Sunflower on 2016/1/11.
 */
public class ApiWrapper {

    private final int pageSize = 10;
    private HttpUtil retrofitInstance;
    private ApiService apiService;

    public ApiWrapper() {
        retrofitInstance = HttpUtil.getInstance();
        apiService = retrofitInstance.getService();
    }

//    public Observable<String> getTestResponse(){
//        return apiService.getTestResponse()
//                .compose(retrofitInstance.<String>applySchedulers());
//    }

//    public Observable<ResponsePic> getPictures(){
//        return apiService.getPictures();
//    }

//    /**
//     * 请求验证码
//     * @param mobile
//     * @return
//     */
//    public Observable<ResponseDetail> getMessagePass(String mobile) {
//        return apiService.getMessagePass(mobile);
////                .compose(retrofitInstance.<String>applySchedulersDetail());
//    }

//    /**
//     * 请求登录
//     * @param mobile
//     * @param verifyCode
//     * @return
//     */
//    public Observable<User> getUserLogin(String mobile, String verifyCode){
//        return apiService.getUserLogin(mobile, verifyCode)
//                .compose(retrofitInstance.<User>applySchedulersDetail());
//    }
//
//    public Observable<List<BookShelf>> getBookShelfLocat(String longitude , String latitude){
//        return apiService.getBookShelfLocation(longitude, latitude)
//                .compose(retrofitInstance.<List<BookShelf>>applySchedulersItems());
//    }
//
//    public Observable<User> getUserInfo(String userId ,String userToken){
//        return apiService.getUserInfo(userId ,userToken)
//                .compose(retrofitInstance.<User>applySchedulersDetail());
//    }
//
//    public Observable<List<BookList>> getBookList(String pageSize ,String pageNo){
//        User mUser = AppInstance.getInstance().mUser;
//        return apiService.getBookList(mUser.userid, mUser.usertoken ,pageSize ,pageNo)
//                .compose(retrofitInstance.<List<BookList>>applySchedulersItems());
//    }

//    public Observable<Movie> getMovie(String start, String count){
//        return apiService.getMovie(start,count);
//    }
//    /**
//     * 获取最新接口
//     *
//     * @return
//     */
//    public Observable<ServerData> getNewIndex() {
//        return apiService.getNewIndex()
//                .compose( retrofitInstance.<ServerData>applySchedulersDetail());
//    }

//    /**
//     * 获取登陆后的数据
//     * @param userName
//     * @param psw
//     * @param userType
//     * @param appVersion
//     * @param loginLoc
//     * @param deviceType
//     * @param deviceId
//     * @return
//     */
//    public Observable<User> getLoginData(String userName , String psw , String userType , String appVersion ,
//                                             String loginLoc , String deviceType , String deviceId){
//        return apiService.getLoginData(userName,psw,userType,appVersion,loginLoc,deviceType,deviceId)
//                .compose(retrofitInstance.<User>applySchedulersDetail());
//    }

//    /**
//     * 请求电视端数据
//     * @param tvData
//     * @return
//     */
//    public Call<ResponseBody> getTvData(String tvData){
//        return apiService.getTvData(Constants.TV_API+"?data="+tvData);
//    }
//
//    /**
//     * 游戏点数
//     * @param userId
//     * @param gameTime
//     * @param randKey
//     * @param loginToken
//     * @return
//     */
//    public Call<ResponseBody> getGamePoint(String userId ,String gameTime ,String randKey ,String loginToken){
//        return apiService.getGamePoint(userId,gameTime,randKey,loginToken);
//    }
//
//    /**
//     * 检查网络
//     * @param imgurl
//     * @return
//     */
//    public Call<ResponseBody> checkNetWork(String imgurl){
//        return apiService.checkNetWork(imgurl);
//    }
}
