package com.example.android_http;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;

import Controlador.AnalizadorJSON;

public class ActivityAltas extends Activity {
    String res ="null";
    EditText cajaNumControl,cajaNombre,cajaPrimerAp,cajaSegundoAp,cajaEdad,cajaSemestre,cajaCarrera;


    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_altas);


        cajaNumControl=findViewById(R.id.caja_Num_Control);
        cajaNombre=findViewById(R.id.caja_Nombre);
        cajaPrimerAp=findViewById(R.id.caja_Primer_Ap);
        cajaSegundoAp=findViewById(R.id.caja_Segundo_Ap);
        cajaEdad=findViewById(R.id.caja_Edad);
        cajaSemestre=findViewById(R.id.caja_Semestre);
        cajaCarrera=findViewById(R.id.caja_Carrera);

    }

    public void registrarAlumno(View v){
        String nc=cajaNumControl.getText().toString();
        String n=cajaNombre.getText().toString();
        String pa=cajaPrimerAp.getText().toString();
        String sa=cajaSegundoAp.getText().toString();
        byte e=Byte.parseByte(cajaEdad.getText().toString());
        byte s=Byte.parseByte(cajaSemestre.getText().toString());
        String c=cajaCarrera.getText().toString();

        //Revisar conectividad Wifi

        ConnectivityManager cm= (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni=cm.getActiveNetworkInfo();
        if(ni != null && ni.isConnected()){
            //proceso para enviar peticion HTTP con la cadena JSON que contendra los datos del alumno
            new AgregarAlumnos().execute(nc,n,pa,sa,String.valueOf(e),String.valueOf(s),c);

        }else{
            Toast.makeText(this,"ERROR DE CONEXION \n REVISA TU CONECTIVIDAD A INTERNET",Toast.LENGTH_LONG).show();
            Log.i("MSJ =>","Error en WIFI");
        }
    }
    //Clase interna despues dentro de ACtyvity ALtas
    class AgregarAlumnos extends AsyncTask<String,String,String>{

        @Override
        protected String doInBackground(String... strings) {

            Map<String, String>mapAlumnos=new HashMap<>();
            mapAlumnos.put("nc",strings[0]);
            mapAlumnos.put("n",strings[1]);
            mapAlumnos.put("pa",strings[2]);
            mapAlumnos.put("sa",strings[3]);
            mapAlumnos.put("e",strings[4]);
            mapAlumnos.put("s",strings[5]);
            mapAlumnos.put("c",strings[6]);

            AnalizadorJSON analizadorJSON=new AnalizadorJSON();
            String url="http://176.48.16.7:80/PHP_Pruebas/API_PHP_Android/altas_alumnos.php";
            JSONObject jsonObject=analizadorJSON.peticionHTTP(url, "POST", mapAlumnos);

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
                Toast toast=Toast.makeText(getApplicationContext(),"Registro insertado",Toast.LENGTH_SHORT);
                toast.setMargin(50,50);
                toast.show();
            }else{
                Toast toast=Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_SHORT);
                toast.setMargin(50,50);
                toast.show();
            }
        }
    }
}
