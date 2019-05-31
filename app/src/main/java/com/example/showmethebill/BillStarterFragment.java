package com.example.showmethebill;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.showmethebill.databinding.FragmentStarterBillBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import static androidx.constraintlayout.widget.Constraints.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class BillStarterFragment extends Fragment {

    FragmentStarterBillBinding binding;
    private AppDatabase mDb;
    RecyclerView generalRecyclerView,middleRecyclerView,endRecyclerView;
    GeneralAdapter generalAdapter;
    MiddleAdapter middleAdapter;

    Observer GeneralObserver;
    Observer MiddleObserver;
    FloatingActionButton fab;

    private enum FabStyle{GENERAL,MIDDLE,END};



    public BillStarterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
    binding = DataBindingUtil.inflate(
            inflater,R.layout.fragment_starter_bill,container,false);

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        fab = binding.fab;
        setFab(FabStyle.GENERAL);

        generalRecyclerView = binding.reGeneral;
        generalAdapter = new GeneralAdapter(getContext(), new GeneralAdapter.OnGeneralClickListener() {
            @Override
            public void onItemClick(View view, generalWorkType item) {
                Log.d(TAG, "onItemClick: "+ item.WorkType);
                Toast.makeText(getActivity().getApplicationContext(),"Hello Javatpoint", Toast.LENGTH_LONG).show();
                onGeneralItemClick(view,item);
            }
        });
        middleRecyclerView = binding.reMiddle;
        // Toolbar toolbar = findViewById(R.id.toolbar);
        // setSupportActionBar(toolbar);
        generalRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        middleRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        /*new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                AppExecutors.getInstance().diskIO().execute(new Runnable() {
                    @Override
                    public void run() {
                        int position = viewHolder.getAdapterPosition();
                        List<generalWorkType> tasks = generalAdapter.getGeneralWorkTypeList();
                        mDb.generalDao().deleteGeneralType(tasks.get(position));
                    }
                });
            }
        })*/SwipeHelper swipeHelper = new SwipeHelper(getContext(), generalRecyclerView) {
            @Override
            public void instantiateUnderlayButton(RecyclerView.ViewHolder viewHolder, List<UnderlayButton> underlayButtons) {
                underlayButtons.add(new SwipeHelper.UnderlayButton(
                        "Delete",
                        0,
                        Color.parseColor("#FF3C30"),
                        new SwipeHelper.UnderlayButtonClickListener() {
                            @Override
                            public void onClick(int pos) {
                                // TODO: onDelete
                            }
                        }
                ));

                underlayButtons.add(new SwipeHelper.UnderlayButton(
                        "Transfer",
                        0,
                        Color.parseColor("#FF9502"),
                        new SwipeHelper.UnderlayButtonClickListener() {
                            @Override
                            public void onClick(int pos) {
                                // TODO: OnTransfer
                            }
                        }
                ));
                underlayButtons.add(new SwipeHelper.UnderlayButton(
                        "Unshare",
                        0,
                        Color.parseColor("#C7C7CB"),
                        new SwipeHelper.UnderlayButtonClickListener() {
                            @Override
                            public void onClick(int pos) {
                                // TODO: OnUnshare
                            }
                        }
                ));
            }
        };//.attachToRecyclerView(generalRecyclerView);
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                AppExecutors.getInstance().diskIO().execute(new Runnable() {
                    @Override
                    public void run() {
                        int position = viewHolder.getAdapterPosition();
                        List<middleWorkType> tasks = middleAdapter.getMiddleWorkTypeList();
                        mDb.middleDao().deleteMiddleType(tasks.get(position));
                    }
                });
            }
        }).attachToRecyclerView(middleRecyclerView);
        mDb = AppDatabase.getInstance(getContext().getApplicationContext());
        final GeneralWorkTypeViewModel generalWorkTypeViewModel =
                ViewModelProviders.of(this).get(GeneralWorkTypeViewModel.class);
        GeneralObserver = (Observer<List<generalWorkType>>) generalWorkTypes -> {
            if(generalWorkTypes != null && !generalWorkTypes.isEmpty()){
                generalAdapter.setAdaptorList(generalWorkTypes);
                generalRecyclerView.setAdapter(generalAdapter);
            }
        };
        generalWorkTypeViewModel.getGeneralWorkType().observe(this, GeneralObserver);
    }
    public void onGeneralItemClick(View view, generalWorkType item) {
        Log.d(TAG, "onItemClick: "+ item.WorkType);
        Toast.makeText(getContext().getApplicationContext(),"you clicked "+item.WorkType, Toast.LENGTH_LONG).show();
        setFab(FabStyle.MIDDLE);
        MiddleWorkTypeIdVmFactory factory = new MiddleWorkTypeIdVmFactory(mDb,item.id);
        final MiddleWorkTypeIdViewModel viewModel =
                ViewModelProviders.of(this,factory).get(MiddleWorkTypeIdViewModel.class);
        MiddleObserver = (Observer<List<middleWorkType>>) middleWorkTypes -> {
            if(middleWorkTypes != null && !middleWorkTypes.isEmpty()){
                middleAdapter.setAdaptorList(middleWorkTypes);
                middleRecyclerView.setAdapter(generalAdapter);
            }
            else{
                Intent intent1 = new Intent(
                        getContext().getApplicationContext(),
                        MiddleWorkTypeEditor.class);
                startActivity(intent1);
            }
        };
        viewModel.getMiddleWorkTypeOfGeneralId().observe(this, MiddleObserver);

    }
    private void setFab(FabStyle FAB){

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String classType;
                switch(FAB){
                    case GENERAL:Intent intent = new Intent(getContext(),
                            GeneralWorkTypeEditor.class);
                        startActivity(intent);
                        break;
                    case MIDDLE:Intent intent1 = new Intent(getContext(),
                            MiddleWorkTypeEditor.class);
                        startActivity(intent1);
                        break;
                    default:Intent intent2 = new Intent(getContext(),
                            GeneralWorkTypeEditor.class);
                        startActivity(intent2);
                }


            }
        });
    }
}

