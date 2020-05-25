package com.milkaxe_studios.clinicaapp.model;

import org.json.JSONException;
import org.json.JSONObject;

public class FormaPagamento {

    public String Id;
    public String descFormaPagamento;

    public FormaPagamento() {}

    public FormaPagamento(String Id, String descFormaPagamento) {
        this.Id = Id;
        this.descFormaPagamento = descFormaPagamento;
    }

    @Override
    public String toString() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("Id", Id);
            jsonObject.put("descFormaPagamento", descFormaPagamento);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }

    public static FormaPagamento getFormaPagamentoFromJSON(String jsonString) {
        FormaPagamento formaPagamento = null;
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            formaPagamento = new FormaPagamento(
                    jsonObject.getString("Id"),
                    jsonObject.getString("descFormaPagamento")
            );
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return formaPagamento;
    }

}
