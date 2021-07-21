package edu.pe.idat.proyectofinal2.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import edu.pe.idat.proyectofinal2.databinding.ResumeRowBinding;
import edu.pe.idat.proyectofinal2.models.CarritoItem;

public class ResumeAdapter extends ListAdapter<CarritoItem, ResumeAdapter.ViewHolder> {
    public ResumeAdapter() {
        super(CarritoItem.itemCallback);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ResumeRowBinding resumeRowBinding =ResumeRowBinding.inflate(layoutInflater, parent , false);

        return new ViewHolder(resumeRowBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.resumeRowBinding.setResumeItem(getItem(position));
        holder.resumeRowBinding.executePendingBindings();

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ResumeRowBinding resumeRowBinding;
        public ViewHolder(ResumeRowBinding resumeRowBinding) {
            super(resumeRowBinding.getRoot());
            this.resumeRowBinding = resumeRowBinding;
        }
    }
}
