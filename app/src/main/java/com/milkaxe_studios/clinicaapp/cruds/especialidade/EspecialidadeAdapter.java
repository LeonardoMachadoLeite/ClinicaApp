package com.milkaxe_studios.clinicaapp.cruds.especialidade;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.milkaxe_studios.clinicaapp.R;
import com.milkaxe_studios.clinicaapp.model.Especialidade;

import java.util.List;

public class EspecialidadeAdapter extends ArrayAdapter<Especialidade> {


    public EspecialidadeAdapter(@NonNull Context context, int resource, int textViewResourceId, @NonNull List<Especialidade> objects) {
        super(context, resource, textViewResourceId, objects);
    }

}
