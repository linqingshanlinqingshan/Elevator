package com.example.administrator.elevator;


import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
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


public class MainActivity extends BaseActivity {


    WebView web_view_left;
    WebView web_view_right;

//    WebView web_view_side;
//    WebView web_view_straight;

    TextView tv_next;

    TextView tv_left;
    TextView tv_right;

//    TextView tv_side;
//    TextView tv_straight;

    LinearLayout llt_left;
    LinearLayout llt_right;

//    LinearLayout llt_side;
//    LinearLayout llt_straight;

    FrameLayout flt_container;

    String url_bending_elevator = "file:///android_asset/bending_elevator/index.html";  //转角
    String straight_elevator = "file:///android_asset/straight_elevator/index.html";    //直角
    String straight_platform = "file:///android_asset/straight_platform/index.html";    //直平台
    String side_platform = "file:///android_asset/side_platform/index.html";            //侧平台


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        flt_container = findViewById(R.id.flt_container);

        llt_left = findViewById(R.id.llt_left);
        llt_right = findViewById(R.id.llt_right);

//        llt_side = findViewById(R.id.llt_side);
//        llt_straight = findViewById(R.id.llt_straight);

        tv_next = findViewById(R.id.tv_next);

        tv_left = findViewById(R.id.tv_left);
        tv_right = findViewById(R.id.tv_right);

//        tv_side = findViewById(R.id.tv_side);
//        tv_straight = findViewById(R.id.tv_straight);

        tv_left.setOnClickListener(this);
        tv_right.setOnClickListener(this);
//        tv_side.setOnClickListener(this);
//        tv_straight.setOnClickListener(this);
        tv_next.setOnClickListener(this);

        tv_left.setTextColor(getResources().getColor(R.color.color_Black));
        tv_right.setTextColor(getResources().getColor(R.color.color_Gray));
//        tv_side.setTextColor(getResources().getColor(R.color.color_Gray));
//        tv_straight.setTextColor(getResources().getColor(R.color.color_Gray));


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

                startActivity(new Intent(MainActivity.this, TwoActivity.class));

                MainActivity.this.finish();

//                showWebView();
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
//    boolean isShowSide = false;
//    boolean isShowStraight = false;


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_left:

//                onLeft();

                break;
            case R.id.tv_right:

//                onRight();

                break;

            case R.id.tv_next:

                startActivity(new Intent(this, TwoActivity.class));

                break;
        }
    }


