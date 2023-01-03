package com.Hanif.underboneapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

public class MotorNextActivity extends AppCompatActivity {

    private TextView txtMotorNameA, txtBrandNameA,txtEngineTypeA,txtEngineCoolingA,txtDisplacementA,txtFuelTankA,txtStartingA,txtFrameA,txtMaxA,txtTransmissionA;
    private Button btnAddFavourA;
    private Button btnRecommendation;
    private ImageView motorImageA;
    private FirebaseFirestore db =FirebaseFirestore.getInstance();
    private CollectionReference MotorColRef = db.collection("Underbone");
    private CollectionReference Fav = db.collection("Users");

    private FirebaseAuth firebaseAuth;
    private String UserId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_motor_next);
        firebaseAuth = FirebaseAuth.getInstance();
        txtMotorNameA = findViewById(R.id.txtAnswerNameMotor);
        txtMotorNameA.setText(getIntent().getStringExtra("name"));
        String favName = getIntent().getStringExtra("name");
        //String RecName = getIntent().getStringExtra("name");
        txtBrandNameA = findViewById(R.id.txtAnswerBrandName);
        txtBrandNameA.setText(getIntent().getStringExtra("brand"));
        String  favbrand = getIntent().getStringExtra("brand");

        txtDisplacementA= findViewById(R.id.txtAnswerDisplacement);
        txtDisplacementA.setText(String.valueOf(getIntent().getIntExtra("displacment",0)));
        int favdisplacement = getIntent().getIntExtra("displacment",0);

        txtEngineTypeA = findViewById(R.id.txtAnswerEngineType);
        txtEngineTypeA.setText(getIntent().getStringExtra("EngineType"));
        String  favEngineType = getIntent().getStringExtra("EngineType");

        txtEngineCoolingA=findViewById(R.id.txtEngineCoolingAnswer);
        txtEngineCoolingA.setText(getIntent().getStringExtra("EngineCooling"));
        String favEngineCooling = getIntent().getStringExtra("EngineCooling");

        txtFuelTankA = findViewById(R.id.txtAnswerFuel);
        txtFuelTankA.setText(String.valueOf(getIntent().getDoubleExtra("fuelTankCapacity",0.0)));
        double favTank = getIntent().getDoubleExtra("fuelTankCapacity",0.0);

        txtStartingA = findViewById(R.id.txtStartingsystemAnswer);
        txtStartingA .setText(getIntent().getStringExtra("startingSystem"));
        String favStarting = getIntent().getStringExtra("startingSystem");

        txtFrameA = findViewById(R.id.txtFrameAnswer);
        txtFrameA.setText(getIntent().getStringExtra("frameType"));
        String favFrame = getIntent().getStringExtra("frameType");

        txtMaxA = findViewById(R.id.txtPowerAnswer);
        txtMaxA.setText(String.valueOf(getIntent().getDoubleExtra("maxPower",0.0)));
        double favPower = getIntent().getDoubleExtra("maxPower",0.0);

        txtTransmissionA = findViewById(R.id.txtTransmissionAnswer);
        txtTransmissionA.setText(getIntent().getStringExtra("transmission"));
        String favTransmission = getIntent().getStringExtra("transmission");

        motorImageA = findViewById(R.id.imageMotor);
        Picasso.get()
                .load(getIntent().getStringExtra("imageUrl"))
                .into(motorImageA);
//                .load(model.getImageUrl())
//                .into(holder.imgMotor);

        String favImageUrl = getIntent().getStringExtra("imageUrl");
        btnAddFavourA = findViewById(R.id.btnAddToFav);
        btnAddFavourA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // UserId =firebaseAuth.getCurrentUser().getUid();
                Toast.makeText(MotorNextActivity.this, "Add to favourite", Toast.LENGTH_SHORT).show();
                Fav.document(firebaseAuth.getCurrentUser().getUid()).collection("Favourite")
                        .add(new Favourite(favName,favbrand,favEngineType,favEngineCooling,favdisplacement,favTank,favStarting,favFrame,favPower,favTransmission,favImageUrl));

                //    db.collection("Users").document(UserId);
//                CollectionReference fav = FirebaseFirestore.getInstance().collection("Favourite");
//                fav.add(new Favourite(UserId));
            }
        });

        btnRecommendation =findViewById(R.id.bttnSimilarMotor);
        btnRecommendation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MotorNextActivity.this, "Click Motor Name to Copy", Toast.LENGTH_SHORT).show();
                Intent intent =new Intent(MotorNextActivity.this,Recomendation.class);
                intent.putExtra("NameRec",favName);
                startActivity(intent);

            }
        });


//        intent.putExtra("name",motor.getName());
//        intent.putExtra("brand",motor.getBrand());
//        intent.putExtra("EngineType",motor.getEngineType());
//        intent.putExtra("EngineCooling",motor.getEngineCooling());
//        intent.putExtra("displacment",motor.getDisplacment());
//        intent.putExtra("fuelTankCapacity",motor.getFuelTankCapacity());
//        intent.putExtra("transmission",motor.getTransmission());
//        intent.putExtra("imageUrl",motor.getImageUrl());
//        intent.putExtra("maxPower",motor.getMaxPower());
//        intent.putExtra("startingSystem",motor.getStartingSystem());
//        intent.putExtra("frameType",motor.getFrameType());



    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this,AllMotorcycleActivity.class));
    }
}