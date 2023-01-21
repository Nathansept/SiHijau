package com.nathan.sihijau;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class KontenSampah extends AppCompatActivity implements View.OnClickListener{
    private Button BtnCair, BtnManusia, BtnNuklir;
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
        setContentView(R.layout.activity_konten_sampah);
        BtnCair = (Button) findViewById(R.id.button);
        BtnCair.setOnClickListener(this);
        BtnManusia = (Button) findViewById(R.id.button1);
        BtnManusia.setOnClickListener(this);
        BtnNuklir = (Button) findViewById(R.id.button2);
        BtnNuklir.setOnClickListener(this);
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

        adapter = new MainAdapter(this, arrayList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        ImageView imageView;
        imageView = (ImageView) findViewById(R.id.imageView5);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),
                        ImageActivity.class));
            }
        });


        btMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }
        public void onClick(View view){
            switch (view.getId()) {
                case R.id.button:
                    startActivity(new Intent(this, KontenSampahCair.class));
                    break;
                case R.id.button1:
                    startActivity(new Intent(this, KontenSampahManusia.class));
                    break;
                case R.id.button2:
                    startActivity(new Intent(this, KontenSampahRadioaktif.class));
                    break;
            }
        }



    @Override
    protected void onPause() {
        super.onPause();
        closeDrawer(drawerLayout);
    }
}