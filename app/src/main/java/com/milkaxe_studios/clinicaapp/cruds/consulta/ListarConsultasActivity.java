package com.milkaxe_studios.clinicaapp.cruds.consulta;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.milkaxe_studios.clinicaapp.R;
import com.milkaxe_studios.clinicaapp.controllers.CoberturaController;
import com.milkaxe_studios.clinicaapp.controllers.ConsultaController;
import com.milkaxe_studios.clinicaapp.controllers.MedicoController;
import com.milkaxe_studios.clinicaapp.controllers.PacienteController;
import com.milkaxe_studios.clinicaapp.controllers.PagamentoController;
import com.milkaxe_studios.clinicaapp.model.ActivityController;
import com.milkaxe_studios.clinicaapp.model.Consulta;
import com.milkaxe_studios.clinicaapp.model.Medico;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class ListarConsultasActivity extends ActivityController {

    ArrayAdapter<String> adapter;
    ArrayList<String> consultas;

    Consulta consulta;

    ConsultaController consultaController;
    PacienteController pacienteController;
    MedicoController medicoController;
    CoberturaController coberturaController;
    PagamentoController pagamentoController;

    ListView listConsultas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_forma_pagamento);

        consultaController = new ConsultaController(this, preferences);
        medicoController = new MedicoController(this, preferences);
        pacienteController = new PacienteController(this, preferences);
        pagamentoController = new PagamentoController(this, preferences);

        this.progressBar = (ProgressBar) findViewById(R.id.progressBar);
        this.listConsultas = (ListView) findViewById(R.id.list_view_especialidades);

        consultas = loadConsultasArray();

        adapter = new ArrayAdapter<>(
                getApplicationContext(),
                android.R.layout.simple_list_item_1,
                consultas
        );
        this.listConsultas.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        listConsultas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                consultaController.getConsultaString(consultas.get(position), "Atualizar");
            }
        });
    }

    private ArrayList<String> loadConsultasArray() {
        String consultasJsonString = preferences.getString("Consulta/Lista","[]");
        ArrayList<String> array = new ArrayList<>();

        try {
            JSONArray especialidadesJsonArray = new JSONArray(consultasJsonString);
            for (int i = 0; i < especialidadesJsonArray.length(); i++) {
                array.add(especialidadesJsonArray.getString(i));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return array;
    }

    public void onClickNovaConsulta(View view) {
        Intent intent = new Intent(this, CadastrarConsultaActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void notifyActivity(String... args) {

        if (args[0].equals("Atualizar")) {
            consulta = Consulta.getConsultaFromJSON(preferences.getString("Consulta/Get", "{}"));
            medicoController.getMedicoId(consulta.IdMedico, "Medico");
        } if (args[0].equals("Medico")) {
            String medicoString = preferences.getString("Medico/Get", "{}");
            preferences.edit().putString("Consulta/Medico", medicoString).apply();
            pacienteController.getPacienteId(consulta.IdPaciente, "Paciente");
        } if (args[0].equals("Paciente")) {
            String pacienteString = preferences.getString("Paciente/Get", "{}");
            preferences.edit().putString("Consulta/Paciente", pacienteString).apply();
            coberturaController.getCoberturaId(consulta.IdCobertura, "Cobertura");
        } if (args[0].equals("Cobertura")) {
            String coberturaString = preferences.getString("Cobertura/Get", "{}");
            preferences.edit().putString("Consulta/Cobertura", coberturaString).apply();
            pagamentoController.getPagamento(consulta.IdPagamento, "Pagamento");
        }  else {
            String pacienteString = preferences.getString("Pagamento/Get", "{}");
            preferences.edit().putString("Consulta/Pagamento", pacienteString).apply();
            Intent intent = new Intent(this, AtualizarConsultaActivity.class);
            startActivity(intent);
            finish();;
        }
    }
}
