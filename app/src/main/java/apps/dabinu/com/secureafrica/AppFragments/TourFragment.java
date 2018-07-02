package apps.dabinu.com.secureafrica.AppFragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import apps.dabinu.com.secureafrica.R;
import apps.dabinu.com.secureafrica.activities.OnboardingActivity;


public class TourFragment extends android.app.Fragment {


    public TourFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_tour, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getView().findViewById(R.id.goToTour).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity().getApplicationContext(), OnboardingActivity.class).putExtra("FROM", "tourfragment"));
            }
        });

    }
}
