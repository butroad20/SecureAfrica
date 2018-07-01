package apps.dabinu.com.secureafrica.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.Toast;
import apps.dabinu.com.secureafrica.R;


public class SplashActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor mEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        sharedPreferences = getSharedPreferences("FIRST", Context.MODE_PRIVATE);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        mEditor = sharedPreferences.edit();


        findViewById(R.id.outer).setOnTouchListener(new View.OnTouchListener() {
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


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run(){
                String someString = sharedPreferences.getString("FIRST", "no");
                Log.d("SOME", someString);

                if(someString.equals("no")){
                    mEditor.putString("FIRST", "yes");
                    mEditor.apply();
                    startActivity(new Intent(getApplicationContext(), OnboardingActivity.class));
                }
                else{
                    Intent launchNextActivity = new Intent(getApplicationContext(), MainActivity.class);
                    launchNextActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    launchNextActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    launchNextActivity.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(launchNextActivity);
                }
            }
        }, 2000);
    }

    @Override
    public void onBackPressed() {
        //Do nothing!
    }
}