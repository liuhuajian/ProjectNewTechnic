package com.project.common.core.http;


import com.project.common.core.download.update.entity.AppVersion;
import com.project.common.core.http.bean.BaseTagBean;
import com.project.common.core.http.bean.JsonResult;

import com.alibaba.fastjson.JSON;
import com.project.common.core.http.bean.JsonResult;
import com.project.common.core.http.bean.LaberModel;
import com.project.common.core.utils.Constants;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * 项目名称：
 * 类描述： - 公用的请求API接口
 * 创建人：julyzeng
 * 创建时间：2018/6/10  11:36
 * 修改人：
 * 修改时间：
 * 修改备注：
 *
 * @version 1.0
 */
public class ApiService extends Api{


    RequestApiService apiService =  ServiceFactory.createServiceFrom(RequestApiService.class);

    /**
     * 单图上传图片
     * @param file
     * @return
     */

    public Observable<JsonResult<String>> uploadImage(File file) {

            MultipartBody.Builder builder = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM);//表单类型
            RequestBody imageBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
            builder.addFormDataPart("file", file.getName(), imageBody);//imgfile 后台接收图片流的参数名
            List<MultipartBody.Part> parts = builder.build().parts();

            return appLySchedulers(apiService.imagePost(parts));
    }



    /**
     * 多图上传图片
     * @param files
     * @return
     */

    public Observable<JsonResult<List<String>>> uploadImageList(List<File> files) {

        MultipartBody.Builder builder = new MultipartBody.Builder()
                .setType(MultipartBody.FORM);//表单类型
        for (File file : files) {
            RequestBody imageBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
            builder.addFormDataPart("file", file.getName(), imageBody);
        }
        List<MultipartBody.Part> parts = builder.build().parts();

        return appLySchedulers(apiService.imageListPost(parts));
    }



    /**
     * 系统标签
     */

    public Observable<JsonResult<List<LaberModel>>> getLaberList(@Query("typeId")String typeId) {

        HashMap<String, Object> map = new HashMap<>();
        map.put("typeId", typeId);

        return appLySchedulers(apiService.getLaberList(RequestBody
                .create(MediaType.parse(Constants.MEDIA_TYPE_CHARSET), JSON.toJSONString(map))));
    }


    /**
     * 系统标签控制器返回集合
     *
     * @return
     */

    public Observable<JsonResult<List<BaseTagBean>>> selectTagForApp(String id) {
        Map<String, String> map = new HashMap();
        map.put("typeId", "" + id);
        return appLySchedulers(ServiceFactory.createServiceFrom(RequestApiService.class).selectTagForApp(map));
    }

    /**
     * 系统版本升级
     *
     * @return
     */

    public Observable<JsonResult<AppVersion>> appVersionUpdate() {
        Map<String, String> map = new HashMap();
        map.put("os", "2");
        return appLySchedulers(apiService.appVersionUpdate(RequestBody
                .create(MediaType.parse(Constants.MEDIA_TYPE_CHARSET), JSON.toJSONString(map))));
    }

}
