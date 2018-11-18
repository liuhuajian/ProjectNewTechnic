package com.project.common.core.base;

import com.project.common.core.view.uiManage.StatesLayoutManager;

/**
 * 项目：原产地
 * 作    者：julyzeng （曾招林)  364298140@qq.com
 * 版    本：1.0
 * 创建日期：2017/4/2 22:57
 * 描    述：mvp中的基类View
 * 修订历史：叶贤明 去除显示隐藏view的藏在，改成获取页面错误管理器，统一操作错误
 */

public interface BaseView {


    //是否加载状态管理器
    StatesLayoutManager getStatesLayoutManager();

    //是否显示错误页面
    boolean isShowErrorLayout();
}
