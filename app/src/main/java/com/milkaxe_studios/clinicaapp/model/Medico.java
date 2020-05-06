package com.milkaxe_studios.clinicaapp.model;

public class Medico {

    public String Id;
    public String Nome;
    public String CRM;

    public Medico(String id, String nome, String CRM) {
        Id = id;
        Nome = nome;
        this.CRM = CRM;
    }
}
