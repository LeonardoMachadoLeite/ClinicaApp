package com.milkaxe_studios.clinicaapp.cruds.forma_pagamento;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.milkaxe_studios.clinicaapp.R;
import com.milkaxe_studios.clinicaapp.controllers.FormaPagamentoController;
import com.milkaxe_studios.clinicaapp.model.ActivityController;
import com.milkaxe_studios.clinicaapp.model.FormaPagamento;

public class CadastrarFormaPagamentoActivity extends ActivityController {

    EditText formaPagamentoEditText;

    FormaPagamentoController formaPagamentoController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_forma_pagamento);

        this.progressBar = (ProgressBar) findViewById(R.id.progressBar);

        formaPagamentoEditText = (EditText) findViewById(R.id.create_forma_pagamento_desc_text_field);
        formaPagamentoController = new FormaPagamentoController(this, preferences);
    }

    @Override
    public void notifyActivity(String... args) {}

    public void onClickCancelarButton(View view) {
        finish();
    }

    public void onClickCreateButton(View view) {
        formaPagamentoController.inserirFormaPagamento(new FormaPagamento(
                null,
                formaPagamentoEditText.getText().toString())
        );
        finish();
    }

}
