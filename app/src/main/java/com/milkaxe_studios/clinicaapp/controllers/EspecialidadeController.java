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
import com.milkaxe_studios.clinicaapp.model.Especialidade;
import com.milkaxe_studios.clinicaapp.model.ActivityController;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class EspecialidadeController {

    private ActivityController activity;
    private DatabaseReference mDatabase;
    private SharedPreferences preferences;
    private JSONArray arrayListEspecialidades;

    public EspecialidadeController(ActivityController activity, SharedPreferences preferences) {
        this.activity = activity;
        mDatabase = FirebaseDatabase.getInstance().getReference();
        this.preferences = preferences;
    }

    public void inserirEspecialidade(Especialidade especialidade) {
        activity.setProgressBarVisible();

        DatabaseReference espReference = mDatabase.child("Especialidades").push();
        especialidade.Id = espReference.getKey();

        espReference.setValue(especialidade)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        activity.toast("Especialidade Cadastrada!");
                        activity.finish();
                    }
                })
                .addOnCanceledListener(new OnCanceledListener() {
                    @Override
                    public void onCanceled() {
                        activity.setProgressBarInvisible();
                        activity.toast("Falha no Cadastro da Especialidade");
                    }
                });
    }

    public void inserirEspecialidade(String especialidade) {
        this.inserirEspecialidade(new Especialidade("", especialidade));
    }

    public void getEspecialidade(final String descEspecialidade, final String...tags) {
        activity.setProgressBarVisible();

        mDatabase.child("Especialidades")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        Especialidade especialidade = null;

                        for (DataSnapshot snapshot: dataSnapshot.getChildren()) {
                            especialidade = snapshot.getValue(Especialidade.class);
                            if (especialidade.descEspecialidade.equals(descEspecialidade)) {
                                break;
                            }
                        }

                        preferences.edit().putString("Especialidade/Get", especialidade.toString()).apply();
                        activity.notifyActivity(tags);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
    }

    public void atualizarEspecialidade(Especialidade especialidade) {
        activity.setProgressBarVisible();

        DatabaseReference espReference = mDatabase.child("Especialidades").child(especialidade.Id);

        espReference.setValue(especialidade)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        activity.toast("Especialidade Atualizada!");
                        activity.finish();
                    }
                })
                .addOnCanceledListener(new OnCanceledListener() {
                    @Override
                    public void onCanceled() {
                        activity.setProgressBarInvisible();
                        activity.toast("Falha na Atualização da Especialidade");
                    }
                });
    }

    public void deletarEspecialidade(Especialidade especialidade) {
        activity.setProgressBarVisible();

        DatabaseReference espReference = mDatabase.child("Especialidades").child(especialidade.Id);

        espReference.setValue(null)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        activity.toast("Especialidade Deletada!");
                        activity.finish();
                    }
                })
                .addOnCanceledListener(new OnCanceledListener() {
                    @Override
                    public void onCanceled() {
                        activity.setProgressBarInvisible();
                        activity.toast("Falha na Atualização da Especialidade");
                    }
                });
    }

    public void getListaEspecialidades(final String...args) {
        activity.setProgressBarVisible();
        Query especialidades = mDatabase.child("Especialidades").orderByChild("descEspecialidade");

        especialidades.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                arrayListEspecialidades = new JSONArray();
                String value;

                for (DataSnapshot s : dataSnapshot.getChildren()) {
                    value = s.child("descEspecialidade").getValue(String.class);
                    arrayListEspecialidades.put(value);
                }

                preferences.edit().putString("Especialidade/Lista",arrayListEspecialidades.toString()).apply();
                activity.notifyActivity(args);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void getListaEspecialidadesStartsWith(final String startsWith, final String... args) {
        activity.setProgressBarVisible();
        Query especialidades = mDatabase.child("Especialidades");

        especialidades.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                arrayListEspecialidades = new JSONArray();
                String value, compare;

                for (DataSnapshot s : dataSnapshot.getChildren()) {
                    value = s.child("descEspecialidade").getValue(String.class);
                    compare = value.toLowerCase();
                    if (compare.startsWith(startsWith)) {
                        arrayListEspecialidades.put(value);
                    }
                }

                preferences.edit().putString("Especialidade/Lista",arrayListEspecialidades.toString()).apply();
                activity.notifyActivity(args);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
/*
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
*/
}
