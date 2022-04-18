package com.example.mathxjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private Button Button_Start;
    private Button Button_Pezult,Button_Close;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button_Start =(Button)findViewById(R.id.Button_Start);
        Button_Pezult=(Button)findViewById(R.id.Button_Pezult);
        Button_Close=(Button)findViewById(R.id.Button_Close);
        Button_Start.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        openUser_name();
                    }
                }
        );
        Button_Pezult.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        openRezult();
                    }
                }
        );
        Button_Close.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(Intent.ACTION_MAIN);
                        i.addCategory(Intent.CATEGORY_HOME);
                        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(i);
                    }
                }
        );

    }
    public void openUser_name(){
        Intent intent = new Intent(this,User_Name.class);
        startActivity(intent);
    }
    public void openRezult(){


        Intent intent = new Intent(this,Rezult.class);
        startActivity(intent);


    }




}

