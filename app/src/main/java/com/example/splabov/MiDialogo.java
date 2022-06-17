package com.example.splabov;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.fragment.app.DialogFragment;

    public class MiDialogo extends DialogFragment {

        Activity a;


        public MiDialogo(Activity a){
            this.a = a;
        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            LayoutInflater li = LayoutInflater.from(getActivity());
            View viewAlert = li.inflate(R.layout.layout_dialogo, null);

            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("Crear Usuario");
            builder.setView(viewAlert);

            DialogListener l = new DialogListener(a, viewAlert);
            builder.setPositiveButton("Aceptar", l);
            builder.setNegativeButton("Cancelar", l);

            AlertDialog ad = builder.create();

            return ad;
        }
    }


