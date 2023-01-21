package com.nathan.sihijau;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class SplashScreen extends AppCompatActivity {

    Animation zoomIn, slideUp, slideDown;
    ImageView img, animVec, tema;
    TextView Version;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);


        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
        );

        getWindow().setStatusBarColor(Color.TRANSPARENT);

        zoomIn = AnimationUtils.loadAnimation(this, R.anim.zoom);
        slideUp = AnimationUtils.loadAnimation(this ,R.anim.slidetop);
        slideDown = AnimationUtils.loadAnimation(this,R.anim.slidedown);

        img = findViewById(R.id.logo);
        tema = findViewById(R.id.Title);
        animVec = findViewById(R.id.animatedVector);
        Version = findViewById(R.id.version);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                img.setVisibility(View.VISIBLE);
                Version.setVisibility(View.VISIBLE);
                animVec.setVisibility(View.VISIBLE);
                tema.setVisibility(View.VISIBLE);
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    AnimatedVectorDrawable drawable = (AnimatedVectorDrawable) animVec.getDrawable();
                    drawable.start();
                }
                img.startAnimation(zoomIn);
                Version.startAnimation(slideUp);
                tema.startAnimation(slideDown);
            }
        }, 0);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }
        }, 3000);
    }
}
