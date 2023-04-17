package com.solaris.bitzone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.solaris.bitzone.fragments.ClubFragment;
import com.solaris.bitzone.fragments.CourseFragment;
import com.solaris.bitzone.fragments.HomeFragment;
import com.solaris.bitzone.fragments.ProfileFragment;
import com.solaris.bitzone.fragments.SarcFragment;

public class LandingActivity extends AppCompatActivity {

    int fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);

        Fragment contactfragment = new HomeFragment();
        openfragment(contactfragment);
        fragment = 1;



        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.m_home:
                        if(fragment != 1){
                            Fragment contactfragment = new HomeFragment();
                            openfragment(contactfragment);
                            fragment= 1;
                        }
                        return true;
                    case R.id.m_course:
                        if(fragment != 2){
                            Fragment Coursefragment = new CourseFragment();
                            openfragment(Coursefragment);
                            fragment= 2;
                        }
                        return true;

                    case R.id.m_club:
                        if(fragment != 3){
                            Fragment Clubfragment = new ClubFragment();
                            openfragment(Clubfragment);
                            fragment= 3;
                        }
                        return true;

                    case R.id.m_profile:
                        if(fragment != 4){
                            Fragment Profilefragment = new ProfileFragment();
                            openfragment(Profilefragment);
//                            Intent intent = new Intent(LandingActivity.this, ProfileActivity.class);
//                            startActivity(intent);
                            fragment= 4;
                        }
                        return true;
                    case R.id.m_sarc:
                        if(fragment != 4){
                            Fragment sarcfragment = new SarcFragment();
                            openfragment(sarcfragment);
//                            Intent intent = new Intent(LandingActivity.this, ProfileActivity.class);
//                            startActivity(intent);
                            fragment= 4;
                        }
                        return true;
                }
                return true;
            }
        });

    }

    public void openfragment(Fragment fragment){
        androidx.fragment.app.FragmentManager fm1 = getSupportFragmentManager();
        androidx.fragment.app.FragmentTransaction transaction1 = fm1.beginTransaction();
        transaction1.replace(R.id.fragment_container, fragment);
        transaction1.commit();
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
    }
}