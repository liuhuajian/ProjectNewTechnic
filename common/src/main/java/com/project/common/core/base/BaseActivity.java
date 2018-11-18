package com.project.common.core.base;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.julyzeng.baserecycleradapterlib.listener.RequestLoadMoreListener;
import com.project.common.R;
import com.project.common.core.http.BaseObserver;
import com.project.common.core.http.HttpCallBack;
import com.project.common.core.http.bean.JsonResult;
import com.project.common.core.listener.PullToRefreshListener;
import com.project.common.core.utils.DisplayUtil;
import com.project.common.core.utils.ExitAppUtils;
import com.project.common.core.utils.StatusBarUtil;
import com.project.common.core.utils.ToastUtil;
import com.project.common.core.utils.WYQYHelp;
import com.project.common.core.view.TitleView;
import com.project.common.core.view.uiManage.StateLayoutManagerBuilder;
import com.project.common.core.view.uiManage.StatesLayoutManager;
import com.umeng.analytics.MobclickAgent;

import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
/**
 *项目 国民健康
 * @Create by yexm
 * @创建日期 2018/8/30 14:54
 *
 * @版本 0.2
 * @类说明:
 */
public abstract class BaseActivity<T extends BaseCommonPresenter> extends AppCompatActivity implements IActivity, BaseView,PullToRefreshListener,RequestLoadMoreListener {
//public abstract class BaseActivity<T extends BaseCommonPresenter> extends AppCompatActivity implements IActivity, BaseView,PullToRefreshListener {

    /**
     * 使用CompositeDisposable来持有所有的compositeDisposable
     */
    protected CompositeDisposable compositeDisposable;

    public T presenter;
    protected InputMethodManager inputMethodManager;
    /*****
     * 公共变量
     */
    protected WeakReferenceHandler handler;

    protected Activity mContext;
    /**
     * 页面布局的 根view
     */
    protected TitleView titleView;
    private RelativeLayout container;
    private View contentView;
    private View mStatusView;
    private View mTopLine;

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);//支持SVG动画
    }

    //页面状态管理器
    public StatesLayoutManager mStatesLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.setTransparent(this);
        mContext = this;
        if (android.os.Build.VERSION.SDK_INT == 26){//目前只有8.0版本出现透明背景不能设置屏幕方向

        }else {// 竖屏显示
            if (getRequestedOrientation() != ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            }
        }
        //创建 CompositeSubscription 对象 使用CompositeSubscription来持有所有的Subscriptions，然后在onDestroy()或者onDestroyView()里取消所有的订阅。
        compositeDisposable = new CompositeDisposable();

        View baseView = LayoutInflater.from(this).inflate(R.layout.activity_base_layout, null);//公共标题和容器
        titleView = baseView.findViewById(R.id.title_base);//公共的title
        mStatusView = baseView.findViewById(R.id.view_status);//公共的title
        mTopLine = baseView.findViewById(R.id.top_line);//公共的title
        container = baseView.findViewById(R.id.container);//公共的布局容器
        contentView = LayoutInflater.from(this).inflate(getContentViewId(), null, false);//创建一个指定布局的view并添加到父容器container中
        mStatesLayoutManager = new StateLayoutManagerBuilder(this)
                .setContentEmptyLayoutResId(R.layout.view_network_error_layout)// 数据为空
                .setContentErrorResId(R.layout.view_network_error_layout)//页面错误
                .setNetWorkErrorLayoutResId(R.layout.view_network_error_layout)//显示网络异常
                .setNetWorkErrorRetryViewId(R.id.tv_retry)//网络异常 重试id
                .setSystemMaintainResId(R.layout.view_network_error_layout) //系统维护
                .setSystemBusyResId(R.layout.view_network_error_layout) //系统繁忙
                .setLoadingLayoutResId(R.layout.view_network_error_layout) //loading
                .setContentView(contentView) //内容
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
        setContentView(baseView);
//        if (AndroidWorkaround.checkDeviceHasNavigationBar(this)) {
//            AndroidWorkaround.assistActivity(findViewById(android.R.id.content));
//        }
        initStatus();
        // 类压入堆栈
        ExitAppUtils.getInstance().addActivity(this);
        // 初始化activity
        initActivity(savedInstanceState);
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
        layoutParams.height = DisplayUtil.getStatusBarHeight(this);
        mStatusView.setLayoutParams(layoutParams);
        //适配底部虚拟按键
