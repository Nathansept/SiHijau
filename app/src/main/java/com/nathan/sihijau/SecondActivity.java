package com.nathan.sihijau;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.firebase.auth.FirebaseAuth;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;


public class SecondActivity extends AppCompatActivity implements View.OnClickListener {
    private AdView mAdView;
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

    String url1 = "https://indonesia.go.id/assets/upload/headline//afbaddjbfjkda.jpeg";

    String url2 = "https://media.suara.com/pictures/970x544/2020/03/04/33774-ilustrasi-lautan-sampah-plastik.jpg";

    String url3 = "https://4.bp.blogspot.com/-6GCTi_pEPJY/XTF9gtfqPFI/AAAAAAAAIRY/2upg9NYgoJkQyZ2cRs8K1E_oARpj4O5ygCK4BGAYYCw/s1600/Sampah-Plastik-dan-Wajah-Indonesia-Kini.jpg";
    private FirebaseAuth mAuth;
    private CardView BtnSampah, BtnAkun, BtnAboutUs, BtnDatabase, BtnKeluar, BtnMaps, BtnFaQ, BtnTips;
    private AlertDialog.Builder alertdialog;
    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);

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

        btMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        BtnSampah = (CardView) findViewById(R.id.Sampah);
        BtnSampah.setOnClickListener(this);
        BtnAkun = (CardView) findViewById(R.id.Account);
        BtnAkun.setOnClickListener(this);
        BtnAboutUs = (CardView) findViewById(R.id.aboutUs);
        BtnAboutUs.setOnClickListener(this);
        BtnDatabase = (CardView) findViewById(R.id.Contacts);
        BtnDatabase.setOnClickListener(this);
        BtnMaps = (CardView) findViewById(R.id.Maps);
        BtnMaps.setOnClickListener(this);
        BtnKeluar = (CardView) findViewById(R.id.Keluar);
        BtnKeluar.setOnClickListener(this);
        BtnTips = (CardView) findViewById(R.id.tips);
        BtnTips.setOnClickListener(this);
        BtnFaQ = (CardView) findViewById(R.id.faq);
        BtnFaQ.setOnClickListener(this);
        // we are creating array list for storing our image urls.

        ArrayList<SliderData> sliderDataArrayList = new ArrayList<>();


        // initializing the slider view.

        SliderView sliderView = findViewById(R.id.slider);


        // adding the urls inside array list

        sliderDataArrayList.add(new SliderData(url1));

        sliderDataArrayList.add(new SliderData(url2));

        sliderDataArrayList.add(new SliderData(url3));


        // passing this array list inside our adapter class.

        SliderAdapter adapter = new SliderAdapter(this, sliderDataArrayList);


        // below method is used to set auto cycle direction in left to

        // right direction you can change according to requirement.

        sliderView.setAutoCycleDirection(SliderView.LAYOUT_DIRECTION_LTR);


        // below method is used to

        // setadapter to sliderview.

        sliderView.setSliderAdapter(adapter);


        // below method is use to set

        // scroll time in seconds.

        sliderView.setScrollTimeInSec(3);


        // to set it scrollable automatically

        // we use below method.

        sliderView.setAutoCycle(true);


        // to start autocycle below method is used.

        sliderView.startAutoCycle();

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(@NonNull InitializationStatus initializationStatus) {

            }
        });
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.Sampah:
                startActivity(new Intent(this, KontenSampah.class));
                break;
                case R.id.Account:
                    startActivity(new Intent(this, ProfileUser.class));
                    break;
            case R.id.aboutUs:
                startActivity(new Intent(this, AboutUs.class));
                break;
            case R.id.Maps:
                startActivity(new Intent(this, ThirdActivity.class));
                break;
            case R.id.Contacts:
                startActivity(new Intent(this, DatabaseContact.class));
                break;
            case R.id.tips:
                startActivity(new Intent(this, FAQdanTnT.class));
                break;
            case R.id.faq:
                startActivity(new Intent(this, TnT.class));
                break;
            case R.id.Keluar:
                alertdialog = new AlertDialog.Builder(SecondActivity.this);
                alertdialog.setIcon(R.drawable.ic_baseline_contact_support_24);
                alertdialog.setTitle(R.string.title);
                alertdialog.setMessage(R.string.message);

                alertdialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FirebaseAuth.getInstance().signOut();
                        startActivity(new Intent(SecondActivity.this, MainActivity.class));
                        Toast.makeText(getApplicationContext(), "Akun anda telah Log Out", Toast.LENGTH_LONG).show();
                        finish();
                    }
                });
                alertdialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(SecondActivity.this, "You have clicked No Button",Toast.LENGTH_SHORT).show();
                    }
                });
                alertdialog.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(SecondActivity.this, "You have clicked Cancel Button",Toast.LENGTH_SHORT).show();
                    }
                });
                AlertDialog tDialog = alertdialog.create();
                tDialog.show();
            }
        }
    private void reload() { }
    @Override
    protected void onPause() {
        super.onPause();
        closeDrawer(drawerLayout);
    }
    }