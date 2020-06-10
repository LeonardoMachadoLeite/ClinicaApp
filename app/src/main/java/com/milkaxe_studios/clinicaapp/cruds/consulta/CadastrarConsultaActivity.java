package com.milkaxe_studios.clinicaapp.cruds.consulta;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.milkaxe_studios.clinicaapp.R;
import com.milkaxe_studios.clinicaapp.controllers.ConsultaController;
import com.milkaxe_studios.clinicaapp.controllers.PagamentoController;
import com.milkaxe_studios.clinicaapp.model.ActivityController;
import com.milkaxe_studios.clinicaapp.model.Cobertura;
import com.milkaxe_studios.clinicaapp.model.Consulta;
import com.milkaxe_studios.clinicaapp.model.Medico;
import com.milkaxe_studios.clinicaapp.model.Paciente;
import com.milkaxe_studios.clinicaapp.model.Pagamento;

public class CadastrarConsultaActivity extends ActivityController {

    private Medico medico;
    private Paciente paciente;
    private Cobertura cobertura;
    private Pagamento pagamento;
    private String data;

    private TextView medicoTextView;
    private TextView pacienteTextView;
    private TextView coberturaTextView;
    private TextView pagamentoTextView;
    private EditText dataEditText;

    private ConsultaController consultaController;
    private PagamentoController pagamentoController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_consulta);

        this.progressBar = (ProgressBar) findViewById(R.id.progressBar);
        consultaController = new ConsultaController(this, preferences);
        pagamentoController = new PagamentoController(this, preferences);

        medico = Medico.getMedicoFromJSON(preferences.getString("Consulta/Medico", "{}"));
        paciente = Paciente.getPacienteFromJSON(preferences.getString("Consulta/Paciente", "{}"));
        cobertura = Cobertura.getCoberturaFromJSON(preferences.getString("Consulta/Cobertura", "{}"));
        pagamento = Pagamento.getPagamentoFromJSON(preferences.getString("Consulta/Pagamento", "{}"));
        data = preferences.getString("Consulta/Data","");

        medicoTextView = findViewById(R.id.textViewMedico);
        pacienteTextView = findViewById(R.id.textViewPaciente);
        coberturaTextView = findViewById(R.id.textViewCobertura);
        pagamentoTextView = findViewById(R.id.textViewPagamento);
        dataEditText = findViewById(R.id.data_text_field);

        if (medico != null && medico.Id != null) {
            medicoTextView.setText(medico.Nome);
        }
        if (paciente != null && paciente.Id != null) {
            pacienteTextView.setText(paciente.Nome);
        }
        if (cobertura != null && cobertura.Id != null) {
            coberturaTextView.setText(cobertura.descCobertura);
        }
        if (pagamento != null && pagamento.Id != null) {
            pagamentoTextView.setText(String.format("R$ %s,00", pagamento.valor));
        }

    }

    @Override
    public void notifyActivity(String... args) {
        if (args[0].equals("Pagamento")) {
            pagamento.Id = args[1];

            Consulta consulta = new Consulta(
                    "null",
                    medico.Id,
                    paciente.Id,
                    cobertura.Id,
                    pagamento.Id,
                    dataEditText.getText().toString(),
                    String.format("%s - %s - %s", paciente.Nome, medico.Nome, dataEditText.getText().toString())
            );

            consultaController.inserirConsulta(consulta);
        } else {
            finish();
        }
    }

    public void onClickAlterarPaciente(View view) {
        Intent intent = new Intent(this, SelecionarPacienteActivity.class);
        startActivity(intent);
        finish();
    }

    public void onClickAlterarMedico(View view) {
        Intent intent = new Intent(this, SelecionarMedicoConsultaActivity.class);
        startActivity(intent);
        finish();
    }

    public void onClickAlterarCobertura(View view) {
        Intent intent = new Intent(this, SelecionarCoberturaActivity.class);
        startActivity(intent);
        finish();
    }

    public void onClickPagar(View view) {
        Intent intent = new Intent(this, CadastrarPagamentoActivity.class);
        startActivity(intent);
        finish();
    }

    public void onClickCancelarButton(View view) {
        finish();
    }

    public void onClickCreateButton(View view) {
        if (medico.Id != null && paciente.Id != null &&
                cobertura.Id != null && pagamento.valor != null &&
                !dataEditText.getText().toString().equals("")) {
            pagamentoController.inserirPagamento(pagamento);
        }
    }

}
