package com.Hanif.underboneapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class InstructionUser extends AppCompatActivity {
    private Button bttnNext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instruction_user);

        bttnNext = findViewById(R.id.bttnInstructNext);
        bttnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(InstructionUser.this, "Next Instruction", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(InstructionUser.this,InstructionUser2.class);
                startActivity(intent);
            }
        });

    }
}