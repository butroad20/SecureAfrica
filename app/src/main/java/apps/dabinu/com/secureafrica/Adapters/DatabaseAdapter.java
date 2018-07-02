package apps.dabinu.com.secureafrica.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

import apps.dabinu.com.secureafrica.R;

public class DatabaseAdapter extends RecyclerView.Adapter<DatabaseAdapter.MyViewHolder> {

    Context context;
    ArrayList<String> name;
    ArrayList<String> number;


    public DatabaseAdapter(Context context, ArrayList<String> name, ArrayList<String> number){
        this.name = name;
        this.number = number;
        this.context = context;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View v = layoutInflater.inflate(R.layout.layout_for_state_and_numbers, parent, false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.stateName.setText(name.get(position));
        holder.numberOfState.setText(number.get(position));
    }

    @Override
    public int getItemCount() {
        return name.size();
    }



    public class MyViewHolder extends RecyclerView.ViewHolder{

        public TextView stateName, numberOfState;

        public MyViewHolder(View itemView) {
            super(itemView);

            stateName = itemView.findViewById(R.id.stateName);
            numberOfState = itemView.findViewById(R.id.numberOfState);
        }
    }
}
