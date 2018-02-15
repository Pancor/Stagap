package com.stagap.stagap;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.PhoneNumberUtils;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {
    private Button signInBtn;
    private Button signUpBtn;
    private EditText emailField;
    private EditText passwordField;
    private RelativeLayout relativeLoading;

    private String email;
    private String password;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        auth = FirebaseAuth.getInstance();

        emailField = findViewById(R.id.emailField);
        passwordField = findViewById(R.id.passwordField);
        signUpBtn = findViewById(R.id.signUpButton);
        relativeLoading= findViewById(R.id.relativeLoading);


        signInBtn = findViewById(R.id.signInBtn);
        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = emailField.getText().toString();
                password = passwordField.getText().toString();
                if(!TextUtils.isEmpty(password) && !TextUtils.isEmpty(email)){
                    setLoading(true);
                    auth.signInWithEmailAndPassword(email, password).addOnCompleteListener
                            (new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Intent intent = new Intent(getBaseContext(), MainActivity.class);
                                        setLoading(false);
                                        startActivity(intent);
                                        finish();
                                    } else {
                                        setLoading(false);
                                        Toast.makeText(LoginActivity.this, "Login error",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }else{
                    Toast.makeText(LoginActivity.this, "The fields are empty",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });



    }

    private void setLoading(Boolean truefalse){
        if(truefalse.equals(true)){
            relativeLoading.setVisibility(View.VISIBLE);
        }else{
            relativeLoading.setVisibility(View.GONE);
        }
        signUpBtn.setClickable(!truefalse);
        signInBtn.setClickable(!truefalse);
        emailField.setEnabled(!truefalse);
        passwordField.setEnabled(!truefalse);
    }

}
