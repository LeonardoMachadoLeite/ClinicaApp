package com.milkaxe_studios.clinicaapp.controllers;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.milkaxe_studios.clinicaapp.exceptions.ControllerUnexpectedResult;
import com.milkaxe_studios.clinicaapp.model.Especialidade;

import java.util.ArrayList;
import java.util.List;

public class EspecialidadeController {

    private DatabaseReference mDatabase;

    public EspecialidadeController() {
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    public boolean inserirEspecialidade(Especialidade especialidade) {
        final boolean[] insertionResult = new boolean[1];

        DatabaseReference espReference = mDatabase.child("Especialidades").push();
        especialidade.setId(espReference.getKey());
        espReference.setValue(especialidade)
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
    public boolean inserirEspecialidade(String especialidade) {
        final boolean[] insertionResult = new boolean[1];

        DatabaseReference espReference = mDatabase.child("Especialidades").push();
        espReference.setValue(new Especialidade(espReference.getKey(), especialidade))
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

    public Especialidade getEspecialidade(String Id) throws ControllerUnexpectedResult {
        Query q;

        q = mDatabase.child("Especialidades").child(Id).limitToFirst(1);
        final Especialidade[] especialidade = new Especialidade[1];

        q.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                especialidade[0] = dataSnapshot.getValue(Especialidade.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        if (especialidade[0] == null) {
            throw new ControllerUnexpectedResult("EspecialidadeController");
        }

        return especialidade[0];
    }

    public boolean atualizarEspecialidade(Especialidade especialidade) {
        return this.inserirEspecialidade(especialidade);
    }

    public List<String> getListaEspecialidadesMedicos() {
        final ArrayList<String> ListaEspecialidades = new ArrayList<>();

        DatabaseReference especialidades = mDatabase.child("Especialidade");
        especialidades.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ListaEspecialidades.clear();
                for (DataSnapshot s : dataSnapshot.getChildren()) {
                    String e = s.getKey();
                    ListaEspecialidades.add(e);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return ListaEspecialidades;
    }

    public List<String> getListaEspecialidadesMedicos(final @NonNull String startsWith) {
        final ArrayList<String> ListaEspecialidades = new ArrayList<>();

        DatabaseReference especialidades = mDatabase.child("Especialidade");
        especialidades.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value, compare;
                ListaEspecialidades.clear();
                for (DataSnapshot s : dataSnapshot.getChildren()) {
                    value = s.getKey();
                    compare = value.toLowerCase();
                    if (compare.startsWith(startsWith)) {
                        ListaEspecialidades.add(value);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return ListaEspecialidades;
    }

}
