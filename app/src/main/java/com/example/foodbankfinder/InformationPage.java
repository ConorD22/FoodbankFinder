package com.example.foodbankfinder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class InformationPage extends AppCompatActivity {

    Button houseOfCommons;
    Button houseOfCommons2;
    Button trussel;
    Button bbc;

    String houseCom = "https://commonslibrary.parliament.uk/research-briefings/cbp-8585/";
    String houseCom2 = "https://commonslibrary.parliament.uk/research-briefings/cbp-9209/";
    String trus = "https://www.trusselltrust.org/news-and-blog/latest-stats/end-year-stats/";
    String bc = "https://www.bbc.co.uk/news/uk-65050920";

    public static final String webMessage = " ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_page);

        houseOfCommons = (Button) findViewById(R.id.btn_houseofcom);
        houseOfCommons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(InformationPage.this, InformationSites.class);
                i.putExtra(webMessage, houseCom);
                startActivity(i);
            }
        });

        houseOfCommons2 = (Button) findViewById(R.id.btn_houseofcom2);
        houseOfCommons2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(InformationPage.this, InformationSites.class);
                i.putExtra(webMessage, houseCom2);
                startActivity(i);
            }
        });

        trussel = (Button) findViewById(R.id.btn_trussel);
        trussel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(InformationPage.this, InformationSites.class);
                i.putExtra(webMessage, trus);
                startActivity(i);
            }
        });

        bbc = (Button) findViewById(R.id.btn_bbc);
        bbc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(InformationPage.this, InformationSites.class);
                i.putExtra(webMessage, bc);
                startActivity(i);
            }
        });
    }
}