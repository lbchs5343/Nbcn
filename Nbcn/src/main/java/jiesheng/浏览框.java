/*
 * Decompiled with CFR 0.152.
 *
 * Could not load the following classes:
 *  android.annotation.SuppressLint
 *  android.annotation.TargetApi
 *  android.app.Activity
 *  android.app.DownloadManager
 *  android.app.DownloadManager$Request
 *  android.content.ActivityNotFoundException
 *  android.content.Context
 *  android.content.Intent
 *  android.graphics.Bitmap
 *  android.net.Uri
 *  android.net.http.SslError
 *  android.os.Build$VERSION
 *  android.view.View
 *  android.view.ViewGroup$LayoutParams
 *  android.webkit.CookieManager
 *  android.webkit.CookieSyncManager
 *  android.webkit.DownloadListener
 *  android.webkit.JavascriptInterface
 *  android.webkit.SslErrorHandler
 *  android.webkit.ValueCallback
 *  android.webkit.WebChromeClient
 *  android.webkit.WebChromeClient$CustomViewCallback
 *  android.webkit.WebChromeClient$FileChooserParams
 *  android.webkit.WebResourceRequest
 *  android.webkit.WebResourceResponse
 *  android.webkit.WebSettings
 *  android.webkit.WebSettings$PluginState
 *  android.webkit.WebView
 *  android.webkit.WebViewClient
 *  android.widget.AbsoluteLayout$LayoutParams
 *  android.widget.FrameLayout
 *  android.widget.FrameLayout$LayoutParams
 *  android.widget.ProgressBar
 *  java.io.File
 *  java.lang.CharSequence
 *  java.lang.Exception
 *  java.lang.Object
 *  java.lang.Override
 *  java.lang.String
 */
package jiesheng;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.DownloadManager;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.DownloadListener;
import android.webkit.JavascriptInterface;
import android.webkit.SslErrorHandler;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AbsoluteLayout;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import java.io.File;

