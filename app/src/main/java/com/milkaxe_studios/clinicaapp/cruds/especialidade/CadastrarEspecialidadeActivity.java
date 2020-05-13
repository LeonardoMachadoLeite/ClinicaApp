package com.milkaxe_studios.clinicaapp.cruds.especialidade;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.milkaxe_studios.clinicaapp.R;
import com.milkaxe_studios.clinicaapp.controllers.EspecialidadeController;
import com.milkaxe_studios.clinicaapp.model.ActivityController;

public class CadastrarEspecialidadeActivity extends ActivityController {

    private EspecialidadeController controller;
    private EditText especialidadeNomeEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_especialidade);

        this.progressBar = (ProgressBar) findViewById(R.id.progressBar);
        especialidadeNomeEditText = (EditText) findViewById(R.id.create_espec_nome_text_field);
        controller = new EspecialidadeController(this, this.preferences);
    }

    @Override
    public void notifyActivity(String... args) {}

    public void onClickCancelarButton(View view) {
        finish();
    }

    public void onClickCreateButton(View view) {
        controller.inserirEspecialidade(especialidadeNomeEditText.getText().toString());
        finish();
    }

}