//        if(StatusBarUtil.hasNavBar(this)){
//            getWindow().getDecorView().setBackgroundColor(ContextCompat.getColor(this, R.color.black));
//        }
    }


    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        ButterKnife.bind(this);
        //初始化页面
        init();
    }

    protected void initData() {

    }

    protected boolean isShowStatus() {
        return isShowTitleBar();
    }

    protected boolean isShowTitleBar() {
        return true;
    }

    protected void setStatusBarColor(int color) {
        mStatusView.setBackgroundColor(color);
    }

    /**
     * 初始化页面
     */
    private void init() {
        // 获取上一页面传入的参数信息
        getParams();
        // 默认请求（分类信息查询）
        defaultRequest();
        //初始化数据
        initData();
        // button事件绑定
        bindBtnEvent();
    }

    @Override
    protected void onResume() {
        super.onResume();

        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    /**
     * 获取显示view的xml文件ID
     * <p>
     * 在Activity的 {@link #onCreate(Bundle)}里边被调用
     *
     * @return xml文件ID
     * @version 1.0
     * @createTime 2014年4月21日, 下午2:31:19
     * @updateTime 2014年4月21日, 下午2:31:19
     * @createAuthor CodeApe
     * @updateAuthor CodeApe
     * @updateInfo (此处输入修改内容, 若无修改可不写.)
     */
    protected abstract int getContentViewId();

    @Override
    public void onCreateInfo(Bundle savedInstanceState) {


    }

    @Override
    public void initActivity(Bundle savedInstanceState) {


    }

    @Override
    public void bindBtnEvent() {


    }

    @Override
    public void getParams() {


    }

    @Override
    public void defaultRequest() {


    }

    /**
     * 创建相应的 presenter
     */
    public void createPresenter(T presenter) {
        if (presenter != null) {
            this.presenter = presenter;
        }

    }

    /**
     * 创建相应的 presenter
     */
    public T getPresenter() {
        if (presenter != null) {
            return presenter;
        }
        return null;
    }

    protected void hideSoftKeyboard() {

        inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (getWindow().getAttributes().softInputMode != WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN) {
            if (getCurrentFocus() != null)
                inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }


    @Override
    public void finish() {
        // this.overridePendingTransition(R.anim.slide_left_in, R.anim.slide_right_out);
        super.finish();
    }

    @Override
    protected void onDestroy() {
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
//		//Acitvity 释放子view资源
        ExitAppUtils.getInstance().removeActivity(this);
        if (compositeDisposable != null) {
            compositeDisposable.clear();
        }
        //解绑 presenter
        if (presenter != null) {
            presenter.unsubscribe();
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
     * 创建观察者  这里对观察着 过滤一次，过滤出我们想要的信息，错误的信息toast
     *
     * @param onNext
     * @param <T>  上面的方法加了是否显示进度条
     * @return
     */
    protected <T> Observer newObserver(final HttpCallBack<T> onNext,boolean isShowDialog) {
        return new BaseObserver<T>( onNext,this,isShowDialog) {
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
    protected void showDataView() {
        defaultRequest();
    }

    @Override
    public boolean isShowErrorLayout() {
        return true;
    }

    @Override
    public StatesLayoutManager getStatesLayoutManager() {
        return mStatesLayoutManager;
    }

    /**
     * 点击空白处关闭软键盘逻辑
     */
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View currentFocus = getCurrentFocus();
            if (isShouldHideKeyboard(currentFocus, ev)) {
                hideKeyboard(currentFocus.getWindowToken());
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    private boolean mNeedHandleEditSelf;

    void setNeedHandleEditSelf(boolean needHandleEditSelf) {
        mNeedHandleEditSelf = needHandleEditSelf;
    }


    /**
     * 根据EditText所在坐标和用户点击的坐标相对比，来判断是否隐藏键盘，因为当用户点击EditText时则不能隐藏
     *
     * @param currentFocus *
     * @param event        *
     * @return
     */
    private boolean isShouldHideKeyboard(View currentFocus, MotionEvent event) {
        if (currentFocus != null && currentFocus instanceof EditText) {
            int[] l = new int[2];
            currentFocus.getLocationInWindow(l);
            int left = l[0];
            int top = l[1];
            int bottom = top + currentFocus.getHeight();
            int right = left + currentFocus.getWidth();
            if (event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom) {
                // 点击EditText的事件，忽略它。
                return false;
            } else {
                if (mNeedHandleEditSelf) {
                    return false;
                }
                return true;
            }
        }
        // 如果焦点不是EditText则忽略，这个发生在视图刚绘制完，第一个焦点不在EditText上，和用户用轨迹球选择其他的焦点
        return false;
    }

    /**
     * 获取InputMethodManager，隐藏软键盘
     *
     * @param token
     */
    private void hideKeyboard(IBinder token) {
        if (token != null) {
            InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            im.hideSoftInputFromWindow(token, InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {//配置app字体不随系统设置而变化
        if (newConfig.fontScale != 1)//非默认值
            getResources();
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public Resources getResources() {
        Resources res = super.getResources();
        if (res.getConfiguration().fontScale != 1) {//非默认值
            Configuration newConfig = new Configuration();
            newConfig.setToDefaults();//设置默认
            res.updateConfiguration(newConfig, res.getDisplayMetrics());
        }
        return res;
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMoreRequested() {

    }

    protected void displayService() {
        titleView.setRightAlignToLeftImageResource(R.mipmap.ic_customer_service);
        titleView.setRightAlignToLeftButtonListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WYQYHelp.getInstance().contactCustomerService();
            }
        });
    }
}
