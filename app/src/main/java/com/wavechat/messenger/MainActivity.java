package com.wavechat.messenger;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.webkit.*;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

public class MainActivity extends Activity {

    private WebView webView;
    private ProgressBar progressBar;

    // Основной URL мессенджера
    private static final String APP_URL = "https://nnoxtgram.mooo.com";
    private static final String FALLBACK_URL = "http://144.31.244.143";

    @SuppressLint({"SetJavaScriptEnabled", "NewApi"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Полноэкранный режим с тёмным статус-баром
        getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        );

        // Создаём layout программно
        RelativeLayout layout = new RelativeLayout(this);
        layout.setBackgroundColor(0xFF0d0d14); // цвет фона приложения

        // ProgressBar (загрузочная полоса)
        progressBar = new ProgressBar(this, null, android.R.attr.progressBarStyleHorizontal);
        progressBar.setMax(100);
        progressBar.setIndeterminate(false);
        progressBar.setProgressTintList(android.content.res.ColorStateList.valueOf(0xFF5b6af5));
        progressBar.setBackgroundColor(0xFF0d0d14);
        RelativeLayout.LayoutParams pbParams = new RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.MATCH_PARENT, 6
        );
        pbParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        progressBar.setLayoutParams(pbParams);

        // WebView
        webView = new WebView(this);
        RelativeLayout.LayoutParams wvParams = new RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.MATCH_PARENT,
            RelativeLayout.LayoutParams.MATCH_PARENT
        );
        webView.setLayoutParams(wvParams);

        layout.addView(webView);
        layout.addView(progressBar);

        setContentView(layout);

        setupWebView();
        webView.loadUrl(APP_URL);
    }

    @SuppressLint({"SetJavaScriptEnabled", "NewApi"})
    private void setupWebView() {
        WebSettings settings = webView.getSettings();

        // Основные настройки
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setDatabaseEnabled(true);
        settings.setAppCacheEnabled(true);
        settings.setCacheMode(WebSettings.LOAD_DEFAULT);

        // WebSocket поддержка
        settings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);

        // Медиа
        settings.setMediaPlaybackRequiresUserGesture(false);
        settings.setAllowFileAccess(true);
        settings.setAllowContentAccess(true);

        // Зум и скролл
        settings.setSupportZoom(false);
        settings.setBuiltInZoomControls(false);
        settings.setDisplayZoomControls(false);

        // Viewport
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);

        // User Agent - представляемся мобильным браузером
        settings.setUserAgentString(
            "Mozilla/5.0 (Linux; Android 13; Pixel 7) " +
            "AppleWebKit/537.36 (KHTML, like Gecko) " +
            "Chrome/120.0.0.0 Mobile Safari/537.36 WaveChat/2.0"
        );

        // WebViewClient
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                progressBar.setVisibility(View.VISIBLE);
                progressBar.setProgress(0);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onReceivedError(WebView view, int errorCode,
                                        String description, String failingUrl) {
                // Если основной URL не доступен - пробуем резервный
                if (failingUrl != null && failingUrl.equals(APP_URL)) {
                    webView.loadUrl(FALLBACK_URL);
                } else {
                    showOfflinePage();
                }
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                String url = request.getUrl().toString();
                // Открываем внешние ссылки в браузере
                if (!url.startsWith("http://144.31.244.143") &&
                    !url.startsWith("https://nnoxtgram.mooo.com")) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(intent);
                    return true;
                }
                return false;
            }
        });

        // WebChromeClient для уведомлений и медиа
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                progressBar.setProgress(newProgress);
                if (newProgress == 100) {
                    progressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPermissionRequest(PermissionRequest request) {
                request.grant(request.getResources());
            }

            @Override
            public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
                return true;
            }

            // Поддержка уведомлений
            @Override
            public void onGeolocationPermissionsShowPrompt(
                String origin, GeolocationPermissions.Callback callback) {
                callback.invoke(origin, true, false);
            }
        });

        // Включаем отладку WebView (можно убрать в продакшене)
        WebView.setWebContentsDebuggingEnabled(false);
    }

    private void showOfflinePage() {
        String offlinePage = "<!DOCTYPE html><html><head>" +
            "<meta charset='UTF-8'>" +
            "<meta name='viewport' content='width=device-width,initial-scale=1'>" +
            "<style>" +
            "body{background:#0d0d14;color:#f0f0fa;font-family:sans-serif;" +
            "display:flex;align-items:center;justify-content:center;height:100vh;margin:0;}" +
            ".box{text-align:center;padding:40px;}" +
            ".icon{font-size:64px;margin-bottom:20px;}" +
            "h2{color:#5b6af5;margin-bottom:12px;font-size:22px;}" +
            "p{color:#9898b8;font-size:15px;line-height:1.5;}" +
            ".btn{margin-top:24px;padding:14px 32px;background:#5b6af5;color:#fff;" +
            "border:none;border-radius:14px;font-size:16px;cursor:pointer;" +
            "font-weight:700;}" +
            "</style></head><body>" +
            "<div class='box'>" +
            "<div class='icon'>📡</div>" +
            "<h2>Нет соединения</h2>" +
            "<p>Проверьте интернет-соединение<br>и попробуйте снова</p>" +
            "<button class='btn' onclick='location.reload()'>Обновить</button>" +
            "</div></body></html>";
        webView.loadData(offlinePage, "text/html", "UTF-8");
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (webView.canGoBack()) {
                webView.goBack();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onPause() {
        super.onPause();
        webView.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        webView.onResume();
        webView.reload();
    }

    @Override
    protected void onDestroy() {
        if (webView != null) {
            webView.destroy();
        }
        super.onDestroy();
    }
}
