package com.example.showmethebill;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.showmethebill.databinding.GeneralCardViewBinding;
import com.example.showmethebill.databinding.GeneralViewHolderBinding;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

public class GeneralAdapter extends RecyclerView.Adapter<GeneralAdapter.GeneralViewHolder> {
   public  List<? extends generalWorkType> mGeneralList;
    @NonNull
    @Override
    public GeneralAdapter.GeneralViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        GeneralCardViewBinding binding = DataBindingUtil
        .inflate(LayoutInflater.from(parent.getContext()),
                R.layout.general_card_view,parent,false);
        return  new GeneralAdapter.GeneralViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull GeneralAdapter.GeneralViewHolder holder, int position) {
        holder.binding.setGeneralWorkType(mGeneralList.get(position));
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return mGeneralList == null? 0: mGeneralList.size();
    }
    class GeneralViewHolder extends RecyclerView.ViewHolder{
        final GeneralCardViewBinding binding;
        public GeneralViewHolder(@NonNull GeneralCardViewBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
    public void setAdaptorList(List<generalWorkType>list){
        mGeneralList = list;}

}
