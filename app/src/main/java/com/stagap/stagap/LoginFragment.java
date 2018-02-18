package com.stagap.stagap;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class LoginFragment extends Fragment {
    private Button signInBtn;
    private EditText emailField;
    private EditText passwordField;
    private RelativeLayout relativeLoading;

    private String email;
    private String password;

    private FirebaseAuth auth;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        auth = FirebaseAuth.getInstance();

        emailField = view.findViewById(R.id.emailField);
        passwordField = view.findViewById(R.id.passwordField);
        relativeLoading= getActivity().findViewById(R.id.relativeLoading);
        signInBtn = view.findViewById(R.id.signInBtn);

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
                                        Intent intent = new Intent(getActivity(), MainActivity.class);
                                        setLoading(false);
                                        startActivity(intent);
                                        getActivity().finish();
                                    } else {
                                        setLoading(false);
                                        Toast.makeText(getActivity(), "Login error",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }else{
                    Toast.makeText(getActivity(), "The fields are empty",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

    private void setLoading(Boolean truefalse){
        if(truefalse.equals(true)){
            relativeLoading.setVisibility(View.VISIBLE);
        }else{
            relativeLoading.setVisibility(View.GONE);
        }
        signInBtn.setClickable(!truefalse);
        emailField.setEnabled(!truefalse);
        passwordField.setEnabled(!truefalse);
    }

}
