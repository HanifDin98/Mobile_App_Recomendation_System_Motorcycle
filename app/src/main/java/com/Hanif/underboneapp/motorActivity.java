//package com.example.underboneapp;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.widget.Button;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import com.bumptech.glide.Glide;
//
//public class motorActivity extends AppCompatActivity {
//    private TextView txtMotorNameA, txtBrandNameA,txtEngineTypeA,txtEngineCoolingA,txtDisplacementA,txtFuelTankA,txtStartingA,txtFrameA,txtMaxA,txtTransmissionA;
//    private Button btnAddFavourA;
//    private ImageView motorImageA;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_motor);
//        initViews();
//        //tukar ni letak hardcode
//       //Motorcycle motor = new Motorcycle("id","Yamaha Y15ZR(2020)","Yamaha","SOHC","Liquid Cooled",150,4.2,"Electric","Backbone",11.3,"Manual Gearing","https://paultan.org/image/2020/11/2020-Yamaha-Y15ZR-Malaysia-1-e1605002062540-850x579.jpg");
//        setData(motor);
//
////        Intent intent =getIntent();
////        if(null !=intent){
////            int motorid = intent.getIntExtra("motorId",-1);
////            if(motorid != -1){
////                Motorcycle incomingMotor = Utils.getInstance(this).getMotorById(motorid);
////                if(null !=incomingMotor){
////                    setData(incomingMotor);
////                    handleFavouriteMotors(incomingMotor);
////                }
////            }
////        }
//    }
//
//    private void setData(Motorcycle motor){
//        txtMotorNameA.setText(motor.getName());
//        txtBrandNameA.setText(motor.getBrand());
//        txtEngineTypeA.setText(motor.getEngineType());
//        txtEngineCoolingA.setText(motor.getEngineCooling());
//        txtDisplacementA.setText(String.valueOf(motor.getDisplacment()));
//        txtFuelTankA.setText(String.valueOf(motor.getFuelTankCapacity()));
//        txtStartingA.setText(motor.getStartingSystem());
//        txtFrameA.setText(motor.getFrameType());
//        //txtMaxA.setText(String.valueOf(motor.getMaxPower()));
//        txtTransmissionA.setText(motor.getTransmission());
//
//        Glide.with(this)
//                .asBitmap().load(motor.getImageUrl())
//                .into(motorImageA);
//    }
//
//    private void initViews(){
//        txtMotorNameA = findViewById(R.id.txtAnswerNameMotor);
//        txtBrandNameA = findViewById(R.id.txtAnswerBrandName);
//        txtDisplacementA= findViewById(R.id.txtAnswerDisplacement);
//        txtEngineTypeA = findViewById(R.id.txtAnswerEngineType);
//        txtEngineCoolingA=findViewById(R.id.txtEngineCoolingAnswer);
//        txtFuelTankA = findViewById(R.id.txtAnswerFuel);
//        txtStartingA = findViewById(R.id.txtStartingsystemAnswer);
//        txtFrameA = findViewById(R.id.txtFrameAnswer);
//        txtMaxA = findViewById(R.id.txtPowerAnswer);
//        txtTransmissionA = findViewById(R.id.txtTransmissionAnswer);
//
//        btnAddFavourA = findViewById(R.id.btnAddToFav);
//        motorImageA = findViewById(R.id.imageMotor);
//
//
//    }
//}