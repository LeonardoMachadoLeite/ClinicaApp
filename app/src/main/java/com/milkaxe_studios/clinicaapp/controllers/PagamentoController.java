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
import com.milkaxe_studios.clinicaapp.model.Pagamento;

public class PagamentoController {

    private ActivityController activity;
    private DatabaseReference mDatabase;
    private SharedPreferences preferences;

    public PagamentoController(ActivityController activity, SharedPreferences preferences) {
        this.activity = activity;
        mDatabase = FirebaseDatabase.getInstance().getReference();
        this.preferences = preferences;
    }

    public void inserirPagamento(final Pagamento pagamento) {
        activity.setProgressBarVisible();

        DatabaseReference espReference = mDatabase.child("Pagamento").push();
        pagamento.Id = espReference.getKey();

        espReference.setValue(pagamento)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        System.out.println("Pagamento Cadastrado!");
                        activity.notifyActivity("Pagamento", pagamento.Id);
                    }
                })
                .addOnCanceledListener(new OnCanceledListener() {
                    @Override
                    public void onCanceled() {
                        activity.setProgressBarInvisible();
                        activity.toast("Falha no Cadastro de Pagamento");
                    }
                });
    }

    public void getPagamento(final String idConsulta, final String...tags) {
        activity.setProgressBarVisible();

        mDatabase.child("Pagamento").child(idConsulta)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        Pagamento pagamento = dataSnapshot.getValue(Pagamento.class);
                        preferences.edit().putString("Pagamento/Get", pagamento.toString()).apply();
                        activity.notifyActivity(tags);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
    }

    public void atualizarPagamento(Pagamento pagamento) {
        activity.setProgressBarVisible();

        DatabaseReference espReference = mDatabase.child("Pagamento").child(pagamento.Id);

        espReference.setValue(pagamento)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        activity.toast("Pagamento Atualizado!");
                        activity.finish();
                    }
                })
                .addOnCanceledListener(new OnCanceledListener() {
                    @Override
                    public void onCanceled() {
                        activity.setProgressBarInvisible();
                        activity.toast("Falha na Atualização no Pagamento");
                    }
                });
    }

}
