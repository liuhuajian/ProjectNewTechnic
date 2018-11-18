package com.project.common.core.view.dialog.api;

import com.project.common.core.http.bean.JsonResult;
import com.project.common.core.http.constant.NetConstantURL;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * 项目 国民健康
 *
 * @Create by yexm
 * @创建日期 2018/7/12 17:01
 * @版本 0.1
 * @类说明:
 */

public interface AddressApiservice {


    @POST(NetConstantURL.GET_ADDRESS)
    Observable<JsonResult<JDAddressBean>> getAddress(@Body Map markMoudle);
}
