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
import com.milkaxe_studios.clinicaapp.model.Consulta;

import org.json.JSONArray;

public class ConsultaController {

    private ActivityController activity;
    private DatabaseReference mDatabase;
    private SharedPreferences preferences;

    JSONArray arrayListConsultas;

    public ConsultaController(ActivityController activity, SharedPreferences preferences) {
        this.activity = activity;
        mDatabase = FirebaseDatabase.getInstance().getReference();
        this.preferences = preferences;
    }

    public void inserirConsulta(final Consulta consulta) {
        activity.setProgressBarVisible();

        DatabaseReference espReference = mDatabase.child("Consulta").push();
        consulta.Id = espReference.getKey();

        espReference.setValue(consulta)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        activity.setProgressBarInvisible();
                        System.out.println("Consulta Agendada!");
                        activity.notifyActivity("Consulta");
                    }
                })
                .addOnCanceledListener(new OnCanceledListener() {
                    @Override
                    public void onCanceled() {
                        activity.setProgressBarInvisible();
                        activity.toast("Falha no Cadastro da Consulta");
                    }
                });
    }

    public void getConsulta(final String id, final String... tags) {
        activity.setProgressBarVisible();

        mDatabase.child("Consulta").child(id)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        Consulta consulta = dataSnapshot.getValue(Consulta.class);
                        preferences.edit().putString("Consulta/Get", consulta.toString()).apply();
                        activity.notifyActivity(tags);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
    }

    public void getConsultaString(final String toString, final String... tags) {
        activity.setProgressBarVisible();

        mDatabase.child("Consulta")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        Consulta consulta = null;
                        String value, compare;

                        for (DataSnapshot s : dataSnapshot.getChildren()) {
                            value = s.child("toString").getValue(String.class);
                            compare = value.toLowerCase();
                            if (compare.equals(toString)) {
                                consulta = new Consulta(
                                        s.child("Id").getValue(String.class),
                                        s.child("IdMedico").getValue(String.class),
                                        s.child("IdPaciente").getValue(String.class),
                                        s.child("IdCobertura").getValue(String.class),
                                        s.child("dataConsulta").getValue(String.class),
                                        s.child("IdPagamento").getValue(String.class),
                                        s.child("toString").getValue(String.class)
                                );
                            }
                        }

                        preferences.edit().putString("Consulta/Get", consulta.toString()).apply();
                        activity.notifyActivity(tags);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
    }

    public void atualizarConsulta(Consulta consulta) {
        activity.setProgressBarVisible();

        DatabaseReference espReference = mDatabase.child("Consulta").child(consulta.Id);

        espReference.setValue(consulta)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        activity.toast("Forma de Pagamento Atualizado!");
                        activity.finish();
                    }
                })
                .addOnCanceledListener(new OnCanceledListener() {
                    @Override
                    public void onCanceled() {
                        activity.setProgressBarInvisible();
                        activity.toast("Falha na Atualização da Forma de Pagamento");
                    }
                });
    }

    public void deletarConsulta(Consulta consulta) {
        activity.setProgressBarVisible();

        DatabaseReference espReference = mDatabase.child("Consulta").child(consulta.Id);

        espReference.setValue(null)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        activity.toast("Consulta Deletada!");
                        activity.finish();
                    }
                })
                .addOnCanceledListener(new OnCanceledListener() {
                    @Override
                    public void onCanceled() {
                        activity.setProgressBarInvisible();
                        activity.toast("Falha na Atualização da Consulta");
                    }
                });
    }

    public void getListaConsultas(final String... args) {
        activity.setProgressBarVisible();
        Query especialidades = mDatabase.child("Consulta");

        especialidades.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                arrayListConsultas = new JSONArray();
                String value;

                for (DataSnapshot s : dataSnapshot.getChildren()) {
                    value = s.child("toString").getValue(String.class);
                    arrayListConsultas.put(value);
                }

                preferences.edit().putString("Consulta/Lista", arrayListConsultas.toString()).apply();
                activity.notifyActivity(args);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
