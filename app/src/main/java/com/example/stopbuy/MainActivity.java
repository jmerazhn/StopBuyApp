package com.example.stopbuy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private final int MULTIPLE_PERMISSIONS=10;
    String[] permisions = new String[]{
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.READ_EXTERNAL_STORAGE
    };

    private WebView myWebView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Theme_StopBuy);
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        //getWindow().setFlags(1024, 1024);
        setContentView(R.layout.activity_main);

        myWebView=findViewById(R.id.webview);
        WebSettings webSettings=myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setAllowFileAccess(true);
        myWebView.setWebChromeClient(new WebChromeClient());
        myWebView.setVerticalScrollBarEnabled(false);
        myWebView.loadUrl("http://190.97.188.2/stopbuy/consultarprecio");

        checkPermision();
    }

    @Override
    public void onBackPressed() {
        this.myWebView.canGoBack();
    }

    private boolean checkPermision(){
        int result;
        List<String> listPermisionNeeded = new ArrayList<>();
        for (String p:permisions){
            result= ContextCompat.checkSelfPermission(this,p);
            if(result != PackageManager.PERMISSION_GRANTED){
                listPermisionNeeded.add(p);
            }
        }
        if(!listPermisionNeeded.isEmpty()){
            ActivityCompat.requestPermissions(this, listPermisionNeeded.toArray(new String[listPermisionNeeded.size()]), MULTIPLE_PERMISSIONS);
            return false;
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode==MULTIPLE_PERMISSIONS){
            if(grantResults.length>0){
                for (int i=0;i<permissions.length;i++){
                    if(permissions[i].equals(Manifest.permission.READ_PHONE_STATE)){
                        if(grantResults[i]==PackageManager.PERMISSION_GRANTED){

                        }
                    } else if (permissions[i].equals(Manifest.permission.READ_EXTERNAL_STORAGE)) {
                        if(grantResults[i]==PackageManager.PERMISSION_GRANTED){

                        }
                    }
                }
            }
        }
    }
}