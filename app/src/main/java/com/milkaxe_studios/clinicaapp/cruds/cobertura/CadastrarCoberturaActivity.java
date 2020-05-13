package com.milkaxe_studios.clinicaapp.cruds.cobertura;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.milkaxe_studios.clinicaapp.R;
import com.milkaxe_studios.clinicaapp.controllers.CoberturaController;

public class CadastrarCoberturaActivity extends AppCompatActivity {

    private EditText nomePlanoCoberturaEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_cobertura);
        nomePlanoCoberturaEditText = findViewById(R.id.create_cobertura_nome_text_field);
    }

    public void onCLickCancelarButton(View view) {
        finish();
    }

    public void onClickCreateButton(View view) {
        String nomePlano = nomePlanoCoberturaEditText.getText().toString();
        CoberturaController controller = new CoberturaController();
        controller.inserirCobertura(nomePlano);
        finish();
    }
}
