package com.milkaxe_studios.clinicaapp.model;

import org.json.JSONException;
import org.json.JSONObject;

public class Pagamento {

    public String IdConsulta;
    public String IdFormaPagamento;
    public String valor;
    public String datPagamento;

    public Pagamento() {}

    public Pagamento(String idConsulta, String idFormaPagamento, String valor, String datPagamento) {
        IdConsulta = idConsulta;
        IdFormaPagamento = idFormaPagamento;
        this.valor = valor;
        this.datPagamento = datPagamento;
    }

    @Override
    public String toString() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("IdConsulta", IdConsulta);
            jsonObject.put("IdFormaPagamento", IdFormaPagamento);
            jsonObject.put("valor", valor);
            jsonObject.put("datPagamento", datPagamento);
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
                    jsonObject.getString("IdConsulta"),
                    jsonObject.getString("IdFormaPagamento"),
                    jsonObject.getString("valor"),
                    jsonObject.getString("datPagamento")
            );
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return pagamento;
    }

}
