package com.example.splabov;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.List;

public class HiloConexion extends Thread{

    List<String> lista = null;
    Handler colaMensajes;
    boolean texto;
    public HiloConexion(Handler colaMensajes,boolean texto){
        this.colaMensajes = colaMensajes;
        this.texto=texto;
    }

    public void run(){
        HttpManager con = new HttpManager();
        if(this.texto) {
            byte[] respueta = con.obtenerInformacion("http://192.168.0.9:3001/usuarios");
            String respuetaS = new String(respueta);
            Log.d("respueta",respuetaS);

            Message message = new Message();
            message.arg1 = MainActivity.MENSAJE_STRING;
            message.obj = respuetaS;
            this.colaMensajes.sendMessage(message);
        }else{

            byte[] respueta = con.obtenerInformacion("https://depor.com/resizer/PkYoB5NMAbTrT-mzXk8iyfwZ5fs=/580x330/smart/filters:format(jpeg):quality(75)/cloudfront-us-east-1.images.arcpublishing.com/elcomercio/GBQSRILFOBCX7KERNRKUTP55WM.jpg");

            //https://www.infobae.com/pf/resources/images/logo_infobae_naranja.svg?d=955"


            Message message = new Message();
            message.arg1 = 3;
            message.obj = respueta;
            this.colaMensajes.sendMessage(message);

        }

    }
}
