package com.Hanif.underboneapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class EditMotor extends AppCompatActivity {
    private EditText txtMotorNameA, txtBrandNameA,txtEngineTypeA,txtEngineCoolingA,txtDisplacementA,txtFuelTankA
            ,txtStartingA,txtFrameA,txtMaxA,txtTransmissionA,ImageURL,txtid;
    private Button bttnEditSave;

    FirebaseFirestore firebaseFirestore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_motor);
        txtMotorNameA =findViewById(R.id.EdName);
        txtBrandNameA = findViewById(R.id.EdBrand);
        txtDisplacementA = findViewById(R.id.EdDisplacement);
       // String ed_dis = txtDisplacementA.getText().toString().trim();
        txtEngineCoolingA = findViewById(R.id.EdEngineCooling);
        txtEngineTypeA = findViewById(R.id.EdEngineType);
        txtFuelTankA = findViewById(R.id.EdFuelCapacity);
        txtStartingA = findViewById(R.id.EdStartingSystem);
        txtFrameA = findViewById(R.id.EdFrameType);
        txtMaxA = findViewById(R.id.EdMaxPower);
        txtTransmissionA = findViewById(R.id.EdTransmission);
        ImageURL = findViewById(R.id.EdImageURI);
        bttnEditSave = findViewById(R.id.bttnEditSave);
//        txtid =findViewById(R.id.EdId);

//        intent.putExtra("Id",UserId);
//        intent.putExtra("name1",favName);
//        intent.putExtra("brand1",favbrand);
//        intent.putExtra("enginType1",favEngineType);
//        intent.putExtra("engineCooling1",favEngineCooling);
//        intent.putExtra("displacement1",favdisplacement);//
//        intent.putExtra("tank1",favTank);//
//        intent.putExtra("starting1",favStarting);
//        intent.putExtra("frame1",favFrame);
//        intent.putExtra("power1",favPower);//
//        intent.putExtra("trans",favTransmission);
//        intent.putExtra("image1",favImageUrl);

        Intent data1 = getIntent();
        String id = data1.getStringExtra("Id");
        String name = data1.getStringExtra("name1");
        //String search = name.toLowerCase();
        String brand = data1.getStringExtra("brand1");
        int displacement = data1.getIntExtra("displacement1",0);
        String EngineType = data1.getStringExtra("enginType1");
        String EngineCooling = data1.getStringExtra("engineCooling1");
        double fuelTank = data1.getDoubleExtra("tank1",0.0);
        double MaxPower = data1.getDoubleExtra("power1",0.0);
        String starting = data1.getStringExtra("starting1");
        String frame = data1.getStringExtra("frame1");
        String transmission =data1.getStringExtra("trans");
        String imageUrl = data1.getStringExtra("image1");
        firebaseFirestore = FirebaseFirestore.getInstance();

        bttnEditSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new AlertDialog.Builder(view.getContext())
                        .setMessage("Are you sure to edit this motorcycle?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if(txtMotorNameA.getText().toString().isEmpty()|| txtBrandNameA.getText().toString().isEmpty()|| txtEngineTypeA.getText().toString().isEmpty()
                                        ||txtStartingA.getText().toString().isEmpty()||txtFrameA.getText().toString().isEmpty()||txtTransmissionA.getText().toString().isEmpty()
                                        ||ImageURL.getText().toString().isEmpty()|| TextUtils.isEmpty(txtDisplacementA.getText().toString())
                                        ||TextUtils.isEmpty(txtFuelTankA.getText().toString())||TextUtils.isEmpty(txtMaxA.getText().toString())){
                                    Toast.makeText(EditMotor.this, "One of the field is empty", Toast.LENGTH_SHORT).show();
                                    return;
                                }

                                DocumentReference documentReference =firebaseFirestore.collection("Underbone").document(id);
                                Map<String,Object> edited = new HashMap<>();
                                edited.put("name",txtMotorNameA.getText().toString());
                                edited.put("brand",txtBrandNameA.getText().toString());
                                edited.put("engineType",txtEngineTypeA.getText().toString());
                                edited.put("engineCooling",txtEngineCoolingA.getText().toString());
                                edited.put("displacment",Integer.parseInt(txtDisplacementA.getText().toString()));
                                edited.put("fuelTankCapacity",Double.parseDouble(txtFuelTankA.getText().toString()));
                                edited.put("startingSystem",txtStartingA.getText().toString());
                                edited.put("frameType",txtFrameA.getText().toString());
                                edited.put("maxPower",Double.parseDouble(txtMaxA.getText().toString()));
                                edited.put("transmission",txtTransmissionA.getText().toString());
                                edited.put("imageUrl",ImageURL.getText().toString());
                                //edited.put("search",search);
                                documentReference.update(edited).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Toast.makeText(EditMotor.this, "Motorcycle Update", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(getApplicationContext(),AdminMainActivity.class));
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(EditMotor.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }).create().show();





            }
        });
        //        private EditText txtMotorNameA, txtBrandNameA,txtEngineTypeA,txtEngineCoolingA,txtDisplacementA,txtFuelTankA
//                ,txtStartingA,txtFrameA,txtMaxA,txtTransmissionA,ImageURL;
        //txtid.setText(id);
        txtMotorNameA.setText(name);
        txtBrandNameA.setText(brand);
        txtEngineTypeA.setText(EngineType);
        txtEngineCoolingA.setText(EngineCooling);
        //txtDisplacementA.setText(String.valueOf(getIntent().getIntExtra("displacment1",0)));
        txtDisplacementA.setText(String.valueOf(displacement));
        txtFuelTankA.setText(String.valueOf(fuelTank));
        txtStartingA.setText(starting);
        txtFrameA.setText(frame);
        txtMaxA.setText(String.valueOf(MaxPower));
        txtTransmissionA.setText(transmission);
        ImageURL.setText(imageUrl);




//        private EditText txtMotorNameA, txtBrandNameA,txtEngineTypeA,txtEngineCoolingA,txtDisplacementA,txtFuelTankA
//                ,txtStartingA,txtFrameA,txtMaxA,txtTransmissionA,ImageURL;
//        String name = txtMotorNameA.getText().toString();
//        String brand = txtBrandNameA.getText().toString();
//        int displacement = Integer.parseInt(txtDisplacementA.getText().toString());
//        String EngineType = txtEngineTypeA.getText().toString();
//        String EngineCooling = txtEngineCoolingA.getText().toString();
//        double fuelTank = Double.parseDouble(txtFuelTankA.getText().toString());
//        double MaxPower = Double.parseDouble(txtMaxA.getText().toString());
//        String starting = txtStartingA.getText().toString();
//        String frame = txtFrameA.getText().toString();
//        String transmission = txtTransmissionA.getText().toString();
//        String image = ImageURL.getText().toString();
    }
}