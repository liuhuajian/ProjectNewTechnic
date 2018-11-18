package com.project.common.core.base;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import com.project.common.R;
import com.project.common.core.http.BaseObserver;
import com.project.common.core.http.HttpCallBack;
import com.project.common.core.http.bean.JsonResult;
import com.project.common.core.http.exception.NetWorkThrowable;
import com.project.common.core.utils.DisplayUtil;
import com.project.common.core.utils.ExitAppUtils;
import com.project.common.core.utils.MyLogger;
import com.project.common.core.utils.ToastUtil;
import com.project.common.core.view.TitleView;
import com.project.common.core.view.uiManage.StateLayoutManagerBuilder;
import com.project.common.core.view.uiManage.StatesLayoutManager;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * 基类Fragment
 *
 * @author julyzeng
 * @version 1.0
 * @Description 所有的Fragment必须继承自此类
 * @date 2014年3月29日
 * @Copyright:
 */
public abstract class BaseFragment<T extends BaseCommonPresenter> extends Fragment implements BaseView {
    /**
     * 父视图
     */
    protected View mViewRoot = null;
    protected Activity activity;
    protected Handler mHandler;
    protected FragmentManager fm;
    protected Context mContext;

    public TitleView getTitleView() {
        return titleView;
    }

    public void setTitleView(TitleView titleView) {
        this.titleView = titleView;
    }

    public void setTitle(String title) {
        titleView.setTitleText(title);
    }

    public boolean isHideBackArrow() {
        return true;
    }

    /**
     * 页面布局的 根view
     */
    protected TitleView titleView;
    private View mViewContent;
    private View mStatusView;
    private View mTopLine;

    //页面状态管理器
    public StatesLayoutManager mStatesLayoutManager;
    /**
     * 使用CompositeDisposable来持有所有的compositeDisposable
     */
    protected CompositeDisposable compositeDisposable;

    protected T presenter;
    protected InputMethodManager inputMethodManager;

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        /**
         * Fragment之间切换时每次都会调用onCreateView方法，
         * 导致每次Fragment的布局都重绘，无法保持Fragment原有状态。 解决办法：在Fragment
         * onCreateView方法中缓存View
         */
        if (mViewRoot == null) {
            mViewRoot = LayoutInflater.from(mContext).inflate(R.layout.activity_base_layout, null);
        }
        mStatusView = mViewRoot.findViewById(R.id.view_status);
        mTopLine = mViewRoot.findViewById(R.id.top_line);//公共的title
        titleView = mViewRoot.findViewById(R.id.title_base);//公共的title
        container = mViewRoot.findViewById(R.id.container);//公共的布局容器
        mViewContent = LayoutInflater.from(mContext).inflate(getLayoutId(), null, false);//创建一个指定布局的view并添加到父容器container中
        mStatesLayoutManager = new StateLayoutManagerBuilder(getActivity())
                .setContentEmptyLayoutResId(R.layout.view_network_error_layout)// 数据为空
                .setContentErrorResId(R.layout.view_network_error_layout)//页面错误
                .setNetWorkErrorLayoutResId(R.layout.view_network_error_layout)//显示网络异常
                .setNetWorkErrorRetryViewId(R.id.tv_retry)//网络异常 重试id
                .setSystemMaintainResId(R.layout.view_network_error_layout) //系统维护
                .setSystemBusyResId(R.layout.view_network_error_layout) //系统繁忙
                .setLoadingLayoutResId(R.layout.view_network_error_layout) //loading
                .setContentView(mViewContent) //内容
                .setOnRetryListener(new StatesLayoutManager.OnRetryListener() {
                    @Override
                    public void onRetry() {
                        mStatesLayoutManager.showContent();
                        showDataView();
                    }
                })
                .create();
        container.removeAllViews();
        container.addView(mStatesLayoutManager.getRootLayout());
        initStatus();
        if (isHideBackArrow()) {
            getTitleView().getBackLayout().setVisibility(View.GONE);
        } else {
            getTitleView().getBackLayout().setVisibility(View.VISIBLE);
        }

