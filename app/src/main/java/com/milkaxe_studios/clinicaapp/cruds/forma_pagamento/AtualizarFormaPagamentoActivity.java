package com.milkaxe_studios.clinicaapp.cruds.forma_pagamento;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.milkaxe_studios.clinicaapp.R;
import com.milkaxe_studios.clinicaapp.controllers.FormaPagamentoController;
import com.milkaxe_studios.clinicaapp.model.ActivityController;
import com.milkaxe_studios.clinicaapp.model.FormaPagamento;

public class AtualizarFormaPagamentoActivity extends ActivityController {

    FormaPagamento formaPagamento;
    FormaPagamentoController formaPagamentoController;

    EditText descEspecialidadeEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atualizar_especialidade);


        this.progressBar = (ProgressBar) findViewById(R.id.progressBar);
        this.formaPagamentoController = new FormaPagamentoController(this, this.preferences);
        this.formaPagamento = FormaPagamento.getFormaPagamentoFromJSON(preferences.getString("FormaPagamento/Get",null));

        this.descEspecialidadeEditText = (EditText) findViewById(R.id.nome_especialidade_text_field);
        this.descEspecialidadeEditText.setText(this.formaPagamento.descFormaPagamento);
    }

    @Override
    public void notifyActivity(String... args) {}

    public void onClickAtualizar(View view) {
        formaPagamento.descFormaPagamento = descEspecialidadeEditText.getText().toString();

        formaPagamentoController.atualizarFormaPagamento(formaPagamento);
    }

    public void onClickDelete(View view) {
        formaPagamentoController.deletarFormaPagamento(formaPagamento);
    }
}
