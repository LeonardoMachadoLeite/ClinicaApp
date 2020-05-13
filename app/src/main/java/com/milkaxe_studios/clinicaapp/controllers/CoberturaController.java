package com.milkaxe_studios.clinicaapp.controllers;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.milkaxe_studios.clinicaapp.model.Cobertura;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

public class CoberturaController {

    private DatabaseReference mDatabase;

    public CoberturaController() {
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    public boolean inserirCobertura(Cobertura cobertura) {
        final boolean[] insertionResult = new boolean[1];

        DatabaseReference espReference = mDatabase.child("Coberturas").push();
        cobertura.Id = espReference.getKey();
        espReference.setValue(cobertura)
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
    public boolean inserirCobertura(String nomeCobertura) {
        return this.inserirCobertura(new Cobertura(null, nomeCobertura));
    }

    public boolean atualizarCobertura() {
        return false;
    }

    public List<String> getListaCoberturas() {
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
