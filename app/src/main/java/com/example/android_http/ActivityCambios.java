package com.example.android_http;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Controlador.AnalizadorJSON;

public class ActivityCambios extends AppCompatActivity {
    String res ="null";
    ArrayList<String> arrayList;
    EditText caja_clave;
    String dato;
    String campo;
    Spinner sp;
    Button b1, b2,b3;
    EditText cajaNumControl,cajaNombre,cajaPrimerAp,cajaSegundoAp,cajaEdad,cajaSemestre,cajaCarrera;
    private final static String[] op = { "Num_Control"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cambios);
        ArrayAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, op);
        sp = findViewById(R.id.opciones);
        sp.setAdapter(adapter);
        caja_clave=findViewById(R.id.clave);
        b1=findViewById(R.id.mostrar_espeifico);
        b2=findViewById(R.id.mostrar_todos);
        cajaNumControl=findViewById(R.id.caja_Num_Control);
        cajaNombre=findViewById(R.id.caja_Nombre);
        cajaPrimerAp=findViewById(R.id.caja_Primer_Ap);
        cajaSegundoAp=findViewById(R.id.caja_Segundo_Ap);
        cajaEdad=findViewById(R.id.caja_Edad);
        cajaSemestre=findViewById(R.id.caja_Semestre);
        cajaCarrera=findViewById(R.id.caja_Carrera);
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
            case R.id.guardar:
                String nc=cajaNumControl.getText().toString();
                String n=cajaNombre.getText().toString();
                String pa=cajaPrimerAp.getText().toString();
                String sa=cajaSegundoAp.getText().toString();
                byte e=Byte.parseByte(cajaEdad.getText().toString());
                byte s=Byte.parseByte(cajaSemestre.getText().toString());
                String c=cajaCarrera.getText().toString();
                new ActualizarAlumnos().execute(nc,n,pa,sa,String.valueOf(e),String.valueOf(s),c);
                break;
            case R.id.eliminar:
                    String nc1=caja_clave.getText().toString();
                    new EliminarAlumnos().execute(nc1);
                break;
        }
    }
    //Mostrar alumno en los campos
    class mostrarAlumno extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... strings) {
            Map<String, String> mapAlumnos=new HashMap<>();
            mapAlumnos.put("ca",strings[0]);
            mapAlumnos.put("da",strings[1]);


            AnalizadorJSON analizadorJSON=new AnalizadorJSON();
            String url="http://176.48.16.7:80/PHP_Pruebas/API_PHP_Android/consulta_especifica.php";
            JSONObject jsonObject=analizadorJSON.consultaEspecificaHTTP(url, "POST", mapAlumnos);

            arrayList=new ArrayList<String>();
            try {
                JSONArray jsonArray=jsonObject.getJSONArray("Alumnos");
                for (int i = 0; i<jsonArray.length(); i++){
                    arrayList.add(jsonArray.getJSONObject(i).getString("nc"));
                    arrayList.add(jsonArray.getJSONObject(i).getString("n"));
                    arrayList.add(jsonArray.getJSONObject(i).getString("pa"));
                    arrayList.add(jsonArray.getJSONObject(i).getString("sa"));
                    arrayList.add(jsonArray.getJSONObject(i).getString("e"));
                    arrayList.add(jsonArray.getJSONObject(i).getString("s"));
                    arrayList.add(jsonArray.getJSONObject(i).getString("c"));
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
                cajaNumControl.setText(arrayList.get(0));
                cajaNombre.setText(arrayList.get(1));
                cajaPrimerAp.setText(arrayList.get(2));
                cajaSegundoAp.setText(arrayList.get(3));
                cajaEdad.setText(arrayList.get(4));
                cajaSemestre.setText(arrayList.get(5));
                cajaCarrera.setText(arrayList.get(6));
            }else{
                Toast toast=Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_SHORT);
                toast.setMargin(50,50);
                toast.show();
            }
        }
    }
    //Actualizar
    class ActualizarAlumnos extends AsyncTask<String,String,String>{

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
            String url="http://176.48.16.7:80/PHP_Pruebas/API_PHP_Android/actualizacion.php";
            JSONObject jsonObject=analizadorJSON.actualizacionHTTP(url, "POST", mapAlumnos);

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
    //Eliminacion
    class EliminarAlumnos extends AsyncTask<String,String,String>{

        @Override
        protected String doInBackground(String... strings) {

            Map<String, String>mapAlumnos=new HashMap<>();
            mapAlumnos.put("nc1",strings[0]);

            AnalizadorJSON analizadorJSON=new AnalizadorJSON();
            String url="http://176.48.16.7:80/PHP_Pruebas/API_PHP_Android/eliminacion.php";
            JSONObject jsonObject=analizadorJSON.eliminacionHTTP(url, "POST", mapAlumnos);

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
                Toast toast=Toast.makeText(getApplicationContext(),"Registro eliminado",Toast.LENGTH_SHORT);
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
