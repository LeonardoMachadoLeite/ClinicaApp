package com.milkaxe_studios.clinicaapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.milkaxe_studios.clinicaapp.controllers.CoberturaController;
import com.milkaxe_studios.clinicaapp.controllers.EspecialidadeController;
import com.milkaxe_studios.clinicaapp.controllers.FormaPagamentoController;
import com.milkaxe_studios.clinicaapp.cruds.cobertura.ListarCoberturaActivity;
import com.milkaxe_studios.clinicaapp.cruds.especialidade.ListarEspecialidadesActivity;
import com.milkaxe_studios.clinicaapp.cruds.forma_pagamento.ListarFormaPagamentoActivity;
import com.milkaxe_studios.clinicaapp.model.ActivityController;

public class SelecionarPerifericoActivity extends ActivityController {

    CoberturaController coberturaController;
    EspecialidadeController especialidadeController;
    FormaPagamentoController formaPagamentoController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selecionar_periferico);

        especialidadeController = new EspecialidadeController(this, this.preferences);
        coberturaController = new CoberturaController(this, this.preferences);
        formaPagamentoController = new FormaPagamentoController(this, this.preferences);

        this.progressBar = (ProgressBar) findViewById(R.id.progressBar);
    }

    public void onClickIrParaMenuFormaPagamento(View view) {
        formaPagamentoController.getListaFormasPagamentos("FormaPagamento");
    }

    public void onClickIrParaMenuEspecialidade(View view) {
        especialidadeController.getListaEspecialidades("Especialidade");
    }

    public void onClickIrParaMenuCobertura(View view) {
        coberturaController.getListaCoberturas("Cobertura");
    }

    @Override
    public void notifyActivity(String... args) {
        Intent intent = null;

        switch (args[0]) {
            case "Especialidade":
                intent = new Intent(this, ListarEspecialidadesActivity.class);
                break;
            case "Cobertura":
                intent = new Intent(this, ListarCoberturaActivity.class);
                break;
            case "FormaPagamento":
                intent = new Intent(this, ListarFormaPagamentoActivity.class);
                break;
        }

        startActivity(intent);
        this.setProgressBarInvisible();
    }

}
