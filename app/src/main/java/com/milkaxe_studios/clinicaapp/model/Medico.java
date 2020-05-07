package com.milkaxe_studios.clinicaapp.model;

public class Medico {

    public String Id;
    public String Nome;
    public String CRM;
    public String Especialidade;

    public Medico(String nome, String CRM, String Especialidade) {
        Nome = nome;
        this.CRM = CRM;
        this.Especialidade = Especialidade;
    }
}
