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
import com.milkaxe_studios.clinicaapp.model.Medico;

import org.json.JSONArray;

public class MedicoController {

    private ActivityController activity;
    private DatabaseReference mDatabase;
    private SharedPreferences preferences;
    private JSONArray arrayListMedicos;

    public MedicoController(ActivityController activity, SharedPreferences preferences) {
        this.activity = activity;
        mDatabase = FirebaseDatabase.getInstance().getReference();
        this.preferences = preferences;
    }

    public void inserirMedico(Medico medico) {
        activity.setProgressBarVisible();

        DatabaseReference espReference = mDatabase.child("Medicos").push();
        medico.Id = espReference.getKey();

        espReference.setValue(medico)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        activity.toast("Medico Cadastrado!");
                        activity.finish();
                    }
                })
                .addOnCanceledListener(new OnCanceledListener() {
                    @Override
                    public void onCanceled() {
                        activity.setProgressBarInvisible();
                        activity.toast("Falha no Cadastro do Medico");
                    }
                });
    }

    public void getMedico(final String descMedico, final String...tags) {
        activity.setProgressBarVisible();

        mDatabase.child("Medicos")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        Medico medico = null;

                        for (DataSnapshot snapshot: dataSnapshot.getChildren()) {
                            medico = snapshot.getValue(Medico.class);
                            if (medico.Nome.equals(descMedico)) {
                                break;
                            }
                        }

                        preferences.edit().putString("Medico/Get", medico.toString()).apply();
                        activity.notifyActivity(tags);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
    }

    public void atualizarMedico(Medico Medico) {
        activity.setProgressBarVisible();

        DatabaseReference espReference = mDatabase.child("Medicos").child(Medico.Id);

        espReference.setValue(Medico)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        activity.toast("Medico Atualizado!");
                        activity.finish();
                    }
                })
                .addOnCanceledListener(new OnCanceledListener() {
                    @Override
                    public void onCanceled() {
                        activity.setProgressBarInvisible();
                        activity.toast("Falha na Atualização do Medico");
                    }
                });
    }

    public void deletarMedico(Medico Medico) {
        activity.setProgressBarVisible();

        DatabaseReference espReference = mDatabase.child("Medicos").child(Medico.Id);

        espReference.setValue(null)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        activity.toast("Medico Deletado!");
                        activity.finish();
                    }
                })
                .addOnCanceledListener(new OnCanceledListener() {
                    @Override
                    public void onCanceled() {
                        activity.setProgressBarInvisible();
                        activity.toast("Falha na Atualização do Medico");
                    }
                });
    }

    public void getListaMedicos(final String...args) {
        activity.setProgressBarVisible();
        Query Medicos = mDatabase.child("Medicos").orderByChild("Nome");

        Medicos.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                arrayListMedicos = new JSONArray();
                String medico;

                for (DataSnapshot s : dataSnapshot.getChildren()) {
                    medico = s.child("Nome").getValue(String.class);
                    arrayListMedicos.put(medico);
                }

                preferences.edit().putString("Medico/Lista",arrayListMedicos.toString()).apply();
                activity.notifyActivity(args);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.err.println(databaseError.getMessage());
            }
        });
    }

    public void getListaMedicosStartsWith(final String startsWith, final String... args) {
        activity.setProgressBarVisible();
        Query Medicos = mDatabase.child("Medicos").orderByChild("Nome");

        Medicos.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                arrayListMedicos = new JSONArray();
                String value, compare;

                for (DataSnapshot s : dataSnapshot.getChildren()) {
                    value = s.child("Nome").getValue(String.class);
                    compare = value.toLowerCase();
                    if (compare.startsWith(startsWith)) {
                        arrayListMedicos.put(value);
                    }
                }

                preferences.edit().putString("Medico/Lista",arrayListMedicos.toString()).apply();
                activity.notifyActivity(args);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.err.println(databaseError.getMessage());
            }
        });
    }

}
