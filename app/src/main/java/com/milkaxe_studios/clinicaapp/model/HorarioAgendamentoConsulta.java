package com.milkaxe_studios.clinicaapp.model;

import java.util.ArrayList;

public enum HorarioAgendamentoConsulta {

    SETE_HORAS_AM("7:00"),
    OITO_HORAS_AM("8:00"),
    NOVE_HORAS_AM("9:00"),
    DEZ_HORAS_AM("10:00"),
    ONZE_HORAS_AM("11:00"),
    UMA_HORA_PM("13:00"),
    DUAS_HORAS_PM("14:00"),
    TRES_HORAS_PM("15:00"),
    QUATRO_HORAS_PM("16:00"),
    CINCO_HORAS_PM("17:00");

    public String horario;

    HorarioAgendamentoConsulta(String horario) {
        this.horario = horario;
    }

    public static ArrayList<String> getHorariosPossiveis() {
        ArrayList<String> arrayList = new ArrayList<>();
        for (HorarioAgendamentoConsulta h: HorarioAgendamentoConsulta.values()) {
            arrayList.add(h.horario);
        }
        return arrayList;
    }

}
