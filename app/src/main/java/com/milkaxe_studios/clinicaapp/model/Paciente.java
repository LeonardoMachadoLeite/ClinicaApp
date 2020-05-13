package com.milkaxe_studios.clinicaapp.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Paciente {

    public String Id;
    public String Nome;
    public String datNascimento;
    public int celPaciente;
    public int RGPaciente;
    public int CPFPaciente;

    public Paciente(String id, String nome, String datNascimento, int celPaciente, int RGPaciente, int CPFPaciente) {
        Id = id;
        Nome = nome;
        this.datNascimento = datNascimento;
        this.celPaciente = celPaciente;
        this.RGPaciente = RGPaciente;
        this.CPFPaciente = CPFPaciente;
    }

    public Calendar getDatNascimento() throws ParseException {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
        cal.setTime(sdf.parse(datNascimento));
        return cal;
    }
}
