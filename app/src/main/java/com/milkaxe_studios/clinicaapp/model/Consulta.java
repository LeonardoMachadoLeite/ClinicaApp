package com.milkaxe_studios.clinicaapp.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Consulta {

    public String Id;
    public String IdMedico;
    public String IdPaciente;
    public String IdCobertura;
    public String IdPagamento;
    public String dataConsulta;
    public String toString;

    public Consulta() {}

    public Consulta(String id, String idMedico, String idPaciente, String idCobertura, String idPagamento, String dataConsulta, String toString) {
        this.Id = id;
        this.IdMedico = idMedico;
        this.IdPaciente = idPaciente;
        this.IdCobertura = idCobertura;
        this.IdPagamento = idPagamento;
        this.dataConsulta = dataConsulta;
        this.toString = toString;
    }

    @Override
    public String toString() {
        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("Id", this.Id);
            jsonObject.put("IdMedico", this.IdMedico);
            jsonObject.put("IdPaciente", this.IdPaciente);
            jsonObject.put("IdCobertura", this.IdCobertura);
            jsonObject.put("IdPagamento", this.IdPagamento);
            jsonObject.put("dataConsulta", this.dataConsulta);
            jsonObject.put("toString", this.toString);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonObject.toString();
    }

    public static Consulta getConsultaFromJSON(String jsonString) {
        Consulta consulta = new Consulta();

        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            consulta.Id = jsonObject.getString("Id");
            consulta.IdMedico = jsonObject.getString("IdMedico");
            consulta.IdPaciente = jsonObject.getString("IdPaciente");
            consulta.IdCobertura = jsonObject.getString("IdCobertura");
            consulta.IdPagamento = jsonObject.getString("IdPagamento");
            consulta.dataConsulta = jsonObject.getString("dataConsulta");
            consulta.toString = jsonObject.getString("toString");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return consulta;
    }

}
