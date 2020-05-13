package com.milkaxe_studios.clinicaapp.cruds.paciente;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.milkaxe_studios.clinicaapp.R;
import com.milkaxe_studios.clinicaapp.controllers.MedicoController;
import com.milkaxe_studios.clinicaapp.controllers.PacienteController;
import com.milkaxe_studios.clinicaapp.model.ActivityController;
import com.milkaxe_studios.clinicaapp.model.Paciente;

public class CadastrarPacienteActivity extends ActivityController {

    private EditText NomePacienteEditText;
    private EditText DataNascPacienteEditText;
    private EditText CelPacienteEditText;
    private EditText RgPacienteEditText;
    private EditText CpfPacienteEditText;

    private PacienteController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_paciente);

        this.progressBar = (ProgressBar) findViewById(R.id.progressBar);
        controller = new PacienteController(this, this.preferences);

        NomePacienteEditText = (EditText) findViewById(R.id.create_paciente_nome_text_field);
        DataNascPacienteEditText = (EditText) findViewById(R.id.create_paciente_data_nasc_text_field);
        CelPacienteEditText = (EditText) findViewById(R.id.create_paciente_cel_text_field);
        RgPacienteEditText = (EditText) findViewById(R.id.create_paciente_rg_text_field);
        CpfPacienteEditText = (EditText) findViewById(R.id.create_paciente_cpf_text_field);
    }

    @Override
    public void notifyActivity(String... args) {

    }

    public void onClickCancelarButton(View view) {
        finish();
    }

    public void onClickCreateButton(View view) {
        Paciente paciente = new Paciente(
                NomePacienteEditText.getText().toString(),
                DataNascPacienteEditText.getText().toString(),
                CelPacienteEditText.getText().toString(),
                RgPacienteEditText.getText().toString(),
                CpfPacienteEditText.getText().toString()
        );

        controller.inserirPaciente(paciente);
        finish();
    }
}
