package com.example.splabov;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements Handler.Callback, SearchView.OnQueryTextListener {
    public static  final  int MENSAJE_STRING =1;
    static List<Usuario> lista = null;
    String stringTV = "";
    static SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        prefs = getSharedPreferences("miConfig", Context.MODE_PRIVATE);

        String s = prefs.getString("datos", "");

        if(s.isEmpty()){
            Handler handler = new Handler(this);
            HiloConexion hilo = new HiloConexion(handler,true);
            hilo.start();
        }else{
            this.lista = this.parsearRespusta(s);
            this.stringTV = s;
            TextView tv = super.findViewById(R.id.tv);
            tv.setText(this.stringTV);
        }


    }

    @Override
    public boolean handleMessage(@NonNull Message message) {
        if(message.arg1==MENSAJE_STRING){

            List<Usuario> aux = this.parsearRespusta(message.obj.toString());
            this.stringTV = message.obj.toString();
            TextView tv = super.findViewById(R.id.tv);
            tv.setText(this.stringTV);

            this.lista = aux;

        }else if(message.arg1==3){
            byte[] img = (byte[]) message.obj;
            Log.d("img tamño",""+img.length);
//            this.img.setImageBitmap(BitmapFactory.decodeByteArray(img,0,img.length));

        }
        return false;
    }

    public List<Usuario> parsearRespusta(String paises){
        List<Usuario> aux = new ArrayList<>();

        JSONArray json = null;
        try {
            json = new JSONArray(paises);
            for(int i = 0; i < json.length(); i++){
                JSONObject elemento = json.getJSONObject(i);

                Usuario usuario = new Usuario(Integer.parseInt(elemento.getString("id")), elemento.getString("username"), elemento.getString("rol"), Boolean.parseBoolean(elemento.getString("admin")));
                aux.add(usuario);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return aux;
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        this.filter(s);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {

        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        MenuItem searchItem = menu.findItem(R.id.svSearch);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setOnQueryTextListener(this);

        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == R.id.agregarUsuario){
            MiDialogo dialogo = new MiDialogo(this);
            dialogo.show(getSupportFragmentManager(),"Crear Usuario");
        }

        return super.onOptionsItemSelected(item);
    }

    public static String StringifyUsuarios(List<Usuario> usuarios){

        String res = "[";

        for ( Usuario usuario : usuarios) {
            res += "{\"id\":\"" + usuario.getId() + "\",";
            res += "\"username\":\"" + usuario.getNombre() + "\",";
            res += "\"rol\":\"" + usuario.getRol() + "\",";
            res += "\"admin\":\"" + usuario.getAdmin() + "\"},";
        }
        res = res.substring(0,res.length() - 1);
        res += "]";

        Log.d("res", res);

        return res;
    }

    public static void AgregarUsuarioJSON(){

        String aux = StringifyUsuarios(lista);

        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("datos", aux);
        editor.commit();

    }


    public void filter(String s){

        if(s.length() == 0){

            DialogoBuscar d = new DialogoBuscar(this, "Usuario no encontrado", "Debe ingresar un nombre valido para buscar un usuario");
            d.show(getSupportFragmentManager(), "noEncontrado");
        }else{

            Usuario usuarioAux = null;

            for(Usuario u : lista){
                if(u.getNombre().toLowerCase().equals(s.toLowerCase())){

                    usuarioAux = u;
                    break;

                }

            }

            if(usuarioAux != null){
                DialogoBuscar d = new DialogoBuscar(this, "Usuario encontrado", "El rol del usuario es " + usuarioAux.getRol());
                d.show(getSupportFragmentManager(), "encontrado");
            }else{
                DialogoBuscar d = new DialogoBuscar(this, "Usuario no encontrado", "El usuario " + s + " no esta dentro de la lista");
                d.show(getSupportFragmentManager(), "noEncontrado");
            }

        }
    }

}