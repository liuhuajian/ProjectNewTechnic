package com.project.common.core.http;


import android.support.v7.app.AppCompatDialog;

import com.google.gson.JsonObject;
import com.project.common.core.download.update.entity.AppVersion;
import com.project.common.core.http.bean.BaseTagBean;
import com.project.common.core.http.bean.JsonResult;
import com.project.common.core.http.constant.NetConstantURL;
import com.project.common.core.http.bean.LaberModel;

import org.json.JSONObject;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * 项目名称：
 * 类描述： - 所有的请求API接口
 * 创建人：julyzeng
 * 创建时间：2018/6/10  11:36
 * 修改人：
 * 修改时间：
 * 修改备注：
 *
 * @version 1.0
 */
public interface RequestApiService {

    /**
     * @GET 表明方法是 get请求
     * "api" 请求的接口,请注意前面的/符号
     * @Query 表示这是一个参数
     * Call<ResponseBody> :Call是必须的,ResponseBody是Retrofit默认的返回数据类型,也就是String体
     */

    //getApi方法,
    @Headers("Cache-Control: max-age=640000")
    @POST("api/refresh/token")
    Call<JsonResult<JSONObject>> refreshToken(@Query("token") String token);

    @POST("refresh/token")
    Observable<JsonResult<List<?>>> getNewAddress(@Body RequestBody requestBody);

    @POST("{path}")
    Observable<JsonObject> post(@Path(value = "path", encoded = true) String path, @Body TreeMap<String, Object> map);

    /**
     * 单图上传图片
     * @param imgurl
     * @return
     */
    @Multipart
    @POST("imageLoad")
    Observable<JsonResult<String>> imagePost(@Part List<MultipartBody.Part> imgurl);
    /**
     * 多图上传图片
     * @param imgurls
     * @return
     */
    @Multipart
    @POST("imageLoadList")
    Observable<JsonResult<List<String>>> imageListPost(@Part() List<MultipartBody.Part> imgurls);

    /**
     * 系统标签控制器
     *
     * @return
     */
    @POST(NetConstantURL.SELECT_TAG_FORAPP)
    Observable<JsonResult<List<BaseTagBean>>> selectTagForApp(@Body  Map<String,String> map);
    /**
     * 系统标签
     */
    @POST("sysTag/tagInfo/selectTagForApp")
    Observable<JsonResult<List<LaberModel>>> getLaberList(@Body RequestBody requestBody);

    /**
     * 版本升级
    */
    @POST("operate/physique/selectVersion")
    Observable<JsonResult<AppVersion>> appVersionUpdate(@Body RequestBody requestBody);

}
