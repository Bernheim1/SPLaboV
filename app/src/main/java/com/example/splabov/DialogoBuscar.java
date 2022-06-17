package com.example.splabov;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.fragment.app.DialogFragment;

public class DialogoBuscar  extends DialogFragment {

    String titulo;
    String mensaje;
    Activity a;

    public DialogoBuscar(Activity a,String t, String m){
        this.titulo = t;
        this.mensaje = m;
        this.a = a;
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(this.titulo);
        builder.setMessage(this.mensaje);


        builder.setNegativeButton("Cerrar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });


        AlertDialog ad = builder.create();

        return ad;
    }
}
