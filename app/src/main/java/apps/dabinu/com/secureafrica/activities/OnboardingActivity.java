package apps.dabinu.com.secureafrica.activities;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;
import apps.dabinu.com.secureafrica.OnboardingFragments.OnboardingAA;
import apps.dabinu.com.secureafrica.OnboardingFragments.OnboardingB;
import apps.dabinu.com.secureafrica.OnboardingFragments.OnboardingC;
import apps.dabinu.com.secureafrica.OnboardingFragments.OnboardingD;
import apps.dabinu.com.secureafrica.OnboardingFragments.OnboardingE;
import apps.dabinu.com.secureafrica.R;



public class OnboardingActivity extends AppCompatActivity {


    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().hide();


        ArrayList<Fragment> list = new ArrayList<>();
        list.add(new OnboardingAA());
        list.add(new OnboardingB());
        list.add(new OnboardingC());
        list.add(new OnboardingD());
        list.add(new OnboardingE());


        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(), list, getApplicationContext());


        mViewPager = findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

    }





    public static class PlaceholderFragment extends Fragment{

        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }


        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_dummy, container, false);
            return rootView;
        }
    }




    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        private List<Fragment> allFragments;
        private Context context;

        public SectionsPagerAdapter(FragmentManager fm, List<Fragment> allFragments, Context context){
            super(fm);
            this.allFragments = allFragments;
            this.context = context;
        }


        @Override
        public Fragment getItem(int position) {
            return allFragments.get(position);
        }


        @Override
        public int getCount() {
            return allFragments.size();
        }
    }


    @Override
    public void onBackPressed() {
        //do nothing
    }
}