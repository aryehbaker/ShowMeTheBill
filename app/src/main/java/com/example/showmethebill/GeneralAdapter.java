package com.example.showmethebill;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.showmethebill.databinding.GeneralCardViewBinding;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

public class GeneralAdapter extends RecyclerView.Adapter<GeneralAdapter.GeneralViewHolder> {
   private List<? extends generalWorkType> mGeneralList;
   private OnGeneralClickListener mOnGeneralClickListener;
   @NonNull
    @Override
    public GeneralAdapter.GeneralViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        GeneralCardViewBinding binding = DataBindingUtil
        .inflate(LayoutInflater.from(parent.getContext()),
                R.layout.general_card_view,parent,false);
        return  new GeneralAdapter.GeneralViewHolder(binding);
    }
    public void setOnItemClickListener(OnGeneralClickListener onGeneralClickListener) {
        mOnGeneralClickListener = onGeneralClickListener;
    }


    @Override
    public void onBindViewHolder(@NonNull GeneralAdapter.GeneralViewHolder holder, int position) {
        generalWorkType generalWorkType = mGeneralList.get(position);
        holder.bind(generalWorkType, mOnGeneralClickListener);
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
        public void bind (generalWorkType item, OnGeneralClickListener onGeneralClickListener) {
            binding.setGeneralWorkType(item);
            binding.executePendingBindings();
            itemView.setOnClickListener(v -> {
                if (onGeneralClickListener != null) {
                    onGeneralClickListener.onItemClick(v, item);
                }
            });
        }
    }
    public void setAdaptorList(List<generalWorkType>list){
        mGeneralList = list;
    }
    public void setMiddleAdaptor(int id){

    }
    public interface OnGeneralClickListener {
        void onItemClick(View view, generalWorkType item);
    }

}

