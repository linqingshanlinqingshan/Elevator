package com.example.administrator.elevator;


import android.app.ProgressDialog;
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


public class TwoActivity extends BaseActivity {


    WebView web_view_side;
    WebView web_view_straight;

    TextView tv_side;
    TextView tv_straight;

    LinearLayout llt_side;
    LinearLayout llt_straight;

    FrameLayout flt_container;

    String straight_platform = "file:///android_asset/straight_platform/index.html";    //直平台
    String side_platform = "file:///android_asset/side_platform/index.html";            //侧平台


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);


        flt_container = findViewById(R.id.flt_container);

        llt_side = findViewById(R.id.llt_side);
        llt_straight = findViewById(R.id.llt_straight);


        tv_side = findViewById(R.id.tv_side);
        tv_straight = findViewById(R.id.tv_straight);

//        tv_left.setOnClickListener(this);
//        tv_right.setOnClickListener(this);
        tv_side.setOnClickListener(this);
        tv_straight.setOnClickListener(this);
//        tv_next.setOnClickListener(this);

//        tv_left.setTextColor(getResources().getColor(R.color.color_Black));
//        tv_right.setTextColor(getResources().getColor(R.color.color_Gray));
        tv_side.setTextColor(getResources().getColor(R.color.color_Black));
        tv_straight.setTextColor(getResources().getColor(R.color.color_Gray));


        web_view_side = new WebView(getApplicationContext());


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

                onSide();
            }
        });

    }


    int width;
    int height;


    boolean isShowSide = false;
    boolean isShowStraight = false;


    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.tv_side:

                onSide();

                break;
            case R.id.tv_straight:

                onStraight();

                break;

        }
    }


    ProgressDialog straightDialog;

    /**
     * 直平台
     */
    private void onStraight() {

        tv_straight.setTextColor(getResources().getColor(R.color.color_Black));
        tv_side.setTextColor(getResources().getColor(R.color.color_Gray));

        llt_side.setVisibility(View.GONE);
        llt_straight.setVisibility(View.VISIBLE);


        if (!isShowStraight) {
            isShowStraight = !isShowStraight;

            straightDialog = new ProgressDialog(this);
            straightDialog.setTitle("正在加载");
            straightDialog.setMessage("请稍后...");
            straightDialog.setIndeterminate(true);
            straightDialog.setCancelable(false);

            web_view_straight = new WebView(getApplicationContext());


            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(width, height);
            web_view_straight.setLayoutParams(layoutParams);

            llt_straight.addView(web_view_straight);

            WebSettings webSettings_straight = web_view_straight.getSettings();
            //设置WebView属性，能够执行Javascript脚本
            webSettings_straight.setJavaScriptEnabled(true);
            //设置可以访问文件
            webSettings_straight.setAllowFileAccess(true);
            //设置支持缩放
            webSettings_straight.setBuiltInZoomControls(true);

            webSettings_straight.setAllowUniversalAccessFromFileURLs(true);

            web_view_straight.loadUrl(straight_platform);

            web_view_straight.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                    view.loadUrl(url);
                    return true;
                }

                @Override
                public void onPageStarted(WebView view, String url, Bitmap favicon) {
                    super.onPageStarted(view, url, favicon);
                    straightDialog.show();
                }

                @Override
                public void onPageFinished(WebView view, String url) {
                    super.onPageFinished(view, url);
                    straightDialog.dismiss();
                    straightDialog = null;
                }
            });

        }
    }


    ProgressDialog sideDialog;

    /**
     * 侧平台
     */
    private void onSide() {

        tv_side.setTextColor(getResources().getColor(R.color.color_Black));
        tv_straight.setTextColor(getResources().getColor(R.color.color_Gray));

        llt_side.setVisibility(View.VISIBLE);
        llt_straight.setVisibility(View.GONE);


        if (!isShowSide) {
            isShowSide = !isShowSide;

            sideDialog = new ProgressDialog(this);
            sideDialog.setTitle("正在加载");
            sideDialog.setMessage("请稍后...");
            sideDialog.setIndeterminate(true);
            sideDialog.setCancelable(false);

            web_view_side = new WebView(getApplicationContext());


            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(width, height);
            web_view_side.setLayoutParams(layoutParams);

            llt_side.addView(web_view_side);

            WebSettings webSettings_side = web_view_side.getSettings();
            //设置WebView属性，能够执行Javascript脚本
            webSettings_side.setJavaScriptEnabled(true);
            //设置可以访问文件
            webSettings_side.setAllowFileAccess(true);
            //设置支持缩放
            webSettings_side.setBuiltInZoomControls(true);

            webSettings_side.setAllowUniversalAccessFromFileURLs(true);

            web_view_side.loadUrl(side_platform);

            web_view_side.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                    view.loadUrl(url);
                    return true;
                }

                @Override
                public void onPageStarted(WebView view, String url, Bitmap favicon) {
                    super.onPageStarted(view, url, favicon);
                    sideDialog.show();
                }

                @Override
                public void onPageFinished(WebView view, String url) {
                    super.onPageFinished(view, url);
                    sideDialog.dismiss();
                    sideDialog = null;
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

        if (web_view_side != null) {

            llt_side.removeView(web_view_side);

            web_view_side.removeAllViews();
            web_view_side.destroy();
            web_view_side = null;
        }

        if (web_view_straight != null) {

            llt_straight.removeView(web_view_straight);

            web_view_straight.removeAllViews();
            web_view_straight.destroy();
            web_view_straight = null;
        }

        finish();
    }
}
