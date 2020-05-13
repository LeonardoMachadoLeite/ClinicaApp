package com.milkaxe_studios.clinicaapp.cruds.medico;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.milkaxe_studios.clinicaapp.R;
import com.milkaxe_studios.clinicaapp.controllers.MedicoController;
import com.milkaxe_studios.clinicaapp.model.ActivityController;
import com.milkaxe_studios.clinicaapp.model.Cobertura;
import com.milkaxe_studios.clinicaapp.model.Medico;

public class AtualizarMedicoActivity extends ActivityController {

    private EditText NomeEditText;
    private EditText CRMEditText;
    private EditText EspecialidadeEditText;

    private MedicoController controller;
    private Medico medico;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atualizar_medico);

        this.progressBar = (ProgressBar) findViewById(R.id.progressBar);
        controller = new MedicoController(this, this.preferences);

        this.medico = Medico.getMedicoFromJSON(preferences.getString("Medico/Get",null));

        NomeEditText = (EditText) findViewById(R.id.nome_text_field);
        CRMEditText = (EditText) findViewById(R.id.crm_text_field);
        EspecialidadeEditText = (EditText) findViewById(R.id.especialidade_text_field);

        NomeEditText.setText(medico.Nome);
        CRMEditText.setText(medico.CRM);
        EspecialidadeEditText.setText(medico.Especialidade);
    }

    @Override
    public void notifyActivity(String... args) {}

    public void onClickDeletarButton(View view) {
        controller.deletarMedico(medico);
    }

    public void onClickAtualizarButton(View view) {

        medico.Nome = NomeEditText.getText().toString();
        medico.CRM = CRMEditText.getText().toString();
        medico.Especialidade = EspecialidadeEditText.getText().toString();

        controller.atualizarMedico(medico);
    }

}