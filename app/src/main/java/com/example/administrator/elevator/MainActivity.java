package com.example.administrator.elevator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class MainActivity extends AppCompatActivity {

    WebView web_view_left;
    WebView web_view_right;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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


        web_view_right.loadUrl("file:///android_asset/bending_elevator/index.html");



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
}
