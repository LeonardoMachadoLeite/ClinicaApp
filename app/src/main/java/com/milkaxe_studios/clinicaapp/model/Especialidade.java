package com.milkaxe_studios.clinicaapp.model;

public class Especialidade {

    public String Id;
    public String descEspecialidade;

    public Especialidade() {}
    public Especialidade(String descEspecialidade) {
        this.descEspecialidade = descEspecialidade;
    }
    public Especialidade(String id, String descEspecialidade) {
        Id = id;
        this.descEspecialidade = descEspecialidade;
    }

}
