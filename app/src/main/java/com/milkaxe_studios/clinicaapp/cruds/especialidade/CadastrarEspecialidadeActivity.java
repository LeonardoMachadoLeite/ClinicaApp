package com.milkaxe_studios.clinicaapp.cruds.especialidade;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.milkaxe_studios.clinicaapp.R;
import com.milkaxe_studios.clinicaapp.controllers.EspecialidadeController;
import com.milkaxe_studios.clinicaapp.model.Especialidade;

public class CadastrarEspecialidadeActivity extends AppCompatActivity {

    private EspecialidadeController controller;
    private EditText especialidadeNomeEditText;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_especialidade);
        especialidadeNomeEditText = (EditText) findViewById(R.id.create_espec_nome_text_field);
        controller = null;
    }

    public void onCLickCancelarButton(View view) {
        finish();
    }

    public void onClickCreateButton(View view) {
        controller.inserirEspecialidade(especialidadeNomeEditText.getText().toString());
        finish();
    }

}
