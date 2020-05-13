package com.milkaxe_studios.clinicaapp.model;

import org.json.JSONException;
import org.json.JSONObject;

public class Cobertura {

    public String Id;
    public String descCobertura;

    public Cobertura() {}
    public Cobertura(String id, String descCobertura) {
        Id = id;
        this.descCobertura = descCobertura;
    }

    public static Cobertura getCoberturaFromJSON(String jsonString) {
        Cobertura cobertura = null;
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            cobertura = new Cobertura(
                    jsonObject.getString("Id"),
                    jsonObject.getString("descCobertura")
            );
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return cobertura;
    }

    @Override
    public String toString() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("Id", this.Id);
            jsonObject.put("descCobertura", this.descCobertura);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }

}
