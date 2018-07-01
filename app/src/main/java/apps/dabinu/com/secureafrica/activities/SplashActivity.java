package apps.dabinu.com.secureafrica.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.nfc.NfcAdapter;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import apps.dabinu.com.secureafrica.R;


public class SplashActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor mEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        sharedPreferences = getSharedPreferences("FIRST", Context.MODE_PRIVATE);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        mEditor = sharedPreferences.edit();


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
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                }
            }
        }, 2000);
    }
}