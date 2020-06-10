package com.milkaxe_studios.clinicaapp.cruds.forma_pagamento;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.milkaxe_studios.clinicaapp.R;
import com.milkaxe_studios.clinicaapp.controllers.FormaPagamentoController;
import com.milkaxe_studios.clinicaapp.cruds.especialidade.AtualizarEspecialidadeActivity;
import com.milkaxe_studios.clinicaapp.model.ActivityController;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class ListarFormaPagamentoActivity extends ActivityController {

    ArrayAdapter<String> adapter;
    ArrayList<String> formasPagamento;

    FormaPagamentoController formaPagamentoController;
    EditText descFormaPagamentoTextField;
    ListView listFormasPagamento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_forma_pagamento);

        formaPagamentoController = new FormaPagamentoController(this, preferences);
        this.progressBar = (ProgressBar) findViewById(R.id.progressBar);
        this.listFormasPagamento = (ListView) findViewById(R.id.list_view_especialidades);
        this.descFormaPagamentoTextField = (EditText) findViewById(R.id.search_edit_text);

        formasPagamento = loadFormasPagamentoArray();

        adapter = new ArrayAdapter<>(
                getApplicationContext(),
                android.R.layout.simple_list_item_1,
                formasPagamento
        );
        this.listFormasPagamento.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        listFormasPagamento.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                formaPagamentoController.getFormaPagamentoDesc(formasPagamento.get(position), "Atualizar");
            }
        });
    }

    private ArrayList<String> loadFormasPagamentoArray() {
        String especialidadesJsonString = preferences.getString("FormaPagamento/Lista","[]");
        ArrayList<String> array = new ArrayList<>();

        try {
            JSONArray especialidadesJsonArray = new JSONArray(especialidadesJsonString);
            for (int i = 0; i < especialidadesJsonArray.length(); i++) {
                array.add(especialidadesJsonArray.getString(i));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return array;
    }

    public void onClickNovaFormaPagamento(View view) {
        Intent intent = new Intent(this, CadastrarFormaPagamentoActivity.class);
        startActivity(intent);
        finish();
    }

    public void onClickBuscarEspecialidade(View view) {
        String alvo = this.descFormaPagamentoTextField.getText().toString().trim().toLowerCase();

        if (!alvo.equals("")) {
            formaPagamentoController.getListaFormasPagamentosStartsWith(alvo, "Busca");
        } else {
            formaPagamentoController.getListaFormasPagamentos("Busca");
        }
    }

    @Override
    public void notifyActivity(String... args) {
        if (args[0].equals("Atualizar")) {
            Intent intent = new Intent(this, AtualizarFormaPagamentoActivity.class);
            startActivity(intent);
            finish();
        } else {
            this.refresh();
        }
    }
}
