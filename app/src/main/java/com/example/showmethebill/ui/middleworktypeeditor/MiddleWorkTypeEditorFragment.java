package com.example.showmethebill.ui.middleworktypeeditor;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.showmethebill.R;

public class MiddleWorkTypeEditorFragment extends Fragment {

    private MiddleWorkTypeEditorViewModel mViewModel;

    public static MiddleWorkTypeEditorFragment newInstance() {
        return new MiddleWorkTypeEditorFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.middle_work_type_editor_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(MiddleWorkTypeEditorViewModel.class);
        // TODO: Use the ViewModel
    }

}
