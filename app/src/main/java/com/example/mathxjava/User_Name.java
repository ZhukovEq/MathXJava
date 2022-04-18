package com.example.mathxjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class User_Name extends AppCompatActivity {
    private Button Button_Game;
    private Button button_Back;
    public EditText editTextTextPersonName;
    public String Username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_name);
        Button_Game =(Button)findViewById(R.id.Button_Game);
        //button_Back ==(Button)findViewById(R.id.button_Back);

        editTextTextPersonName=(EditText)findViewById(R.id.editTextTextPersonName);

        Button_Game.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        openMathX();
                    }
                }
        );

    }
    public void openMathX(){
       if(editTextTextPersonName.getText().toString().equals("")){
           Toast toast = Toast.makeText(getApplicationContext(),
                   "Введите ваше имя!",
                   Toast.LENGTH_SHORT);
           toast.setGravity(Gravity.CENTER,0,0);
           toast.show();
       }else {
           Username = editTextTextPersonName.getText().toString();
           Intent intent = new Intent(this, MathX.class);
           intent.putExtra("savename",Username);
           startActivity(intent);

       }
    }

}