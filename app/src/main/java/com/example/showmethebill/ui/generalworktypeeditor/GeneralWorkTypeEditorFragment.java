package com.example.showmethebill.ui.generalworktypeeditor;

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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.showmethebill.AppDatabase;
import com.example.showmethebill.AppExecutors;
import com.example.showmethebill.R;
import com.example.showmethebill.databinding.GeneralWorkTypeEditorFragmentBinding;
import com.example.showmethebill.generalWorkType;

import java.util.Date;

public class GeneralWorkTypeEditorFragment extends Fragment {

    private GeneralWorkTypeEditorViewModel mViewModel;
    // Extra for the task ID to be received in the intent
    public static final String EXTRA_TASK_ID = "extraTaskId";
    // Extra for the task ID to be received after rotation
    public static final String INSTANCE_TASK_ID = "instanceTaskId";
    // Constants for priority
    public static final int PRIORITY_HIGH = 1;
    public static final int PRIORITY_MEDIUM = 2;
    public static final int PRIORITY_LOW = 3;
    // Constant for default task id to be used when not in update mode
    private static final int DEFAULT_TASK_ID = -1;
    // Constant for logging
    private static final String TAG = GeneralWorkTypeEditorFragment.class.getSimpleName();
    // Fields for views
    EditText mEditText;
    TextView mId;
    Button mButton;
    GeneralWorkTypeEditorFragmentBinding binding;

    private int mTaskId = DEFAULT_TASK_ID;

    // Member variable for the Database
    private AppDatabase mDb;


    public static GeneralWorkTypeEditorFragment newInstance() {
        return new GeneralWorkTypeEditorFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding =
               DataBindingUtil.inflate(
                inflater,R.layout.general_work_type_editor_fragment,container,false);
        mViewModel = ViewModelProviders.of(this).get(GeneralWorkTypeEditorViewModel.class);

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
            mButton.setText(R.string.update_button);
            if (mTaskId == DEFAULT_TASK_ID) {
                // populate the UI
                mTaskId = intent.getIntExtra(EXTRA_TASK_ID, DEFAULT_TASK_ID);

                 GeneralWorkTypeEditorVMFactory factory = new GeneralWorkTypeEditorVMFactory(
                         getActivity().getApplication(), mTaskId);
                 final GeneralWorkTypeEditorViewModel viewModel
                        = ViewModelProviders.of(this, factory).get(GeneralWorkTypeEditorViewModel.class);

                viewModel.getGeneralWorkType().observe(this, new Observer<generalWorkType>() {
                    @Override
                    public void onChanged(@Nullable generalWorkType taskEntry) {
                        viewModel.getGeneralWorkType().removeObserver(this);
                        populateUI(taskEntry);
                    }
                });
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt(INSTANCE_TASK_ID, mTaskId);
        super.onSaveInstanceState(outState);
    }
    /**
     * initViews is called from onCreate to init the member variable views
     */
    private void initViews() {
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSaveButtonClicked();
            }
        });
    }

    /**
     * populateUI would be called to populate the UI when in update mode
     *
     * @param task the taskEntry to populate the UI
     */
    private void populateUI(generalWorkType task) {
        if (task == null) {
            return;
        }
        binding.setVM(task);
        /*mEditText.setText(task.WorkType);
        mId.setText(task.id);*/


    }

    /**
     * onSaveButtonClicked is called when the "save" button is clicked.
     * It retrieves user input and inserts that new task data into the underlying database.
     */
    public void onSaveButtonClicked() {
        String description = mEditText.getText().toString();
        String id = mId.getText().toString();
        int Iid = Integer.parseInt(id);
        Date date = new Date();

        final generalWorkType task = new generalWorkType(description, date);
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                if (mTaskId == DEFAULT_TASK_ID) {
                    // insert new task
                    mDb.generalDao().insertGeneralType(task);
                } else {
                    //update task
                    task.setId(mTaskId);
                    mDb.generalDao().updateGeneralType(task);
                }
                getActivity().finish();
            }
        });
    }





}


