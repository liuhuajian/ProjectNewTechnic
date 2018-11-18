package com.project.common.core.http;


import android.support.annotation.Nullable;

import com.project.common.core.utils.Tools;

import java.io.IOException;

import okhttp3.Authenticator;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;

/**
 * 项目：健康商城
 *
 * @Creator:曾招林julyzeng
 * @创建日期： 2018/6/21 14:54
 * @版本1.0
 * @类说明：token验证机制
 */

public class TokenAuthenticator implements Authenticator {

    @Nullable
    @Override
    public Request authenticate(Route route, Response response) throws IOException {

        //取出本地的refreshToken
        String refreshToken = Tools.getTokenString();

        // 通过一个特定的接口获取新的token，此处要用到同步的retrofit请求
        RequestApiService service = ServiceFactory.createServiceFrom(RequestApiService.class);
//        Call<String> call = service.refreshToken(refreshToken);
        //要用retrofit的同步方式
//         String newToken = call.execute().body();
         return response.request().newBuilder()
         .header("token", "")
         .build();
    }
}
