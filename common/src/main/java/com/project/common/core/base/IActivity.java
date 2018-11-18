package com.project.common.core.base;

import android.os.Bundle;

/**
 * activity数据UI处理顺序
 * 明确定义了在activity中各处项对应的方法，为activity工作流程定义一套处理规范
 * 第一步,initActivity(),初始化activity参数
 * 第二步,getParams(),获取请求参数信息
 * 第三步,handleMessage(),handler=new Handler(this) 监听请求响应
 * 第四步,defaultRequest(),发送默认的请求
 * 第五步,activity页面基本元素事件的绑定及事件处理
 * 
 * 在默认的onCreate方法中调用onCreateInfo()默认的执行顺
 * @author july.zeng
 * @date 2014年4月28日 上午11:11:46
 */
public interface IActivity  {
	/**
	 * 默认的调用顺序
	 * @author july.zeng
	 * 2014年4月28日 上午11:11:31
	 * @param savedInstanceState
	 */
	public void onCreateInfo(Bundle savedInstanceState);
	/**
	 * 初始化进入场景时的信息
	 * @author july.zeng
	 * 2014年4月28日 上午11:11:21
	 * @param savedInstanceState
	 */
	public void initActivity(Bundle savedInstanceState);
	/**
	 * btn事件的绑定及处理
	 * @author july.zeng
	 * 2014年5月5日 上午10:42:31
	 */
	public void bindBtnEvent();
	/**
	 * 获取请求参数信息
	 * @author july.zeng
	 * 2014年4月28日 上午11:08:52
	 */
	public void getParams();
	/**
	 * 进入页面的默认请求
	 * @author july.zeng
	 * 2014年4月28日 上午11:08:47
	 */
	public void defaultRequest();
	
}
