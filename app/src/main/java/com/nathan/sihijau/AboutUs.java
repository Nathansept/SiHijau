package com.nathan.sihijau;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AboutUs extends AppCompatActivity {

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

//    private ImageButton btnFacebook2, ic_action_instagram, ic_action_name, btnwhatsapp, btnFacebook3, imageView32, btnInstagram2, imageView52;
    private ImageButton ivWhatsapp, ivInstagram, ivFacebook, ivTwitter;
    private ImageButton SvInstagram, SvFacebook;
    private ImageButton LvWhatsapp, LvInstagram, LvFacebook, LvTwitter;
    private ImageButton UvWhatsapp, UvInstagram, UvFacebook, UvTwitter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);


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




        // Sosial Media Nathan
        ivWhatsapp = findViewById(R.id.btnwhatsapp);
        ivFacebook = findViewById(R.id.btnFacebook3);
        ivInstagram = findViewById(R.id.btnInstagram2);
        ivTwitter = findViewById(R.id.imageView32);

        //Sosial Media Sihijau
        SvFacebook = findViewById(R.id.btnFacebook2);
        SvInstagram = findViewById(R.id.btnInstagram3);

        //Sosial Media Lugli Corporation
        LvWhatsapp = findViewById(R.id.btnwhatsapp1);
        LvFacebook = findViewById(R.id.btnFacebook);
        LvInstagram = findViewById(R.id.btnInstagram);
        LvTwitter = findViewById(R.id.imageView3);

        //Sosial Media Ukrida
        UvWhatsapp = findViewById(R.id.btnwhatsapp2);
        UvFacebook = findViewById(R.id.btnFacebook4);
        UvInstagram = findViewById(R.id.btnInstagram4);
        UvTwitter = findViewById(R.id.imageView4);

        ivWhatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sNumber = "+6283878973311";

                Uri uri = Uri.parse(String.format(
                        "https://api.whatsapp.com/send?phone=%s",sNumber
                ));
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(uri);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);

            }
        });
        ivFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sAppLink = "";
                String sPackage = "com.facebook.katana";
                String sWebLink = "https://www.facebook.com/profile.php?id=100012410258303";
                openLink(sAppLink,sPackage,sWebLink);
            }
        });

        ivInstagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sAppLink = "https://www.instagram.com/nathansept";
                String sPackage = "com.instagram.android";
                openLink(sAppLink,sPackage,sAppLink);
            }
        });


        ivTwitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sAppLink = "twitter://user?screen_name=Nathanseptian1";
                String sPackage = "com.twitter.android";
                String sWebLink = "https://twitter.com/Nathanseptian1";
                openLink(sAppLink,sPackage,sWebLink);
            }
        });

//    Sihi
        SvFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sAppLink = "";
                String sPackage = "com.facebook.katana";
                String sWebLink = "https://www.facebook.com/profile.php?id=100082699865240";
                openLink(sAppLink,sPackage,sWebLink);
            }
        });

        SvInstagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sAppLink = "https://www.instagram.com/greentech_sihi/";
                String sPackage = "com.instagram.android";
                openLink(sAppLink,sPackage,sAppLink);
            }
        });

        // Lugli Corporation
        LvWhatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sNumber = "+6283878973311";

                Uri uri = Uri.parse(String.format(
                        "https://api.whatsapp.com/send?phone=%s",sNumber
                ));
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(uri);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);

            }
        });
        LvFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sAppLink = "";
                String sPackage = "com.facebook.katana";
                String sWebLink = "https://www.facebook.com/profile.php?id=100012410258303";
                openLink(sAppLink,sPackage,sWebLink);
            }
        });

        LvInstagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sAppLink = "https://www.instagram.com/nathansept";
                String sPackage = "com.instagram.android";
                openLink(sAppLink,sPackage,sAppLink);
            }
        });


        LvTwitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sAppLink = "twitter://user?screen_name=Nathanseptian1";
                String sPackage = "com.twitter.android";
                String sWebLink = "https://twitter.com/Nathanseptian1";
                openLink(sAppLink,sPackage,sWebLink);
            }
        });
        // Ukrida
        UvWhatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sNumber = "+6281280961970";

                Uri uri = Uri.parse(String.format(
                        "https://api.whatsapp.com/send?phone=%s",sNumber
                ));
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(uri);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);

            }
        });
        UvFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sAppLink = "";
                String sPackage = "com.facebook.katana";
                String sWebLink = "https://www.facebook.com/profile.php?id=100063652059101";
                openLink(sAppLink,sPackage,sWebLink);
            }
        });

        UvInstagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sAppLink = "https://www.instagram.com/kampusukrida";
                String sPackage = "com.instagram.android";
                openLink(sAppLink,sPackage,sAppLink);
            }
        });


        UvTwitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sAppLink = "https://twitter.com/UKRIDA?t=9NLAUXxL9QhIXsQT5r1Hlw&s=09";
                String sPackage = "com.twitter.android";
                String sWebLink = "https://twitter.com/UKRIDA";
                openLink(sAppLink,sPackage,sWebLink);
            }
        });
    }

    private void openLink(String sAppLink, String sPackage, String sWebLink) {
        try{
            // jika Facebook terinstall
            Uri uri = Uri.parse(sAppLink);
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(uri);
            intent.setPackage(sPackage);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            // Jika tidak terinstall maka dibuka di browser
        }catch (ActivityNotFoundException activityNotFoundException){
            Uri uri = Uri.parse(sWebLink);
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(uri);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    }
    @Override
    protected void onPause() {
        super.onPause();
        closeDrawer(drawerLayout);
    }
}