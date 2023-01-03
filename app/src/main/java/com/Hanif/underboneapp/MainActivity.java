package com.Hanif.underboneapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private DocumentReference MotorRef = db.document("Underbone/1");
    private CollectionReference MotorColRef = db.collection("Underbone");
    private Button bttnAllMotor,bttnInstruction,bttnFavourite,bttnProfile,bttnLogout;
    private TextView txtVerify;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user =firebaseAuth.getCurrentUser();
//        if(!user.isEmailVerified()){
//            txtVerify.setVisibility(View.VISIBLE);
//
//            txtVerify.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//
//                    user.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
//                        @Override
//                        public void onSuccess(Void aVoid) {
//                            Toast.makeText(MainActivity.this, "Verification Email Has been Sent", Toast.LENGTH_SHORT).show();
//                        }
//                    }).addOnFailureListener(new OnFailureListener() {
//                        @Override
//                        public void onFailure(@NonNull Exception e) {
//                            Log.d("tag", "onFailure: Email not sent" + e.getMessage());
//                        }
//                    });
//                }
//            });
//        }


        bttnAllMotor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this, "Go to all moto", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this,AllMotorcycleActivity.class);
                startActivity(intent);
            }
        });
        bttnFavourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this, "Go to Favourite", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this,favourite_list.class);
                startActivity(intent);
            }
        });

        bttnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(),Login.class));
                finish();
            }
        });

        bttnInstruction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Go to User Instruction", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this,InstructionUser.class);
                startActivity(intent);
            }
        });

        bttnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,ShowProfile.class);
                startActivity(intent);
            }
        });

    }

    private void initView() {

        bttnAllMotor =findViewById(R.id.bttnAllMotorcycle);
        bttnFavourite = findViewById(R.id.buttonFav);
        bttnProfile = findViewById(R.id.bttnProfile);
        bttnLogout = findViewById(R.id.buttonLogoutID);
        bttnInstruction = findViewById(R.id.bttnSpecification);

        txtVerify = findViewById(R.id.txtVerify);


    }

    @Override
    public void onBackPressed() {
        //NONE
        Toast.makeText(this, "Please logout ", Toast.LENGTH_SHORT).show();
    }
}