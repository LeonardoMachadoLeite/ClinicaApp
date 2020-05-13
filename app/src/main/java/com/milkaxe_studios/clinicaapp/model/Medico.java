package com.milkaxe_studios.clinicaapp.model;

import org.json.JSONException;
import org.json.JSONObject;

public class Medico {

    public String Id;
    public String Nome;
    public String CRM;
    public String Especialidade;

    public Medico() {}
    public Medico(String nome, String CRM, String Especialidade) {
        Nome = nome;
        this.CRM = CRM;
        this.Especialidade = Especialidade;
    }

    public static Medico getMedicoFromJSON(String jsonString) {
        Medico medico = new Medico();
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            medico.Id = jsonObject.getString("Id");
            medico.Nome = jsonObject.getString("Nome");
            medico.CRM = jsonObject.getString("CRM");
            medico.Especialidade = jsonObject.getString("Especialidade");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return medico;
    }

    @Override
    public String toString() {
        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("Id", this.Id);
            jsonObject.put("Nome", this.Nome);
            jsonObject.put("CRM", this.CRM);
            jsonObject.put("Especialidade", this.Especialidade);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonObject.toString();
    }

}
