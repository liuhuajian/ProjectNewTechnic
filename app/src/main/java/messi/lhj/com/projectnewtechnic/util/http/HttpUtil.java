package messi.lhj.com.projectnewtechnic.util.http;

import android.util.Log;


import messi.lhj.com.projectnewtechnic.util.Logger;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/8/18.
 */

public class HttpUtil {
//    public String API_HOST = "https://api.douban.com/v2/movie/";
    public String API_HOST = "http://cube-server.liaohuqiu.net/";
//    public String API_HOST = "http://192.168.0.12:8080";
    private static HttpUtil sHttpUtil = null;
    private ApiService apiService;
    private final Retrofit retrofit;

    public static synchronized HttpUtil getInstance() {
        if (sHttpUtil == null)
            sHttpUtil = new HttpUtil();
        return sHttpUtil;
    }

    private HttpUtil() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.i("RxJava", message);
                Logger.d("message = " + message);
                Logger.d("message = " + message);
            }
        });
        OkHttpClient client = new OkHttpClient.Builder()
                //设置缓存
                //      .cache(new Cache(httpCacheDirectory, 10 * 1024 * 1024))
                //log请求参数
                .addInterceptor(interceptor)
//                    .readTimeout(5, TimeUnit.SECONDS)
//                    .writeTimeout(5,TimeUnit.SECONDS)
                //网络请求缓存，未实现
//                    .addInterceptor(cacheInterceptor)
                .build();

        retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(API_HOST)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    public ApiService getService() {
        if (apiService == null)
            apiService = retrofit.create(ApiService.class);
        return apiService;
    }

    /**
     * 自定义异常，当接口返回的{@link ResponseDetail#code}不为{@link 1}时，需要跑出此异常
     * eg：登陆时验证码错误；参数为传递等
     */
    public static class APIException extends Exception {
        public String code;
        public String message;

        public APIException(String code, String message) {
            this.code = code;
            this.message = message;
        }

        @Override
        public String getMessage() {
            return message;
        }
    }

    /**
     * 对网络接口返回的Response进行分割操作
     *
     * @param response
     * @param <T>
     * @return
     */
    public <T> Observable<T> flatResponseDetail(final ResponseDetail<T> response) {
        return Observable.create(new Observable.OnSubscribe<T>() {

            @Override
            public void call(Subscriber<? super T> subscriber) {
                Logger.d("response.toString()-->"+response.toString());
                if (response.isSuccess()) {
                    if (!subscriber.isUnsubscribed()) {
                        Logger.d("string-->detail=="+response.detail+"-->items==");
//                        if (response.detail!=null){
                            subscriber.onNext(response.detail);
//                        }else if (response.items!=null){
//                            subscriber.onNext(response.items);
//                        }
                    }
                } else {
                    if (!subscriber.isUnsubscribed()) {
                        subscriber.onError(new APIException(response.code, response.message));
                    }
                    return;
                }

                if (!subscriber.isUnsubscribed()) {
                    subscriber.onCompleted();
                }
            }
        });
    }

//    protected <T> Observable.Transformer<Response<T>, T> applySchedulersDetail() {
//        return (Observable.Transformer<ResponseDetail<T>, T>) transformerDetail;
//    }

    final Observable.Transformer transformerDetail = new Observable.Transformer() {
        @Override
        public Object call(Object observable) {
            return ((Observable) observable).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .flatMap(new Func1() {
                        @Override
                        public Object call(Object response) {
                            return flatResponseDetail((ResponseDetail<Object>)response);
                        }
                    })
                    ;
        }
    };

    protected <T> Observable.Transformer<ResponseIterms<T>, T> applySchedulersItems() {
        return (Observable.Transformer<ResponseIterms<T>, T>) transformerItems;
    }

    final Observable.Transformer transformerItems = new Observable.Transformer() {
        @Override
        public Object call(Object observable) {
            return ((Observable) observable).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .flatMap(new Func1() {
                        @Override
                        public Object call(Object response) {
                            return flatResponseItems((ResponseIterms<Object>)response);
                        }
                    })
                    ;
        }
    };

    /**
     * 对网络接口返回的Response进行分割操作
     *
     * @param response
     * @param <T>
     * @return
     */
    public <T> Observable<T> flatResponseItems(final ResponseIterms<T> response) {
        return Observable.create(new Observable.OnSubscribe<T>() {

            @Override
            public void call(Subscriber<? super T> subscriber) {
                Logger.d("response.toString()-->"+response.toString());
                if (response.isSuccess()) {
                    if (!subscriber.isUnsubscribed()) {
                        Logger.d("string-->detail=="+"-->items=="+response.items);
                            subscriber.onNext(response.items);
                    }
                } else {
                    if (!subscriber.isUnsubscribed()) {
                        subscriber.onError(new APIException(response.code, response.message));
                    }
                    return;
                }

                if (!subscriber.isUnsubscribed()) {
                    subscriber.onCompleted();
                }
            }
        });
    }
}
