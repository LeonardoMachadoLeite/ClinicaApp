package com.milkaxe_studios.clinicaapp.cruds.especialidade;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.milkaxe_studios.clinicaapp.R;
import com.milkaxe_studios.clinicaapp.controllers.EspecialidadeController;
import com.milkaxe_studios.clinicaapp.model.Especialidade;

import java.util.List;

public class ListarEspecialidadesActivity extends AppCompatActivity {

    EspecialidadeController controller;
    QueryEspecialdadesTask queryEspecialidades;

    EditText nomeEspecialidadeTextField;
    ListView listEspecialidades;
    List<String> listaStringEspecialidades;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_especialidades);

        controller = new EspecialidadeController();
        this.listEspecialidades = (ListView) findViewById(R.id.list_view_especialidades);
        this.nomeEspecialidadeTextField = (EditText) findViewById(R.id.search_edit_text);

        queryEspecialidades = new QueryEspecialdadesTask();
        queryEspecialidades.execute();
    }

    public void onClickBuscarEspecialidade(View view) {
        String alvo = this.nomeEspecialidadeTextField.getText().toString().trim().toLowerCase();
        if (!alvo.equals("")) {
            queryEspecialidades = new QueryEspecialdadesTask();
            queryEspecialidades.execute(alvo);
        } else {
            queryEspecialidades = new QueryEspecialdadesTask();
            queryEspecialidades.execute();
        }
    }

    public void refreshListView() {
        listEspecialidades.setAdapter(new ArrayAdapter<String>(
                getApplicationContext(),
                android.R.layout.simple_list_item_1,
                listaStringEspecialidades
        ));
    }

    private class QueryEspecialdadesTask extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... params) {
            if (params.length == 0) {
                listaStringEspecialidades = controller.getListaEspecialidadesMedicos();
            } else {
                listaStringEspecialidades = controller.getListaEspecialidadesMedicos(params[0]);
            }

            publishProgress();
            return null;
        }

        @Override
        protected void onProgressUpdate(Void ... voids) {
            refreshListView();
        }

    }

}
