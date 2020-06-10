package com.milkaxe_studios.clinicaapp.cruds.consulta;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.milkaxe_studios.clinicaapp.R;
import com.milkaxe_studios.clinicaapp.controllers.CoberturaController;
import com.milkaxe_studios.clinicaapp.cruds.cobertura.AtualizarCoberturaActivity;
import com.milkaxe_studios.clinicaapp.cruds.cobertura.CadastrarCoberturaActivity;
import com.milkaxe_studios.clinicaapp.model.ActivityController;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class SelecionarCoberturaActivity extends ActivityController {

    ArrayAdapter<String> adapter;
    ArrayList<String> coberturas;

    CoberturaController controller;
    EditText nomeCoberturaTextField;
    ListView listCoberturas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selecionar_cobertura);

        controller = new CoberturaController(this, preferences);
        this.progressBar = (ProgressBar) findViewById(R.id.progressBar);
        this.listCoberturas = (ListView) findViewById(R.id.list_view_coberturas);
        this.nomeCoberturaTextField = (EditText) findViewById(R.id.search_edit_text);

        coberturas = loadCoberturasArray();

        adapter = new ArrayAdapter<>(
                getApplicationContext(),
                android.R.layout.simple_list_item_1,
                coberturas
        );
        this.listCoberturas.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        listCoberturas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                controller.getCobertura(coberturas.get(position), "Selecionar");
            }
        });
    }

    private ArrayList<String> loadCoberturasArray() {
        String coberturasJsonString = preferences.getString("Cobertura/Lista","[]");
        ArrayList<String> array = new ArrayList<>();

        try {
            JSONArray coberutasJsonArray = new JSONArray(coberturasJsonString);
            for (int i = 0; i < coberutasJsonArray.length(); i++) {
                array.add(coberutasJsonArray.getString(i));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return array;
    }

    public void onClickNovaCobertura(View view) {
        Intent intent = new Intent(this, CadastrarCoberturaActivity.class);
        startActivity(intent);
        finish();
    }

    public void onClickBuscarCobertura(View view) {
        String alvo = this.nomeCoberturaTextField.getText().toString().trim().toLowerCase();

        if (!alvo.equals("")) {
            controller.getListaCoberturasStartsWith(alvo, "Busca");
        } else {
            controller.getListaCoberturas("Busca");
        }
    }

    @Override
    public void notifyActivity(String... args) {
        if (args[0].equals("Selecionar")) {
            String coberturaString = preferences.getString("Cobertura/Get", "{}");
            preferences.edit().putString("Consulta/Cobertura", coberturaString).apply();
            Intent intent = new Intent(this, CadastrarConsultaActivity.class);
            startActivity(intent);
            finish();
        } else {
            this.refresh();
        }
    }

}
