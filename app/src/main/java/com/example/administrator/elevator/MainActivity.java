package com.example.administrator.elevator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    WebView web_view_left;
    WebView web_view_right;

    TextView tv_left;
    TextView tv_right;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_left = findViewById(R.id.tv_left);
        tv_right = findViewById(R.id.tv_right);

        tv_left.setOnClickListener(this);
        tv_right.setOnClickListener(this);

        tv_left.setTextColor(getResources().getColor(R.color.color_Black));
        tv_right.setTextColor(getResources().getColor(R.color.color_Gray));

        web_view_left = findViewById(R.id.web_view_left);
        web_view_right = findViewById(R.id.web_view_right);

        WebSettings webSettings_left = web_view_left.getSettings();
        //设置WebView属性，能够执行Javascript脚本
        webSettings_left.setJavaScriptEnabled(true);
        //设置可以访问文件
        webSettings_left.setAllowFileAccess(true);
        //设置支持缩放
        webSettings_left.setBuiltInZoomControls(true);

        webSettings_left.setAllowUniversalAccessFromFileURLs(true);

        web_view_left.loadUrl("file:///android_asset/bending_elevator/index.html");


        ////////////////////////////////////////////////////////////////////////////////////////////


    }

    boolean isShow = false;


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_left:

                tv_left.setTextColor(getResources().getColor(R.color.color_Black));
                tv_right.setTextColor(getResources().getColor(R.color.color_Gray));

                web_view_left.setVisibility(View.VISIBLE);
                web_view_right.setVisibility(View.GONE);

                break;
            case R.id.tv_right:

                tv_left.setTextColor(getResources().getColor(R.color.color_Gray));
                tv_right.setTextColor(getResources().getColor(R.color.color_Black));

                web_view_left.setVisibility(View.GONE);
                web_view_right.setVisibility(View.VISIBLE);

                if (!isShow) {
                    isShow = !isShow;

                    WebSettings webSettings_right = web_view_right.getSettings();
                    //设置WebView属性，能够执行Javascript脚本
                    webSettings_right.setJavaScriptEnabled(true);
                    //设置可以访问文件
                    webSettings_right.setAllowFileAccess(true);
                    //设置支持缩放
                    webSettings_right.setBuiltInZoomControls(true);

                    webSettings_right.setAllowUniversalAccessFromFileURLs(true);


                    web_view_right.loadUrl("file:///android_asset/straight_elevator/index.html");

                }


                break;
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

            web_view_left.removeAllViews();
            web_view_left.destroy();
        }

        if (web_view_right != null) {

            web_view_right.removeAllViews();
            web_view_right.destroy();
        }

        finish();
    }
}