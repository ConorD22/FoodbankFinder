package com.example.foodbankfinder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;

public class InformationSites extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_sites);

        Intent i = getIntent();
        String webMessage = i.getStringExtra(InformationPage.webMessage);

        WebView webView = new WebView(this);
        setContentView(webView);
        webView.loadUrl(webMessage);
    }
}