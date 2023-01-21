package com.nathan.sihijau;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class Registrasi extends AppCompatActivity implements View.OnClickListener {

    private EditText emailTextView, passwordTextView, retypeTextView, fullNameTextView, umurTextView, nomorTextView;
    private TextView  punyaAkun;
    private ImageView logo;
    private Button Btn;
    private ProgressBar progressbar;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        // taking FirebaseAuth instance
        mAuth = FirebaseAuth.getInstance();

        // initialising all views through id defined above
        logo = (ImageView) findViewById(R.id.imageView2);
        logo.setOnClickListener(this);
        punyaAkun = (TextView) findViewById(R.id.adaAkun);
        punyaAkun.setOnClickListener(this);
        fullNameTextView = (EditText) findViewById(R.id.fullName);
        umurTextView = (EditText) findViewById(R.id.umur);
        nomorTextView = (EditText) findViewById(R.id.nomorTelepon);
        emailTextView = (EditText) findViewById(R.id.email);
        passwordTextView = (EditText) findViewById(R.id.password);
        retypeTextView = (EditText) findViewById(R.id.retype);
        Btn = findViewById(R.id.btnregister);
        Btn.setOnClickListener(this);
        progressbar = findViewById(R.id.progressbar);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageView2:
                startActivity(new Intent(this, MainActivity.class));
                break;
            case R.id.btnregister:
                Btn();
                break;
                case R.id.adaAkun:
                    startActivity(new Intent(this, Login.class));
                    break;
            }


        }



    private void Btn() {
        String email = emailTextView.getText().toString().trim();
        String fullname = fullNameTextView.getText().toString().trim();
        String umur = umurTextView.getText().toString().trim();
        String phone = nomorTextView.getText().toString().trim();


        if (fullname.isEmpty()) {
            fullNameTextView.setError("Isi Nama lengkap Anda!");
            fullNameTextView.requestFocus();
            return;
        }
        if (umur.isEmpty()) {
            umurTextView.setError("Isi umur Anda!");
            umurTextView.requestFocus();
            return;
        }

        if (email.isEmpty()) {
            emailTextView.setError("Isi Email Anda!");
            emailTextView.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailTextView.setError("Masukan Email yang benar!");
            emailTextView.requestFocus();
            return;
        }




        if(retypeTextView.getText().toString().length()==0) {
            retypeTextView.setError("Masukan Kembali Password yang diperlukan!");
        }
        if (passwordTextView.length() < 8) {
            passwordTextView.setError("Password min 8 karakter");
            passwordTextView.requestFocus();
            return;
        }
        if (!passwordTextView.getText().toString().equals(retypeTextView.getText().toString())) {
            Toast.makeText(getApplicationContext(), "Password Tidak Sama",
                    Toast.LENGTH_SHORT).show();
            retypeTextView.requestFocus();
            return;
        }
        if (phone.isEmpty()) {
            nomorTextView.setError("Isi Nomor Telpon Anda!");
            nomorTextView.requestFocus();
            return;
        }

        if (!(phone.length() == 12)) {
            nomorTextView.setError("Masukan Nomor Telepon yang benar!");
            nomorTextView.requestFocus();
            return;
        }
        String password = passwordTextView.getText().toString().trim();
        progressbar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){
                            User user = new User(fullname, umur, email, phone);

                            FirebaseDatabase.getInstance("\n" +
                                            "https://sihijau-18e6e-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("User")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                Toast.makeText(Registrasi.this, "User has been Registered successfully!", Toast.LENGTH_LONG).show();
                                                progressbar.setVisibility(View.GONE);
                                                FirebaseUser user = mAuth.getCurrentUser();
                                                // pergi ke home
                                                Intent intent = new Intent(Registrasi.this, MainActivity.class);startActivity(intent);
                                            }
                                            else{
                                                Toast.makeText(Registrasi.this, "Failed to Register! Try again!", Toast.LENGTH_LONG).show();
                                                progressbar.setVisibility(View.GONE);
                                            }
                                        }
                                    });
                        }else{
                            Toast.makeText(Registrasi.this, "Failed to Register! Try again!", Toast.LENGTH_LONG).show();
                            progressbar.setVisibility(View.GONE);

                        }
                    }
                });
    }
}
