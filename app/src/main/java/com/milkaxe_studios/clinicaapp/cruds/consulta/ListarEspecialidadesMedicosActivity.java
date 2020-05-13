package com.milkaxe_studios.clinicaapp.cruds.consulta;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.milkaxe_studios.clinicaapp.R;
import com.milkaxe_studios.clinicaapp.controllers.EspecialidadeController;
import com.milkaxe_studios.clinicaapp.cruds.especialidade.ListarEspecialidadesActivity;

import java.util.List;

public class ListarEspecialidadesMedicosActivity extends AppCompatActivity {

    EspecialidadeController controller;
    QueryEspecialdadesTask queryEspecialidades;

    EditText nomeEspecialidadeTextField;
    ListView listEspecialidades;
    List<String> listaStringEspecialidades;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_especialidades_medicos);

        controller = null;
        this.listEspecialidades = (ListView) findViewById(R.id.list_view_especialidades);
        this.nomeEspecialidadeTextField = (EditText) findViewById(R.id.search_edit_text);

        queryEspecialidades = new QueryEspecialdadesTask();
        queryEspecialidades.execute();
        hideKeyboard(this);
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
        hideKeyboard(this);
    }

    public List<String> getAutoFill(View view) {
        return listaStringEspecialidades;
    }

    public void refreshListView() {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getApplicationContext(),
                android.R.layout.simple_list_item_1,
                listaStringEspecialidades
        );
        listEspecialidades.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
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
