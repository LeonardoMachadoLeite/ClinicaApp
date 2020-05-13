package com.milkaxe_studios.clinicaapp.cruds.especialidade;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.milkaxe_studios.clinicaapp.R;
import com.milkaxe_studios.clinicaapp.controllers.EspecialidadeController;
import com.milkaxe_studios.clinicaapp.model.ActivityController;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class ListarEspecialidadesActivity extends AppCompatActivity implements ActivityController {

    SharedPreferences preferences;
    ArrayAdapter<String> adapter;
    ArrayList<String> especialidades;

    EspecialidadeController controller;
    ProgressBar progressBar;
    EditText nomeEspecialidadeTextField;
    ListView listEspecialidades;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_especialidades);

        preferences = getSharedPreferences("com.milkaxe_studios.clinicaapp", MODE_PRIVATE);
        controller = new EspecialidadeController(this, preferences);
        this.listEspecialidades = (ListView) findViewById(R.id.list_view_especialidades);
        this.nomeEspecialidadeTextField = (EditText) findViewById(R.id.search_edit_text);
        this.progressBar = (ProgressBar) findViewById(R.id.progressBar);

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
                controller.getEspecialidade(especialidades.get(position), getActivityController());
            }
        });
    }

    private ActivityController getActivityController() {
        return this;
    }

    private ArrayList<String> loadEspecialidadesArray() {
        String especialidadesJsonString = preferences.getString("ListaEspecialidades","[]");
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

    public void onClickNovaEspecialidade(View view) {
        Intent intent = new Intent(this, CadastrarEspecialidadeActivity.class);
        startActivity(intent);
        finish();
    }

    public void onClickBuscarEspecialidade(View view) {
        String alvo = this.nomeEspecialidadeTextField.getText().toString().trim().toLowerCase();

        if (!alvo.equals("")) {
            controller.getListaEspecialidades(alvo);
        } else {
            controller.getListaEspecialidades();
        }
    }

    @Override
    public void goToNewActivityWhenReady(String... args) {
        Intent intent = new Intent(this, AtualizarEspecialidadeActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void setProgressBarVisible() {
        this.progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void refresh() {
        finish();
        overridePendingTransition(0, 0);
        startActivity(getIntent());
        overridePendingTransition(0, 0);
    }
}
