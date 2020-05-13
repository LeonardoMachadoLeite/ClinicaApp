package com.milkaxe_studios.clinicaapp.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Paciente {

    public String Id;
    public String Nome;
    public String datNascimento;
    public String celPaciente;
    public String RGPaciente;
    public String CPFPaciente;

    public Paciente() {}
    public Paciente(String nome, String datNascimento, String celPaciente, String RGPaciente, String CPFPaciente) {
        this.Nome = nome;
        this.datNascimento = datNascimento;
        this.celPaciente = celPaciente;
        this.RGPaciente = RGPaciente;
        this.CPFPaciente = CPFPaciente;
    }

    public static Paciente getPacienteFromJSON(String JsonString) {
        Paciente paciente = new Paciente();
        try {
            JSONObject jsonObject = new JSONObject(JsonString);
            paciente.Id = jsonObject.getString("Id");
            paciente.Nome = jsonObject.getString("Nome");
            paciente.datNascimento = jsonObject.getString("datNascimento");
            paciente.celPaciente = jsonObject.getString("celPaciente");
            paciente.RGPaciente = jsonObject.getString("RGPaciente");
            paciente.CPFPaciente = jsonObject.getString("CPFPaciente");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return paciente;
    }

    @Override
    public String toString() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("Id", this.Id);
            jsonObject.put("Nome", this.Nome);
            jsonObject.put("datNascimento", this.datNascimento);
            jsonObject.put("celPaciente", this.celPaciente);
            jsonObject.put("RGPaciente", this.RGPaciente);
            jsonObject.put("CPFPaciente", this.CPFPaciente);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }
}
