package com.milkaxe_studios.clinicaapp.controllers;

import android.content.SharedPreferences;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.milkaxe_studios.clinicaapp.model.ActivityController;
import com.milkaxe_studios.clinicaapp.model.ReceitaMedica;

import org.json.JSONArray;

public class ReceitaMedicaController {

    private ActivityController activity;
    private DatabaseReference mDatabase;
    private SharedPreferences preferences;

    public ReceitaMedicaController(ActivityController activity, SharedPreferences preferences) {
        this.activity = activity;
        mDatabase = FirebaseDatabase.getInstance().getReference();
        this.preferences = preferences;
    }

    public void inserirReceitaMedica(final ReceitaMedica receita) {
        activity.setProgressBarVisible();

        DatabaseReference espReference = mDatabase.child("ReceitaMedica").push();
        receita.Id = espReference.getKey();

        espReference.setValue(receita)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        System.out.println("Receita Médica Cadastrada!");
                        activity.notifyActivity();
                    }
                })
                .addOnCanceledListener(new OnCanceledListener() {
                    @Override
                    public void onCanceled() {
                        activity.setProgressBarInvisible();
                        activity.toast("Falha no Cadastro da Receita Médica");
                    }
                });
    }

    public void getReceitaMedica(final String id, final String...tags) {
        activity.setProgressBarVisible();

        mDatabase.child("ReceitaMedica")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        ReceitaMedica receitaMedica = null;

                        for (DataSnapshot snapshot: dataSnapshot.getChildren()) {
                            receitaMedica = snapshot.getValue(ReceitaMedica.class);
                            if (receitaMedica.Id.equals(id)) {
                                break;
                            }
                        }

                        preferences.edit().putString("ReceitaMedica/Get", receitaMedica.toString()).apply();
                        activity.notifyActivity(tags);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
    }

    public void getReceitasMedicas(final String idConsulta, final String...tags) {
        activity.setProgressBarVisible();
        final JSONArray array = new JSONArray();

        mDatabase.child("ReceitaMedica")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        ReceitaMedica receitaMedica = null;

                        for (DataSnapshot snapshot: dataSnapshot.getChildren()) {
                            receitaMedica = snapshot.getValue(ReceitaMedica.class);
                            if (receitaMedica.IdConsulta.equals(idConsulta)) {
                                array.put(receitaMedica.toString());
                            }
                        }

                        preferences.edit().putString("ReceitaMedica/Lista", array.toString()).apply();
                        activity.notifyActivity(tags);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
    }

    public void atualizarReceitaMedica(final ReceitaMedica receitaMedica) {
        activity.setProgressBarVisible();

        DatabaseReference espReference = mDatabase.child("ReceitaMedica").child(receitaMedica.Id);

        espReference.setValue(receitaMedica)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        System.out.println("Receita Médica Atualizada!");
                        activity.notifyActivity();
                    }
                })
                .addOnCanceledListener(new OnCanceledListener() {
                    @Override
                    public void onCanceled() {
                        activity.setProgressBarInvisible();
                        activity.toast("Falha na Atualização da Receita Médica");
                    }
                });
    }

    public void deletarReceitaMedica(ReceitaMedica receitaMedica) {
        activity.setProgressBarVisible();

        DatabaseReference espReference = mDatabase.child("ReceitaMedica").child(receitaMedica.Id);

        espReference.setValue(null)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        activity.toast("Receita Médica Deletada!");
                        activity.finish();
                    }
                })
                .addOnCanceledListener(new OnCanceledListener() {
                    @Override
                    public void onCanceled() {
                        activity.setProgressBarInvisible();
                        activity.toast("Falha na Atualização da Receita Médica");
                    }
                });
    }

}
