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
import com.milkaxe_studios.clinicaapp.model.ReqExame;

import org.json.JSONArray;

public class ReqExameController {

    private ActivityController activity;
    private DatabaseReference mDatabase;
    private SharedPreferences preferences;

    public ReqExameController(ActivityController activity, SharedPreferences preferences) {
        this.activity = activity;
        mDatabase = FirebaseDatabase.getInstance().getReference();
        this.preferences = preferences;
    }

    public void inserirReqExame(final ReqExame reqExame) {
        activity.setProgressBarVisible();

        DatabaseReference espReference = mDatabase.child("ReqExame").push();
        reqExame.Id = espReference.getKey();

        espReference.setValue(reqExame)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        System.out.println("Requerimento de Exame Cadastrado!");
                        activity.notifyActivity();
                    }
                })
                .addOnCanceledListener(new OnCanceledListener() {
                    @Override
                    public void onCanceled() {
                        activity.setProgressBarInvisible();
                        activity.toast("Falha no Cadastro do Requerimento de Exame");
                    }
                });
    }

    public void getReqExame(final String id, final String...tags) {
        activity.setProgressBarVisible();

        mDatabase.child("ReqExame")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        ReqExame reqExame = null;

                        for (DataSnapshot snapshot: dataSnapshot.getChildren()) {
                            reqExame = snapshot.getValue(ReqExame.class);
                            if (reqExame.Id.equals(id)) {
                                break;
                            }
                        }

                        preferences.edit().putString("ReqExame/Get", reqExame.toString()).apply();
                        activity.notifyActivity(tags);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
    }

    public void getRequisicoesExames(final String idConsulta, final String...tags) {
        activity.setProgressBarVisible();
        final JSONArray array = new JSONArray();

        mDatabase.child("ReqExame")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        ReqExame reqExame = null;

                        for (DataSnapshot snapshot: dataSnapshot.getChildren()) {
                            reqExame = snapshot.getValue(ReqExame.class);
                            if (reqExame.IdConsulta.equals(idConsulta)) {
                                array.put(reqExame.toString());
                            }
                        }

                        preferences.edit().putString("ReqExame/Lista", array.toString()).apply();
                        activity.notifyActivity(tags);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
    }

    public void atualizarReqExame(final ReqExame reqExame) {
        activity.setProgressBarVisible();

        DatabaseReference espReference = mDatabase.child("ReqExame").child(reqExame.Id);

        espReference.setValue(reqExame)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        System.out.println("Requerimento de Exame Atualizado!");
                        activity.notifyActivity();
                    }
                })
                .addOnCanceledListener(new OnCanceledListener() {
                    @Override
                    public void onCanceled() {
                        activity.setProgressBarInvisible();
                        activity.toast("Falha na Atualização do Requerimento de Exame");
                    }
                });
    }

    public void deletarReqExame(ReqExame reqExame) {
        activity.setProgressBarVisible();

        DatabaseReference espReference = mDatabase.child("ReqExame").child(reqExame.Id);

        espReference.setValue(null)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        activity.toast("Requerimento de Exame Deletado!");
                        activity.finish();
                    }
                })
                .addOnCanceledListener(new OnCanceledListener() {
                    @Override
                    public void onCanceled() {
                        activity.setProgressBarInvisible();
                        activity.toast("Falha na Atualização do Requerimento de Exame");
                    }
                });
    }

}
