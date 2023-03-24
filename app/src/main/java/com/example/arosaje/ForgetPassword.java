package com.example.arosaje;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class ForgetPassword extends AppCompatActivity {

    private Button forgetbtn, loginBtn;
    private TextInputLayout textEmail;
    private String email;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        auth = FirebaseAuth.getInstance();

        textEmail = findViewById(R.id.ForgetPassword_edittext);
        forgetbtn = findViewById(R.id.ForgetPasswordbutton);
        loginBtn = findViewById(R.id.buttontologin);

        forgetbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateData();
            }
        });
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();
            }
        });

    }

    private void validateData() {
        email = Objects.requireNonNull(textEmail.getEditText()).getText().toString();
        if (email.isEmpty()) {
            textEmail.setError("Obligatoire");
        } else {
            forgetPass();
        }
    }

    private void forgetPass() {
        auth.sendPasswordResetEmail(email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(ForgetPassword.this, "Veuillez vérifier vos emails", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(ForgetPassword.this, Login.class));
                            finish();
                        } else {
                            Toast.makeText(ForgetPassword.this, "Error"+ Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}