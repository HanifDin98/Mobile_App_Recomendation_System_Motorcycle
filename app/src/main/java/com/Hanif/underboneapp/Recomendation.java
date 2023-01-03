 package com.Hanif.underboneapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Recomendation extends AppCompatActivity {
    private TextView NameMotor,Rec1,Rec2,Rec3,Rec4,Rec5;
    private Button bttntoSearch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recomendation);
        FloatingActionButton bttnquestionRec = findViewById(R.id.questionRec);
        bttnquestionRec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(view.getContext())
                        .setMessage("Click name of the Motorcycle to copy")
                        .setPositiveButton("Got it", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        }).create()
                        .show();
            }
        });
        NameMotor = findViewById(R.id.in1);
        Rec1 = findViewById(R.id.OneAnswer);
        //Rec1.setTextIsSelectable(true);
        Rec2 = findViewById(R.id.TwoAnswer);
        //Rec2.setTextIsSelectable(true);
        Rec3 = findViewById(R.id.ThreeAnswer);
       // Rec3.setTextIsSelectable(true);
        Rec4 = findViewById(R.id.FourAnswer);
       // Rec4.setTextIsSelectable(true);
        Rec5 = findViewById(R.id.FiveAnswer);
        //Rec5.setTextIsSelectable(true);
        bttntoSearch = findViewById(R.id.bttntoSearch);
        //NameRec
        Bundle bundle = getIntent().getExtras();
        String name = bundle.getString("NameRec");
        NameMotor.setText(name);

        if(!Python.isStarted()){
            Python.start(new AndroidPlatform(this));
        }
        Python py= Python.getInstance();
        final Python pyobj = Python.getInstance();

        PyObject pyo = pyobj.getModule("main");
        PyObject obj = pyo.callAttr("get_recomendations",name,0);
        Rec1.setText(obj.toString());

        PyObject pyo1 = pyobj.getModule("main");
        PyObject obj1 = pyo1.callAttr("get_recomendations",name,1);
        Rec2.setText(obj1.toString());

        PyObject pyo2 = pyobj.getModule("main");
        PyObject obj2 = pyo2.callAttr("get_recomendations",name,2);
        Rec3.setText(obj2.toString());

        PyObject pyo3 = pyobj.getModule("main");
        PyObject obj3 = pyo3.callAttr("get_recomendations",name,3);
        Rec4.setText(obj3.toString());

        PyObject pyo4 = pyobj.getModule("main");
        PyObject obj4 = pyo4.callAttr("get_recomendations",name,4);
        Rec5.setText(obj4.toString());

        Rec1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String output = Rec1.getText().toString();
                ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clipData = ClipData.newPlainText("MyData",output);
                clipboardManager.setPrimaryClip(clipData);
                Toast.makeText(Recomendation.this, "Text copied", Toast.LENGTH_SHORT).show();
            }
        });

        Rec2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String output = Rec2.getText().toString();
                ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clipData = ClipData.newPlainText("MyData",output);
                clipboardManager.setPrimaryClip(clipData);
                Toast.makeText(Recomendation.this, "Text copied", Toast.LENGTH_SHORT).show();
            }
        });

        Rec3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String output = Rec3.getText().toString();
                ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clipData = ClipData.newPlainText("MyData",output);
                clipboardManager.setPrimaryClip(clipData);
                Toast.makeText(Recomendation.this, "Text copied", Toast.LENGTH_SHORT).show();
            }
        });

        Rec4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String output = Rec4.getText().toString();
                ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clipData = ClipData.newPlainText("MyData",output);
                clipboardManager.setPrimaryClip(clipData);
                Toast.makeText(Recomendation.this, "Text copied", Toast.LENGTH_SHORT).show();
            }
        });

        Rec5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String output = Rec5.getText().toString();
                ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clipData = ClipData.newPlainText("MyData",output);
                clipboardManager.setPrimaryClip(clipData);
                Toast.makeText(Recomendation.this, "Text copied", Toast.LENGTH_SHORT).show();
            }
        });


        bttntoSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(Recomendation.this,AllMotorcycleActivity.class);
                startActivity(intent);

            }
        });

    }
//    public void onBackPressed() {
//        //NONE
//        Toast.makeText(this, "Make butto back letter ", Toast.LENGTH_SHORT).show();
//    }
}