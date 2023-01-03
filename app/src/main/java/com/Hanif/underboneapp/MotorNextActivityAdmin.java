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

public class MotorNextActivityAdmin extends AppCompatActivity {
    private TextView txtMotorNameA, txtBrandNameA,txtEngineTypeA,txtEngineCoolingA,txtDisplacementA,txtFuelTankA,txtStartingA,txtFrameA,txtMaxA,txtTransmissionA;
    private Button btnEdit;
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
        setContentView(R.layout.activity_motor_next_admin);
        firebaseAuth = FirebaseAuth.getInstance();

        UserId = getIntent().getStringExtra("docId");

        txtMotorNameA = findViewById(R.id.txtAnswerNameMotorAdmin);
        txtMotorNameA.setText(getIntent().getStringExtra("name"));
        String favName = getIntent().getStringExtra("name");
        //String RecName = getIntent().getStringExtra("name");
        txtBrandNameA = findViewById(R.id.txtAnswerBrandNameAdmin);
        txtBrandNameA.setText(getIntent().getStringExtra("brand"));
        String  favbrand = getIntent().getStringExtra("brand");

        txtDisplacementA= findViewById(R.id.txtAnswerDisplacementAdmin);
        txtDisplacementA.setText(String.valueOf(getIntent().getIntExtra("displacment",0)));
        int favdisplacement = getIntent().getIntExtra("displacment",0);

        txtEngineTypeA = findViewById(R.id.txtAnswerEngineTypeAdmin);
        txtEngineTypeA.setText(getIntent().getStringExtra("EngineType"));
        String  favEngineType = getIntent().getStringExtra("EngineType");

        txtEngineCoolingA=findViewById(R.id.txtEngineCoolingAnswerAdmin);
        txtEngineCoolingA.setText(getIntent().getStringExtra("EngineCooling"));
        String favEngineCooling = getIntent().getStringExtra("EngineCooling");

        txtFuelTankA = findViewById(R.id.txtAnswerFuelAdmin);
        txtFuelTankA.setText(String.valueOf(getIntent().getDoubleExtra("fuelTankCapacity",0.0)));
        double favTank = getIntent().getDoubleExtra("fuelTankCapacity",0.0);

        txtStartingA = findViewById(R.id.txtStartingsystemAnswerAdmin);
        txtStartingA .setText(getIntent().getStringExtra("startingSystem"));
        String favStarting = getIntent().getStringExtra("startingSystem");

        txtFrameA = findViewById(R.id.txtFrameAnswerAdmin);
        txtFrameA.setText(getIntent().getStringExtra("frameType"));
        String favFrame = getIntent().getStringExtra("frameType");

        txtMaxA = findViewById(R.id.txtPowerAnswerAdmin);
        txtMaxA.setText(String.valueOf(getIntent().getDoubleExtra("maxPower",0.0)));
        double favPower = getIntent().getDoubleExtra("maxPower",0.0);

        txtTransmissionA = findViewById(R.id.txtTransmissionAnswerAdmin);
        txtTransmissionA.setText(getIntent().getStringExtra("transmission"));
        String favTransmission = getIntent().getStringExtra("transmission");

        motorImageA = findViewById(R.id.imageMotorAdmin);
        Picasso.get()
                .load(getIntent().getStringExtra("imageUrl"))
                .into(motorImageA);
//                .load(model.getImageUrl())
//                .into(holder.imgMotor);

        String favImageUrl = getIntent().getStringExtra("imageUrl");

//        btnRecommendation =findViewById(R.id.bttnSimilarMotor);
//        btnRecommendation.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // Toast.makeText(MotorNextActivity.this, "next", Toast.LENGTH_SHORT).show();
//                Intent intent =new Intent(MotorNextActivityAdmin.this,Recomendation.class);
//                intent.putExtra("NameRec",favName);
//                startActivity(intent);
//            }
//        });
        btnEdit = findViewById(R.id.bttnEditMotor);
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(MotorNextActivityAdmin.this, "Go to edit", Toast.LENGTH_SHORT).show();
                Intent intent =new Intent(MotorNextActivityAdmin.this,EditMotor.class);
                intent.putExtra("Id",UserId);
                intent.putExtra("name1",favName);
                intent.putExtra("brand1",favbrand);
                intent.putExtra("enginType1",favEngineType);
                intent.putExtra("engineCooling1",favEngineCooling);
                intent.putExtra("displacement1",favdisplacement);//
                intent.putExtra("tank1",favTank);//
                intent.putExtra("starting1",favStarting);
                intent.putExtra("frame1",favFrame);
                intent.putExtra("power1",favPower);//
                intent.putExtra("trans",favTransmission);
                intent.putExtra("image1",favImageUrl);
                startActivity(intent);
                 //.add(new Favourite(favName,favbrand,favEngineType,favEngineCooling,favdisplacement,favTank,favStarting,favFrame,favPower,favTransmission,favImageUrl));

            }
        });
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this,AdminAllMotorcyle.class));
    }
}