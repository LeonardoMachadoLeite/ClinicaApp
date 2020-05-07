package com.milkaxe_studios.clinicaapp.cruds.medico;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.database.DatabaseReference;
import com.milkaxe_studios.clinicaapp.R;
import com.milkaxe_studios.clinicaapp.controllers.EspecialidadeController;
import com.milkaxe_studios.clinicaapp.controllers.MedicoController;

import java.util.List;

public class CadastrarMedicoActivity extends AppCompatActivity {

    private EditText NomeEditText;
    private EditText CRMEditText;
    private Spinner EspecialidadeSpinner;
    private DatabaseReference mDatabase;

    private MedicoController controller;
    private List<String> listaStringEspecialidades;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_medico);
        controller = new MedicoController();
    }

    public void starParams() {
        NomeEditText = (EditText) findViewById(R.id.nome_text_field);
        CRMEditText = (EditText) findViewById(R.id.crm_text_field);
        EspecialidadeSpinner = (Spinner) findViewById(R.id.spinner_espec);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_dropdown_item_1line,
                listaStringEspecialidades
        );
        EspecialidadeSpinner.setAdapter(adapter);

        QueryEspecialdadesTask queryEspecialidades = new QueryEspecialdadesTask();
        queryEspecialidades.execute();
    }

    public void refreshSpinner() {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_dropdown_item_1line,
                listaStringEspecialidades
        );
        EspecialidadeSpinner.setAdapter(adapter);
    }

    public void onCLickCancelarButton(View view) {
        finish();
    }

    public void onClickCreateButton(View view) {
        String nome = NomeEditText.getText().toString();
        String CRM = CRMEditText.getText().toString();
        String especialidade = EspecialidadeSpinner.getSelectedItem().toString();
        controller.inserirMedico(nome, CRM, especialidade);
        finish();
    }

    private class QueryEspecialdadesTask extends AsyncTask<String, Void, Void> {

        EspecialidadeController controller = new EspecialidadeController();

        @Override
        protected Void doInBackground(String... params) {
            if (params.length == 0) {
                listaStringEspecialidades = controller.getListaEspecialidades();
            } else {
                listaStringEspecialidades = controller.getListaEspecialidades(params[0]);
            }

            publishProgress();
            return null;
        }

        @Override
        protected void onProgressUpdate(Void ... voids) {
            refreshSpinner();
        }

    }

}
