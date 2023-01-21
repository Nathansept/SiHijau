package com.nathan.sihijau;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ProfileUser extends AppCompatActivity {
    private FirebaseUser user;
    private DatabaseReference reference;
    private Button logout;
    private String userID;
    private FirebaseAuth mAuth;

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
        setContentView(R.layout.activity_profile_user);

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

        mAuth = FirebaseAuth.getInstance();
        logout= (Button) findViewById(R.id.signout);
        logout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(ProfileUser.this, MainActivity.class));
                Toast.makeText(getApplicationContext(), "Akun anda telah Log Out", Toast.LENGTH_LONG).show();
            }
            });
        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance("\n" +
                        "https://sihijau-18e6e-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("User");
        userID = user.getUid();

        final TextView greetingTextView = (TextView)  findViewById(R.id.greeting);
        final TextView fullNameTextView = (TextView)  findViewById(R.id.fullName);
        final TextView emailTextView = (TextView)  findViewById(R.id.emailAddress);
        final TextView ageTextView = (TextView)  findViewById(R.id.age);
        final TextView phoneTextView = (TextView)  findViewById(R.id.phone);

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);

                if(userProfile != null){
                    String fullName = userProfile.fullName;
                    String email = userProfile.email;
                    String age = userProfile.age;
                    String phone = userProfile.phonenumber;

                    greetingTextView.setText("Welcome "+ fullName + "!");
                    fullNameTextView.setText(fullName);
                    emailTextView.setText(email);
                    ageTextView.setText(age);
                    phoneTextView.setText(phone);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            Toast.makeText(ProfileUser.this,"Something went wrong, Contact Help Center!", Toast.LENGTH_LONG).show();
            }
        });

    }
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            reload();
        }else if (currentUser == null) {
            startActivity(new Intent(ProfileUser.this, MainActivity.class));
        }
    }
    private void reload() {
    }
    protected void onPause() {
        super.onPause();
        closeDrawer(drawerLayout);
    }
}
