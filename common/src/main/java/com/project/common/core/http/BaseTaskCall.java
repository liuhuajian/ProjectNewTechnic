package com.project.common.core.http;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.project.common.core.http.bean.JsonResult;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by julyzeng on 2016/6/9.
 *
 * 基于retrofit call 请求回调的统一封装
 */
public class BaseTaskCall<T> {

        private Call<JsonResult<T>> mCall;
        private Context mContext;
        private final String SUCCESS = "1000";
        private final String TAG = "response";

        public BaseTaskCall(Context context, Call call) {
            mCall = call;
            mContext = context;
//            if (!PublicStatic.initDialog(mContext).isShowing()){
//                PublicStatic.initDialog(mContext);
//            }
        }

        public void handleResponse(final ResponseListener listener) {
            mCall.enqueue(new Callback<JsonResult<T>>() {
                @Override
                public void onResponse(Call<JsonResult<T>> call, Response<JsonResult<T>> response) {
                    if (response.isSuccessful() && response.errorBody() == null) {
                        if (response.body().getCode() == SUCCESS) {
                            listener.onSuccess((T) response.body().getData());
                        } else {
                            Toast.makeText(mContext, response.body().getMessage(), Toast.LENGTH_LONG).show();
                            listener.onFail();
                        }
                    } else {
                        Log.d(TAG, "error code:" + response.code());
                        Log.d(TAG, "error message:" + response.message());

                        Toast.makeText(mContext, "网络请求返回异常！", Toast.LENGTH_LONG).show();
                    }
//                    if (PublicStatic.initDialog(mContext).isShowing()) {
//                        PublicStatic.initDialog(mContext).dismiss();
//                    }

                }

                @Override
                public void onFailure(Call<JsonResult<T>> call, Throwable t) {
                    Log.d(TAG, "error:" + t.getMessage());
//                    if (PublicStatic.initDialog(mContext).isShowing()){
//                        PublicStatic.initDialog(mContext).dismiss();
//                    }
                    Toast.makeText(mContext, "网络请求出现异常！", Toast.LENGTH_LONG).show();
                }
            });
        }

        public interface ResponseListener<T> {
            void onSuccess(T t);
            void onFail();
        }
}
