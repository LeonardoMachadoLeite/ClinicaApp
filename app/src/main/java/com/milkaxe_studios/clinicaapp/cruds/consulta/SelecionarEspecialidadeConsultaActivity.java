package com.milkaxe_studios.clinicaapp.cruds.consulta;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.milkaxe_studios.clinicaapp.R;
import com.milkaxe_studios.clinicaapp.controllers.EspecialidadeController;
import com.milkaxe_studios.clinicaapp.controllers.MedicoController;
import com.milkaxe_studios.clinicaapp.cruds.especialidade.AtualizarEspecialidadeActivity;
import com.milkaxe_studios.clinicaapp.model.ActivityController;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class SelecionarEspecialidadeConsultaActivity extends ActivityController {

    ArrayAdapter<String> adapter;
    ArrayList<String> especialidades;

    EspecialidadeController controller;
    MedicoController medicoController;
    EditText nomeEspecialidadeTextField;
    ListView listEspecialidades;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selecionar_especialidade_consulta);

        controller = new EspecialidadeController(this, preferences);
        medicoController = new MedicoController(this, preferences);

        this.progressBar = (ProgressBar) findViewById(R.id.progressBar);
        this.listEspecialidades = (ListView) findViewById(R.id.list_view_especialidades);
        this.nomeEspecialidadeTextField = (EditText) findViewById(R.id.search_edit_text);

        especialidades = loadEspecialidadesArray();

        adapter = new ArrayAdapter<>(
                getApplicationContext(),
                android.R.layout.simple_list_item_1,
                especialidades
        );
        this.listEspecialidades.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        listEspecialidades.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                controller.getEspecialidade(especialidades.get(position), "Especialidade Selecionada");
            }
        });
    }

    @Override
    public void notifyActivity(String... args) {
        if (args[0].equals("Especialidade Selecionada")) {

        } if (args[0].equals("Array Medicos")) {
            Intent intent = new Intent(this, SelecionarMedicoConsultaActivity.class);
            startActivity(intent);
            finish();
        } else {
            this.refresh();
        }
    }

    private ArrayList<String> loadEspecialidadesArray() {
        String especialidadesJsonString = preferences.getString("Especialidade/Lista","[]");
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

    public void onClickBuscarEspecialidade(View view) {
        String alvo = this.nomeEspecialidadeTextField.getText().toString().trim().toLowerCase();

        if (!alvo.equals("")) {
            controller.getListaEspecialidadesStartsWith(alvo, "Busca");
        } else {
            controller.getListaEspecialidades("Busca");
        }
    }

}
