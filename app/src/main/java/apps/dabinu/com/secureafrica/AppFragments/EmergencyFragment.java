package apps.dabinu.com.secureafrica.AppFragments;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Set;

import apps.dabinu.com.secureafrica.R;
import apps.dabinu.com.secureafrica.activities.MainActivity;


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

        String fDetail = sharedPreferences.getString("fNumber", "General 112");
        String sDetail = sharedPreferences.getString("sNumber", "nuller");
        String tDetail = sharedPreferences.getString("tNumber", "nuller");


        getView().findViewById(R.id.addNewEmergency).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

                View view = getActivity().getLayoutInflater().inflate(R.layout.fragment_add_new_em_number, null);
                builder.setView(view);

                builder.create();

                final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
                LayoutInflater inflater = getActivity().getLayoutInflater();
                final View dialogView = inflater.inflate(R.layout.fragment_add_new_em_number, null);
                dialogBuilder.setView(dialogView);

                (dialogView.findViewById(R.id.cancel)).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v){

                        Intent launchNextActivity = new Intent(getActivity().getApplicationContext(), MainActivity.class);
                        launchNextActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        launchNextActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        launchNextActivity.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(launchNextActivity);
                    }
                });

                (dialogView.findViewById(R.id.add)).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String ssDetail = sharedPreferences.getString("sNumber", "nuller");
                        String ttDetail = sharedPreferences.getString("tNumber", "nuller");

                        if(((EditText) dialogView.findViewById(R.id.name)).getText().toString().trim().equals("")){
                            ((EditText) dialogView.findViewById(R.id.name)).setError("Field cannot be empty");
                        }
                        else if(((EditText) dialogView.findViewById(R.id.number)).getText().toString().trim().equals("")){
                            ((EditText) dialogView.findViewById(R.id.number)).setError("Field cannot be empty");
                        }
                        else if(!(isText(((EditText) dialogView.findViewById(R.id.name)).getText().toString()))){
                            ((EditText) dialogView.findViewById(R.id.name)).setError("Invalid name");
                        }
                        else if(!(isNumeric(((EditText) dialogView.findViewById(R.id.number)).getText().toString()))){
                            ((EditText) dialogView.findViewById(R.id.number)).setError("Invalid number");
                        }

                        else{
                            if(ssDetail.equals("nuller")){
                                mEditor.putString("sNumber", String.format("%s %s", ((((EditText) dialogView.findViewById(R.id.name))).getText().toString().trim()), ((((EditText) dialogView.findViewById(R.id.number))).getText().toString().trim())));
                                mEditor.apply();
                                Intent launchNextActivity = new Intent(getActivity().getApplicationContext(), MainActivity.class);
                                launchNextActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                launchNextActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                launchNextActivity.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                startActivity(launchNextActivity);
                            }

                            else if(ttDetail.equals("nuller")){
                                mEditor.putString("tNumber", String.format("%s %s", ((((EditText) dialogView.findViewById(R.id.name))).getText().toString().trim()), ((((EditText) dialogView.findViewById(R.id.number))).getText().toString().trim())));
                                mEditor.apply();
                                Intent launchNextActivity = new Intent(getActivity().getApplicationContext(), MainActivity.class);
                                launchNextActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                launchNextActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                launchNextActivity.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                startActivity(launchNextActivity);
                            }
                            else{

                                new AlertDialog.Builder(getActivity())
                                        .setMessage("You can have a maximum of only three emergency numbers. Which phone number would you replace for this?")
                                        .setCancelable(false)
                                        .setPositiveButton(ssDetail, new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                mEditor.putString("sNumber", String.format("%s %s", ((((EditText) dialogView.findViewById(R.id.name))).getText().toString().trim()), ((((EditText) dialogView.findViewById(R.id.number))).getText().toString().trim())));
                                                mEditor.apply();
                                                getView().findViewById(R.id.bck).performClick();

                                            }
                                        })
                                        .setNegativeButton(ttDetail, new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                mEditor.putString("tNumber", String.format("%s %s", ((((EditText) dialogView.findViewById(R.id.name))).getText().toString().trim()), ((((EditText) dialogView.findViewById(R.id.number))).getText().toString().trim())));
                                                mEditor.apply();
                                                getView().findViewById(R.id.bck).performClick();
                                            }
                                        })
                                        .setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                dialog.dismiss();
                                                getView().findViewById(R.id.bck).performClick();
                                            }
                                        })
                                        .show();
                            }

                        }
                    }
                });


                AlertDialog alertDialog = dialogBuilder.create();
                alertDialog.show();


            }
        });


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


    public boolean isNumeric(String str){
        for (char c : str.toCharArray()){
            if (!Character.isDigit(c)){
                return false;
            }
        }

        return true;
    }


    public boolean isText(String str){
        for (char c : str.toCharArray()){
            if (!Character.isAlphabetic(c)){
                return false;
            }
        }

        return true;
    }

}