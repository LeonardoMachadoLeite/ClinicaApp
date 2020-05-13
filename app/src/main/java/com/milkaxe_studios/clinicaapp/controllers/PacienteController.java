package com.milkaxe_studios.clinicaapp.controllers;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.milkaxe_studios.clinicaapp.model.Paciente;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

public class PacienteController {

    private DatabaseReference mDatabase;

    public PacienteController() {
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    public boolean inserirPaciente(Paciente paciente) {
        final boolean[] insertionResult = new boolean[1];

        DatabaseReference espReference = mDatabase.child("Pacientes").push();
        paciente.Id = espReference.getKey();
        espReference.setValue(paciente)
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
    public boolean inserirPaciente(String nome, String datNascimento, int telPaciente, int RGPaciente, int CPFPaciente) {
        return this.inserirPaciente(new Paciente(
                null,
                nome,
                datNascimento,
                telPaciente,
                RGPaciente,
                CPFPaciente
        ));
    }

    public boolean atualizarPaciente() {
        return false;
    }

    public List<String> getListaPacientes() {
        final SortedSet<String> SortedSetEspec = new TreeSet<>();

        DatabaseReference especialidades = mDatabase.child("Coberturas");
        especialidades.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                SortedSetEspec.clear();
                for (DataSnapshot s : dataSnapshot.getChildren()) {
                    String e = s.child("descCobertura").toString();
                    SortedSetEspec.add(e);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        List<String> listEspec = new ArrayList<>();
        for (String s : SortedSetEspec) {
            listEspec.add(s);
        }

        return listEspec;
    }

}
