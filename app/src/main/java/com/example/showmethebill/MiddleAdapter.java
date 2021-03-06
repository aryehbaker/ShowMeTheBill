package com.example.showmethebill;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.showmethebill.databinding.MiddleCardViewBinding;


public class MiddleAdapter extends RecyclerView.Adapter<MiddleAdapter.MiddleViewHolder> {
    public List<MiddleWorkType> mMiddleList;
    private Context mContext;
    private OnMiddleClickListener mOnMiddleClickListener;
    MiddleCardViewBinding binding;
    public  MiddleAdapter(Context context, OnMiddleClickListener onMiddleClickListener){
        mContext = context;
        mOnMiddleClickListener = onMiddleClickListener;
    }
    @NonNull
    @Override
    public MiddleAdapter.MiddleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       binding = DataBindingUtil.inflate(LayoutInflater.from(mContext),
               R.layout.middle_card_view,parent,false);
       return new MiddleViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MiddleAdapter.MiddleViewHolder holder, int position) {
        MiddleWorkType middleWorkType = mMiddleList.get(position);
        holder.bind(middleWorkType);
    }

    @Override
    public int getItemCount() {
        return mMiddleList == null?0:mMiddleList.size();
    }
    class MiddleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        final MiddleCardViewBinding binding;
        public MiddleViewHolder(@NonNull MiddleCardViewBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            itemView.setOnClickListener(this);
        }
        public void bind(MiddleWorkType item){
            binding.setMiddleType(item);
            binding.executePendingBindings();
        }

        @Override
        public void onClick(View v) {
            MiddleWorkType m = mMiddleList.get(getAdapterPosition());
            mOnMiddleClickListener.onItemClick(v,m);

        }
    }
    public List <MiddleWorkType> getMiddleWorkTypeList(){return  mMiddleList;}
    public void setAdaptorList(List<MiddleWorkType> e){mMiddleList = e;}
    public interface OnMiddleClickListener {
        void onItemClick(View view, MiddleWorkType item);
    }
}
