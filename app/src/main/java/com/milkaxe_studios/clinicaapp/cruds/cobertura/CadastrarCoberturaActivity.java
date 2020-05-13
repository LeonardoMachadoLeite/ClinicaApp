package com.milkaxe_studios.clinicaapp.cruds.cobertura;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.milkaxe_studios.clinicaapp.R;
import com.milkaxe_studios.clinicaapp.controllers.CoberturaController;
import com.milkaxe_studios.clinicaapp.model.ActivityController;

public class CadastrarCoberturaActivity extends ActivityController {

    private CoberturaController controller;
    private EditText coberturaNomeEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_especialidade);

        this.progressBar = (ProgressBar) findViewById(R.id.progressBar);
        coberturaNomeEditText = (EditText) findViewById(R.id.create_espec_nome_text_field);
        controller = new CoberturaController(this, this.preferences);
    }

    @Override
    public void notifyActivity(String... args) {}

    public void onClickCancelarButton(View view) {
        finish();
    }

    public void onClickCreateButton(View view) {
        controller.inserirCobertura(coberturaNomeEditText.getText().toString());
        finish();
    }

}