package com.milkaxe_studios.clinicaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.milkaxe_studios.clinicaapp.controllers.EspecialidadeController;
import com.milkaxe_studios.clinicaapp.cruds.cobertura.CadastrarCoberturaActivity;
import com.milkaxe_studios.clinicaapp.cruds.cobertura.ListarCoberturaActivity;
import com.milkaxe_studios.clinicaapp.cruds.especialidade.CadastrarEspecialidadeActivity;
import com.milkaxe_studios.clinicaapp.cruds.especialidade.ListarEspecialidadesActivity;
import com.milkaxe_studios.clinicaapp.cruds.medico.CadastrarMedicoActivity;
import com.milkaxe_studios.clinicaapp.cruds.medico.ListarMedicoActivity;
import com.milkaxe_studios.clinicaapp.cruds.paciente.ListarPacienteActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences preferences = getSharedPreferences("com.milkaxe_studios.clinicaapp", MODE_PRIVATE);
        //new EspecialidadeController(preferences, null).getListaEspecialidades();
    }

    public void onClickIrParaMenuMedico(View view) {
        Intent intent = new Intent(this, ListarMedicoActivity.class);
        startActivity(intent);
    }

    public void onClickIrParaMenuEspecialidade(View view) {
        Intent intent = new Intent(this, ListarEspecialidadesActivity.class);
        startActivity(intent);
    }

    public void onClickIrParaMenuCobertura(View view) {
        Intent intent = new Intent(this, ListarCoberturaActivity.class);
        startActivity(intent);
    }

    public void onClickIrParaMenuPaciente(View view) {
        Intent intent = new Intent(this, ListarPacienteActivity.class);
        startActivity(intent);
    }

}
