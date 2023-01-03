package com.Hanif.underboneapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class ShowProfileAdmin extends AppCompatActivity {
    TextView textView1, textView2, textView3;
    Button buttonEditProfile;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;
    String userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_profile_admin);
        textView1 =findViewById(R.id.ViewregisterNameIdAd);
        textView2 = findViewById(R.id.ViewRegisterEmailIdAd);
        //textView3 = findViewById(R.id.ViewRegisterPhoneAd);
        buttonEditProfile = findViewById(R.id.buttonEditProfile1Ad);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();

        userId = firebaseAuth.getCurrentUser().getUid();

        DocumentReference documentReference = firebaseFirestore.collection("Users").document(userId);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                //textView3.setText(value.getString("phoneNum"));
                textView1.setText(value.getString("name"));
                textView2.setText(value.getString("email"));


            }
        });
        buttonEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(),EditProfileAdmin.class);
                i.putExtra("fullName",textView1.getText().toString());
                i.putExtra("email",textView2.getText().toString());
                //i.putExtra("phone",textView3.getText().toString());
                startActivity(i);
            }
        });
    }
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this,AdminMainActivity.class));
    }
}