package com.example.showmethebill;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;


import com.example.showmethebill.databinding.EndCardViewBinding;

import java.util.List;

public class EndAdapter extends RecyclerView.Adapter<EndAdapter.EndViewHolder> {
    public List<endWorkType> mEndList;
    private Context mContext;
    private EndAdapter.OnEndClickListener mOnEndClickListener;
    EndCardViewBinding binding;
    public  EndAdapter(Context context, EndAdapter.OnEndClickListener onEndClickListener){
        mContext = context;
        mOnEndClickListener = onEndClickListener;
    }
    @NonNull
    @Override
    public EndAdapter.EndViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(mContext),
                R.layout.end_card_view,parent,false);
        return new EndAdapter.EndViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull EndAdapter.EndViewHolder holder, int position) {
        endWorkType endWorkType = mEndList.get(position);
        holder.bind(endWorkType);
    }

    @Override
    public int getItemCount() {
        return mEndList == null?0:mEndList.size();
    }
    class EndViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        final EndCardViewBinding binding;
        public EndViewHolder(@NonNull EndCardViewBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            itemView.setOnClickListener(this);
        }
        public void bind(endWorkType item){
            binding.setEndType(item);
            binding.executePendingBindings();
        }

        @Override
        public void onClick(View v) {
            endWorkType m = mEndList.get(getAdapterPosition());
            mOnEndClickListener.onItemClick(v,m);

        }
    }
    public List <endWorkType> getEndWorkTypeList(){return  mEndList;}
    public void setAdaptorList(List<endWorkType> e){mEndList = e;}
    public interface OnEndClickListener {
        void onItemClick(View view, endWorkType item);
    }
}
