package com.nathan.sihijau;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;

public class DatabaseContact extends AppCompatActivity {
    DatabaseReference databaseReference;
    DrawerLayout drawerLayout;
    ImageView btMenu;
    RecyclerView recyclerview;
    RecyclerView RecyclerView;
    static ArrayList<String> arrayList = new ArrayList<>();
    MainAdapter adapter1;

    public static void closeDrawer(DrawerLayout drawerLayout){
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }
    ArrayList<ContactItem> usersItemArrayList;
    ContactRecyclerAdapter adapter;

    Button buttonAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database_contact);
        FirebaseDatabase.getInstance().setPersistenceEnabled(true); // work offline

        databaseReference = FirebaseDatabase.getInstance("https://sihijau-18e6e-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Contacts");

        RecyclerView = findViewById(R.id.recyclerView);
        RecyclerView.setHasFixedSize(true);
        RecyclerView.setLayoutManager(new LinearLayoutManager(this));

        usersItemArrayList = new ArrayList<>();

        buttonAdd = findViewById(R.id.buttonAdd);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ViewDialogAdd viewDialogAdd = new ViewDialogAdd();
                viewDialogAdd.showDialog(DatabaseContact.this);
            }
        });

        readData();

        drawerLayout = findViewById(R.id.drawer_layout);
        btMenu = findViewById(R.id.btmenu);
        recyclerview = findViewById(R.id.recycler_view);

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

        adapter1 = new MainAdapter(this,arrayList);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        recyclerview.setAdapter(adapter1);

        btMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }

    private void readData() {

        databaseReference.child("USERS").orderByChild("userName").addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                usersItemArrayList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    ContactItem users = dataSnapshot.getValue(ContactItem.class);
                    usersItemArrayList.add(users);
                }
                adapter = new ContactRecyclerAdapter(DatabaseContact.this, usersItemArrayList);
                RecyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    public class ViewDialogAdd {
        public void showDialog(Context context) {
            final Dialog dialog = new Dialog(context);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(false);
            dialog.setContentView(R.layout.alert_dialog_add_new_user);

            EditText textName = dialog.findViewById(R.id.textName);
            EditText textEmail = dialog.findViewById(R.id.textEmail);
            EditText textCountry = dialog.findViewById(R.id.textCountry);


            Button buttonAdd = dialog.findViewById(R.id.buttonAdd);
            Button buttonCancel = dialog.findViewById(R.id.buttonCancel);

            buttonAdd.setText("ADD");
            buttonCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });

            buttonAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String id = "user" + new Date().getTime();
                    String name = textName.getText().toString();
                    String email = textEmail.getText().toString();
                    String country = textCountry.getText().toString();

                    if (name.isEmpty() || email.isEmpty() || country.isEmpty()) {
                        Toast.makeText(context, "Please Enter All data...", Toast.LENGTH_SHORT).show();
                    } else {
                        databaseReference.child("USERS").child(id).setValue(new ContactItem(id, name, email, country));
                        Toast.makeText(context, "DONE!", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                }
            });


            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();

        }
    }
    @Override
    protected void onPause() {
        super.onPause();
        closeDrawer(drawerLayout);
    }
}