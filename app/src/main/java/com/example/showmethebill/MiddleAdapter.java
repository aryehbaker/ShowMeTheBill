package com.example.showmethebill;

import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MiddleAdapter extends RecyclerView.Adapter<MiddleAdapter.MiddleViewHolder> {
    public List<? extends middleWorkType> mMiddleList;
    @NonNull
    @Override
    public MiddleAdapter.MiddleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull MiddleAdapter.MiddleViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
    class MiddleViewHolder extends RecyclerView.ViewHolder {
        public MiddleViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
