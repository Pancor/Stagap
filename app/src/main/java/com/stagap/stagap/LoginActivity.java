package com.stagap.stagap;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setupMainImage();
        setFragment(new LoginFragment());
    }

    public void onClick(View view){
        int id = view.getId();
        switch(id){
            case(R.id.baseLoginBtn):
                LoginFragment loginFragment = new LoginFragment();
                setFragment(loginFragment);
                break;
            case(R.id.baseRegBtn):
                RegisterFragment registerFragment = new RegisterFragment();
                setFragment(registerFragment);
                break;
        }

    }

    private void setFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.loginRegFragment, fragment);
        fragmentTransaction.commit();
    }

    private void setupMainImage() {
        ImageView mainImage = findViewById(R.id.imageView2);
        Glide.with(this)
                .load(R.drawable.happy)
                .into(mainImage);
    }
}
