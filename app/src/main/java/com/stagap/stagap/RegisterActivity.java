package com.stagap.stagap;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.api.Batch;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {
    private EditText mName;
    private EditText mSurname;
    private EditText mEmail;
    private EditText mPassword;
    private EditText mPasswordRep;

    private FirebaseAuth mAuth;

    private Button mHaveAccBtn;
    private Button mSignUp;

    private FirebaseFirestore mDataBase;
    private Batch mDataBanch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mName = findViewById(R.id.nameField);
        mSurname = findViewById(R.id.surnameField);
        mEmail = findViewById(R.id.emailFieldReg);
        mPassword = findViewById(R.id.passwordFieldReg);
        mPasswordRep = findViewById(R.id.passwordFieldRegRep);

        mAuth = FirebaseAuth.getInstance();

        mHaveAccBtn = findViewById(R.id.haveAccBtn);
        mSignUp = findViewById(R.id.signUpBtn);

        mDataBase = FirebaseFirestore.getInstance();

        mHaveAccBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

        mSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String name = mName.getText().toString();
                final String surname = mSurname.getText().toString();
                final String email = mEmail.getText().toString();
                String password = mPassword.getText().toString();
                String passwordRep = mPasswordRep.getText().toString();

                if(!TextUtils.isEmpty(name) && !TextUtils.isEmpty(surname) && !TextUtils.isEmpty(email)
                        && !TextUtils.isEmpty(password) && !TextUtils.isEmpty(passwordRep)){
                    if(password.equals(passwordRep)){
                        if(password.length()>=6){
                        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener
                                (new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    Map<String, String> values = new HashMap<>();
                                    values.put("Name", name);
                                    values.put("Surname", surname);

                                    String userId = mAuth.getCurrentUser().getUid();

                                    mDataBase.collection("Users").document(userId).set(values).
                                            addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if(task.isSuccessful()){
                                                Toast.makeText(getBaseContext(),
                                                        "The account was created succesfully",
                                                        Toast.LENGTH_SHORT).show();

                                                mAuth.signOut();
                                                Intent intent = new Intent(getBaseContext(),
                                                        LoginActivity.class);
                                                startActivity(intent);
                                                finish();
                                            }else{
                                                mAuth.getCurrentUser().delete();
                                                Toast.makeText(getBaseContext(),
                                                        task.getException().toString(),
                                                        Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });

                                }else{
                                    String errorMess = task.getException().getMessage();
                                    Toast.makeText(getBaseContext(), "Error: " + errorMess,
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });}
                        else{
                            Toast.makeText(getBaseContext(),
                                    "Password must have more than six symbols.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(getBaseContext(), "Passwords are different",
                                Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getBaseContext(),"Fields are empty",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
