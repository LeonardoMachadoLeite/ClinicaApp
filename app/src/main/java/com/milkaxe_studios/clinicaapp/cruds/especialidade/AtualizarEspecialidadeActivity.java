package com.milkaxe_studios.clinicaapp.cruds.especialidade;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.milkaxe_studios.clinicaapp.R;
import com.milkaxe_studios.clinicaapp.controllers.EspecialidadeController;
import com.milkaxe_studios.clinicaapp.model.Especialidade;

public class AtualizarEspecialidadeActivity extends AppCompatActivity {

    Especialidade especialidade;
    SharedPreferences preferences;
    EspecialidadeController controller;

    EditText descEspecialidadeEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atualizar_especialidade);

        this.preferences = getSharedPreferences("com.milkaxe_studios.clinicaapp", MODE_PRIVATE);
        this.especialidade = Especialidade.getEspecialidadeFromJSON(preferences.getString("Especialidade/Get",null));

        this.descEspecialidadeEditText = (EditText) findViewById(R.id.nome_especialidade_text_field);
        this.descEspecialidadeEditText.setText(this.especialidade.descEspecialidade);
    }

    public void onClickAtualizar(View view) {
        controller.atualizarEspecialidade(especialidade);
    }

    public void onClickDelete(View view) {
        controller.deletarEspecialidade(especialidade);
    }

}
