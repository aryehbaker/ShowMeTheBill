package com.example.showmethebill;


import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.showmethebill.databinding.FragmentStarterBillBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


import java.util.List;

import static androidx.constraintlayout.widget.Constraints.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class BillStarterFragment extends Fragment {
    FragmentStarterBillBinding binding;
    Context context;
    Application application;
    private AppDatabase mDb;
    RecyclerView generalRecyclerView, middleRecyclerView, endRecyclerView;
    GeneralAdapter generalAdapter;
    MiddleAdapter middleAdapter;
    EndAdapter endAdapter;
    BillStarterViewModel billStarterViewModel;
    GeneralWorkTypeViewModel generalWorkTypeViewModel;
    MiddleWorkTypeIdViewModel middleWorkTypeIdViewModel;
    EndWorkTypeIdViewModel endWorkTypeIdViewModel;
    ViewModelProvider viewModelProvider;

    FloatingActionButton fab;


    public BillStarterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_starter_bill, container, false);
        binding.setLifecycleOwner(this);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        context = getContext();



        generalRecyclerView = binding.reGeneral;
        generalAdapter = new GeneralAdapter(context, (view, item) -> {
            Log.d(TAG, "onItemClick: " + item.WorkType);
            onGeneralItemClick(view, item);
        });
        middleRecyclerView = binding.reMiddle;
        middleAdapter = new MiddleAdapter(context, (view, item) -> {
            Log.d(TAG, "onItemClick: " + item.WorkType);
            onMiddleItemClicked(view, item);
        });
        endRecyclerView = binding.reEnd;
        endAdapter = new EndAdapter(context, (view, item) -> {
            Log.d(TAG, "onEndClick" + item.workType);
            onEndItemClicked(view, item);
        });
        generalRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        middleRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        endRecyclerView.setLayoutManager(new LinearLayoutManager(context));

        billStarterViewModel = ViewModelProviders.of(getActivity()).get(BillStarterViewModel.class);
        binding.setFragVM(billStarterViewModel);

        generalWorkTypeViewModel =
                ViewModelProviders.of(getActivity()).get(GeneralWorkTypeViewModel.class);
        binding.setGeneralVM(generalWorkTypeViewModel);
        generalWorkTypeViewModel.getGeneralWorkType()
                .observe(getViewLifecycleOwner(), GeneralWorkTypes -> {
                    if (GeneralWorkTypes != null) {
                        generalAdapter.setAdaptorList(GeneralWorkTypes);
                        generalRecyclerView.setAdapter(generalAdapter);
                        generalAdapter.notifyDataSetChanged();
                    }

                });
        middleWorkTypeIdViewModel = ViewModelProviders.of(getActivity())
                .get(MiddleWorkTypeIdViewModel.class);
        binding.setMiddleVM(middleWorkTypeIdViewModel);
        binding.getMiddleVM().getMiddleWorkTypeOfGeneralId()
                .observe(getViewLifecycleOwner(), middleWorkTypes -> {
                    if (middleWorkTypes != null ) {
                        middleAdapter.setAdaptorList(middleWorkTypes);
                        middleRecyclerView.setAdapter(middleAdapter);
                        middleAdapter.notifyDataSetChanged();
                    }

                });

        endWorkTypeIdViewModel = ViewModelProviders.of(getActivity())
                .get(EndWorkTypeIdViewModel.class);
        binding.setEndVM(endWorkTypeIdViewModel);
        endWorkTypeIdViewModel.getEndWorkTypeOfMiddleId()
                .observe(getViewLifecycleOwner(), endWorkTypes -> {
                    if (endWorkTypes != null ) {
                        endAdapter.setAdaptorList(endWorkTypes);
                        endRecyclerView.setAdapter(endAdapter);
                        endAdapter.notifyDataSetChanged();
                    }
                });
        binding.executePendingBindings();


        // Toolbar toolbar = findViewById(R.id.toolbar);
        // setSupportActionBar(toolbar);
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
                        List<GeneralWorkType> tasks = generalAdapter.getGeneralWorkTypeList();
                        mDb.generalDao().deleteGeneralType(tasks.get(position));
                    }
                });
            }
        })*/
        SwipeHelper swipeHelper = new SwipeHelper(context, generalRecyclerView) {
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
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
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
                        List<MiddleWorkType> tasks = middleAdapter.getMiddleWorkTypeList();
                        mDb.middleDao().deleteMiddleType(tasks.get(position));
                    }
                });
            }
        }).attachToRecyclerView(middleRecyclerView);
        fab = binding.fab;
        fab.setOnClickListener(view -> {
            LiveData<BillStarterViewModel.ActiveRecycler> activeRecycler =
            binding.getFragVM().getGetActiveRecyclerLiveData();
            if (activeRecycler.getValue() == null) return;
            switch (activeRecycler.getValue()) {
                case GENERAL:

                    Intent intent = new Intent(context,
                            GeneralWorkTypeEditor.class);
                                        startActivity(intent);
                    break;
                case MIDDLE:
                    Intent intent1 = new Intent(context,
                            MiddleWorkTypeEditor.class);
                    intent1.putExtra("generalId",binding.getGeneralVM().getGeneralOneLiveData().getValue().id);
                    startActivity(intent1);
                    break;
                case END:
                    Intent intent2 = new Intent(context,
                            EndWorkTypeEditor.class);
                    intent2.putExtra("middleId",binding.getMiddleVM().getMiddleOneLiveData().getValue().id);
                    startActivity(intent2);
            }


        });

    }

    public void onGeneralItemClick(View view, GeneralWorkType item) {
        if (view.getId() == R.id.general_add) {
            Log.i("general", "you pressed add");
        } else {
            generalWorkTypeViewModel.setGeneralOneLiveData(item);
            middleWorkTypeIdViewModel.setGeneralId(item.id);
            billStarterViewModel.setRecyclerToMiddle();
 //           binding.billStarterMainLayout.invalidate();
 //           binding.billStarterMainLayout.requestLayout();
        }


    }

    public void onMiddleItemClicked(View view, MiddleWorkType item) {
        middleWorkTypeIdViewModel.setMiddleOneLiveData(item);
        endWorkTypeIdViewModel.setMiddleId(item.id);
        billStarterViewModel.setRecyclerToEnd();
    }

    public void onEndItemClicked(View view, EndWorkType item) {

    }


}

