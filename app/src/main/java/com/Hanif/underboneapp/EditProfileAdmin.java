package com.Hanif.underboneapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import static com.Hanif.underboneapp.Register.TAG;

public class EditProfileAdmin extends AppCompatActivity {
    EditText editText1,editText2;
    FirebaseAuth firebaseAuth;
    TextView resetPassword;
    FirebaseFirestore firebaseFirestore;
    FirebaseUser user;
    private Button bttnsave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile_admin);
        editText1 = findViewById(R.id.EditregisterNameIdAd);
        editText2 = findViewById(R.id.EditRegisterEmailIdAd);
        //editText3 = findViewById(R.id.EditRegisterPhoneAd);
        bttnsave = findViewById(R.id.buttonSaveAd);
        resetPassword = findViewById(R.id.toResetProfileIdAd);
        this.setTitle("Edit Admin Profile");

        Intent data = getIntent();
        String fullname = data.getStringExtra("fullName");
        String email = data.getStringExtra("email");
        //String phone = data.getStringExtra("phone");
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore =FirebaseFirestore.getInstance();
        user =firebaseAuth.getCurrentUser();

        resetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText resetMail = new EditText(view.getContext());
                AlertDialog.Builder passwordReset = new AlertDialog.Builder(view.getContext());
                passwordReset.setMessage("Enter Your Email To Received Reset Link");
                passwordReset.setView(resetMail);

                passwordReset.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String mail = resetMail.getText().toString();
                        firebaseAuth.sendPasswordResetEmail(mail).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(EditProfileAdmin.this, "Reset Link Sent To your Email", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(EditProfileAdmin.this, "Error ! Reset Link Not Send" + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });

                passwordReset.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                passwordReset.create().show();
            }
        });

        bttnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editText1.getText().toString().isEmpty()|| editText2.getText().toString().isEmpty()){
                    Toast.makeText(EditProfileAdmin.this, "One of the field is empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                String email1 = editText2.getText().toString();
                user.updateEmail(email1).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        DocumentReference documentReference = firebaseFirestore.collection("Users").document(user.getUid());
                        Map<String,Object> edited = new HashMap<>();
                        edited.put("email",email1);
                        user.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(EditProfileAdmin.this, "Verification Email Has been Sent ", Toast.LENGTH_SHORT).show();
                                //Toast.makeText(Register.this, "'Click EMAIL NOT VERIFIED TEXT' to if not received email", Toast.LENGTH_LONG).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.d(TAG, "onFailure: Email not sent" + e.getMessage());
                            }
                        });
                        edited.put("name",editText1.getText().toString());
                        //edited.put("phoneNum",editText3.getText().toString());
                        documentReference.update(edited).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(EditProfileAdmin.this, "Profile Update", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(),ShowProfileAdmin.class));
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(EditProfileAdmin.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                        //Toast.makeText(EditProfile.this, "email changed", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(EditProfileAdmin.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        editText1.setText(fullname);
        editText2.setText(email);
        //editText3.setText(phone);
    }
}