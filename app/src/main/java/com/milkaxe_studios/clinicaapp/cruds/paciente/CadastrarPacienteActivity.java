package com.milkaxe_studios.clinicaapp.cruds.paciente;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.milkaxe_studios.clinicaapp.R;
import com.milkaxe_studios.clinicaapp.controllers.PacienteController;

public class CadastrarPacienteActivity extends AppCompatActivity {

    private EditText NomePacienteEditText;
    private EditText DataNascPacienteEditText;
    private EditText CelPacienteEditText;
    private EditText RgPacienteEditText;
    private EditText CpfPacienteEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_paciente);

        NomePacienteEditText = (EditText) findViewById(R.id.create_paciente_nome_text_field);
        DataNascPacienteEditText = (EditText) findViewById(R.id.create_paciente_data_nasc_text_field);
        CelPacienteEditText = (EditText) findViewById(R.id.create_paciente_cel_text_field);
        RgPacienteEditText = (EditText) findViewById(R.id.create_paciente_rg_text_field);
        CpfPacienteEditText = (EditText) findViewById(R.id.create_paciente_cpf_text_field);
    }

    public void onCLickCancelarButton(View view) {
        finish();
    }

    public void onClickCreateButton(View view) {
        String nome =  NomePacienteEditText.getText().toString();
        String dataNasc =  DataNascPacienteEditText.getText().toString();
        String cel =  CelPacienteEditText.getText().toString();
        String rg =  RgPacienteEditText.getText().toString();
        String cpf =  CpfPacienteEditText.getText().toString();

        PacienteController controller = new PacienteController();
        /*
        controller.inserirPaciente(
                nome,
                dataNasc,
                cel,
                rg,
                cpf
        );*/

        finish();
    }
}
