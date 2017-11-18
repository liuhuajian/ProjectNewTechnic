package messi.lhj.com.projectnewtechnic.sonic;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.tencent.sonic.sdk.SonicConfig;
import com.tencent.sonic.sdk.SonicEngine;
import com.tencent.sonic.sdk.SonicSession;
import com.tencent.sonic.sdk.SonicSessionConfig;

import butterknife.BindView;
import butterknife.ButterKnife;
import messi.lhj.com.projectnewtechnic.R;

public class WebViewActivity extends AppCompatActivity {

    @BindView(R.id.webview)
    WebView webview;

    private static final String base_url = "http://mc.vip.qq.com/demo/indexv2";
    private SonicSession sonicSession;
    private SonicSessionClientImpl sonicSessionClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        ButterKnife.bind(this);
        int type = getIntent().getIntExtra("type", 0);
        if (type ==1) {
            initSonic();
        }
        initWebView();
    }

    private void initSonic() {

        sonicSessionClient = null;

        // step 2: Create SonicSession
        sonicSession = SonicEngine.getInstance().createSession(base_url,  new SonicSessionConfig.Builder().build());
        if (null != sonicSession) {
            sonicSession.bindClient(sonicSessionClient = new SonicSessionClientImpl());
        } else {
            // this only happen when a same sonic session is already running,
            // u can comment following codes to feedback as a default mode.
            throw new UnknownError("create session fail!");
        }
    }

    private void initWebView() {
        webview.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                if (sonicSession != null) {
                    sonicSession.getSessionClient().pageFinish(url);
                }
            }

            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
                return super.shouldInterceptRequest(view, request);
            }

            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
                if (sonicSession != null) {
                    //step 6: Call sessionClient.requestResource when host allow the application
                    // to return the local data .
                    return (WebResourceResponse) sonicSession.getSessionClient().requestResource(url);
                }
                return null;
            }


        });

        WebSettings webSettings = webview.getSettings();
        webSettings.setJavaScriptEnabled(true);

//        webview.removeJavascriptInterface("searchBoxJavaBridge_");
//        intent.putExtra(SonicJavaScriptInterface.PARAM_LOAD_URL_TIME, System.currentTimeMillis());
//        webView.addJavascriptInterface(new SonicJavaScriptInterface(sonicSessionClient, intent), "sonic");
        // init webview settings
        webSettings.setAllowContentAccess(true);
        webSettings.setDatabaseEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setAppCacheEnabled(true);
        webSettings.setSavePassword(false);
        webSettings.setSaveFormData(false);
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        if (sonicSessionClient != null) {
            sonicSessionClient.bindWebView(webview);
            sonicSessionClient.clientReady();
        } else { // default mode
            webview.loadUrl(base_url);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        if (null != sonicSession) {
            sonicSession.destroy();
            sonicSession = null;
        }
        super.onDestroy();
    }
}
