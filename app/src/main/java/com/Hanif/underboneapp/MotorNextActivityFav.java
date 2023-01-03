package com.Hanif.underboneapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

public class MotorNextActivityFav extends AppCompatActivity {


    private TextView txtMotorNameA, txtBrandNameA,txtEngineTypeA,txtEngineCoolingA,txtDisplacementA,txtFuelTankA,txtStartingA,txtFrameA,txtMaxA,txtTransmissionA;
    private Button btnAddFavourA;
    private Button btnRecommendation;
    private ImageView motorImageA;
    private FirebaseFirestore db =FirebaseFirestore.getInstance();
    private CollectionReference MotorColRef = db.collection("Underbone");
    private CollectionReference Fav = db.collection("Users");
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_motor_next_fav);
        this.setTitle("Favourite Specification");
        firebaseAuth = FirebaseAuth.getInstance();
        txtMotorNameA = findViewById(R.id.txtAnswerNameMotorfav);
        txtMotorNameA.setText(getIntent().getStringExtra("name"));
        String favName = getIntent().getStringExtra("name");
        //String RecName = getIntent().getStringExtra("name");
        txtBrandNameA = findViewById(R.id.txtAnswerBrandNamefav);
        txtBrandNameA.setText(getIntent().getStringExtra("brand"));


        txtDisplacementA= findViewById(R.id.txtAnswerDisplacementfav);
        txtDisplacementA.setText(String.valueOf(getIntent().getIntExtra("displacment",0)));


        txtEngineTypeA = findViewById(R.id.txtAnswerEngineTypefav);
        txtEngineTypeA.setText(getIntent().getStringExtra("EngineType"));


        txtEngineCoolingA=findViewById(R.id.txtEngineCoolingAnswerfav);
        txtEngineCoolingA.setText(getIntent().getStringExtra("EngineCooling"));


        txtFuelTankA = findViewById(R.id.txtAnswerFuelfav);
        txtFuelTankA.setText(String.valueOf(getIntent().getDoubleExtra("fuelTankCapacity",0.0)));


        txtStartingA = findViewById(R.id.txtStartingsystemAnswerfav);
        txtStartingA .setText(getIntent().getStringExtra("startingSystem"));


        txtFrameA = findViewById(R.id.txtFrameAnswerfav);
        txtFrameA.setText(getIntent().getStringExtra("frameType"));


        txtMaxA = findViewById(R.id.txtPowerAnswerfav);
        txtMaxA.setText(String.valueOf(getIntent().getDoubleExtra("maxPower",0.0)));


        txtTransmissionA = findViewById(R.id.txtTransmissionAnswerfav);
        txtTransmissionA.setText(getIntent().getStringExtra("transmission"));


        motorImageA = findViewById(R.id.imageMotorfav);
        Picasso.get()
                .load(getIntent().getStringExtra("imageUrl"))
                .into(motorImageA);

        btnRecommendation = findViewById(R.id.bttnSimilarMotorfav);
        btnRecommendation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MotorNextActivityFav.this,Recomendation.class);
                intent.putExtra("NameRec",favName);
                startActivity(intent);
            }
        });
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this,favourite_list.class));
    }
}