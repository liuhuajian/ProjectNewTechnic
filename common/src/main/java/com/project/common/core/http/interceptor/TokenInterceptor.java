package com.project.common.core.http.interceptor;

import java.io.IOException;
import java.nio.charset.Charset;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;


/**
 * 项目：健康商城
 *
 * @Creator:曾招林julyzeng
 * @创建日期： 2018/6/21 15:06
 * @版本1.0
 * @类说明：token拦截器  如果服务器不是根据HTTP CODE 401返回而是自定义返回数据结构返回code来判断token过期则用拦截器模式
 */

public class TokenInterceptor implements Interceptor {

    private static final Charset UTF8 = Charset.forName("UTF-8");
    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();

        String url = request.url().toString();
        // try the request
        Response response = chain.proceed(request);

        /**通过如下的办法取到请求完成的数据
         *
         * 然后去看了okhttp的源码,找到了这个方法,取到请求完成的数据后,根据特定的判断条件去判断token过期
         */
//        ResponseBody responseBody = originalResponse.body();
//        BufferedSource source = responseBody.source();
//        source.request(Long.MAX_VALUE); // Buffer the entire body.
//        Buffer buffer = source.buffer();
//        Charset charset = UTF8;
//        MediaType contentType = responseBody.contentType();
//        if (contentType != null) {
//            charset = contentType.charset(UTF8);
//        }
//        String bodyString = buffer.clone().readString(charset);

        /******************拦截器实现token过期刷新获取新的token*********************/

//        if (bodyString.equals("")){//根据和服务端的约定判断token过期
//
//            //取出本地的refreshToken
//            String refreshToken = Tools.getTokenString();
//
//            // 通过一个特定的接口获取新的token，此处要用到同步的retrofit请求
//            RequestApiService service = ServiceFactory.createServiceFrom(RequestApiService.class);
//            Call<String> call = service.refreshToken(refreshToken);
            //要用retrofit的同步方式
//            String newToken = call.execute().body();

            // create a new request and modify it accordingly using the new token
//            Request newRequest = request.newBuilder().header("token", newToken)
//                    .build();
//
//            originalResponse.body().close();
//            return chain.proceed(newRequest);
//        }

        return response;
    }

}
