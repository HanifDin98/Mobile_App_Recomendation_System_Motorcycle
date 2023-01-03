package com.Hanif.underboneapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class favourite_list extends AppCompatActivity {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference MotorColRef = db.collection("Underbone");
    private CollectionReference Fav = db.collection("Users");
    private DocumentReference doRef;
    private FirebaseAuth firebaseAuth;
    private FavAdapter adapter2;
    private String userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite_list);
        FloatingActionButton bttnquestionFav = findViewById(R.id.questionFav);
        bttnquestionFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               new AlertDialog.Builder(view.getContext())
                       .setMessage("Swipe left or right to delete")
                       .setPositiveButton("Got it", new DialogInterface.OnClickListener() {
                           @Override
                           public void onClick(DialogInterface dialogInterface, int i) {
                               dialogInterface.cancel();
                           }
                       }).create()
                       .show();
            }
        });

        setUpFavRecyclerView();
    }

    private void setUpFavRecyclerView() {
        firebaseAuth =FirebaseAuth.getInstance();
        userId = firebaseAuth.getCurrentUser().getUid();




       Query query = Fav.document(userId).collection("Favourite");

        FirestoreRecyclerOptions<Favourite>options = new FirestoreRecyclerOptions.Builder<Favourite>()
                .setQuery(query,Favourite.class)
                .build();

        adapter2 = new FavAdapter(options);
        RecyclerView recyclerView = findViewById(R.id.FavouriteRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter2);

        //delete method process
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT ) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                new AlertDialog.Builder(viewHolder.itemView.getContext())
                        .setMessage("Are you sure to delete?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                adapter2.deleteItem(viewHolder.getBindingAdapterPosition());
                            }
                        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        adapter2.notifyItemChanged(viewHolder.getBindingAdapterPosition());
                    }
                }).create()
                        .show();


            }
        }).attachToRecyclerView(recyclerView);

        adapter2.setOnItemClickListener2(new FavAdapter.OnItemClickListener2() {
            @Override
            public void onItemClick(DocumentSnapshot documentSnapshot, int position) {
                Motorcycle motor = documentSnapshot.toObject(Motorcycle.class);
                Intent intent = new Intent(favourite_list.this,MotorNextActivityFav.class);
                String id = documentSnapshot.getId();
                intent.putExtra("name",motor.getName());
                intent.putExtra("brand",motor.getBrand());
                intent.putExtra("EngineType",motor.getEngineType());
                intent.putExtra("EngineCooling",motor.getEngineCooling());
                intent.putExtra("displacment",motor.getDisplacment());
                intent.putExtra("fuelTankCapacity",motor.getFuelTankCapacity());
                intent.putExtra("transmission",motor.getTransmission());
                intent.putExtra("imageUrl",motor.getImageUrl());
                intent.putExtra("maxPower",motor.getMaxPower());
                intent.putExtra("startingSystem",motor.getStartingSystem());
                intent.putExtra("frameType",motor.getFrameType());
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter2.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter2.stopListening();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this,MainActivity.class));
    }
}