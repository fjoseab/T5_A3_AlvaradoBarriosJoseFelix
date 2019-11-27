package com.example.android_http;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btn1,btn2,btn3,btn4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1=(Button) findViewById(R.id.btn_altas);

        btn3=(Button) findViewById(R.id.btn_cambios);

        btn4=(Button) findViewById(R.id.btn_consultas);

    }

    public void abrirActivities(View v){
        Intent i;
        switch (v.getId()){
            case R.id.btn_altas:
                i=new Intent(this,ActivityAltas.class);
                startActivity(i);
                break;
            case R.id.btn_cambios:
                i=new Intent(this,ActivityCambios.class);
                startActivity(i);
                break;
            case R.id.btn_consultas:
                i=new Intent(this,ActivityConsultas.class);
                startActivity(i);
                break;
        }

    }
}
