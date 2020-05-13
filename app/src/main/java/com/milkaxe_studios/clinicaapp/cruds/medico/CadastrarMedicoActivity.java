package com.milkaxe_studios.clinicaapp.cruds.medico;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.database.DatabaseReference;
import com.milkaxe_studios.clinicaapp.R;
import com.milkaxe_studios.clinicaapp.controllers.EspecialidadeController;
import com.milkaxe_studios.clinicaapp.controllers.MedicoController;

import java.util.ArrayList;
import java.util.List;

public class CadastrarMedicoActivity extends AppCompatActivity {

    private EditText NomeEditText;
    private EditText CRMEditText;
    private EditText EspecialidadeEditText;

    private MedicoController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_medico);
        controller = new MedicoController();

        NomeEditText = (EditText) findViewById(R.id.nome_text_field);
        CRMEditText = (EditText) findViewById(R.id.crm_text_field);
        EspecialidadeEditText = (EditText) findViewById(R.id.especialidade_text_field);
    }

    public void onCLickCancelarButton(View view) {
        finish();
    }

    public void onClickCreateButton(View view) {
        String nome = NomeEditText.getText().toString();
        String CRM = CRMEditText.getText().toString();
        String especialidade = EspecialidadeEditText.getText().toString();
        controller.inserirMedico(nome, CRM, especialidade);
        finish();
    }

}
