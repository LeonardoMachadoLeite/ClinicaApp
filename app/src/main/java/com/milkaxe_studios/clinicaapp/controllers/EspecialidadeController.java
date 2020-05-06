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
        especialidade.Id = espReference.getKey();
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
        return this.inserirEspecialidade(new Especialidade("", especialidade));
    }

    public boolean atualizarEspecialidade(Especialidade especialidade) {
        return this.inserirEspecialidade(especialidade);
    }

    public List<String> getListaEspecialidades() {
        final ArrayList<String> ListaEspecialidades = new ArrayList<>();

        DatabaseReference especialidades = mDatabase.child("Especialidades").orderByChild("descEspecialidade").getRef();
        especialidades.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ListaEspecialidades.clear();
                for (DataSnapshot s : dataSnapshot.getChildren()) {
                    String e = s.child("descEspecialidade").getValue(String.class);
                    ListaEspecialidades.add(e);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return ListaEspecialidades;
    }

    public List<String> getListaEspecialidades(final String startsWith) {
        final ArrayList<String> ListaEspecialidades = new ArrayList<>();


        DatabaseReference especialidades = mDatabase.child("Especialidades").orderByChild("descEspecialidade").startAt(startsWith).getRef();
        especialidades.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value, compare;
                ListaEspecialidades.clear();
                for (DataSnapshot s : dataSnapshot.getChildren()) {
                    value = s.child("descEspecialidade").getValue(String.class);
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
