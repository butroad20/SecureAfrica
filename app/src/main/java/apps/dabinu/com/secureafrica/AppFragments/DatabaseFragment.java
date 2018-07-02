package apps.dabinu.com.secureafrica.AppFragments;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import apps.dabinu.com.secureafrica.Adapters.DatabaseAdapter;
import apps.dabinu.com.secureafrica.R;


public class DatabaseFragment extends android.app.Fragment {


    SharedPreferences sharedPreferences;
    SharedPreferences.Editor mEditor;


    public DatabaseFragment(){

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_database, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        sharedPreferences = getActivity().getSharedPreferences("NUMBERS", Context.MODE_PRIVATE);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());
        mEditor = sharedPreferences.edit();

        getView().findViewById(R.id.progressBar).setVisibility(View.VISIBLE);

        ArrayList<String> name = new ArrayList<>();
        ArrayList<String> number = new ArrayList<>();


        String fDetail = sharedPreferences.getString("fNumber", "General 112");
        String sDetail = sharedPreferences.getString("sNumber", "nuller");
        String tDetail = sharedPreferences.getString("tNumber", "nuller");


        String[] arr = fDetail.split("\\d+", 2);
        String fName = arr[0].trim();
        String fNumber = fDetail.substring(fName.length() + 1).trim();

        name.add(fName);
        number.add(fNumber);


        if(!(sDetail.equals("nuller"))){
            String[] arry = sDetail.split("\\d+", 2);
            String sName = arry[0].trim();
            String sNumber = sDetail.substring(sName.length() + 1).trim();

            name.add(sName);
            number.add(sNumber);
        }


        if(!(tDetail.equals("nuller"))){
            String[] arryson = tDetail.split("\\d+", 2);
            String tName = arryson[0].trim();
            String tNumber = tDetail.substring(tName.length() + 1).trim();

            name.add(tName);
            number.add(tNumber);
        }



        name.add("Abia");
        number.add("08035415408");

        name.add("Adamawa");
        number.add("08089671313");

        name.add("Akwa Ibom");
        number.add("08039213071");

        name.add("Anambra");
        number.add("08024922772");

        name.add("Bauchi");
        number.add("08151849417");

        name.add("Bayelsa");
        number.add("07034578208");


        name.add("Benue");
        number.add("08066006475");


        name.add("Borno");
        number.add("08068075581");


        name.add("Cross River");
        number.add("08133568456");

        name.add("Delta");
        number.add("08036684974");

        name.add("Ebonyi");
        number.add("07064515001");

        name.add("Edo");
        number.add("08037646272");

        name.add("Ekiti");
        number.add("08062335577");


        name.add("Enugu");
        number.add("08032003702");


        name.add("FCT Abuja");
        number.add("07057337653");


        name.add("Gombe");
        number.add("08150567771");


        name.add("Imo");
        number.add("08034773600");


        name.add("Jigawa");
        number.add("08075391069");


        name.add("Kaduna");
        number.add("08123822284");


        name.add("Kano");
        number.add("08032419754");

        name.add("Katsina");
        number.add("08075391255");


        name.add("Kebbi");
        number.add("08038797644");


        name.add("Kogi");
        number.add("08075391335");

        name.add("Kwara");
        number.add("07032069501");

        name.add("Lagos");
        number.add("07055462708");

        name.add("Nasarawa");
        number.add("08123821571");

        name.add("Niger");
        number.add("08081777498");

        name.add("Ogun");
        number.add("08032136765");

        name.add("Ondo");
        number.add("07034313903");

        name.add("Osun");
        number.add("08075872433");

        name.add("Oyo");
        number.add("08081768614");

        name.add("Plateau");
        number.add("08126375938");

        name.add("Rivers");
        number.add("08032003514");

        name.add("Sokoto");
        number.add("07068848035");

        name.add("Taraba");
        number.add("08140089863");

        name.add("Yobe");
        number.add("07039301585");

        name.add("Zamfara");
        number.add("08106580123");

        name.add("Nigeria");
        number.add("112");


        RecyclerView recyclerView = getView().findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(new DatabaseAdapter(getActivity().getApplicationContext(), name, number));
        getView().findViewById(R.id.progressBar).setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);

    }



}
