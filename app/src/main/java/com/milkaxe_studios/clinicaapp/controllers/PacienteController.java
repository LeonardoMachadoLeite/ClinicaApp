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
import com.milkaxe_studios.clinicaapp.model.Paciente;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

public class PacienteController {

    private ActivityController activity;
    private DatabaseReference mDatabase;
    private SharedPreferences preferences;
    private JSONArray arrayListPacientes;

    public PacienteController(ActivityController activity, SharedPreferences preferences) {
        this.activity = activity;
        mDatabase = FirebaseDatabase.getInstance().getReference();
        this.preferences = preferences;
    }

    public void inserirPaciente(Paciente Paciente) {
        activity.setProgressBarVisible();

        DatabaseReference espReference = mDatabase.child("Pacientes").push();
        Paciente.Id = espReference.getKey();

        espReference.setValue(Paciente)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        activity.toast("Paciente Cadastrado!");
                        activity.finish();
                    }
                })
                .addOnCanceledListener(new OnCanceledListener() {
                    @Override
                    public void onCanceled() {
                        activity.setProgressBarInvisible();
                        activity.toast("Falha no Cadastro do Paciente");
                    }
                });
    }

    public void getPaciente(final String descPaciente, final String...tags) {
        activity.setProgressBarVisible();

        mDatabase.child("Pacientes")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        Paciente Paciente = null;

                        for (DataSnapshot snapshot: dataSnapshot.getChildren()) {
                            Paciente = snapshot.getValue(Paciente.class);
                            if (Paciente.Nome.equals(descPaciente)) {
                                break;
                            }
                        }

                        preferences.edit().putString("Paciente/Get", Paciente.toString()).apply();
                        activity.notifyActivity(tags);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
    }

    public void atualizarPaciente(Paciente Paciente) {
        activity.setProgressBarVisible();

        DatabaseReference espReference = mDatabase.child("Pacientes").child(Paciente.Id);

        espReference.setValue(Paciente)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        activity.toast("Paciente Atualizado!");
                        activity.finish();
                    }
                })
                .addOnCanceledListener(new OnCanceledListener() {
                    @Override
                    public void onCanceled() {
                        activity.setProgressBarInvisible();
                        activity.toast("Falha na Atualização do Paciente");
                    }
                });
    }

    public void deletarPaciente(Paciente Paciente) {
        activity.setProgressBarVisible();

        DatabaseReference espReference = mDatabase.child("Pacientes").child(Paciente.Id);

        espReference.setValue(null)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        activity.toast("Paciente Deletado!");
                        activity.finish();
                    }
                })
                .addOnCanceledListener(new OnCanceledListener() {
                    @Override
                    public void onCanceled() {
                        activity.setProgressBarInvisible();
                        activity.toast("Falha na Atualização do Paciente");
                    }
                });
    }

    public void getListaPacientes(final String...args) {
        activity.setProgressBarVisible();
        Query Pacientes = mDatabase.child("Pacientes").orderByChild("Nome");

        Pacientes.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                arrayListPacientes = new JSONArray();
                String Paciente;

                for (DataSnapshot s : dataSnapshot.getChildren()) {
                    Paciente = s.child("Nome").getValue(String.class);
                    arrayListPacientes.put(Paciente);
                }

                preferences.edit().putString("Paciente/Lista",arrayListPacientes.toString()).apply();
                activity.notifyActivity(args);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.err.println(databaseError.getMessage());
            }
        });
    }

    public void getListaPacientesStartsWith(final String startsWith, final String... args) {
        activity.setProgressBarVisible();
        Query Pacientes = mDatabase.child("Pacientes").orderByChild("Nome");

        Pacientes.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                arrayListPacientes = new JSONArray();
                String value, compare;

                for (DataSnapshot s : dataSnapshot.getChildren()) {
                    value = s.child("Nome").getValue(String.class);
                    compare = value.toLowerCase();
                    if (compare.startsWith(startsWith)) {
                        arrayListPacientes.put(value);
                    }
                }

                preferences.edit().putString("Paciente/Lista",arrayListPacientes.toString()).apply();
                activity.notifyActivity(args);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.err.println(databaseError.getMessage());
            }
        });
    }

}
