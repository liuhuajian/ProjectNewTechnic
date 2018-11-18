package com.project.common.core.http;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import com.project.common.core.BaseApp;
import com.project.common.core.utils.MyLogger;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;


/**
 * 项目名称：
 * 类描述： - 自定义 restAdatper Client 拦截器修改参数类型并添加签名等共同参数重新请求
 * OKhttp3.x不支持Client形式处理请求。只支持拦截器
 * 创建人：julyzeng
 * 创建时间：2016/5/9 10:28
 * 修改人：
 * 修改时间：
 * 修改备注：
 *
 * @version 1.0
 */
public class RequestPamarmsInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();
        MyLogger.e("request：  url--->" + request.url().toString() + "\r\n"
                + bodyToString(request));
        MyLogger.i("\r\n"+request.headers());
        Response request1 = chain.proceed(request);

        ResponseBody responseBody = request1.peekBody(20000);
        MyLogger.e("responseBody  Url-->" + request1.request().url() + "\r\n   body-->" + responseBody.string());
        MyLogger.i("\r\n"+request1.headers());
        if (request1!=null)
            return request1;
        //在这里获取到request后就可以做任何事情了
        if (request != null) {
            Response response = chain.proceed(request);
            return response;
        } else {
            Toast.makeText(BaseApp.mContext, "请求超时", Toast.LENGTH_SHORT).show();
        }
        return null;
    }
    /***
     * 检查网络
     *
     * @param context
     * @return
     */
    public boolean checkNet(Context context) {
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


    private static String bodyToString(final Request request) {

        try {
            final Request copy = request.newBuilder().build();
            final Buffer buffer = new Buffer();
            if (copy.body() == null) {
                return "get------------";
            }
            copy.body().writeTo(buffer);
            return buffer.readUtf8();
        } catch (final IOException e) {
            return "did not work";
        }
    }
}
