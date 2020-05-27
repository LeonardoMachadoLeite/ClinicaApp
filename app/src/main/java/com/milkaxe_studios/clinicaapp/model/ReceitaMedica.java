package com.milkaxe_studios.clinicaapp.model;

import org.json.JSONException;
import org.json.JSONObject;

public class ReceitaMedica {

    public String
            Id,
            IdConsulta,
            descReceita,
            dataReceita;

    public ReceitaMedica() {}

    public ReceitaMedica(String idConsulta, String descReceita, String dataReceita) {
        IdConsulta = idConsulta;
        this.descReceita = descReceita;
        this.dataReceita = dataReceita;
    }

    @Override
    public String toString() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("Id", Id);
            jsonObject.put("IdConsulta", IdConsulta);
            jsonObject.put("descReceita", descReceita);
            jsonObject.put("dataReceita", dataReceita);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }

    public static ReceitaMedica getFromReceitaFromJSON(String jsonString) {
        ReceitaMedica receitaMedica = new ReceitaMedica();
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            receitaMedica.Id = jsonObject.getString("Id");
            receitaMedica.IdConsulta = jsonObject.getString("IdConsulta");
            receitaMedica.descReceita = jsonObject.getString("descReceita");
            receitaMedica.dataReceita = jsonObject.getString("dataReceita");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return receitaMedica;
    }

}
