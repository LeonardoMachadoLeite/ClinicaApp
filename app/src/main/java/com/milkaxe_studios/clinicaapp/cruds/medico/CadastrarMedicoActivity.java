package com.milkaxe_studios.clinicaapp.cruds.medico;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.milkaxe_studios.clinicaapp.R;
import com.milkaxe_studios.clinicaapp.model.Especialidade;

public class CadastrarMedicoActivity extends AppCompatActivity {

    private EditText especialidadeNomeEditText;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_medico);
    }

    public void onCLickCancelarButton(View view) {
        finish();
    }

    public void onClickCreateButton(View view) {
        mDatabase = FirebaseDatabase.getInstance().getReference();
        String novaEspecialidade = especialidadeNomeEditText.getText().toString();
        DatabaseReference espReference = mDatabase.child("Especialidades").push();
        espReference.setValue(new Especialidade(espReference.getKey(), novaEspecialidade));
        System.out.println("Cadastrar Médico");
        finish();
    }

}
