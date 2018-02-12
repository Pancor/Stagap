package com.stagap.stagap;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView mMainNav;
    private FrameLayout mMainFrame;

    private HomeFragment homeFragment;
    private MessagesFragment messagesFragment;
    private NotificationsFragment notificationsFragment;
    private AccountFragment accountFragment;
    private SettingsFragment settingsFragment;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sendToLogin();
        setContentView(R.layout.activity_main);

        homeFragment = new HomeFragment();
        messagesFragment = new MessagesFragment();
        notificationsFragment = new NotificationsFragment();
        accountFragment = new AccountFragment();
        settingsFragment = new SettingsFragment();

        setFragment(homeFragment);
        setTitle("Home");

        mMainNav = findViewById(R.id.navigation);
        mMainNav.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case(R.id.navigation_home):
                        setFragment(homeFragment);
                        setTitle("Home");
                        return true;
                    case(R.id.navigation_messages):
                        setFragment(messagesFragment);
                        setTitle("Messages");
                        return true;
                    case(R.id.navigation_person):
                        setFragment(accountFragment);
                        setTitle("Account");
                        return true;
                    case(R.id.navigation_notifications):
                        setFragment(notificationsFragment);
                        setTitle("Notifications");
                        return true;
                    case(R.id.navigation_settings):
                        setFragment(settingsFragment);
                        setTitle("Settings");
                        return true;
                    default:
                        return false;
                }

                }
        });
    }

    private void setFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_frame, fragment);
        fragmentTransaction.commit();

    }

    private void sendToLogin(){
        firebaseAuth = FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser() == null){
            Intent intent = new Intent(getBaseContext(), LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }

}
