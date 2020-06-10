package com.milkaxe_studios.clinicaapp.model;

import org.json.JSONException;
import org.json.JSONObject;

public class Pagamento {

    public String Id;
    public String IdFormaPagamento;
    public String valor;

    public Pagamento() {}

    public Pagamento(String id, String idFormaPagamento, String valor) {
        Id = id;
        IdFormaPagamento = idFormaPagamento;
        this.valor = valor;
    }

    @Override
    public String toString() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("Id", Id);
            jsonObject.put("IdFormaPagamento", IdFormaPagamento);
            jsonObject.put("valor", valor);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }

    public static Pagamento getPagamentoFromJSON(String jsonString) {
        Pagamento pagamento = null;
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            pagamento = new Pagamento(
                    jsonObject.getString("Id"),
                    jsonObject.getString("IdFormaPagamento"),
                    jsonObject.getString("valor")
            );
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return pagamento;
    }

}
