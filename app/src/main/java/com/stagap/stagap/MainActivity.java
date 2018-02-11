package com.stagap.stagap;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView mMainNav;
    private FrameLayout mMainFrame;

    private HomeFragment homeFragment;
    private NotificationsFragment notificationsFragment;
    private AccountFragment accountFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        homeFragment = new HomeFragment();
        notificationsFragment = new NotificationsFragment();
        accountFragment = new AccountFragment();

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
                    case(R.id.navigation_person):
                        setFragment(accountFragment);
                        setTitle("Account");
                        return true;
                    case(R.id.navigation_notifications):
                        setFragment(notificationsFragment);
                        setTitle("Notifications");
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

}
