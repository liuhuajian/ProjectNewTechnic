package com.project.common.core.http.interceptor;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.project.common.core.BaseApp;
import com.project.common.core.http.RequestApiService;
import com.project.common.core.http.ServiceFactory;
import com.project.common.core.http.bean.JsonResult;
import com.project.common.core.http.constant.HttpErrorCode;
import com.project.common.core.http.constant.NetConstantURL;
import com.project.common.core.utils.Constants;
import com.project.common.core.utils.MyLogger;
import com.project.common.core.utils.SignSort;
import com.project.common.core.utils.Tools;

import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import retrofit2.Call;

/**
 * 项目：国民健康平台
 *
 * @Creator:曾招林julyzeng
 * @创建日期： 2018/8/1 19:44
 * @版本0.1
 * @类说明：请求的公共参数拦截器(token刷新一同处理）  加密签名也可以里面进行处理
 */

public class RequestCommonParameterInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request oldRequest = chain.request();
        Response response = null;

        if (oldRequest.method().equals("GET")) {//GET请求
            // 新的请求,添加参数
            Request newRequest = addCommonParam(oldRequest);
            response = chain.proceed(newRequest);
            // 新的请求,添加参数
            ResponseBody value = response.body();
            byte[] resp = value.bytes();
            String json = new String(resp, "UTF-8");
            // 判断stateCode值
            try {
                JSONObject jsonObject = new JSONObject(json);
                int stateCode = jsonObject.optInt("code");
                if (stateCode == 401) {  // token失效，重新执行请求

                    //取出本地的refreshToken
                    String refreshToken = Tools.getTokenString();
                    // 通过一个特定的接口获取新的token，此处要用到同步的retrofit请求
                    RequestApiService service = ServiceFactory.createServiceFrom(RequestApiService.class);
                    Call<JsonResult<JSONObject>> call = service.refreshToken(refreshToken);
                    // 要用retrofit的同步方式
                    JsonResult<JSONObject> jsonResult = call.execute().body();//返回新的token
                    if (HttpErrorCode.SUCCESS.equals(jsonResult.getCode())) {
                        JSONObject jsonObject1 = jsonResult.getData();
                        String newToken = jsonObject1.getString("token");
                        Tools.saveToken(newToken);//保存token
                    }
                    response = chain.proceed(addCommonParam(oldRequest));
                } else {
                    response = response.newBuilder()
                            .header("token",Tools.getTokenString())
                            .body(ResponseBody.create(null, resp))
                            .build();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }else if(oldRequest.method().equals("POST")) {//post请求方法添加公共参数
            if (checkNet(BaseApp.mContext)) {
                if (oldRequest.body() instanceof RequestBody) {
                    String loginurl = NetConstantURL.BASE_URL+"manager/account/accountCustomerInfo/login";
//<<<<<<< HEAD
                    String wxloginurl = NetConstantURL.BASE_URL+"manager/account/accountCustomerInfo/isExistByUnionId";

                    if (loginurl.equals(oldRequest.url().toString()) || wxloginurl.equals(oldRequest.url().toString()) ) {//登陆接口-拦截器处理请求头中的token
//=======
//
//                    if (loginurl.equals(oldRequest.url().toString())) {//登陆接口-拦截器处理请求头中的token
//>>>>>>> v0.3
                        Headers headers = chain.proceed(oldRequest).headers();
                        String token = headers.get("token");
                        Tools.saveToken(token);
                    }

//                    Buffer buffer = new Buffer();
//                    oldRequest.body().writeTo(buffer);
//                    String oldParamsJson = buffer.readUtf8();
//                    HashMap  rootMap = new Gson().fromJson(oldParamsJson, HashMap.class);
//                    //原始参数
//                    rootMap.put("sign", SignSort.generateSign(rootMap));

//                    rootMap.put("timestamp", System.currentTimeMillis() /1000);
//                    //重新组装
//                    String newJsonParams = new Gson().toJson(rootMap);
//                    //装换成json字符串
//                    Request   request = oldRequest.newBuilder().post(RequestBody.create(MediaType.parse(Constants.MEDIA_TYPE_CHARSET), newJsonParams)).build();
//                    MyLogger.e(newJsonParams);

                    Headers.Builder builder = oldRequest
                            .headers()
                            .newBuilder();
                    //统一追加Header参数
                    Headers newBuilder = builder
                            .add("token",Tools.getTokenString())//应用Key(01APP，02PC，03其他)
                            .build();

                    response = chain.proceed(oldRequest.newBuilder().headers(newBuilder).build());
                }
            }
        }
        return response;
    }

    /***
     * 检查网络
     *
     * @param context
     * @return
     */
    private boolean checkNet(Context context) {
        try {
            ConnectivityManager connectivity = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivity != null) {
                // 获取网络连接管理的对像
                NetworkInfo info = connectivity.getActiveNetworkInfo();
                if (info == null || !info.isAvailable()) {
                    return false;
                } else {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 添加公共参数
     *
     * @param oldRequest
     * @return
     */
    private Request addCommonParam(Request oldRequest) {

        HttpUrl url = oldRequest.url()
                .newBuilder()
                // Provide your custom parameter here
//                .addQueryParameter("appKey","01")//应用Key(01APP，02PC，03其他)
//                .addQueryParameter("uuid", DeviceUtil.getDeviceId(BaseApp.mContext))
                .build();

        Request newRequest = oldRequest.newBuilder()
                .header("token",Tools.getTokenString())//应用Key(01APP，02PC，03其他)
                .method(oldRequest.method(), oldRequest.body())
                .url(url)
                .build();
        return newRequest;
    }
}
