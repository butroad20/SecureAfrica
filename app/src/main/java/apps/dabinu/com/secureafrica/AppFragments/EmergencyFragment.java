package apps.dabinu.com.secureafrica.AppFragments;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.Set;

import apps.dabinu.com.secureafrica.R;


public class EmergencyFragment extends android.app.Fragment {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor mEditor;

    public EmergencyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_emergency, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        sharedPreferences = getActivity().getSharedPreferences("NUMBERS", Context.MODE_PRIVATE);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());
        mEditor = sharedPreferences.edit();

        String fNumber = sharedPreferences.getString("fNumber", "112");
        String sNumber = sharedPreferences.getString("fNumber", "nuller");
        String tNumber = sharedPreferences.getString("fNumber", "nuller");


        getView().findViewById(R.id.firstCallee).setVisibility(View.VISIBLE);

    }
}