package com.nathan.sihijau;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ThirdActivity extends AppCompatActivity {

    WebView myWebView, ukridaWebView;

    String mapPath = "https://www.google.com/maps/place/Universitas+Kristen+Krida+Wacana,+Kampus+I/@-6.1725326,106.7885296,21z/data=!4m5!3m4!1s0x2e69f6595544bf4d:0x474545535294fa8a!8m2!3d-6.172516!4d106.7884645";
    String mapPath1 = "https://goo.gl/maps/t9RkPf97Vu2EFmLg8";
    DrawerLayout drawerLayout;
    ImageView btMenu;
    RecyclerView recyclerView;
    static ArrayList<String> arrayList = new ArrayList<>();
    MainAdapter adapter;

    public static void closeDrawer(DrawerLayout drawerLayout){
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        drawerLayout = findViewById(R.id.drawer_layout);
        btMenu = findViewById(R.id.btmenu);
        recyclerView = findViewById(R.id.recycler_view);

        arrayList.clear();

        arrayList.add("Dashboard");
        arrayList.add("Account");
        arrayList.add("Konten Page");
        arrayList.add("Contact List");
        arrayList.add("About");
        arrayList.add("Maps");
        arrayList.add("Tips");
        arrayList.add("FAQ");
        arrayList.add("Keluar");

        adapter = new MainAdapter(this,arrayList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        btMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        myWebView = (WebView)findViewById(R.id.mapview);
        myWebView.getSettings().setJavaScriptEnabled(true);
        myWebView.setWebViewClient(new WebViewClient());

        myWebView.loadUrl(mapPath);

        ukridaWebView = (WebView)findViewById(R.id.mapview1);
        ukridaWebView.getSettings().setJavaScriptEnabled(true);
        ukridaWebView.setWebViewClient(new WebViewClient());

        ukridaWebView.loadUrl(mapPath1);

        myWebView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });

        ukridaWebView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
    }


    @Override
    protected void onPause() {
        super.onPause();
        closeDrawer(drawerLayout);
    }


}