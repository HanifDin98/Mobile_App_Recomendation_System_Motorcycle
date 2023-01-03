package com.Hanif.underboneapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class AllMotorcycleActivity extends AppCompatActivity {


    //private MotorcycleAppRecycleViewAdapter adapter;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    //private DocumentReference MotorRef = db.document("Underbone/1");
    private CollectionReference MotorColRef = db.collection("Underbone");
    //private CollectionReference MotorColRef2 = db.collection("Underbone");
    FirebaseFirestore rootRef = FirebaseFirestore.getInstance();
    private static final String TAG ="FirestoreSearch";
    private MotorcycleAppRecycleViewAdapter adapter;
    ProgressDialog pd;
    EditText searchEdit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_motorcycle);

        pd = new ProgressDialog(this);
        setUpRecyclerView();

        searchEdit = findViewById(R.id.searchid);
        searchEdit.addTextChangedListener(new TextWatcher() {
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
                    adapter.updateOptions(options);
                    adapter.notifyDataSetChanged();
                }
                else {
                    Query query = db.collection("Underbone").whereEqualTo("name",editable.toString());
                    FirestoreRecyclerOptions<Motorcycle>options = new FirestoreRecyclerOptions.Builder<Motorcycle>()
                            .setQuery(query,Motorcycle.class)
                            .build();
                    adapter.updateOptions(options);
                    adapter.notifyDataSetChanged();
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
        FirestoreRecyclerOptions<Motorcycle>options = new FirestoreRecyclerOptions.Builder<Motorcycle>()
                .setQuery(query,Motorcycle.class)
                .build();

        adapter = new MotorcycleAppRecycleViewAdapter(options);
        RecyclerView recyclerView = findViewById(R.id.MotorRecycleView);
        //recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new MotorcycleAppRecycleViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(DocumentSnapshot documentSnapshot, int position) {
                Motorcycle motor = documentSnapshot.toObject(Motorcycle.class);
                Intent intent = new Intent(AllMotorcycleActivity.this,MotorNextActivity.class);
                String id = documentSnapshot.getId();
                //intent.putExtra("docId",id);
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

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        //inflate menu
//        getMenuInflater().inflate(R.menu.menu_search,menu);
//        //searchView
//        MenuItem item =menu.findItem(R.id.action_Search);
//        SearchView searchView =(SearchView) MenuItemCompat.getActionView(item);
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String s) {
//                //called when we press search button
//                CollectionReference searchQ = (CollectionReference) db.collection("Underbone").whereEqualTo("search",s);
//                if(!s.trim().isEmpty()){
//                    setUpRecyclerView(searchQ);
//                }
//
//
//                //searchData(s);
//                 //querySearch =  db.collection("Underbone").whereEqualTo("search",s.toLowerCase());
//
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String s) {
//                searchData(s);
//                //called ass and when we type single letter
//                return false;
//            }
//        });
//        return super.onCreateOptionsMenu(menu);
//    }
//
//    private void searchData(String s) {
//
//
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        return super.onOptionsItemSelected(item);
//    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this,MainActivity.class));
    }
}