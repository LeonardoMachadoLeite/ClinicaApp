package com.milkaxe_studios.clinicaapp.cruds.paciente;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.milkaxe_studios.clinicaapp.R;
import com.milkaxe_studios.clinicaapp.controllers.PacienteController;
import com.milkaxe_studios.clinicaapp.model.ActivityController;
import com.milkaxe_studios.clinicaapp.model.Paciente;

public class AtualizarPacienteActivity extends ActivityController {

    private EditText NomePacienteEditText;
    private EditText DataNascPacienteEditText;
    private EditText CelPacienteEditText;
    private EditText RgPacienteEditText;
    private EditText CpfPacienteEditText;

    private PacienteController controller;
    private Paciente paciente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atualizar_paciente);

        this.progressBar = (ProgressBar) findViewById(R.id.progressBar);
        controller = new PacienteController(this, this.preferences);
        paciente = Paciente.getPacienteFromJSON(this.preferences.getString("Paciente/Get", null));

        NomePacienteEditText = (EditText) findViewById(R.id.create_paciente_nome_text_field);
        DataNascPacienteEditText = (EditText) findViewById(R.id.create_paciente_data_nasc_text_field);
        CelPacienteEditText = (EditText) findViewById(R.id.create_paciente_cel_text_field);
        RgPacienteEditText = (EditText) findViewById(R.id.create_paciente_rg_text_field);
        CpfPacienteEditText = (EditText) findViewById(R.id.create_paciente_cpf_text_field);

        NomePacienteEditText.setText(paciente.Nome);
        DataNascPacienteEditText.setText(paciente.datNascimento);
        CelPacienteEditText.setText(paciente.celPaciente);
        RgPacienteEditText.setText(paciente.RGPaciente);
        CpfPacienteEditText.setText(paciente.CPFPaciente);
    }

    @Override
    public void notifyActivity(String... args) {

    }

    public void onClickDeletarButton(View view) {
        controller.deletarPaciente(paciente);
        finish();
    }

    public void onClickAtualizarButton(View view) {
        paciente.Nome = NomePacienteEditText.getText().toString();
        paciente.datNascimento = DataNascPacienteEditText.getText().toString();
        paciente.celPaciente = CelPacienteEditText.getText().toString();
        paciente.RGPaciente = RgPacienteEditText.getText().toString();
        paciente.CPFPaciente = CpfPacienteEditText.getText().toString();

        controller.atualizarPaciente(paciente);
        finish();
    }
}
