package com.milkaxe_studios.clinicaapp.cruds.cobertura;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.milkaxe_studios.clinicaapp.R;
import com.milkaxe_studios.clinicaapp.controllers.CoberturaController;
import com.milkaxe_studios.clinicaapp.model.ActivityController;
import com.milkaxe_studios.clinicaapp.model.Cobertura;

public class AtualizarCoberturaActivity extends ActivityController {

    Cobertura cobertura;
    CoberturaController controller;

    EditText descCoberturaEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atualizar_cobertura);


        this.progressBar = (ProgressBar) findViewById(R.id.progressBar);
        this.controller = new CoberturaController(this, this.preferences);
        this.cobertura = Cobertura.getCoberturaFromJSON(preferences.getString("Cobertura/Get",null));

        this.descCoberturaEditText = (EditText) findViewById(R.id.nome_cobertura_text_field);
        this.descCoberturaEditText.setText(this.cobertura.descCobertura);
    }

    @Override
    public void notifyActivity(String... args) {

    }

    public void onClickAtualizar(View view) {
        cobertura.descCobertura = descCoberturaEditText.getText().toString();

        controller.atualizarCobertura(cobertura);
    }

    public void onClickDelete(View view) {
        controller.deletarCobertura(cobertura);
    }
}
