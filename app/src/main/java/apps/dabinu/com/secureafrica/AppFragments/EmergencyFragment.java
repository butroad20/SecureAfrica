package apps.dabinu.com.secureafrica.AppFragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import apps.dabinu.com.secureafrica.R;


public class EmergencyFragment extends android.app.Fragment {


    public EmergencyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_emergency, container, false);
    }

}
