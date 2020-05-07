package com.milkaxe_studios.clinicaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.milkaxe_studios.clinicaapp.cruds.especialidade.CadastrarEspecialidadeActivity;
import com.milkaxe_studios.clinicaapp.cruds.especialidade.ListarEspecialidadesActivity;
import com.milkaxe_studios.clinicaapp.cruds.medico.CadastrarMedicoActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickIrParaCadastroMedico(View view) {
        Intent intent = new Intent(this, CadastrarMedicoActivity.class);
        startActivity(intent);
    }

    public void onClickIrParaCadastroEspecialidade(View view) {
        Intent intent = new Intent(this, CadastrarEspecialidadeActivity.class);
        startActivity(intent);
    }

    public void onClickIrParaListarEspecialidade(View view) {
        Intent intent = new Intent(this, ListarEspecialidadesActivity.class);
        startActivity(intent);
    }

}
