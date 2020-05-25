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
import com.milkaxe_studios.clinicaapp.model.Endereco;

import org.json.JSONArray;

public class EnderecoController {

    private ActivityController activity;
    private DatabaseReference mDatabase;
    private SharedPreferences preferences;

    public EnderecoController(ActivityController activity, SharedPreferences preferences) {
        this.activity = activity;
        mDatabase = FirebaseDatabase.getInstance().getReference();
        this.preferences = preferences;
    }

    public void inserirEndereco(Endereco endereco) {
        activity.setProgressBarVisible();

        DatabaseReference espReference = mDatabase.child("Enderecos").push();
        endereco.Id = espReference.getKey();

        espReference.setValue(endereco)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        activity.toast("Endereco Cadastrado!");
                        activity.notifyActivity();
                    }
                })
                .addOnCanceledListener(new OnCanceledListener() {
                    @Override
                    public void onCanceled() {
                        activity.setProgressBarInvisible();
                        activity.toast("Falha no Cadastro de Endereco");
                    }
                });
    }

    public void inserirEndereco(String idPaciente, String rua, String bairro, String numero) {
        this.inserirEndereco(new Endereco("", idPaciente, rua, bairro, numero));
    }

    public void getEndereco(final String idPaciente, final String...tags) {
        activity.setProgressBarVisible();

        mDatabase.child("Enderecos")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        Endereco endereco = null;

                        for (DataSnapshot snapshot: dataSnapshot.getChildren()) {
                            endereco = snapshot.getValue(Endereco.class);
                            if (endereco.IdPaciente.equals(idPaciente)) {
                                break;
                            }
                        }

                        preferences.edit().putString("Endereco/Get", endereco.toString()).apply();
                        activity.notifyActivity(tags);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
    }

    public void atualizarEndereco(Endereco endereco) {
        activity.setProgressBarVisible();

        DatabaseReference espReference = mDatabase.child("Enderecos").child(endereco.Id);

        espReference.setValue(endereco)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        activity.toast("Endereco Atualizada!");
                        activity.finish();
                    }
                })
                .addOnCanceledListener(new OnCanceledListener() {
                    @Override
                    public void onCanceled() {
                        activity.setProgressBarInvisible();
                        activity.toast("Falha na Atualização da Endereco");
                    }
                });
    }

    public void deletarEndereco(Endereco endereco) {
        activity.setProgressBarVisible();

        DatabaseReference espReference = mDatabase.child("Enderecos").child(endereco.Id);

        espReference.setValue(null)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        activity.toast("Endereco Deletada!");
                        activity.finish();
                    }
                })
                .addOnCanceledListener(new OnCanceledListener() {
                    @Override
                    public void onCanceled() {
                        activity.setProgressBarInvisible();
                        activity.toast("Falha na Atualização da Endereco");
                    }
                });
    }

}
