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
import com.milkaxe_studios.clinicaapp.cruds.medico.AtualizarMedicoActivity;
import com.milkaxe_studios.clinicaapp.cruds.medico.CadastrarMedicoActivity;
import com.milkaxe_studios.clinicaapp.model.ActivityController;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class SelecionarMedicoConsultaActivity extends ActivityController {

    ArrayAdapter<String> adapter;
    ArrayList<String> medicos;

    MedicoController controller;
    EditText nomeMedicoTextField;
    ListView listMedicos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selecionar_medico_consulta);

        controller = new MedicoController(this, preferences);
        this.progressBar = (ProgressBar) findViewById(R.id.progressBar);
        this.listMedicos = (ListView) findViewById(R.id.list_view);
        this.nomeMedicoTextField = (EditText) findViewById(R.id.search_edit_text);

        medicos = loadMedicosArray();

        adapter = new ArrayAdapter<>(
                getApplicationContext(),
                android.R.layout.simple_list_item_1,
                medicos
        );
        this.listMedicos.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        listMedicos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                controller.getMedico(medicos.get(position), "Atualizar");
            }
        });
    }

    private ArrayList<String> loadMedicosArray() {
        String medicosJsonString = preferences.getString("Medico/Lista","[]");
        ArrayList<String> array = new ArrayList<>();

        try {
            JSONArray medicosJsonArray = new JSONArray(medicosJsonString);
            for (int i = 0; i < medicosJsonArray.length(); i++) {
                array.add(medicosJsonArray.getString(i));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return array;
    }

    public void onClickBuscarMedico(View view) {
        String alvo = this.nomeMedicoTextField.getText().toString().trim().toLowerCase();

        if (!alvo.equals("")) {
            controller.getListaMedicosStartsWith(alvo, "Busca");
        } else {
            controller.getListaMedicos("Busca");
        }
    }

    @Override
    public void notifyActivity(String... args) {
        Intent intent;
        switch (args[0]) {
            case "Atualizar":
                intent = new Intent(this, AtualizarMedicoActivity.class);
                startActivity(intent);
                finish();
                break;
            case "Novo":
                intent = new Intent(this, CadastrarMedicoActivity.class);
                startActivity(intent);
                finish();
                break;
            default:
                this.refresh();
        }
    }

}
