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

public class AdminMainActivity extends AppCompatActivity {
    private Button logout, AllMotor,AdminProfile,Instruction;
    private TextView txtVerify;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main);

        logout =findViewById(R.id.buttonlogut);
        AllMotor = findViewById(R.id.bttnAllMotorcycleAdmin);
        AdminProfile = findViewById(R.id.bttnAdminProfile);
        txtVerify = findViewById(R.id.txtVerify1);
        Instruction = findViewById(R.id.bttnAdminAbout);

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
//                            Toast.makeText(AdminMainActivity.this, "Varificatuibb Email Has been Sent", Toast.LENGTH_SHORT).show();
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

        AllMotor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(AdminMainActivity.this, "To delete motorcycle swipe card to left or right", Toast.LENGTH_LONG).show();
                startActivity(new Intent(getApplicationContext(),AdminAllMotorcyle.class));
                finish();
            }
        });

        AdminProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(AdminMainActivity.this, "Go to Admin Profile", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(AdminMainActivity.this,ShowProfileAdmin.class);
                startActivity(intent);
            }
        });



        Instruction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(AdminMainActivity.this, "Go to instruction page", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(AdminMainActivity.this,InstructionAdmin.class);
                startActivity(intent);
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(),Login.class));
                finish();
            }
        });



    }
    public void onBackPressed() {
        //NONE
        Toast.makeText(this, "Please logout ", Toast.LENGTH_SHORT).show();
    }
}