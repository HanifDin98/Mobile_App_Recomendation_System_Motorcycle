package com.Hanif.underboneapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;
import com.squareup.picasso.Picasso;

public class MotorcycleAppRecycleViewAdapter extends FirestoreRecyclerAdapter<Motorcycle, MotorcycleAppRecycleViewAdapter.MotorHolder> {

    private OnItemClickListener listener;
    public MotorcycleAppRecycleViewAdapter(@NonNull FirestoreRecyclerOptions<Motorcycle> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull MotorHolder holder, int position, @NonNull Motorcycle model) {
        holder.txtMotorName.setText(model.getName());
        holder.txtBrand.setText(model.getBrand());
        holder.txtDisplacement.setText(String.valueOf(model.getDisplacment()));

        Picasso.get()
                .load(model.getImageUrl())
                .into(holder.imgMotor);

    }

    @NonNull
    @Override
    public MotorHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v =LayoutInflater.from(parent.getContext()).inflate(R.layout.list_motorcycle,parent,false);
        return new MotorHolder(v);
    }
    public void deleteItemAdmin(int position){
        getSnapshots().getSnapshot(position).getReference().delete();
    }


    class MotorHolder extends RecyclerView.ViewHolder{

        private TextView txtBrand, txtDisplacement;
         ImageView imgMotor;
        private TextView txtMotorName;

        public MotorHolder(@NonNull View itemView) {
            super(itemView);
            imgMotor = itemView.findViewById(R.id.imageAllMotor);
            txtMotorName =itemView.findViewById(R.id.txtViewMotorName);
            txtBrand = itemView.findViewById(R.id.meisam);
            txtDisplacement = itemView.findViewById(R.id.meisam2);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getBindingAdapterPosition();
                    if(position != RecyclerView.NO_POSITION && listener != null){
                        listener.onItemClick(getSnapshots().getSnapshot(position),position);
                    }
                }
            });

        }
    }
    public interface OnItemClickListener{
        void onItemClick(DocumentSnapshot documentSnapshot, int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener =listener;
    }
}