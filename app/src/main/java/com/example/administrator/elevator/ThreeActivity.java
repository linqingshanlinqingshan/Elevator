package com.example.administrator.elevator;


import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;

import com.tencent.smtt.sdk.CookieSyncManager;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;


public class ThreeActivity extends BaseActivity {

    LinearLayout llt_container;
    LinearLayout llt_left;

    WebView web_view_tencent;
    android.webkit.WebView web_view_left;

    String web = "file:///android_asset/web/index.html";            //

    int width;
    int height;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three);

        llt_left = findViewById(R.id.llt_left);
        llt_container = findViewById(R.id.llt_container);
//        web_view_tencent = findViewById(R.id.web_view_tencent);


        ViewTreeObserver vto = llt_container.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {

                llt_container.getViewTreeObserver().removeGlobalOnLayoutListener(this);

                width = llt_container.getWidth();

                height = llt_container.getHeight();

                Log.d("tag", "----->width=" + width);
                Log.d("tag", "----->height=" + height);


                showWebView();
            }
        });


    }

    private void showWebView() {

        //////////////////////////////////////////////////////////////////////////////////////////// 原生

        web_view_left = new android.webkit.WebView(getApplicationContext());

        LinearLayout.LayoutParams layoutParams1 = new LinearLayout.LayoutParams(width, height);
        web_view_left.setLayoutParams(layoutParams1);

        llt_left.addView(web_view_left);

        android.webkit.WebSettings webSettings_left = web_view_left.getSettings();
        //设置WebView属性，能够执行Javascript脚本
        webSettings_left.setJavaScriptEnabled(true);
        //设置可以访问文件
        webSettings_left.setAllowFileAccess(true);
        //设置支持缩放
        webSettings_left.setBuiltInZoomControls(true);

        webSettings_left.setAllowUniversalAccessFromFileURLs(true);

        web_view_left.loadUrl(web);

        web_view_left.setWebViewClient(new android.webkit.WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(android.webkit.WebView view, String url) {
//                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageStarted(android.webkit.WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
//                leftDialog.show();
            }

            @Override
            public void onPageFinished(android.webkit.WebView view, String url) {
                super.onPageFinished(view, url);
//                leftDialog.dismiss();
//                leftDialog = null;
            }
        });

        //////////////////////////////////////////////////////////////////////////////////////////// 原生

        //////////////////////////////////////////////////////////////////////////////////////////// X5

        web_view_tencent = new WebView(getApplicationContext());

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(width, height);
        web_view_tencent.setLayoutParams(layoutParams);

        llt_container.addView(web_view_tencent);

        WebSettings webSettings_tencent = web_view_tencent.getSettings();
        //设置WebView属性，能够执行Javascript脚本
        webSettings_tencent.setJavaScriptEnabled(true);
        //设置可以访问文件
        webSettings_tencent.setAllowFileAccess(true);
        //设置支持缩放
        webSettings_tencent.setBuiltInZoomControls(true);

        webSettings_tencent.setAllowUniversalAccessFromFileURLs(true);


        web_view_tencent.loadUrl(web);

        web_view_tencent.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView webView, String s) {
//              view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageStarted(WebView webView, String s, Bitmap bitmap) {
                super.onPageStarted(webView, s, bitmap);
//                straightDialog.show();
            }

            @Override
            public void onPageFinished(WebView webView, String s) {
                super.onPageFinished(webView, s);
//                straightDialog.dismiss();
//                straightDialog = null;
            }

            //            @Override
//            public boolean shouldOverrideUrlLoading(android.webkit.WebView view, String url) {
////                    view.loadUrl(url);
//                return true;
//            }
//
//            @Override
//            public void onPageStarted(android.webkit.WebView view, String url, Bitmap favicon) {
//                super.onPageStarted(view, url, favicon);
////                straightDialog.show();
//            }
//
//            @Override
//            public void onPageFinished(android.webkit.WebView view, String url) {
//                super.onPageFinished(view, url);
////                straightDialog.dismiss();
////                straightDialog = null;
//            }
        });

        //////////////////////////////////////////////////////////////////////////////////////////// X5



        CookieSyncManager.createInstance(this);
        CookieSyncManager.getInstance().sync();
    }

    @Override
    public void onClick(View view) {

    }
}
