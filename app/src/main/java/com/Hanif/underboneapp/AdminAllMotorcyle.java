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
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class AdminAllMotorcyle extends AppCompatActivity {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    //private DocumentReference MotorRef = db.document("Underbone/1");
    private CollectionReference MotorColRef = db.collection("Underbone");
    private MotorcycleAppRecycleViewAdapter adapterAdmin;
    EditText searchEdit1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_all_motorcyle);
        FloatingActionButton bttnAddMotor = findViewById(R.id.bttnAddMotor);
        bttnAddMotor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminAllMotorcyle.this,AddMotorycle.class));
            }
        });
        setUpRecyclerView();
        searchEdit1 = findViewById(R.id.searchid1);
        searchEdit1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                //Log.d(TAG, "Searchbox has chaged to: "+ editable.toString());
                if(editable.toString().isEmpty()){
                    Query query = db.collection("Underbone");
                    FirestoreRecyclerOptions<Motorcycle>options = new FirestoreRecyclerOptions.Builder<Motorcycle>()
                            .setQuery(query,Motorcycle.class)
                            .build();
                    adapterAdmin.updateOptions(options);
                    adapterAdmin.notifyDataSetChanged();
                }
                else {
                    Query query = db.collection("Underbone").whereEqualTo("name",editable.toString());
                    FirestoreRecyclerOptions<Motorcycle>options = new FirestoreRecyclerOptions.Builder<Motorcycle>()
                            .setQuery(query,Motorcycle.class)
                            .build();
                    adapterAdmin.updateOptions(options);
                    adapterAdmin.notifyDataSetChanged();
                }

                //adapter.startListening();
//                RecyclerView recyclerView = findViewById(R.id.MotorRecycleView);
//                recyclerView.setLayoutManager(new LinearLayoutManager(AllMotorcycleActivity.this));
//                recyclerView.setAdapter(adapter);
            }
        });
    }

    private void setUpRecyclerView() {
        Query query = MotorColRef;
        FirestoreRecyclerOptions<Motorcycle> options = new FirestoreRecyclerOptions.Builder<Motorcycle>()
                .setQuery(query,Motorcycle.class)
                .build();

        adapterAdmin = new MotorcycleAppRecycleViewAdapter(options);
        RecyclerView recyclerView = findViewById(R.id.AdminMotorRecycleView);
        //recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapterAdmin);

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
                                adapterAdmin.deleteItemAdmin(viewHolder.getBindingAdapterPosition());
                            }
                        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        adapterAdmin.notifyItemChanged(viewHolder.getBindingAdapterPosition());
                    }
                }).create()
                        .show();

            }
        }).attachToRecyclerView(recyclerView);

        adapterAdmin.setOnItemClickListener(new MotorcycleAppRecycleViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(DocumentSnapshot documentSnapshot, int position) {
                Motorcycle motor = documentSnapshot.toObject(Motorcycle.class);
                Intent intent = new Intent(AdminAllMotorcyle.this,MotorNextActivityAdmin.class);

                String id = documentSnapshot.getId();
                intent.putExtra("docId",id);
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
//                private String name;
//                private String brand;
//                private String EngineType;
//                private String EngineCooling;
//                private int displacment;
//                private double fuelTankCapacity;
//                private String startingSystem;
//                private String frameType;
//                private double maxPower;
//                private String transmission;
//                private String imageUrl;

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapterAdmin.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapterAdmin.stopListening();
    }
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this,AdminMainActivity.class));
    }
}