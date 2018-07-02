package apps.dabinu.com.secureafrica.activities;

import android.Manifest;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.Toast;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import apps.dabinu.com.secureafrica.AppFragments.DatabaseFragment;
import apps.dabinu.com.secureafrica.AppFragments.EmergencyFragment;
import apps.dabinu.com.secureafrica.AppFragments.TourFragment;
import apps.dabinu.com.secureafrica.R;


public class MainActivity extends BaseActivity {


    private boolean exit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.container).setOnTouchListener(new View.OnTouchListener() {
            Handler handler = new Handler();

            int numberOfTaps = 0;
            long lastTapTimeMs = 0;
            long touchDownMs = 0;

            @Override
            public boolean onTouch(View v, MotionEvent event){

                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        touchDownMs = System.currentTimeMillis();
                        break;
                    case MotionEvent.ACTION_UP:
                        handler.removeCallbacksAndMessages(null);

                        if((System.currentTimeMillis() - touchDownMs) > ViewConfiguration.getTapTimeout()){

                            numberOfTaps = 0;
                            lastTapTimeMs = 0;
                            break;
                        }

                        if(numberOfTaps > 0 && (System.currentTimeMillis() - lastTapTimeMs) < ViewConfiguration.getDoubleTapTimeout()) {
                            numberOfTaps += 1;

                        }

                        else{
                            numberOfTaps = 1;
                        }

                        lastTapTimeMs = System.currentTimeMillis();

                        if (numberOfTaps == 4) {
                            //THIS IS WHERE OUR ACTION WOULD BE!!!!!!!!!!!!!!
                            //revert numberOfTaps to 0
                            Toast.makeText(getApplicationContext(), "Ayokunle is my bitch", Toast.LENGTH_LONG).show();
                        }
                }

                return true;
            }
        });


        if(ContextCompat.checkSelfPermission(this, Manifest.permission.SYSTEM_ALERT_WINDOW) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SYSTEM_ALERT_WINDOW}, 1);
        }

        HomeKeyWatcher mHomeWatcher = new HomeKeyWatcher(this);
        mHomeWatcher.setOnHomePressedListener(new HomeKeyWatcher.OnHomePressedListener() {
            @Override
            public void onHomePressed() {
                // do something here...
                showChatHead();
            }
            @Override
            public void onHomeLongPressed() {
            }
        });


        mHomeWatcher.startWatch();

        AHBottomNavigation bottomNavigation = findViewById(R.id.navigation);

        AHBottomNavigationItem item1 = new AHBottomNavigationItem("Emergency", R.drawable.emergency, android.R.color.darker_gray);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem("Database", R.drawable.database, android.R.color.darker_gray);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem("Tour", R.drawable.tour, android.R.color.darker_gray);


        bottomNavigation.addItem(item1);
        bottomNavigation.addItem(item2);
        bottomNavigation.addItem(item3);


        bottomNavigation.setDefaultBackgroundColor(Color.parseColor("#e0f8e5"));
        bottomNavigation.setAccentColor(Color.parseColor("#FF0000"));
        bottomNavigation.setInactiveColor(Color.parseColor("#222222"));
        bottomNavigation.setForceTint(true);
        bottomNavigation.setTitleState(AHBottomNavigation.TitleState.ALWAYS_SHOW);
        bottomNavigation.setCurrentItem(0);


        final FragmentTransaction fragmentTrnsaction = getFragmentManager().beginTransaction();

        fragmentTrnsaction.replace(R.id.container, new EmergencyFragment());
        fragmentTrnsaction.commit();


        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected){

                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                switch(position){

                    case 0:
                        fragmentTransaction.replace(R.id.container, new EmergencyFragment());
                        fragmentTransaction.commit();
                        break;

                    case 1:
                        fragmentTransaction.replace(R.id.container, new DatabaseFragment());
                        fragmentTransaction.commit();
                        break;

                    case 2:
                        fragmentTransaction.replace(R.id.container, new TourFragment());
                        fragmentTransaction.commit();
                        break;

                }

                return true;
            }
        });
    }

    @Override
    public void onBackPressed() {
        if(exit){
            finish();
        }
        else{
            Toast.makeText(this, "Press back again to exit..", Toast.LENGTH_LONG).show();
            exit = true;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    exit = false;
                }
            }, 2000);

        }

    }
}
