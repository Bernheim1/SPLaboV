package com.example.splabov;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.List;

public class DialogListener implements Dialog.OnClickListener {

    Activity a;
    View v;

    public DialogListener(Activity a, View v){
        this.a = a;
        this.v = v;
    }


    public void onClick(DialogInterface dialog, int which) {

        if(which == -1){

            TextView tvNombre = this.v.findViewById(R.id.nombre);

            String nombre = tvNombre.getText().toString();

            Spinner spinner = this.v.findViewById(R.id.spinner);

            String respuestaSpinner = (String) spinner.getSelectedItem();

            ToggleButton toggleButton = this.v.findViewById(R.id.admin);

            Boolean admin = toggleButton.isChecked();

            if(!nombre.equals("") && respuestaSpinner != "" && admin != null){

                Integer id = MainActivity.lista.size() + 1;

                MainActivity.lista.add(new Usuario(id, nombre,respuestaSpinner,admin));

                MainActivity.AgregarUsuarioJSON();
            }

        }

    }




}