        unbinder = ButterKnife.bind(this, mViewContent);
        //创建 CompositeSubscription 对象 使用CompositeSubscription来持有所有的Subscriptions，然后在onDestroy()或者onDestroyView()里取消所有的订阅。
        compositeDisposable = new CompositeDisposable();
        ViewGroup parent = (ViewGroup) mViewRoot.getParent();
        if (parent != null) {
            parent.removeView(mViewRoot);
        }
        unbinder = ButterKnife.bind(this, mViewContent);

        initData();
        initListener();
        defaultRequest();
        return mViewRoot;
    }

    protected boolean isShowTitleBar() {
        return false;
    }

    protected boolean isShowStatus() {
        return false;
    }

    /**
     * 初始化actionBar
     */
    private void initStatus() {
        if (isShowTitleBar()) {
            titleView.setVisibility(View.VISIBLE);
            mStatusView.setVisibility(View.VISIBLE);
        } else {
            titleView.setVisibility(View.GONE);
            mStatusView.setVisibility(View.GONE);
            mTopLine.setVisibility(View.GONE);
        }
        if (isShowStatus()) {
            mStatusView.setVisibility(View.VISIBLE);
        }
        if (Build.VERSION_CODES.JELLY_BEAN_MR1 >= Build.VERSION.SDK_INT) {
            mStatusView.setVisibility(View.GONE);
        }
        //默认状态栏是主题色
        setStatusBarColor(getResources().getColor(R.color.theme_color));
        ViewGroup.LayoutParams layoutParams = mStatusView.getLayoutParams();
        layoutParams.height = DisplayUtil.getStatusBarHeight(getActivity());
        mStatusView.setLayoutParams(layoutParams);
    }

    protected void setStatusBarColor(int color) {
        mStatusView.setBackgroundColor(color);
    }

    /**
     * 获取view 子类现实此方法
     *
     * @return
     * @version 1.0
     * @createTime 2014年5月22日, 上午10:09:05
     * @updateTime 2014年5月22日, 上午10:09:05
     * @createAuthor
     * @updateAuthor
     * @updateInfo (此处输入修改内容, 若无修改可不写.)
     */
    protected abstract int getLayoutId();

    /**
     * 初始化数据 子类重写此方法
     */
    protected void initData() {
    }

    /**
     * 初始化监听器
     */
    protected void initListener() {
    }

    /**
     * 默认的网咯请求
     *
     * @createAuthor
     */
    protected void defaultRequest() {
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (unbinder != null) {
            unbinder.unbind();
        }
        if (compositeDisposable != null) {
            compositeDisposable.clear();
        }
    }

    public void onResume() {
        super.onResume();
    }

    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroy() {


        if (mHandler != null) {
            mHandler.removeCallbacksAndMessages(null);
        }
        if (mViewContent != null) {
            ExitAppUtils.getInstance().unbindReferences(mViewContent);
        }
        //解绑 presenter
        if (presenter != null) {
            presenter.unsubscribe();
        }
        if (mViewRoot != null) {
            mViewRoot = null;
        }
        super.onDestroy();
        ToastUtil.cancekToast();
    }

    /**
     * 创建观察者  这里对观察着 过滤一次，过滤出我们想要的信息，错误的信息toast
     *
     * @param onNext
     * @param <T>
     * @return
     */
    protected <T> Observer newObserver(final HttpCallBack<T> onNext) {
        return new BaseObserver<T>(this, onNext) {
            @Override
            public void onComplete() {
                super.onComplete();
            }

            @Override
            public void onError(@NonNull Throwable e) {
                super.onError(e);

            }

            @Override
            public void onNext(@NonNull JsonResult<T> t) {
                super.onNext(t);
            }

            @Override
            public void onSubscribe(@NonNull Disposable d) {
                super.onSubscribe(d);
                compositeDisposable.add(d);
            }
        };
    }

    /**
     * 恢复加载数据的界面
     */
    private void showDataView() {
        defaultRequest();
    }

    @Override
    public boolean isShowErrorLayout() {
        return false;
    }

    @Override
    public StatesLayoutManager getStatesLayoutManager() {
        return mStatesLayoutManager;
    }
}
