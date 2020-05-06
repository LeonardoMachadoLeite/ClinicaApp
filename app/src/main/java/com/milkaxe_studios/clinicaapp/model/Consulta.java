package com.milkaxe_studios.clinicaapp.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Consulta {

    public String Id;
    public String IdMedico;
    public String IdPaciente;
    public String IdCobertura;
    public String dataConsulta;

    public Consulta(String id, String idMedico, String idPaciente, String idCobertura, String dataConsulta) {
        Id = id;
        IdMedico = idMedico;
        IdPaciente = idPaciente;
        IdCobertura = idCobertura;
        this.dataConsulta = dataConsulta;
    }

    public Calendar getDataConsulta() throws ParseException {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
        cal.setTime(sdf.parse(dataConsulta));
        return cal;
    }
}
