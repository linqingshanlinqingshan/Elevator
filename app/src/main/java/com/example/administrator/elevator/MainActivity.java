package com.example.administrator.elevator;


import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import android.view.ViewTreeObserver;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    WebView web_view_left;
    WebView web_view_right;

    TextView tv_left;
    TextView tv_right;

    LinearLayout llt_left;
    LinearLayout llt_right;

    FrameLayout flt_container;

//    String url_bending_elevator = "file:///android_asset/bending_elevator/index.html";  //转角
    String url_bending_elevator = "https://baijiahao.baidu.com/s?id=1625419997422125773&wfr=spider&for=pc";  //转角
//    String straight_elevator = "file:///android_asset/straight_elevator/index.html";    //直角
    String straight_elevator = "https://baijiahao.baidu.com/s?id=1621177319452544751&wfr=spider&for=pc";    //直角


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        flt_container = findViewById(R.id.flt_container);

        llt_left = findViewById(R.id.llt_left);
        llt_right = findViewById(R.id.llt_right);

        tv_left = findViewById(R.id.tv_left);
        tv_right = findViewById(R.id.tv_right);

        tv_left.setOnClickListener(this);
        tv_right.setOnClickListener(this);

        tv_left.setTextColor(getResources().getColor(R.color.color_Black));
        tv_right.setTextColor(getResources().getColor(R.color.color_Gray));

//        web_view_left = findViewById(R.id.web_view_left);
//        web_view_right = findViewById(R.id.web_view_right);

        web_view_left = new WebView(getApplicationContext());


        ////////////////////////////////////////////////////////////////////////////////////////////

        ViewTreeObserver vto = flt_container.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {

                flt_container.getViewTreeObserver().removeGlobalOnLayoutListener(this);

                width = flt_container.getWidth();

                height = flt_container.getHeight();

                Log.d("tag", "----->width=" + width);
                Log.d("tag", "----->height=" + height);

                showWebView();
            }
        });

    }


    int width;

    int height;

    ProgressDialog leftDialog;

    private void showWebView() {

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(width, height);
        web_view_left.setLayoutParams(layoutParams);

        llt_left.addView(web_view_left);

        WebSettings webSettings_left = web_view_left.getSettings();
        //设置WebView属性，能够执行Javascript脚本
        webSettings_left.setJavaScriptEnabled(true);
        //设置可以访问文件
        webSettings_left.setAllowFileAccess(true);
        //设置支持缩放
        webSettings_left.setBuiltInZoomControls(true);

        webSettings_left.setAllowUniversalAccessFromFileURLs(true);


        web_view_left.loadUrl(url_bending_elevator);


        leftDialog = new ProgressDialog(this);
        leftDialog.setTitle("正在加载");
        leftDialog.setMessage("请稍后...");
        leftDialog.setIndeterminate(true);
        leftDialog.setCancelable(false);


        web_view_left.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                leftDialog.show();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                leftDialog.dismiss();
                leftDialog = null;
            }
        });

    }

    boolean isShow = false;

    ProgressDialog rightDialog;


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_left:

                onLeft();

                break;
            case R.id.tv_right:

                onRight();

                break;
        }
    }


    private void onLeft() {

        tv_left.setTextColor(getResources().getColor(R.color.color_Black));
        tv_right.setTextColor(getResources().getColor(R.color.color_Gray));

//                web_view_left.setVisibility(View.VISIBLE);
//                web_view_right.setVisibility(View.GONE);

        llt_left.setVisibility(View.VISIBLE);
        llt_right.setVisibility(View.GONE);
    }


    private void onRight() {

        tv_left.setTextColor(getResources().getColor(R.color.color_Gray));
        tv_right.setTextColor(getResources().getColor(R.color.color_Black));

//                web_view_left.setVisibility(View.GONE);
//                web_view_right.setVisibility(View.VISIBLE);

        llt_left.setVisibility(View.GONE);
        llt_right.setVisibility(View.VISIBLE);

        if (!isShow) {
            isShow = !isShow;

            rightDialog = new ProgressDialog(this);
            rightDialog.setTitle("正在加载");
            rightDialog.setMessage("请稍后...");
            rightDialog.setIndeterminate(true);
            rightDialog.setCancelable(false);

            web_view_right = new WebView(getApplicationContext());


            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(width, height);
            web_view_right.setLayoutParams(layoutParams);

            llt_right.addView(web_view_right);

            WebSettings webSettings_right = web_view_right.getSettings();
            //设置WebView属性，能够执行Javascript脚本
            webSettings_right.setJavaScriptEnabled(true);
            //设置可以访问文件
            webSettings_right.setAllowFileAccess(true);
            //设置支持缩放
            webSettings_right.setBuiltInZoomControls(true);

            webSettings_right.setAllowUniversalAccessFromFileURLs(true);

            web_view_right.loadUrl(straight_elevator);

            web_view_right.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                    view.loadUrl(url);
                    return true;
                }

                @Override
                public void onPageStarted(WebView view, String url, Bitmap favicon) {
                    super.onPageStarted(view, url, favicon);
                    rightDialog.show();
                }

                @Override
                public void onPageFinished(WebView view, String url) {
                    super.onPageFinished(view, url);
                    rightDialog.dismiss();
                    rightDialog = null;
                }
            });

        }

    }


    @Override
    protected void onDestroy() {
        onFinish();
        super.onDestroy();
    }


    @Override
    public void onBackPressed() {
        onFinish();
    }

    private void onFinish() {

        if (web_view_left != null) {

            llt_left.removeView(web_view_left);

            web_view_left.removeAllViews();
            web_view_left.destroy();
        }

        if (web_view_right != null) {

            llt_right.removeView(web_view_right);

            web_view_right.removeAllViews();
            web_view_right.destroy();
        }

        finish();
    }
}