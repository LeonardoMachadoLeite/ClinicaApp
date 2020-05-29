package com.milkaxe_studios.clinicaapp.cruds.paciente;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.milkaxe_studios.clinicaapp.R;
import com.milkaxe_studios.clinicaapp.controllers.EnderecoController;
import com.milkaxe_studios.clinicaapp.controllers.PacienteController;
import com.milkaxe_studios.clinicaapp.model.ActivityController;
import com.milkaxe_studios.clinicaapp.model.Endereco;
import com.milkaxe_studios.clinicaapp.model.Paciente;

public class CadastrarPacienteActivity extends ActivityController {

    private EditText NomePacienteEditText;
    private EditText DataNascPacienteEditText;
    private EditText CelPacienteEditText;
    private EditText RgPacienteEditText;
    private EditText CpfPacienteEditText;

    private EditText RuaEnderecoEditText;
    private EditText BairroEnderecoEditText;
    private EditText NumeroEnderecoEditText;

    private PacienteController pacienteController;
    private EnderecoController enderecoController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_paciente);

        this.progressBar = (ProgressBar) findViewById(R.id.progressBar);
        pacienteController = new PacienteController(this, this.preferences);
        enderecoController = new EnderecoController(this, this.preferences);

        NomePacienteEditText = (EditText) findViewById(R.id.create_paciente_nome_text_field);
        DataNascPacienteEditText = (EditText) findViewById(R.id.create_paciente_data_nasc_text_field);
        CelPacienteEditText = (EditText) findViewById(R.id.create_paciente_cel_text_field);
        RgPacienteEditText = (EditText) findViewById(R.id.create_paciente_rg_text_field);
        CpfPacienteEditText = (EditText) findViewById(R.id.create_paciente_cpf_text_field);

        RuaEnderecoEditText = (EditText) findViewById(R.id.create_paciente_rua_text_field);
        BairroEnderecoEditText = (EditText) findViewById(R.id.create_paciente_bairro_text_field);
        NumeroEnderecoEditText = (EditText) findViewById(R.id.create_paciente_numero_text_field);
    }

    @Override
    public void notifyActivity(String... args) {
        if (args[0].equals("Paciente")) {
            Endereco endereco = new Endereco(
                    "",
                    args[1],
                    RuaEnderecoEditText.getText().toString(),
                    BairroEnderecoEditText.getText().toString(),
                    NumeroEnderecoEditText.getText().toString()
            );

            enderecoController.inserirEndereco(endereco);
        } else {
            this.toast("Cadastro realizado com sucesso!");
            finish();
        }
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

        pacienteController.inserirPaciente(paciente);
    }
}
