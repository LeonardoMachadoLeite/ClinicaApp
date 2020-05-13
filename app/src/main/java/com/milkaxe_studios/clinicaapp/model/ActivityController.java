package com.milkaxe_studios.clinicaapp.model;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.milkaxe_studios.clinicaapp.R;

public abstract class ActivityController extends AppCompatActivity {

    protected SharedPreferences preferences;
    protected ProgressBar progressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        preferences = getSharedPreferences("com.milkaxe_studios.clinicaapp", MODE_PRIVATE);
    }

    public abstract void notifyActivity(String... args);

    public void setProgressBarVisible() {
        this.progressBar.setVisibility(View.VISIBLE);
    }

    public void setProgressBarInvisible() {
        this.progressBar.setVisibility(View.GONE);
    }

    public void refresh() {
        finish();
        overridePendingTransition(0, 0);
        startActivity(getIntent());
        overridePendingTransition(0, 0);
    }

    public void toast(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
    }

}