//    ProgressDialog straightDialog;
//
//    /**
//     * 直平台
//     */
//    private void onStraight() {
//
//        tv_straight.setTextColor(getResources().getColor(R.color.color_Black));
//        tv_side.setTextColor(getResources().getColor(R.color.color_Gray));
//        tv_left.setTextColor(getResources().getColor(R.color.color_Gray));
//        tv_right.setTextColor(getResources().getColor(R.color.color_Gray));
//
//        llt_left.setVisibility(View.GONE);
//        llt_right.setVisibility(View.GONE);
//        llt_side.setVisibility(View.GONE);
//        llt_straight.setVisibility(View.VISIBLE);
//
//
//        if (!isShowStraight) {
//            isShowStraight = !isShowStraight;
//
//            straightDialog = new ProgressDialog(this);
//            straightDialog.setTitle("正在加载");
//            straightDialog.setMessage("请稍后...");
//            straightDialog.setIndeterminate(true);
//            straightDialog.setCancelable(false);
//
//            web_view_straight = new WebView(getApplicationContext());
//
//
//            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(width, height);
//            web_view_straight.setLayoutParams(layoutParams);
//
//            llt_straight.addView(web_view_straight);
//
//            WebSettings webSettings_straight = web_view_straight.getSettings();
//            //设置WebView属性，能够执行Javascript脚本
//            webSettings_straight.setJavaScriptEnabled(true);
//            //设置可以访问文件
//            webSettings_straight.setAllowFileAccess(true);
//            //设置支持缩放
//            webSettings_straight.setBuiltInZoomControls(true);
//
//            webSettings_straight.setAllowUniversalAccessFromFileURLs(true);
//
//            web_view_straight.loadUrl(straight_platform);
//
//            web_view_straight.setWebViewClient(new WebViewClient() {
//                @Override
//                public boolean shouldOverrideUrlLoading(WebView view, String url) {
////                    view.loadUrl(url);
//                    return true;
//                }
//
//                @Override
//                public void onPageStarted(WebView view, String url, Bitmap favicon) {
//                    super.onPageStarted(view, url, favicon);
//                    straightDialog.show();
//                }
//
//                @Override
//                public void onPageFinished(WebView view, String url) {
//                    super.onPageFinished(view, url);
//                    straightDialog.dismiss();
//                    straightDialog = null;
//                }
//            });
//
//        }
//    }
//
//
//    ProgressDialog sideDialog;
//
//    /**
//     * 侧平台
//     */
//    private void onSide() {
//
//        tv_side.setTextColor(getResources().getColor(R.color.color_Black));
//        tv_left.setTextColor(getResources().getColor(R.color.color_Gray));
//        tv_right.setTextColor(getResources().getColor(R.color.color_Gray));
//        tv_straight.setTextColor(getResources().getColor(R.color.color_Gray));
//
//        llt_left.setVisibility(View.GONE);
//        llt_right.setVisibility(View.GONE);
//        llt_side.setVisibility(View.VISIBLE);
//        llt_straight.setVisibility(View.GONE);
//
//
//        if (!isShowSide) {
//            isShowSide = !isShowSide;
//
//            sideDialog = new ProgressDialog(this);
//            sideDialog.setTitle("正在加载");
//            sideDialog.setMessage("请稍后...");
//            sideDialog.setIndeterminate(true);
//            sideDialog.setCancelable(false);
//
//            web_view_side = new WebView(getApplicationContext());
//
//
//            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(width, height);
//            web_view_side.setLayoutParams(layoutParams);
//
//            llt_side.addView(web_view_side);
//
//            WebSettings webSettings_side = web_view_side.getSettings();
//            //设置WebView属性，能够执行Javascript脚本
//            webSettings_side.setJavaScriptEnabled(true);
//            //设置可以访问文件
//            webSettings_side.setAllowFileAccess(true);
//            //设置支持缩放
//            webSettings_side.setBuiltInZoomControls(true);
//
//            webSettings_side.setAllowUniversalAccessFromFileURLs(true);
//
//            web_view_side.loadUrl(side_platform);
//
//            web_view_side.setWebViewClient(new WebViewClient() {
//                @Override
//                public boolean shouldOverrideUrlLoading(WebView view, String url) {
////                    view.loadUrl(url);
//                    return true;
//                }
//
//                @Override
//                public void onPageStarted(WebView view, String url, Bitmap favicon) {
//                    super.onPageStarted(view, url, favicon);
//                    sideDialog.show();
//                }
//
//                @Override
//                public void onPageFinished(WebView view, String url) {
//                    super.onPageFinished(view, url);
//                    sideDialog.dismiss();
//                    sideDialog = null;
//                }
//            });
//
//        }
//    }


    /**
     * 转角
     */
    private void onLeft() {

        tv_left.setTextColor(getResources().getColor(R.color.color_Black));
        tv_right.setTextColor(getResources().getColor(R.color.color_Gray));
//        tv_side.setTextColor(getResources().getColor(R.color.color_Gray));
//        tv_straight.setTextColor(getResources().getColor(R.color.color_Gray));

        llt_left.setVisibility(View.VISIBLE);
        llt_right.setVisibility(View.GONE);
//        llt_side.setVisibility(View.GONE);
//        llt_straight.setVisibility(View.GONE);


    }


    ProgressDialog rightDialog;

    /**
     * 直角
     */
    private void onRight() {

        tv_right.setTextColor(getResources().getColor(R.color.color_Black));
        tv_left.setTextColor(getResources().getColor(R.color.color_Gray));
//        tv_side.setTextColor(getResources().getColor(R.color.color_Gray));
//        tv_straight.setTextColor(getResources().getColor(R.color.color_Gray));

        llt_left.setVisibility(View.GONE);
//        llt_side.setVisibility(View.GONE);
//        llt_straight.setVisibility(View.GONE);
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
            web_view_left = null;
        }

        if (web_view_right != null) {

            llt_right.removeView(web_view_right);

            web_view_right.removeAllViews();
            web_view_right.destroy();
            web_view_right = null;
        }

//        if (web_view_side != null) {
//
//            llt_side.removeView(web_view_side);
//
//            web_view_side.removeAllViews();
//            web_view_side.destroy();
//        }
//
//        if (web_view_straight != null) {
//
//            llt_straight.removeView(web_view_straight);
//
//            web_view_straight.removeAllViews();
//            web_view_straight.destroy();
//        }

        finish();
    }
}