package com.milkaxe_studios.clinicaapp.cruds.especialidade;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.milkaxe_studios.clinicaapp.R;
import com.milkaxe_studios.clinicaapp.controllers.EspecialidadeController;
import com.milkaxe_studios.clinicaapp.model.ActivityController;
import com.milkaxe_studios.clinicaapp.model.Especialidade;

public class AtualizarEspecialidadeActivity extends ActivityController {

    Especialidade especialidade;
    EspecialidadeController controller;

    EditText descEspecialidadeEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atualizar_especialidade);


        this.progressBar = (ProgressBar) findViewById(R.id.progressBar);
        this.controller = new EspecialidadeController(this, this.preferences);
        this.especialidade = Especialidade.getEspecialidadeFromJSON(preferences.getString("Especialidade/Get",null));

        this.descEspecialidadeEditText = (EditText) findViewById(R.id.nome_especialidade_text_field);
        this.descEspecialidadeEditText.setText(this.especialidade.descEspecialidade);
    }

    @Override
    public void notifyActivity(String... args) {}

    public void onClickAtualizar(View view) {
        especialidade.descEspecialidade = descEspecialidadeEditText.getText().toString();

        controller.atualizarEspecialidade(especialidade);
    }

    public void onClickDelete(View view) {
        controller.deletarEspecialidade(especialidade);
    }

}
