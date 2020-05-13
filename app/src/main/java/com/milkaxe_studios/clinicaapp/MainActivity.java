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
    EspecialidadeController especialidadeController;
    MedicoController medicoController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        especialidadeController = new EspecialidadeController(this, this.preferences);
        coberturaController = new CoberturaController(this, this.preferences);
        medicoController = new MedicoController(this, this.preferences);

        this.progressBar = (ProgressBar) findViewById(R.id.progressBar);
    }

    public void onClickIrParaMenuMedico(View view) {
        medicoController.getListaMedicos("Medico");
    }

    public void onClickIrParaMenuEspecialidade(View view) {
        especialidadeController.getListaEspecialidades("Especialidade");
    }

    public void onClickIrParaMenuCobertura(View view) {
        coberturaController.getListaCoberturas("Cobertura");
    }

    public void onClickIrParaMenuPaciente(View view) {

    }

    @Override
    public void notifyActivity(String... args) {
        Intent intent = null;

        switch (args[0]) {
            case "Especialidade":
                intent = new Intent(this, ListarEspecialidadesActivity.class);
                break;
            case "Cobertura":
                intent = new Intent(this, ListarCoberturaActivity.class);
                break;
            case "Paciente":
                intent = new Intent(this, ListarPacienteActivity.class);
                break;
            case "Medico":
                intent = new Intent(this, ListarMedicoActivity.class);
                break;
            case "Consulta":

                break;
        }

        startActivity(intent);
        this.setProgressBarInvisible();
    }

}
