package com.milkaxe_studios.clinicaapp.controllers;

import android.content.SharedPreferences;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.milkaxe_studios.clinicaapp.model.ActivityController;
import com.milkaxe_studios.clinicaapp.model.Cobertura;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

public class CoberturaController {

    private ActivityController activity;
    private DatabaseReference mDatabase;
    private SharedPreferences preferences;
    private JSONArray arrayListCoberturas;

    public CoberturaController(ActivityController activity, SharedPreferences preferences) {
        this.activity = activity;
        mDatabase = FirebaseDatabase.getInstance().getReference();
        this.preferences = preferences;
    }

    public void inserirCobertura(Cobertura cobertura) {
        activity.setProgressBarVisible();

        DatabaseReference espReference = mDatabase.child("Coberturas").push();
        cobertura.Id = espReference.getKey();

        espReference.setValue(cobertura)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        activity.toast("Cobertura Cadastrada!");
                        activity.finish();
                    }
                })
                .addOnCanceledListener(new OnCanceledListener() {
                    @Override
                    public void onCanceled() {
                        activity.setProgressBarInvisible();
                        activity.toast("Falha no Cadastro da Cobertura");
                    }
                });
    }

    public void inserirCobertura(String cobertura) {
        this.inserirCobertura(new Cobertura("", cobertura));
    }

    public void getCobertura(final String descCobertura, final String...tags) {
        activity.setProgressBarVisible();

        mDatabase.child("Coberturas")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        Cobertura cobertura = null;

                        for (DataSnapshot snapshot: dataSnapshot.getChildren()) {
                            cobertura = snapshot.getValue(Cobertura.class);
                            if (cobertura.descCobertura.equals(descCobertura)) {
                                break;
                            }
                        }

                        preferences.edit().putString("Cobertura/Get", cobertura.toString()).apply();
                        activity.notifyActivity(tags);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
    }

    public void atualizarCobertura(Cobertura cobertura) {
        activity.setProgressBarVisible();

        DatabaseReference espReference = mDatabase.child("Coberturas").child(cobertura.Id);

        espReference.setValue(cobertura)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        activity.toast("Cobertura Atualizada!");
                        activity.finish();
                    }
                })
                .addOnCanceledListener(new OnCanceledListener() {
                    @Override
                    public void onCanceled() {
                        activity.setProgressBarInvisible();
                        activity.toast("Falha na Atualização da Cobertura");
                    }
                });
    }

    public void deletarCobertura(Cobertura cobertura) {
        activity.setProgressBarVisible();

        DatabaseReference espReference = mDatabase.child("Coberturas").child(cobertura.Id);

        espReference.setValue(null)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        activity.toast("Cobertura Deletada!");
                        activity.finish();
                    }
                })
                .addOnCanceledListener(new OnCanceledListener() {
                    @Override
                    public void onCanceled() {
                        activity.setProgressBarInvisible();
                        activity.toast("Falha na Atualização da Cobertura");
                    }
                });
    }

    public void getListaCoberturas(final String...args) {
        activity.setProgressBarVisible();
        Query coberturas = mDatabase.child("Coberturas").orderByChild("descCobertura");

        coberturas.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                arrayListCoberturas = new JSONArray();
                String value;

                for (DataSnapshot s : dataSnapshot.getChildren()) {
                    value = s.child("descCobertura").getValue(String.class);
                    arrayListCoberturas.put(value);
                }

                preferences.edit().putString("Cobertura/Lista",arrayListCoberturas.toString()).apply();
                activity.notifyActivity(args);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void getListaCoberturasStartsWith(final String startsWith, final String... args) {
        activity.setProgressBarVisible();
        Query coberturas = mDatabase.child("Coberturas");

        coberturas.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                arrayListCoberturas = new JSONArray();
                String value, compare;

                for (DataSnapshot s : dataSnapshot.getChildren()) {
                    value = s.child("descCobertura").getValue(String.class);
                    compare = value.toLowerCase();
                    if (compare.startsWith(startsWith)) {
                        arrayListCoberturas.put(value);
                    }
                }

                preferences.edit().putString("Cobertura/Lista",arrayListCoberturas.toString()).apply();
                activity.notifyActivity(args);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
