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

public class FavAdapter extends FirestoreRecyclerAdapter<Favourite, FavAdapter.FavHolder> {

    private OnItemClickListener2 listener;
    public FavAdapter(@NonNull FirestoreRecyclerOptions<Favourite> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull FavHolder holder, int position, @NonNull Favourite model) {
            holder.txtMotorName.setText(model.getName());
            holder.txtBrand.setText(model.getBrand());
            holder.txtDisplacement.setText(String.valueOf(model.getDisplacment()));
        Picasso.get()
                .load(model.getImageUrl())
                .into(holder.imgMotor);
    }

    @NonNull
    @Override
    public FavHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v1 = LayoutInflater.from(parent.getContext()).inflate(R.layout.fav_item,parent,false);
        return new FavHolder(v1);
    }
    public void deleteItem(int position){
        getSnapshots().getSnapshot(position).getReference().delete();
    }

    class FavHolder extends RecyclerView.ViewHolder{
        private TextView txtBrand, txtDisplacement;
        ImageView imgMotor;
        private TextView txtMotorName;
        public FavHolder(@NonNull View itemView) {
            super(itemView);
            imgMotor = itemView.findViewById(R.id.imageAllMotorfav);
            txtMotorName =itemView.findViewById(R.id.motorNamefav);
            txtBrand = itemView.findViewById(R.id.meisamfav);
            txtDisplacement = itemView.findViewById(R.id.meisam2fav);
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
    public interface OnItemClickListener2{
        void onItemClick(DocumentSnapshot documentSnapshot, int position);
    }
    public void setOnItemClickListener2(OnItemClickListener2 listener){
        this.listener =listener;
    }
}
