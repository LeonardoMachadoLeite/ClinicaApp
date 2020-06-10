package com.milkaxe_studios.clinicaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.milkaxe_studios.clinicaapp.controllers.CoberturaController;
import com.milkaxe_studios.clinicaapp.controllers.EspecialidadeController;
import com.milkaxe_studios.clinicaapp.controllers.MedicoController;
import com.milkaxe_studios.clinicaapp.controllers.PacienteController;
import com.milkaxe_studios.clinicaapp.cruds.cobertura.CadastrarCoberturaActivity;
import com.milkaxe_studios.clinicaapp.cruds.cobertura.ListarCoberturaActivity;
import com.milkaxe_studios.clinicaapp.cruds.especialidade.CadastrarEspecialidadeActivity;
import com.milkaxe_studios.clinicaapp.cruds.especialidade.ListarEspecialidadesActivity;
import com.milkaxe_studios.clinicaapp.cruds.medico.CadastrarMedicoActivity;
import com.milkaxe_studios.clinicaapp.cruds.medico.ListarMedicoActivity;
import com.milkaxe_studios.clinicaapp.cruds.paciente.ListarPacienteActivity;
import com.milkaxe_studios.clinicaapp.model.ActivityController;

public class MainActivity extends ActivityController {

    CoberturaController coberturaController;
    MedicoController medicoController;
    PacienteController pacienteController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        coberturaController = new CoberturaController(this, this.preferences);
        medicoController = new MedicoController(this, this.preferences);
        pacienteController = new PacienteController(this, this.preferences);

        this.progressBar = (ProgressBar) findViewById(R.id.progressBar);
    }

    public void onClickIrParaAgendarConsulta(View view) {
        medicoController.getListaMedicos("Medico");
    }

    public void onClickIrParaMenuMedico(View view) {
        medicoController.getListaMedicos("Medico");
    }

    public void onClickIrParaMenuOutros(View view) {
        this.notifyActivity("Outros");
    }

    public void onClickIrParaMenuCobertura(View view) {
        coberturaController.getListaCoberturas("Cobertura");
    }

    public void onClickIrParaMenuPaciente(View view) {
        pacienteController.getListaPacientes("Paciente");
    }

    @Override
    public void notifyActivity(String... args) {
        Intent intent = null;

        switch (args[0]) {
            case "Outros":
                intent = new Intent(this, SelecionarPerifericoActivity.class);
                break;
            case "Consulta":
                //intent = new Intent(this, .class);
                break;
            case "Paciente":
                intent = new Intent(this, ListarPacienteActivity.class);
                break;
            case "Medico":
                intent = new Intent(this, ListarMedicoActivity.class);
                break;
        }

        startActivity(intent);
        this.setProgressBarInvisible();
    }

}
