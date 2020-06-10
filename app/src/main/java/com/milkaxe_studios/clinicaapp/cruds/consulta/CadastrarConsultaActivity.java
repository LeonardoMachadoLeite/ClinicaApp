package com.milkaxe_studios.clinicaapp.cruds.consulta;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.milkaxe_studios.clinicaapp.R;
import com.milkaxe_studios.clinicaapp.model.ActivityController;
import com.milkaxe_studios.clinicaapp.model.Cobertura;
import com.milkaxe_studios.clinicaapp.model.Medico;
import com.milkaxe_studios.clinicaapp.model.Paciente;
import com.milkaxe_studios.clinicaapp.model.Pagamento;

public class CadastrarConsultaActivity extends ActivityController {

    private Medico medico;
    private Paciente paciente;
    private Cobertura cobertura;
    private Pagamento pagamento;

    private TextView medicoTextView;
    private TextView pacienteTextView;
    private TextView coberturaTextView;
    private TextView pagamentoTextView;
    private EditText dataEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_consulta);

        medico = Medico.getMedicoFromJSON(preferences.getString("Consulta/Medico", "{}"));
        paciente = Paciente.getPacienteFromJSON(preferences.getString("Consulta/Paciente", "{}"));
        cobertura = Cobertura.getCoberturaFromJSON(preferences.getString("Consulta/Cobertura", "{}"));
        String pagamentoString = preferences.getString("Consulta/Pagamento", "{}");
        pagamento = Pagamento.getPagamentoFromJSON(pagamentoString);

        medicoTextView = findViewById(R.id.textViewMedico);
        pacienteTextView = findViewById(R.id.textViewPaciente);
        coberturaTextView = findViewById(R.id.textViewCobertura);
        pagamentoTextView = findViewById(R.id.textViewPagamento);

        if (medico != null && medico.Id != null) {
            medicoTextView.setText(medico.Nome);
        }
        if (paciente != null && paciente.Id != null) {
            pacienteTextView.setText(paciente.Nome);
        }
        if (cobertura != null && cobertura.Id != null) {
            coberturaTextView.setText(cobertura.descCobertura);
        }
        if (pagamento != null && pagamento.IdConsulta != null) {
            pagamentoTextView.setText(String.format("R$ %s,00", pagamento.valor));
        }

    }

    @Override
    public void notifyActivity(String... args) {

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

    }

}
