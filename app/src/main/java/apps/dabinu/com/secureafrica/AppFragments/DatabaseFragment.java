package apps.dabinu.com.secureafrica.AppFragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import apps.dabinu.com.secureafrica.R;


public class DatabaseFragment extends android.app.Fragment {


    public DatabaseFragment(){

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_database, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getView().findViewById(R.id.progressBar).setVisibility(View.VISIBLE);


    }
}
