package apps.dabinu.com.secureafrica.activities;

import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import apps.dabinu.com.secureafrica.AppFragments.DatabaseFragment;
import apps.dabinu.com.secureafrica.AppFragments.HomeFragment;
import apps.dabinu.com.secureafrica.AppFragments.SettingsFragment;
import apps.dabinu.com.secureafrica.R;


public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AHBottomNavigation bottomNavigation = findViewById(R.id.navigation);

        AHBottomNavigationItem item1 = new AHBottomNavigationItem("Home", R.drawable.home, android.R.color.darker_gray);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem("Database", R.drawable.database, android.R.color.darker_gray);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem("Settings", R.drawable.settings, android.R.color.darker_gray);


        bottomNavigation.addItem(item1);
        bottomNavigation.addItem(item2);
        bottomNavigation.addItem(item3);


        bottomNavigation.setDefaultBackgroundColor(Color.parseColor("#e0f8e5"));
        bottomNavigation.setAccentColor(Color.parseColor("#FF0000"));
        bottomNavigation.setInactiveColor(Color.parseColor("#222222"));
        bottomNavigation.setForceTint(true);
        bottomNavigation.setTitleState(AHBottomNavigation.TitleState.ALWAYS_SHOW);
        bottomNavigation.setCurrentItem(1);



        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected){
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();

                switch(position){

                    case 0:
                        fragmentTransaction.replace(R.id.container, new HomeFragment());
                        fragmentTransaction.commit();
                        break;

                    case 1:
                        fragmentTransaction.replace(R.id.container, new DatabaseFragment());
                        fragmentTransaction.commit();
                        break;

                    case 2:
                        fragmentTransaction.replace(R.id.container, new SettingsFragment());
                        fragmentTransaction.commit();
                        break;

                }

                return true;
            }
        });
    }

}