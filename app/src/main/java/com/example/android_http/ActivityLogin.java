package com.example.android_http;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import Controlador.AnalizadorJSON;

public class ActivityLogin extends AppCompatActivity {
    String res ="null";
    EditText cajaUse,cajaPas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        cajaUse=findViewById(R.id.usuario);
        cajaPas=findViewById(R.id.contraseña);
    }
    public void iniciar(View v) {
        switch (v.getId()) {
            case R.id.button_iniciar:
                String use=cajaUse.getText().toString();
                String cont=cajaPas.getText().toString();
                new VerificarUsuario().execute(use,cont);
                break;
        }
    }

    class VerificarUsuario extends AsyncTask<String,String,String>{

        @Override
        protected String doInBackground(String... strings) {

            Map<String, String> mapAlumnos=new HashMap<>();
            mapAlumnos.put("usuario",strings[0]);
            mapAlumnos.put("contrasena",strings[1]);


            AnalizadorJSON analizadorJSON=new AnalizadorJSON();
            String url="http://176.48.16.7:80/PHP_Pruebas/API_PHP_Android/usuario.php";
            JSONObject jsonObject=analizadorJSON.verificacionHTTP(url, "POST", mapAlumnos);

            try {
                if (jsonObject.getInt("exito")==1){
                    Log.i("MSJ==>", "Tododo salio bien");
                    res="1";

                }else{
                    Log.i("MSJ==>", "Error"+jsonObject.getString("mensaje"));
                    res=null;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }
        protected void onPostExecute(String resu){
            resu=res;
            if(resu=="1"){
                Toast toast=Toast.makeText(getApplicationContext(),"Bienvenido",Toast.LENGTH_SHORT);
                toast.setMargin(50,50);
                toast.show();
                Intent i = new Intent(ActivityLogin.this, MainActivity.class);
                startActivity(i);
            }else{
                Toast toast=Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_SHORT);
                toast.setMargin(50,50);
                toast.show();
            }
        }
    }
}
