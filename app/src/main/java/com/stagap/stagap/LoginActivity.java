package com.stagap.stagap;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class LoginActivity extends AppCompatActivity {
    private FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
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
        fragmentTransaction.replace(R.id.loginRegFragment, fragment);
        fragmentTransaction.commit();

    }

}
