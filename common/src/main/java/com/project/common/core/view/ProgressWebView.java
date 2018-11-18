package com.project.common.core.view;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.webkit.CookieManager;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.project.common.core.BaseApp;

/**
 * 
 * @Description 带进度条的WebView
 * @author 曾招林
 * @date 2015年10月15日
 * @Copyright:
 */
public class ProgressWebView extends WebView {

//	private ProgressBar progressbar;

	public ProgressWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
//        progressbar = new ProgressBar(context, null, android.R.attr.progressBarStyle);
//        LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, 10, 0, 0);
//        progressbar.setLayoutParams(layoutParams);
//        addView(progressbar);

//        this.clearCache(true);
//        this.clearHistory();
        WebSettings settings = getSettings();
        // 设置字符集编码
        settings.setDefaultTextEncodingName("UTF-8");
        settings.setSupportZoom(false);
        settings.setLoadWithOverviewMode(true);
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setAppCacheEnabled(true);
        settings.supportMultipleWindows();
        settings.setAllowContentAccess(true);
        settings.setUseWideViewPort(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setLoadsImagesAutomatically(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            settings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        final String cachePath = BaseApp.mContext.getCacheDir().getAbsolutePath();
        settings.setAppCachePath(cachePath);
        settings.setAppCacheMaxSize(10 * 1024 * 1024);
        settings.setAllowFileAccess(true);
        settings.setNeedInitialFocus(true);
        settings.setCacheMode(WebSettings.LOAD_DEFAULT);

        setWebChromeClient(new WebChromeClient());
    }

    public class WebChromeClient extends android.webkit.WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
//            if (newProgress == 100) {
//                progressbar.setVisibility(GONE);
//            } else {
//                if (progressbar.getVisibility() == GONE)
//                    progressbar.setVisibility(VISIBLE);
//                progressbar.setProgress(newProgress);
//            }
            super.onProgressChanged(view, newProgress);
        }

    }

	@Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
//        LayoutParams lp = (LayoutParams) progressbar.getLayoutParams();
//        lp.x = l;
//        lp.y = t;
//        progressbar.setLayoutParams(lp);
        super.onScrollChanged(l, t, oldl, oldt);
    }

    @Override
    public void destroy() {
        super.destroy();
        CookieManager.getInstance().removeAllCookie();
    }
}
