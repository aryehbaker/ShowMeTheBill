package com.example.showmethebill;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.showmethebill.databinding.GeneralCardViewBinding;

public class GeneralAdapter extends RecyclerView.Adapter<GeneralAdapter.GeneralViewHolder> {
   private List<GeneralWorkType> mGeneralList;
   private OnGeneralClickListener mOnGeneralClickListener;
   private Context mContext;
   public GeneralAdapter(Context context,OnGeneralClickListener onGeneralClickListener){
       mOnGeneralClickListener = onGeneralClickListener;
       mContext = context;
   }
   @NonNull
    @Override
    public GeneralAdapter.GeneralViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        GeneralCardViewBinding binding = DataBindingUtil
        .inflate(LayoutInflater.from(mContext),
                R.layout.general_card_view,parent,false);
       return new GeneralViewHolder(binding);
    }


    @Override
    public void onBindViewHolder(@NonNull GeneralAdapter.GeneralViewHolder holder, int position) {
        GeneralWorkType generalWorkType = mGeneralList.get(position);
        holder.bind(generalWorkType);
    }

    @Override
    public int getItemCount() {
        return mGeneralList == null? 0: mGeneralList.size();
    }
    class GeneralViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        final GeneralCardViewBinding binding;
        public GeneralViewHolder(@NonNull GeneralCardViewBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            itemView.setOnClickListener(this);
            binding.generalAdd.setOnClickListener(this);
        }
        public void bind (GeneralWorkType item) {
            binding.setGeneralType(item);
            binding.executePendingBindings();

        }

        @Override
        public void onClick(View v) {
            GeneralWorkType g = mGeneralList.get(getAdapterPosition());
            mOnGeneralClickListener.onItemClick(v,g);
        }
    }
    public void setAdaptorList(List<GeneralWorkType>list){
        mGeneralList = list;
    }

    public List<GeneralWorkType> getGeneralWorkTypeList(){return mGeneralList; }
    public interface OnGeneralClickListener {
        void onItemClick(View view, GeneralWorkType item);
    }

}

