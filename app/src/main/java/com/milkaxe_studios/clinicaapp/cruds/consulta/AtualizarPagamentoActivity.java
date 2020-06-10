package com.milkaxe_studios.clinicaapp.cruds.consulta;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;

import com.milkaxe_studios.clinicaapp.R;
import com.milkaxe_studios.clinicaapp.model.ActivityController;
import com.milkaxe_studios.clinicaapp.model.Pagamento;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class AtualizarPagamentoActivity extends ActivityController {

    private EditText ValorEditText;
    private Pagamento pagamento;
    private Spinner FormaPagamentoSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_pagamento);

        this.progressBar = (ProgressBar) findViewById(R.id.progressBar);
        pagamento = Pagamento.getPagamentoFromJSON(preferences.getString("Consulta/Pagamento", new Pagamento().toString()));

        ValorEditText = (EditText) findViewById(R.id.nome_text_field);
        FormaPagamentoSpinner = (Spinner) findViewById(R.id.especialidade_spinner);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                getApplicationContext(),
                android.R.layout.simple_spinner_dropdown_item,
                loadFormaPagamentoArray()
        );
        FormaPagamentoSpinner.setAdapter(adapter);
    }

    @Override
    public void notifyActivity(String... args) {}

    private ArrayList<String> loadFormaPagamentoArray() {
        String formaPagamentoJsonString = preferences.getString("FormaPagamento/Lista","[]");
        ArrayList<String> array = new ArrayList<>();

        try {
            JSONArray formaPagamentoJsonArray = new JSONArray(formaPagamentoJsonString);
            for (int i = 0; i < formaPagamentoJsonArray.length(); i++) {
                array.add(formaPagamentoJsonArray.getString(i));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return array;
    }

    public void onClickCancelarButton(View view) {
        finish();
    }

    public void onClickCreateButton(View view) {

        pagamento.valor = ValorEditText.getText().toString();
        //pagamento.IdFormaPagamento = FormaPagamentoSpinner.getSelectedItem().toString();

        preferences.edit().putString("Consulta/Pagamento", pagamento.toString()).apply();
        finish();
    }

}