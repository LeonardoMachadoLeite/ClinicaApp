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

public class AtualizarPacienteActivity extends ActivityController {

    private EditText NomePacienteEditText;
    private EditText DataNascPacienteEditText;
    private EditText CelPacienteEditText;
    private EditText RgPacienteEditText;
    private EditText CpfPacienteEditText;

    private EditText RuaEnderecoEditText;
    private EditText BairroEnderecoEditText;
    private EditText NumeroEnderecoEditText;

    private Paciente paciente;
    private Endereco endereco;
    private PacienteController pacienteController;
    private EnderecoController enderecoController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atualizar_paciente);

        this.progressBar = (ProgressBar) findViewById(R.id.progressBar);
        pacienteController = new PacienteController(this, this.preferences);
        enderecoController = new EnderecoController(this, this.preferences);

        paciente = Paciente.getPacienteFromJSON(this.preferences.getString("Paciente/Get", null));
        endereco = Endereco.getEnderecoFromJSON(this.preferences.getString("Endereco/Get", null));

        NomePacienteEditText = (EditText) findViewById(R.id.create_paciente_nome_text_field);
        DataNascPacienteEditText = (EditText) findViewById(R.id.create_paciente_data_nasc_text_field);
        CelPacienteEditText = (EditText) findViewById(R.id.create_paciente_cel_text_field);
        RgPacienteEditText = (EditText) findViewById(R.id.create_paciente_rg_text_field);
        CpfPacienteEditText = (EditText) findViewById(R.id.create_paciente_cpf_text_field);

        RuaEnderecoEditText = (EditText) findViewById(R.id.create_paciente_rua_text_field);
        BairroEnderecoEditText = (EditText) findViewById(R.id.create_paciente_bairro_text_field);
        NumeroEnderecoEditText = (EditText) findViewById(R.id.create_paciente_numero_text_field);

        NomePacienteEditText.setText(paciente.Nome);
        DataNascPacienteEditText.setText(paciente.datNascimento);
        CelPacienteEditText.setText(paciente.celPaciente);
        RgPacienteEditText.setText(paciente.RGPaciente);
        CpfPacienteEditText.setText(paciente.CPFPaciente);

        RuaEnderecoEditText.setText(endereco.Rua);
        BairroEnderecoEditText.setText(endereco.Bairro);
        NumeroEnderecoEditText.setText(endereco.Numero);
    }

    @Override
    public void notifyActivity(String... args) {
        if (args[0].equals("Paciente")) {

            endereco.Rua = RuaEnderecoEditText.getText().toString();
            endereco.Bairro = BairroEnderecoEditText.getText().toString();
            endereco.Numero = NumeroEnderecoEditText.getText().toString();

            enderecoController.atualizarEndereco(endereco);
        } else {
            this.toast("Paciente atualizado com sucesso!");
            finish();
        }
    }

    public void onClickDeletarButton(View view) {
        pacienteController.deletarPaciente(paciente);
        finish();
    }

    public void onClickAtualizarButton(View view) {
        paciente.Nome = NomePacienteEditText.getText().toString();
        paciente.datNascimento = DataNascPacienteEditText.getText().toString();
        paciente.celPaciente = CelPacienteEditText.getText().toString();
        paciente.RGPaciente = RgPacienteEditText.getText().toString();
        paciente.CPFPaciente = CpfPacienteEditText.getText().toString();

        pacienteController.atualizarPaciente(paciente);
    }
}
