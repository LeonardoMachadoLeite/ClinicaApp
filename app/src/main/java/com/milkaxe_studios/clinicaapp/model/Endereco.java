package com.milkaxe_studios.clinicaapp.model;

import org.json.JSONException;
import org.json.JSONObject;

public class Endereco {

    public String Id;
    public String IdPaciente;
    public String Rua;
    public String Bairro;
    public String Numero;

    public Endereco() {}

    public Endereco(String id, String idPaciente, String rua, String bairro, String numero) {
        Id = id;
        IdPaciente = idPaciente;
        Rua = rua;
        Bairro = bairro;
        Numero = numero;
    }

    public String toString() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("Id", Id);
            jsonObject.put("IdPaciente", IdPaciente);
            jsonObject.put("Rua", Rua);
            jsonObject.put("Bairro", Bairro);
            jsonObject.put("Numero", Numero);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }

    public static Endereco getEnderecoFromJSON(String jsonString) {
        Endereco endereco = null;
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            endereco = new Endereco(
                    jsonObject.getString("Id"),
                    jsonObject.getString("IdPaciente"),
                    jsonObject.getString("Rua"),
                    jsonObject.getString("Bairro"),
                    jsonObject.getString("Numero")
            );
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return endereco;
    }

}
