 package com.Hanif.underboneapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {
    public static final String TAG = "TAG";
    EditText RegName,RegEmail,RegPassword;
    Button Regbttn;
    TextView toLogin;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;
    String UserId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        this.setTitle("Register");
        RegName = findViewById(R.id.registerNameId);
        RegEmail = findViewById(R.id.RegisterEmailId);
        RegPassword = findViewById(R.id.RegisterPasswordId);
        //RegPhone = findViewById(R.id.RegisterPhone);
        toLogin = findViewById(R.id.toLoginId);
        Regbttn = findViewById(R.id.buttonRegister);
        firebaseAuth =FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();

        if(firebaseAuth.getCurrentUser()!=null){
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        }
        Regbttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = RegEmail.getText().toString().trim();
                String password = RegPassword.getText().toString().trim();
                //for firestore
                //String phoneNum = RegPhone.getText().toString();
                String name = RegName.getText().toString();

                if(TextUtils.isEmpty(name)){
                    RegName.setError("Name Required");
                    return;
                }
                if(TextUtils.isEmpty(email)){
                    RegEmail.setError("Email Required");
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    RegPassword.setError("Password Required");
                    return;
                }
                if(password.length()<6){
                    RegPassword.setError("Password must be greater than 6 character");
                    return;
                }
                firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            //send verifacation link
                            FirebaseUser user1 = firebaseAuth.getCurrentUser();
                            user1.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(Register.this, "Verification Email Has been Sent ", Toast.LENGTH_SHORT).show();
                                    //Toast.makeText(Register.this, "'Click EMAIL NOT VERIFIED TEXT' to if not received email", Toast.LENGTH_LONG).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d(TAG, "onFailure: Email not sent" + e.getMessage());
                                }
                            });

                            Toast.makeText(Register.this, "User Create", Toast.LENGTH_SHORT).show();
                            UserId =firebaseAuth.getCurrentUser().getUid();
                            DocumentReference documentReference = firebaseFirestore.collection("Users").document(UserId);

                            Map<String, Object> user =new HashMap<>();
                            user.put("name",name);
                            user.put("email",email);
                            //user.put("phoneNum",phoneNum);
                            user.put("isUser","1");
                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d(TAG, "onSuccess: user profile is created"+UserId);
                                }
                            });
                            startActivity(new Intent(getApplicationContext(),Login.class));
                        }
                        else{
                            Toast.makeText(Register.this, "Error in register"+ task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    }
                });
            }
        });

        toLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Login.class));
            }
        });
    }
    public void onBackPressed() {
        finish();
    }
}