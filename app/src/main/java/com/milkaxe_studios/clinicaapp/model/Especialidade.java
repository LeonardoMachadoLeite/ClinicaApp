package com.milkaxe_studios.clinicaapp.model;

import org.json.JSONException;
import org.json.JSONObject;

public class Especialidade {

    public String Id;
    public String descEspecialidade;

    public Especialidade() {}
    public Especialidade(String descEspecialidade) {
        this.descEspecialidade = descEspecialidade;
    }
    public Especialidade(String id, String descEspecialidade) {
        Id = id;
        this.descEspecialidade = descEspecialidade;
    }

    public static Especialidade getEspecialidadeFromJSON(String jsonString) {
        Especialidade especialidade = null;
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            especialidade = new Especialidade(
                    jsonObject.getString("Id"),
                    jsonObject.getString("descEspecialidade")
            );
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return especialidade;
    }

    @Override
    public String toString() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("Id", this.Id);
            jsonObject.put("descEspecialidade", this.descEspecialidade);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }

}
