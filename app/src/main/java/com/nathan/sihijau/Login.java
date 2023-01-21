package com.nathan.sihijau;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

public class Login extends AppCompatActivity implements View.OnClickListener {
    private ImageView logo;
    private TextView register, forgotPassword;
    private EditText emailTextView, passwordTextView;
    private Button Btn;
    private ProgressBar progressbar;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // taking instance of FirebaseAuth
        mAuth = FirebaseAuth.getInstance();

        register = (TextView) findViewById(R.id.registrasi);
        register.setOnClickListener(this);
        logo = (ImageView) findViewById(R.id.imageView2);
        logo.setOnClickListener(this);

        // initialising all views through id defined above
        emailTextView = findViewById(R.id.email);
        passwordTextView = findViewById(R.id.password);
        Btn = findViewById(R.id.login);
        Btn.setOnClickListener(this);
        progressbar = findViewById(R.id.progressBar);
        forgotPassword = (TextView) findViewById(R.id.forgotpassword);
        forgotPassword.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.registrasi:
                startActivity(new Intent(this, Registrasi.class));
                break;
            case R.id.imageView2:
                startActivity(new Intent(this, MainActivity.class));
                break;
            case R.id.login:
                userLogin();
                break;
            case R.id.forgotpassword:
                startActivity(new Intent(this, ForgotPassword.class));
                break;
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            reload();
        }
    }

    private void userLogin() {
        String email = emailTextView.getText().toString().trim();
        String password = passwordTextView.getText().toString().trim();
        if (email.isEmpty()) {
            emailTextView.setError("Masukkan Email anda!");
            emailTextView.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailTextView.setError("Masukkan email yang valid!");
            emailTextView.requestFocus();
            return;
        }
        if (password.isEmpty()) {
            passwordTextView.setError("Masukkan password anda!");
        }
        if (password.length() < 8) {
            passwordTextView.setError("Password min 8 karakter!");
        }
        progressbar.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener((new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    if ((user.isEmailVerified())) {
                        // masuk ke Aplikasi
                        startActivity(new Intent(Login.this, SecondActivity.class));
                        Log.d(TAG, "signInWithEmail:success");
                        updateUI(user);

                    } else {
                        user.sendEmailVerification();
                        Toast.makeText(Login.this, "Check inbox email anda untuk verifikasi akun", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(Login.this, "Gagal login! Cek kembali email dan password anda", Toast.LENGTH_LONG).show();
                    Log.w(TAG, "signInWithEmail:failure", task.getException());
                    updateUI(null);
                }
            }

        }));

    }
    private void reload () {
    }

    private void updateUI (FirebaseUser user){
    }
}

