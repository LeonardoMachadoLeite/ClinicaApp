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
import com.milkaxe_studios.clinicaapp.controllers.EnderecoController;
import com.milkaxe_studios.clinicaapp.controllers.PacienteController;
import com.milkaxe_studios.clinicaapp.cruds.paciente.AtualizarPacienteActivity;
import com.milkaxe_studios.clinicaapp.cruds.paciente.CadastrarPacienteActivity;
import com.milkaxe_studios.clinicaapp.model.ActivityController;
import com.milkaxe_studios.clinicaapp.model.Paciente;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class SelecionarPacienteActivity extends ActivityController {

    ArrayAdapter<String> adapter;
    ArrayList<String> Pacientes;

    PacienteController pacienteController;
    EnderecoController enderecoController;

    EditText nomePacienteTextField;
    ListView listPacientes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_paciente);

        pacienteController = new PacienteController(this, preferences);
        enderecoController = new EnderecoController(this, preferences);

        this.progressBar = (ProgressBar) findViewById(R.id.progressBar);
        this.listPacientes = (ListView) findViewById(R.id.list_view);
        this.nomePacienteTextField = (EditText) findViewById(R.id.search_edit_text);

        Pacientes = loadPacientesArray();

        adapter = new ArrayAdapter<>(
                getApplicationContext(),
                android.R.layout.simple_list_item_1,
                Pacientes
        );
        this.listPacientes.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        listPacientes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                pacienteController.getPaciente(Pacientes.get(position), "Selecionar Paciente");
            }
        });
    }

    private ArrayList<String> loadPacientesArray() {
        String PacientesJsonString = preferences.getString("Paciente/Lista","[]");
        ArrayList<String> array = new ArrayList<>();

        try {
            JSONArray PacientesJsonArray = new JSONArray(PacientesJsonString);
            for (int i = 0; i < PacientesJsonArray.length(); i++) {
                array.add(PacientesJsonArray.getString(i));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return array;
    }

    public void onClickBuscarPaciente(View view) {
        String alvo = this.nomePacienteTextField.getText().toString().trim().toLowerCase();

        if (!alvo.equals("")) {
            pacienteController.getListaPacientesStartsWith(alvo, "Busca");
        } else {
            pacienteController.getListaPacientes("Busca");
        }
    }

    @Override
    public void notifyActivity(String... args) {
        if (args[0].equals("Selecionar Paciente")) {
            String pacienteString = preferences.getString("Paciente/Get", "{}");
            preferences.edit().putString("Consulta/Paciente", pacienteString).apply();
            Intent intent = new Intent(this, CadastrarConsultaActivity.class);
            startActivity(intent);
            finish();
        } else {
            this.refresh();
        }
    }

}