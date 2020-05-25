package com.milkaxe_studios.clinicaapp.cruds.medico;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;

import com.milkaxe_studios.clinicaapp.R;
import com.milkaxe_studios.clinicaapp.controllers.MedicoController;
import com.milkaxe_studios.clinicaapp.model.ActivityController;
import com.milkaxe_studios.clinicaapp.model.Medico;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class CadastrarMedicoActivity extends ActivityController {

    private EditText NomeEditText;
    private EditText CRMEditText;
    private Spinner EspecialidadeSpinner;

    private MedicoController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_medico);

        this.progressBar = (ProgressBar) findViewById(R.id.progressBar);
        controller = new MedicoController(this, this.preferences);

        NomeEditText = (EditText) findViewById(R.id.nome_text_field);
        CRMEditText = (EditText) findViewById(R.id.crm_text_field);
        EspecialidadeSpinner = (Spinner) findViewById(R.id.especialidade_spinner);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                getApplicationContext(),
                android.R.layout.simple_spinner_dropdown_item,
                loadEspecialidadesArray()
        );
        EspecialidadeSpinner.setAdapter(adapter);
    }

    @Override
    public void notifyActivity(String... args) {}

    private ArrayList<String> loadEspecialidadesArray() {
        String medicosJsonString = preferences.getString("Especialidade/Lista","[]");
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

    public void onClickCancelarButton(View view) {
        finish();
    }

    public void onClickCreateButton(View view) {
        Medico medico = new Medico(
                NomeEditText.getText().toString(),
                CRMEditText.getText().toString(),
                EspecialidadeSpinner.getSelectedItem().toString()
        );

        controller.inserirMedico(medico);
        finish();
    }

}