public class 浏览框
        extends 可视化组件 {
    private static ValueCallback<Uri> message_upload;
    private static ValueCallback<Uri[]> message_upload2;
    private final Context 窗口环境;
    private ProgressBar mProgressBar;
    private View mView;
    private int visibleAbility;
    private int original;
    private WebChromeClient.CustomViewCallback mCallback;
    private 网页开始加载 $event_internal_网页开始加载;
    private 网页加载完成 $event_internal_网页加载完成;
    private 进度值改变 $event_internal_进度值改变;
    private 接收到标题 $event_internal_接收到标题;
    private 拦截到Uri $event_internal_拦截到Uri;
    private 拦截到请求 $event_internal_拦截到请求;
    private 拦截到下载 $event_internal_拦截到下载;
    private js交互事件 $event_internal_js交互事件;

    public 浏览框(Context context) {
        super(context);
        this.窗口环境 = context;
        this.init();
    }

    private void enabledCookie(WebView webView) {
        CookieManager cookieManager = CookieManager.getInstance();
        if (Build.VERSION.SDK_INT < 21) {
            CookieSyncManager.createInstance(窗口环境);
        }
        cookieManager.setAcceptCookie(true);
        if (Build.VERSION.SDK_INT >= 21) {
            cookieManager.setAcceptThirdPartyCookies(webView, true);
        }
    }

    private void init() {
        this.mProgressBar = new ProgressBar(窗口环境, null, 16842872);
        this.mProgressBar.setLayoutParams((ViewGroup.LayoutParams) new AbsoluteLayout.LayoutParams(-1, this.dip2px(2), 0, 0));
        this.getView().addView((View) this.mProgressBar);
        this.getView().setWebChromeClient((WebChromeClient) new MyWebChromeClient());
        this.getView().setWebViewClient((WebViewClient) new MyWebViewClient());
        this.getView().setDownloadListener((DownloadListener) new MyDownloadListener());
        WebSettings webSettings = getView().getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDefaultTextEncodingName("utf-8");
        webSettings.setCacheMode(-1);
        webSettings.setPluginState(WebSettings.PluginState.ON);
        webSettings.setDisplayZoomControls(false);
        webSettings.setUseWideViewPort(true);
        webSettings.setAllowFileAccess(true);
        webSettings.setAllowContentAccess(true);
        webSettings.setSupportZoom(true);
        webSettings.setAllowContentAccess(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setSavePassword(true);
        webSettings.setSaveFormData(true);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        if (Build.VERSION.SDK_INT >= 21) {
            webSettings.setMixedContentMode(0);
        }
        if (Build.VERSION.SDK_INT >= 17) {
            webSettings.setMediaPlaybackRequiresUserGesture(true);
        }
        if (Build.VERSION.SDK_INT >= 16) {
            webSettings.setAllowFileAccessFromFileURLs(true);
            webSettings.setAllowUniversalAccessFromFileURLs(true);
        }
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setLoadsImagesAutomatically(true);

        webSettings.setAppCacheEnabled(true);
        webSettings.setAppCachePath(窗口环境.getCacheDir().getAbsolutePath());
        webSettings.setDatabaseEnabled(true);
        webSettings.setGeolocationDatabasePath(窗口环境.getDir("database", 0).getPath());
        webSettings.setGeolocationEnabled(true);
        CookieManager cookieManager = CookieManager.getInstance();
        if (Build.VERSION.SDK_INT < 21) {
            CookieSyncManager.createInstance(窗口环境.getApplicationContext());
        }
        cookieManager.setAcceptCookie(true);
        if (Build.VERSION.SDK_INT >= 21) {
            cookieManager.setAcceptThirdPartyCookies(this.getView(), true);
        }
        this.enabledCookie(this.getView());
    }

    private void showVideo(View view, WebChromeClient.CustomViewCallback customViewCallback) {
        if (this.窗口环境 instanceof Activity) {
            Activity activity = (Activity) this.窗口环境;
            this.mView = view;
            this.visibleAbility = activity.getWindow().getDecorView().getSystemUiVisibility();
            this.original = activity.getRequestedOrientation();
            this.mCallback = customViewCallback;
            FrameLayout frameLayout = (FrameLayout) activity.getWindow().getDecorView();
            frameLayout.addView(view, (ViewGroup.LayoutParams) new FrameLayout.LayoutParams(-1, -1));
            activity.getWindow().getDecorView().setSystemUiVisibility(3846);
            activity.setRequestedOrientation(0);
        }
    }

    private void closeVideo() {
        if (this.窗口环境 instanceof Activity) {
            Activity activity = (Activity) this.窗口环境;
            FrameLayout frameLayout = (FrameLayout) activity.getWindow().getDecorView();
            frameLayout.removeView(this.mView);
            this.mView = null;
            frameLayout.setSystemUiVisibility(this.visibleAbility);
            activity.setRequestedOrientation(this.original);
            this.mCallback.onCustomViewHidden();
            this.mCallback = null;
        }
    }

    public WebView getView() {
        return (WebView) super.getView();
    }

    @Override
    public View 创建视图() {
        return new WebView(this.取上下文());
    }

    public void 显示进度条(boolean bl) {
        if (bl) {
            this.mProgressBar.setVisibility(0);
        } else {
            this.mProgressBar.setVisibility(8);
        }
    }

    public void UA(String string) {
        this.getView().getSettings().setUserAgentString(string);
    }

    public boolean 可后退() {
        return this.getView().canGoBack();
    }

    public boolean 可前进() {
        return this.getView().canGoForward();
    }

    public String 网址() {
        return this.getView().getUrl();
    }

    public String 标题() {
        return this.getView().getTitle();
    }

    public int 进度() {
        return this.getView().getProgress();
    }

    public void 加载网址(String string) {
        this.getView().loadUrl(string);
    }

    public void 加载数据(String string) {
        this.getView().loadData(string, "*", "utf-8");
    }

    public void 打开应用(String string) {
        try {
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse((String) string));
            intent.setFlags(0x30000000);
            this.窗口环境.startActivity(intent);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void 停止加载() {
        this.getView().stopLoading();
    }

    public void 重载() {
        this.getView().reload();
    }

    public void 后退() {
        this.getView().goBack();
    }

    public void 前进() {
        this.getView().goForward();
    }

    public void 置进度条(进度条 进度条2) {
        this.mProgressBar = 进度条2.getView();
    }

    public void 置cookie(String string, String string2) {
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setCookie(string, string2);
    }

    public String 取cookie(String string) {
        CookieManager cookieManager = CookieManager.getInstance();
        return cookieManager.getCookie(string);
    }

    public void 清除历史() {
        this.getView().clearHistory();
    }

    public void 清除表单() {
        this.getView().clearFormData();
    }

    public void 清除cookie() {
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.removeAllCookie();
    }

    public void 添加js接口(String string) {
        this.getView().addJavascriptInterface((Object) new JsInterface(), string);
    }

    public long 下载(String string, String string2) {
        DownloadManager downloadManager = (DownloadManager) this.窗口环境.getSystemService("download");
        Uri uri = Uri.parse(string);
        uri.getLastPathSegment();
        DownloadManager.Request request = new DownloadManager.Request(uri);
        File file = new File(string2);
        request.setDestinationInExternalPublicDir(file.getParent(), file.getName());
        request.setTitle((CharSequence) file.getName());
        request.setDescription((CharSequence) string);
        File file2 = new File(string2);
        if (file2.exists()) {
            file2.delete();
        }
        long l = downloadManager.enqueue(request);
        return l;
    }

    public void 回调(int n, int n2, 意图 意图2) {
        if (n == 5173) {
            if (null == message_upload) {
                return;
            }
            Uri uri = 意图2 == null || n2 != -1 ? null : 意图2.getData();
            message_upload.onReceiveValue(uri);
            message_upload = null;
        } else if (n == 5174) {
            if (null == message_upload2) {
                return;
            }
            message_upload2.onReceiveValue(WebChromeClient.FileChooserParams.parseResult(n2, (Intent) 意图2));
            message_upload2 = null;
        }
    }

    public void 置网页开始加载(网页开始加载 网页开始加载2) {
        this.$event_internal_网页开始加载 = 网页开始加载2;
    }

    public void 置网页加载完成(网页加载完成 网页加载完成2) {
        this.$event_internal_网页加载完成 = 网页加载完成2;
    }

    public void 置进度值改变(进度值改变 进度值改变2) {
        this.$event_internal_进度值改变 = 进度值改变2;
    }

    public void 置接收到标题(接收到标题 接收到标题2) {
        this.$event_internal_接收到标题 = 接收到标题2;
    }

    public void 置拦截到Uri(拦截到Uri 拦截到Uri2) {
        this.$event_internal_拦截到Uri = 拦截到Uri2;
    }

    public void 置拦截到请求(拦截到请求 拦截到请求2) {
        this.$event_internal_拦截到请求 = 拦截到请求2;
    }

    public void 置拦截到下载(拦截到下载 拦截到下载2) {
        this.$event_internal_拦截到下载 = 拦截到下载2;
    }

    public void 置js交互事件(js交互事件 js交互事件2) {
        this.$event_internal_js交互事件 = js交互事件2;
    }

    @Override
    public void 初始化事件() {
    }

    public static interface js交互事件 {
        public void js交互事件(String var1);
    }

    public static interface 拦截到下载 {
        public void 拦截到下载(String var1, String var2, String var3, long var4);
    }

    public static interface 拦截到请求 {
        public void 拦截到请求(String var1);
    }

    public static interface 拦截到Uri {
        public void 拦截到Uri(String var1);
    }

    public static interface 接收到标题 {
        public void 接收到标题(String var1);
    }

    public static interface 进度值改变 {
        public void 进度值改变(int var1);
    }

    public static interface 网页加载完成 {
        public void 网页加载完成(String var1);
    }

    public static interface 网页开始加载 {
        public void 网页开始加载(String var1);
    }

    class JsInterface {
        JsInterface() {
        }

        @JavascriptInterface
        @SuppressLint(value = {"JavascriptInterface"})
        public void js交互(String string) {
            if (浏览框.this.$event_internal_js交互事件 != null) {
                浏览框.this.$event_internal_js交互事件.js交互事件(string);
            }
        }
    }

    class MyDownloadListener
            implements DownloadListener {
        MyDownloadListener() {
        }

        public void onDownloadStart(String string, String string2, String string3, String string4, long l) {
            int n;
            String string5;
            int n2;
            Uri uri = Uri.parse(string);
            String string6 = uri.getLastPathSegment();
            if (string3 != null && (n2 = string3.indexOf(string5 = "filename=\"")) != -1 && (n = string3.indexOf(34, n2 += string5.length())) > n2) {
                string6 = string3.substring(n2, n);
            }
            if (浏览框.this.$event_internal_拦截到下载 != null) {
                浏览框.this.$event_internal_拦截到下载.拦截到下载(string, string6, string4, l);
            }
        }
    }

    private class MyWebChromeClient
            extends WebChromeClient {
        private MyWebChromeClient() {
        }

        public void onProgressChanged(WebView webView, int n) {
            if (浏览框.this.$event_internal_进度值改变 != null) {
                浏览框.this.$event_internal_进度值改变.进度值改变(n);
            }
            if (n == 100) {
                浏览框.this.mProgressBar.setVisibility(8);
            } else {
                if (浏览框.this.mProgressBar.getVisibility() == 8) {
                    浏览框.this.mProgressBar.setVisibility(0);
                }
                浏览框.this.mProgressBar.setProgress(n);
            }
            super.onProgressChanged(webView, n);
        }

        public void onReceivedTitle(WebView webView, String string) {
            super.onReceivedTitle(webView, string);
            if (浏览框.this.$event_internal_接收到标题 != null) {
                浏览框.this.$event_internal_接收到标题.接收到标题(string);
            }
        }

        public void onShowCustomView(View view, WebChromeClient.CustomViewCallback customViewCallback) {
            if (浏览框.this.mView != null) {
                this.onHideCustomView();
                return;
            }
            浏览框.this.showVideo(view, customViewCallback);
        }

        public void onHideCustomView() {
            if (浏览框.this.mView == null) {
                return;
            }
            浏览框.this.closeVideo();
            浏览框.this.getView().setVisibility(0);
        }

        public View getVideoLoadingProgressView() {
            return super.getVideoLoadingProgressView();
        }

        public void openFileChooser(ValueCallback<Uri> valueCallback, String string, String string2) {
            if (浏览框.this.窗口环境 instanceof Activity) {
                Activity activity = (Activity) 浏览框.this.窗口环境;
                message_upload = valueCallback;
                Intent intent = new Intent("android.intent.action.GET_CONTENT");
                intent.addCategory("android.intent.category.OPENABLE");
                intent.setType("*/*");
                activity.startActivityForResult(Intent.createChooser((Intent) intent, (CharSequence) "文件选择"), 5173);
            }
        }

        @TargetApi(value = 21)
        public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> valueCallback, WebChromeClient.FileChooserParams fileChooserParams) {
            if (浏览框.this.窗口环境 instanceof Activity) {
                Activity activity = (Activity) 浏览框.this.窗口环境;
                if (message_upload2 != null) {
                    message_upload2.onReceiveValue(null);
                    message_upload2 = null;
                }
                message_upload2 = valueCallback;
                Intent intent = fileChooserParams.createIntent();
                try {
                    activity.startActivityForResult(intent, 5174);
                    return true;
                } catch (ActivityNotFoundException activityNotFoundException) {
                    message_upload2 = null;
                }
            }
            return false;
        }
    }

    private class MyWebViewClient
            extends WebViewClient {
        private MyWebViewClient() {
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String string) {
            if (string.startsWith("http") || string.startsWith("file")) {
                webView.loadUrl(string);
                return true;
            }
            if (浏览框.this.$event_internal_拦截到Uri != null) {
                浏览框.this.$event_internal_拦截到Uri.拦截到Uri(string);
            }
            return true;
        }

        public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
            sslErrorHandler.proceed();
        }

        public WebResourceResponse shouldInterceptRequest(WebView webView, String string) {
            if (浏览框.this.$event_internal_拦截到请求 != null) {
                浏览框.this.$event_internal_拦截到请求.拦截到请求(string);
            }
            return super.shouldInterceptRequest(webView, string);
        }

        public WebResourceResponse shouldInterceptRequest(WebView webView, WebResourceRequest webResourceRequest) {
            if (Build.VERSION.SDK_INT >= 21) {
                String string = webResourceRequest.getUrl().toString();
                if (浏览框.this.$event_internal_拦截到请求 != null) {
                    浏览框.this.$event_internal_拦截到请求.拦截到请求(string);
                }
            }
            return super.shouldInterceptRequest(webView, webResourceRequest);
        }

        public void onPageStarted(WebView webView, String string, Bitmap bitmap) {
            if (浏览框.this.$event_internal_网页开始加载 != null) {
                浏览框.this.$event_internal_网页开始加载.网页开始加载(string);
            }
        }

        public void onPageFinished(WebView webView, String string) {
            if (浏览框.this.$event_internal_网页加载完成 != null) {
                浏览框.this.$event_internal_网页加载完成.网页加载完成(string);
            }
        }
    }
}

