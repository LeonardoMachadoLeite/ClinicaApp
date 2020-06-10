package com.milkaxe_studios.clinicaapp.controllers;

import android.content.SharedPreferences;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.milkaxe_studios.clinicaapp.model.ActivityController;
import com.milkaxe_studios.clinicaapp.model.FormaPagamento;

import org.json.JSONArray;

public class FormaPagamentoController {

    private ActivityController activity;
    private DatabaseReference mDatabase;
    private SharedPreferences preferences;

    JSONArray arrayListFormasPagamentos;

    public FormaPagamentoController(ActivityController activity, SharedPreferences preferences) {
        this.activity = activity;
        mDatabase = FirebaseDatabase.getInstance().getReference();
        this.preferences = preferences;
    }

    public void inserirFormaPagamento(FormaPagamento formaPagamento) {
        activity.setProgressBarVisible();

        DatabaseReference espReference = mDatabase.child("FormaPagamento").push();
        formaPagamento.Id = espReference.getKey();

        espReference.setValue(formaPagamento)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        System.out.println("Forma de Pagamento Cadastrado!");
                        activity.notifyActivity("Finish");
                    }
                })
                .addOnCanceledListener(new OnCanceledListener() {
                    @Override
                    public void onCanceled() {
                        activity.setProgressBarInvisible();
                        activity.toast("Falha no Cadastro da Forma de Pagamento");
                    }
                });
    }

    public void getFormaPagamento(final String id, final String...tags) {
        activity.setProgressBarVisible();

        mDatabase.child("FormaPagamento").child(id)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        FormaPagamento formaPagamento = dataSnapshot.getValue(FormaPagamento.class);
                        preferences.edit().putString("FormaPagamento/Get", formaPagamento.toString()).apply();
                        activity.notifyActivity(tags);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
    }

    public void getFormaPagamentoDesc(final String descFormaPagamento, final String...tags) {
        activity.setProgressBarVisible();

        mDatabase.child("FormaPagamento")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        FormaPagamento formaPagamento = null;
                        String value, compare;

                        for (DataSnapshot s : dataSnapshot.getChildren()) {
                            value = s.child("descFormaPagamento").getValue(String.class);
                            compare = value.toLowerCase();
                            if (compare.equals(descFormaPagamento)) {
                                formaPagamento = new FormaPagamento(
                                        s.child("Id").getValue(String.class),
                                        s.child("descFormaPagamento").getValue(String.class)
                                );
                                System.out.println();
                            }
                        }

                        preferences.edit().putString("FormaPagamento/Get", formaPagamento.toString()).apply();
                        activity.notifyActivity(tags);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
    }

    public void atualizarFormaPagamento(FormaPagamento formaPagamento) {
        activity.setProgressBarVisible();

        DatabaseReference espReference = mDatabase.child("FormaPagamento").child(formaPagamento.Id);

        espReference.setValue(formaPagamento)
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

    public void deletarFormaPagamento(FormaPagamento formaPagamento) {
        activity.setProgressBarVisible();

        DatabaseReference espReference = mDatabase.child("FormaPagamento").child(formaPagamento.Id);

        espReference.setValue(null)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        activity.toast("Forma de Pagamento Deletado!");
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

    public void getListaFormasPagamentos(final String...args) {
        activity.setProgressBarVisible();
        Query especialidades = mDatabase.child("FormaPagamento").orderByChild("descFormaPagamento");

        especialidades.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                arrayListFormasPagamentos = new JSONArray();
                String value;

                for (DataSnapshot s : dataSnapshot.getChildren()) {
                    value = s.child("descFormaPagamento").getValue(String.class);
                    arrayListFormasPagamentos.put(value);
                }

                preferences.edit().putString("FormaPagamento/Lista", arrayListFormasPagamentos.toString()).apply();
                activity.notifyActivity(args);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void getListaFormasPagamentosStartsWith(final String startsWith, final String... args) {
        activity.setProgressBarVisible();
        Query especialidades = mDatabase.child("FormaPagamento");

        especialidades.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                arrayListFormasPagamentos = new JSONArray();
                String value, compare;

                for (DataSnapshot s : dataSnapshot.getChildren()) {
                    value = s.child("descFormaPagamento").getValue(String.class);
                    compare = value.toLowerCase();
                    if (compare.startsWith(startsWith)) {
                        arrayListFormasPagamentos.put(value);
                    }
                }

                preferences.edit().putString("FormaPagamento/Lista", arrayListFormasPagamentos.toString()).apply();
                activity.notifyActivity(args);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
