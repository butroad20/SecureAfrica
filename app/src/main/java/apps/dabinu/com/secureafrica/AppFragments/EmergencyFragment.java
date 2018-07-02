package apps.dabinu.com.secureafrica.AppFragments;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
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

        String fDetail = sharedPreferences.getString("fNumber", "EMERGENCY112");
        String sDetail = sharedPreferences.getString("sNumber", "nuller");
        String tDetail = sharedPreferences.getString("tNumber", "nuller");


        String[] arr = fDetail.split("\\d+", 2);
        String fName = arr[0].trim();
        String fNumber = fDetail.substring(fName.length() + 1).trim();
        ((TextView) getView().findViewById(R.id.nameOffCallee)).setText(fName);
        getView().findViewById(R.id.callFCallee).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //call fNUmber
            }
        });
        getView().findViewById(R.id.firstCallee).setVisibility(View.VISIBLE);




        if(!(sDetail.equals("nuller"))){
            String[] arry = sDetail.split("\\d+", 2);
            String sName = arry[0].trim();
            String sNumber = sDetail.substring(sName.length() + 1).trim();
            ((TextView) getView().findViewById(R.id.nameOfsCallee)).setText(sName);
            getView().findViewById(R.id.callSCallee).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //call sNUmber
                }
            });
            getView().findViewById(R.id.secondCallee).setVisibility(View.VISIBLE);
        }



        if(!(tDetail.equals("nuller"))){
            String[] arryson = tDetail.split("\\d+", 2);
            String tName = arryson[0].trim();
            String tNumber = tDetail.substring(tName.length() + 1).trim();
            ((TextView) getView().findViewById(R.id.nameOftCallee)).setText(tName);
            getView().findViewById(R.id.callTCallee).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //call tNUmber
                }
            });
            getView().findViewById(R.id.thirdCallee).setVisibility(View.VISIBLE);
        }
    }
}