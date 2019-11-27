package com.example.android_http;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.icu.text.DisplayContext;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Controlador.AnalizadorJSON;

public class ActivityConsultas extends AppCompatActivity {
    ArrayList<StringBuilder> arrayList;
    EditText caja_clave;
    String dato;
    String campo;
    ArrayAdapter<StringBuilder> adapter;
    ListView lv;
    String res ="null";
    Spinner sp, spc;

    Button b1, b2;
    private final static String[] op = { "Num_Control", "Nombre_Alumno", "Primer_Ape",
            "Segundo_Ape", "Edad", "Semestre", "Carrera" };
    private final static String[] opc = { "ISC", "IM", "LA",
            "CP", "IIA"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultas);
        ArrayAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, op);
        ArrayAdapter adapterC = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, opc);
        sp = findViewById(R.id.opciones);
        sp.setAdapter(adapter);
        spc = findViewById(R.id.opcionesC);
        spc.setAdapter(adapterC);
        caja_clave=findViewById(R.id.clave);
        b1=findViewById(R.id.mostrar_espeifico);
        b2=findViewById(R.id.mostrar_todos);
        spc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        dato=spc.getSelectedItem().toString().toLowerCase();
                        campo ="Carrera";
                        new mostrarAlumno().execute(campo,dato);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }



    public void consultarAlumnos(View v){
        switch (v.getId()){
            case R.id.mostrar_espeifico:
                dato=caja_clave.getText().toString();
                campo = sp.getSelectedItem().toString();
                if(dato.equals("")){
                    Toast toast=Toast.makeText(getApplicationContext(),"Ingrese clave",Toast.LENGTH_SHORT);
                    toast.setMargin(50,50);
                    toast.show();
                }else{
                    new mostrarAlumno().execute(campo,dato);
                }
                break;
            case R.id.mostrar_todos:
                new mostrarAlumnos().execute();
                break;
        }
    }
    //Hilo para consulta simple
    class mostrarAlumno extends AsyncTask<String, String, String>{

        @Override
        protected String doInBackground(String... strings) {
            Map<String, String> mapAlumnos=new HashMap<>();
            mapAlumnos.put("ca",strings[0]);
            mapAlumnos.put("da",strings[1]);


            AnalizadorJSON analizadorJSON=new AnalizadorJSON();
            String url="http://176.48.16.7/PHP_Pruebas/API_PHP_Android/consulta_especifica.php";
            JSONObject jsonObject=analizadorJSON.consultaEspecificaHTTP(url, "POST", mapAlumnos);

            arrayList=new ArrayList<>();
            try {
                JSONArray jsonArray=jsonObject.getJSONArray("Alumnos");
                for (int i = 0; i<jsonArray.length(); i++){
                    StringBuilder cadenaAlumno=new StringBuilder();
                    cadenaAlumno.append(jsonArray.getJSONObject(i).getString("nc")).append("-")
                            .append(jsonArray.getJSONObject(i).getString("n")).append("-")
                            .append(jsonArray.getJSONObject(i).getString("pa")).append("-")
                            .append(jsonArray.getJSONObject(i).getString("sa")).append("-")
                            .append(jsonArray.getJSONObject(i).getString("e")).append("-")
                            .append(jsonArray.getJSONObject(i).getString("s")).append("-")
                            .append(jsonArray.getJSONObject(i).getString("c"));
                    arrayList.add(cadenaAlumno);
                }
                res="1";

            } catch (JSONException e) {
                e.printStackTrace();
                res ="null";
            }
            return null;
        }
        protected void onPostExecute(String resu){
            resu=res;
            if(resu=="1"){
                lv = findViewById(R.id.tabla);
                adapter =new ArrayAdapter<>(ActivityConsultas.this, android.R.layout.simple_list_item_1, arrayList);
                lv.setAdapter(adapter);
            }else{
                Toast toast=Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_SHORT);
                toast.setMargin(50,50);
                toast.show();
            }
        }
    }
    //Hilo para consulta completa
    class mostrarAlumnos extends AsyncTask<String, String, String>{

        @Override
        protected String doInBackground(String... strings) {
            AnalizadorJSON analizadorJSON=new AnalizadorJSON();
            String url="http://176.48.16.7:80/PHP_Pruebas/API_PHP_Android/consultas_alumnos.php";
            JSONObject jsonObject=analizadorJSON.consultaHTTP(url);


            arrayList=new ArrayList<>();
            try {
                JSONArray jsonArray=jsonObject.getJSONArray("Alumnos");
                for (int i = 0; i<jsonArray.length(); i++){
                    StringBuilder cadenaAlumno=new StringBuilder();
                    cadenaAlumno.append(jsonArray.getJSONObject(i).getString("nc")).append("-")
                            .append(jsonArray.getJSONObject(i).getString("n")).append("-")
                            .append(jsonArray.getJSONObject(i).getString("pa")).append("-")
                            .append(jsonArray.getJSONObject(i).getString("sa")).append("-")
                            .append(jsonArray.getJSONObject(i).getString("e")).append("-")
                            .append(jsonArray.getJSONObject(i).getString("s")).append("-")
                            .append(jsonArray.getJSONObject(i).getString("c"));
                    arrayList.add(cadenaAlumno);
                }
                res="1";

            } catch (JSONException e) {
                e.printStackTrace();
                res ="null";
            }
            return null;
        }
        protected void onPostExecute(String resu){
            resu=res;
            if(resu=="1"){
                lv = findViewById(R.id.tabla);
                adapter =new ArrayAdapter<>(ActivityConsultas.this, android.R.layout.simple_list_item_1, arrayList);
                lv.setAdapter(adapter);
            }else{
                Toast toast=Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_SHORT);
                toast.setMargin(50,50);
                toast.show();
            }
        }
    }
}
