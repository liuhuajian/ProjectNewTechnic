package com.project.common.core.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import com.project.common.core.utils.ExitAppUtils;
import com.project.common.core.utils.ToastUtil;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.disposables.CompositeDisposable;

/**
 * 基类Fragment
 * 
 * @Description 所有的Fragment必须继承自此类
 * @author julyzeng
 * @version 1.0
 * @date 2014年3月29日
 * @Copyright:
 * 
 */
public abstract class BaseViewPagerFragment<T extends BaseCommonPresenter>extends Fragment implements Handler.Callback{
	/** Fragment当前状态是否可见 */
	protected boolean isVisible;
	/** 是否已被加载过一次，第二次就不再去请求数据了 */
	protected boolean mHasLoadedOnce;
	/** 标志位，标志已经初始化完成 */
	private boolean isPrepared;/** 父视图 */
	protected View view_Parent = null;
	protected Activity activity;
	protected Handler mHandler;
	protected FragmentManager fm;
	protected Context mContext;
	/**
	 * 使用CompositeDisposable来持有所有的compositeDisposable
	 */
	protected CompositeDisposable compositeDisposable;

	public  T presenter;
	protected InputMethodManager inputMethodManager;
	SparseArray<View> cacheView;
	private Unbinder unbinder;

	@Override
	public void onAttach(Activity activity) {
		this.activity = activity;
		fm = getFragmentManager();
		super.onAttach(activity);
	}

	@Override
	public void onAttach(Context context) {
		mContext = context;
		super.onAttach(context);
	}
	/**
	 * 创建相应的 presenter
	 */
	public void createPresenter(T presenter) {
		if (presenter != null) {
			this.presenter = presenter;
		}

	}


	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		compositeDisposable = new CompositeDisposable();
		if (view_Parent == null) {
			view_Parent = inflater.inflate(getLayoutId(),container,false);
			unbinder = ButterKnife.bind(this,view_Parent);
			mHandler = new Handler();
			cacheView = new SparseArray<>();
			isPrepared  = true;
			initData();//初始化控件数据
			onLoad();
			cacheView.put(getLayoutId(),view_Parent);
		}else {
			view_Parent = cacheView.get(getLayoutId());
		}
		// Cache rootView.
		// remove rootView from its parent
		ViewGroup parent = (ViewGroup) view_Parent.getParent();
		if (parent != null) {
			parent.removeView(view_Parent);
		}
		return view_Parent;
	}

	@Override
	public void setUserVisibleHint(boolean isVisibleToUser) {
		super.setUserVisibleHint(isVisibleToUser);

		if(getUserVisibleHint()) {
			isVisible = true;
			onVisible();
		} else {
			isVisible = false;
			onInvisible();
		}
	}


	/**
	 * 可见
	 */
	protected void onVisible() {
		if (!isPrepared || !isVisible || mHasLoadedOnce) {
			return;
		}
		onLoad();
	}

	@Override
	public boolean handleMessage(Message msg) {
		return false;
	}

	/**
	 * 不可见
	 */
	protected void onInvisible() {

	}


	/**
	 * 延迟加载
	 * 子类必须重写此方法
	 */
	protected abstract void onLoad();


	/**
	 * 获取view 子类现实此方法
	 *
	 * @version 1.0
	 * @createTime 2014年5月22日,上午10:09:05
	 * @updateTime 2014年5月22日,上午10:09:05
	 * @createAuthor CodeApe
	 * @updateAuthor CodeApe
	 * @updateInfo (此处输入修改内容,若无修改可不写.)
	 *
	 * @return
	 */
	protected abstract int getLayoutId();

	/**
	 * 初始化数据 子类重写此方法
	 */
	protected void initData() {}

	/**
	 * 初始化监听器
	 */
	protected void initListener() {}

	/**
	 * 默认的网咯请求
	 *
	 * @createAuthor spideman
	 */
	protected void defaultRequest() {}


	/**
	 * 设置广播过滤器，在此添加广播过滤器之后，所有的子类都将收到该广播
	 *
	 * @version 1.0
	 * @createTime 2014年5月22日,下午1:43:15
	 * @updateTime 2014年5月22日,下午1:43:15
	 * @createAuthor CodeApe
	 * @updateAuthor CodeApe
	 * @updateInfo (此处输入修改内容,若无修改可不写.)
	 *
	 */

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		unbinder.unbind();
	}


	public void onResume() {
		super.onResume();
//		MobclickAgent.onPageStart(this.getClass().getSimpleName()); //统计页面，"ssss"为页面名称，可自定义
	}
	public void onPause() {
		super.onPause();
//		MobclickAgent.onPageEnd(this.getClass().getSimpleName());
	}

	@Override
	public void onDestroy() {

		if (mHandler !=null) {
			mHandler.removeCallbacksAndMessages(null);
		}
		if (view_Parent !=null) {
			ExitAppUtils.getInstance().unbindReferences(view_Parent);
		}
		if(compositeDisposable != null){
			compositeDisposable.clear();
		}
		//解绑 presenter
		if (presenter != null) {
			presenter.unsubscribe();
		}
		super.onDestroy();
		ToastUtil.cancekToast();
	}

}
