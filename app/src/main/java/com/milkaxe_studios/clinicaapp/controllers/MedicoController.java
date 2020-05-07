package com.milkaxe_studios.clinicaapp.controllers;

import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.milkaxe_studios.clinicaapp.model.Medico;

public class MedicoController {

    private DatabaseReference dbMedicos;

    public MedicoController() {
        dbMedicos = FirebaseDatabase.getInstance().getReference("Medicos");
    }

    public boolean inserirMedico(String nome, String CRM, String Especialidade) {
        final boolean[] insertionResult = new boolean[1];

        Medico medico = new Medico(nome, CRM, Especialidade);
        DatabaseReference ref = dbMedicos.push();
        medico.Id = ref.getKey();
        ref.setValue(medico)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        insertionResult[0] = true;
                    }
                })
                .addOnCanceledListener(new OnCanceledListener() {
                    @Override
                    public void onCanceled() {
                        insertionResult[0] = false;
                    }
                });

        return insertionResult[0];
    }

}
