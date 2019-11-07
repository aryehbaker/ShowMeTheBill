package com.example.showmethebill.ui.middleworktypeeditor;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.showmethebill.AppDatabase;
import com.example.showmethebill.AppExecutors;
import com.example.showmethebill.MiddleWorkType;
import com.example.showmethebill.R;
import com.example.showmethebill.databinding.MiddleWorkTypeEditorFragmentBinding;

import java.util.Date;



public class MiddleWorkTypeEditorFragment extends Fragment {

    private MiddleWorkTypeEditorViewModel mViewModel;
    public static final String EXTRA_TASK_ID = "extraTaskId";
    // Extra for the task ID to be received after rotation
    public static final String INSTANCE_TASK_ID = "instanceTaskId";
    // Constants for priority
    // Constant for default task id to be used when not in update mode
    private static final int DEFAULT_TASK_ID = -1;
    // Constant for logging
    private static final String TAG = MiddleWorkTypeEditorFragment.class.getSimpleName();


    public static MiddleWorkTypeEditorFragment newInstance() {
        return new MiddleWorkTypeEditorFragment();
    }
    MiddleWorkTypeEditorFragmentBinding binding;

    private int mTaskId = DEFAULT_TASK_ID;

    // Member variable for the Database
    private AppDatabase mDb;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding =
                DataBindingUtil.inflate(
                        inflater,R.layout.middle_work_type_editor_fragment,container,false);

        mViewModel = ViewModelProviders.of(this).get(MiddleWorkTypeEditorViewModel.class);
        binding.setFragment(this);
       return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // TODO: Use the ViewModel
        mDb = AppDatabase.getInstance(getActivity().getApplicationContext());

        if (savedInstanceState != null && savedInstanceState.containsKey(INSTANCE_TASK_ID)) {
            mTaskId = savedInstanceState.getInt(INSTANCE_TASK_ID, DEFAULT_TASK_ID);
        }

        Intent intent = getActivity().getIntent();
        if (intent != null && intent.hasExtra(EXTRA_TASK_ID)) {
            binding.middleAdd.setText(R.string.update_button);
            if (mTaskId == DEFAULT_TASK_ID) {
                // populate the UI
                mTaskId = intent.getIntExtra(EXTRA_TASK_ID, DEFAULT_TASK_ID);

                MiddleWorkTypeEditorVMFactory factory = new MiddleWorkTypeEditorVMFactory(
                        mDb, mTaskId);
                final MiddleWorkTypeEditorViewModel viewModel
                        = ViewModelProviders.of(this, factory).get(MiddleWorkTypeEditorViewModel.class);

                viewModel.getMiddleWorkType().observe(this, new Observer<MiddleWorkType>() {
                    @Override
                    public void onChanged(@Nullable MiddleWorkType taskEntry) {
                        viewModel.getMiddleWorkType().removeObserver(this);
                        populateUI(taskEntry);
                    }
                });
            }
        }
//        initViews();
    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt(INSTANCE_TASK_ID, mTaskId);
        super.onSaveInstanceState(outState);
    }


    /**
     * populateUI would be called to populate the UI when in update mode
     *
     * @param task the taskEntry to populate the UI
     */
    private void populateUI(MiddleWorkType task) {
        if (task == null) {
            return;
        }
        binding.setMiddle(task);
        /*mEditText.setText(task.WorkType);
        mId.setText(task.id);*/


    }
    public void onSaveButtonClicked(View view) {
        String description = binding.middleworktype.getText().toString();
        String ID = binding.middleid.getText().toString();
        final int id = Integer.parseInt(ID);
        String Cost = binding.middleWorktypeCost.getText().toString();
        float cost = Float.valueOf(Cost);
        int  generalId = Integer.valueOf(binding.middleidgeneral.getText().toString());
        Date date = new Date();

        final MiddleWorkType task = new MiddleWorkType(id, description,cost,generalId);
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                if (mTaskId == DEFAULT_TASK_ID) {
                    // insert new task
                    mDb.middleDao().insertMiddleType(task);
                } else {
                    //update task
                    task.setId(id);
                    mDb.middleDao().updateMiddleType(task);
                }
                getActivity().finish();
            }
        });
    }

}


