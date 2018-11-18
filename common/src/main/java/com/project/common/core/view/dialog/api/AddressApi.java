package com.project.common.core.view.dialog.api;

import com.project.common.core.http.Api;
import com.project.common.core.http.ServiceFactory;
import com.project.common.core.http.bean.JsonResult;

import java.util.Map;

import io.reactivex.Observable;

/**
 * 项目 国民健康
 *
 * @Create by yexm
 * @创建日期 2018/7/12 15:47
 * @版本 0.1
 * @类说明: common 模块请求类
 */

public class AddressApi extends Api {

    public Observable<JsonResult<JDAddressBean>> getAddress(Map markMoudle) {
        return appLySchedulers(ServiceFactory.createServiceFrom(AddressApiservice.class).getAddress(markMoudle));
    }
}
