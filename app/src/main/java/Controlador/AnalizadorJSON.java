package Controlador;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

public class AnalizadorJSON {
    InputStream is=null;
    OutputStream os=null;
    JSONObject jsonObject=null;
    String cadenaJSONrecibida;

    //Metodo para la ejecucion de instrucciones DML
    public JSONObject peticionHTTP(String url, String metodo, Map<String, String> datos){
        HttpURLConnection conexion=null;
        URL mUrl=null;
        //{}
        try {
            String cadenaJSON="{\"nc\":\""+ URLEncoder.encode(datos.get("nc"), "UTF-8")+"\",\"n\":\""+ URLEncoder.encode(datos.get("n"), "UTF-8")+"\",\"pa\":\""+ URLEncoder.encode(datos.get("pa"), "UTF-8")+"\",\"sa\":\""+ URLEncoder.encode(datos.get("sa"), "UTF-8")+"\",\"e\":\""+ URLEncoder.encode(datos.get("e"), "UTF-8")+"\",\"s\":\""+ URLEncoder.encode(datos.get("s"), "UTF-8")+"\",\"c\":\""+ URLEncoder.encode(datos.get("c"), "UTF-8")+"\"}";
            Log.d("MSJ =>", cadenaJSON);
            mUrl=new URL(url);
            conexion=(HttpURLConnection) mUrl.openConnection();

            //Activar el envio de datos
            conexion.setDoOutput(true);
            conexion.setRequestMethod(metodo);
            conexion.setFixedLengthStreamingMode(cadenaJSON.getBytes().length);
            conexion.setRequestProperty("Content-type", "application/x-www-form-urlencoded");

            //Objetos para envio de datos

            os=new BufferedOutputStream(conexion.getOutputStream());
            os.write(cadenaJSON.getBytes());
            os.flush();
            os.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Si no se genera un excepcion continua
        try {
            is=new BufferedInputStream(conexion.getInputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            StringBuilder cadena = new StringBuilder();

            String fila="";

            while ((fila=br.readLine())!=null){
                cadena.append(fila+"\n");
            }
            is.close();

            cadenaJSONrecibida=cadena.toString();
        } catch (IOException e) {

            e.printStackTrace();
        }
        //Si no se genera un excepcion continua
        try {
            jsonObject=new JSONObject(cadenaJSONrecibida);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    //Metodo para las consultas
    public JSONObject consultaHTTP(String url){
        HttpURLConnection conexion=null;
        URL mUrl=null;
        try {
            mUrl=new URL(url);
            conexion=(HttpURLConnection) mUrl.openConnection();

            //Activar el envio de datos
            conexion.setDoOutput(true);

            conexion.setRequestProperty("Content-type", "application/x-www-form-urlencoded");

            //Objetos para envio de datos

            os=new BufferedOutputStream(conexion.getOutputStream());

            os.flush();
            os.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            is=new BufferedInputStream(conexion.getInputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            StringBuilder cadena = new StringBuilder();

            String fila="";

            while ((fila=br.readLine())!=null){
                cadena.append(fila+"\n");
            }
            is.close();
            cadenaJSONrecibida=cadena.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Si no se genera un excepcion continua
        try {
            jsonObject=new JSONObject(cadenaJSONrecibida);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    //Metodo consulta especifica
    public JSONObject consultaEspecificaHTTP(String url, String metodo, Map<String, String> datos){
        HttpURLConnection conexion=null;
        URL mUrl=null;
        //{}
        try {
            String cadenaJSON="{\"ca\":\""+ URLEncoder.encode(datos.get("ca"), "UTF-8")+"\",\"da\":\""+ URLEncoder.encode(datos.get("da"), "UTF-8")+"\"}";
            Log.d("MSJ =>", cadenaJSON);
            mUrl=new URL(url);
            conexion=(HttpURLConnection) mUrl.openConnection();

            //Activar el envio de datos
            conexion.setDoOutput(true);
            conexion.setRequestMethod(metodo);
            conexion.setFixedLengthStreamingMode(cadenaJSON.getBytes().length);
            conexion.setRequestProperty("Content-type", "application/x-www-form-urlencoded");

            //Objetos para envio de datos

            os=new BufferedOutputStream(conexion.getOutputStream());
            os.write(cadenaJSON.getBytes());
            os.flush();
            os.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Si no se genera un excepcion continua
        try {
            is=new BufferedInputStream(conexion.getInputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            StringBuilder cadena = new StringBuilder();

            String fila="";

            while ((fila=br.readLine())!=null){
                cadena.append(fila+"\n");
            }
            is.close();

            cadenaJSONrecibida=cadena.toString();
        } catch (IOException e) {

            e.printStackTrace();
        }
        //Si no se genera un excepcion continua
        try {
            jsonObject=new JSONObject(cadenaJSONrecibida);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
    // Actulizaciones
    public JSONObject actualizacionHTTP(String url, String metodo, Map<String, String> datos){
        HttpURLConnection conexion=null;
        URL mUrl=null;
        //{}
        try {
            String cadenaJSON="{\"nc\":\""+ URLEncoder.encode(datos.get("nc"), "UTF-8")+"\",\"n\":\""+ URLEncoder.encode(datos.get("n"), "UTF-8")+"\",\"pa\":\""+ URLEncoder.encode(datos.get("pa"), "UTF-8")+"\",\"sa\":\""+ URLEncoder.encode(datos.get("sa"), "UTF-8")+"\",\"e\":\""+ URLEncoder.encode(datos.get("e"), "UTF-8")+"\",\"s\":\""+ URLEncoder.encode(datos.get("s"), "UTF-8")+"\",\"c\":\""+ URLEncoder.encode(datos.get("c"), "UTF-8")+"\"}";
            Log.d("MSJ =>", cadenaJSON);
            mUrl=new URL(url);
            conexion=(HttpURLConnection) mUrl.openConnection();

            //Activar el envio de datos
            conexion.setDoOutput(true);
            conexion.setRequestMethod(metodo);
            conexion.setFixedLengthStreamingMode(cadenaJSON.getBytes().length);
            conexion.setRequestProperty("Content-type", "application/x-www-form-urlencoded");

            //Objetos para envio de datos

            os=new BufferedOutputStream(conexion.getOutputStream());
            os.write(cadenaJSON.getBytes());
            os.flush();
            os.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Si no se genera un excepcion continua
        try {
            is=new BufferedInputStream(conexion.getInputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            StringBuilder cadena = new StringBuilder();

            String fila="";

            while ((fila=br.readLine())!=null){
                cadena.append(fila+"\n");
            }
            is.close();

            cadenaJSONrecibida=cadena.toString();
        } catch (IOException e) {

            e.printStackTrace();
        }
        //Si no se genera un excepcion continua
        try {
            jsonObject=new JSONObject(cadenaJSONrecibida);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
    // Eliminaciones
    public JSONObject eliminacionHTTP(String url, String metodo, Map<String, String> datos){
        HttpURLConnection conexion=null;
        URL mUrl=null;
        //{}
        try {
            String cadenaJSON="{\"nc\":\""+ URLEncoder.encode(datos.get("nc1"), "UTF-8")+"\"}";
            Log.d("MSJ =>", cadenaJSON);
            mUrl=new URL(url);
            conexion=(HttpURLConnection) mUrl.openConnection();

            //Activar el envio de datos
            conexion.setDoOutput(true);
            conexion.setRequestMethod(metodo);
            conexion.setFixedLengthStreamingMode(cadenaJSON.getBytes().length);
            conexion.setRequestProperty("Content-type", "application/x-www-form-urlencoded");

            //Objetos para envio de datos

            os=new BufferedOutputStream(conexion.getOutputStream());
            os.write(cadenaJSON.getBytes());
            os.flush();
            os.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Si no se genera un excepcion continua
        try {
            is=new BufferedInputStream(conexion.getInputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            StringBuilder cadena = new StringBuilder();

            String fila="";

            while ((fila=br.readLine())!=null){
                cadena.append(fila+"\n");
            }
            is.close();

            cadenaJSONrecibida=cadena.toString();
        } catch (IOException e) {

            e.printStackTrace();
        }
        //Si no se genera un excepcion continua
        try {
            jsonObject=new JSONObject(cadenaJSONrecibida);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    //Verificacion usuario
    public JSONObject verificacionHTTP(String url, String metodo, Map<String, String> datos){
        HttpURLConnection conexion=null;
        URL mUrl=null;
        //{}
        try {
            String cadenaJSON="{\"user\":\""+ URLEncoder.encode(datos.get("usuario"), "UTF-8")+"\",\"password\":\""+ URLEncoder.encode(datos.get("contrasena"), "UTF-8")+"\"}";
            Log.d("MSJ =>", cadenaJSON);
            mUrl=new URL(url);
            conexion=(HttpURLConnection) mUrl.openConnection();

            //Activar el envio de datos
            conexion.setDoOutput(true);
            conexion.setRequestMethod(metodo);
            conexion.setFixedLengthStreamingMode(cadenaJSON.getBytes().length);
            conexion.setRequestProperty("Content-type", "application/x-www-form-urlencoded");

            //Objetos para envio de datos

            os=new BufferedOutputStream(conexion.getOutputStream());
            os.write(cadenaJSON.getBytes());
            os.flush();
            os.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Si no se genera un excepcion continua
        try {
            is=new BufferedInputStream(conexion.getInputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            StringBuilder cadena = new StringBuilder();

            String fila="";

            while ((fila=br.readLine())!=null){
                cadena.append(fila+"\n");
            }
            is.close();

            cadenaJSONrecibida=cadena.toString();
        } catch (IOException e) {

            e.printStackTrace();
        }
        //Si no se genera un excepcion continua
        try {
            jsonObject=new JSONObject(cadenaJSONrecibida);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
}
