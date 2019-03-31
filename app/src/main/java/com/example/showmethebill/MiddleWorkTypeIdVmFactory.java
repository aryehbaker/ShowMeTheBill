package com.example.showmethebill;

import com.example.showmethebill.ui.middleworktypeeditor.MiddleWorkTypeEditorViewModel;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class MiddleWorkTypeIdVmFactory extends ViewModelProvider.NewInstanceFactory {
    private final AppDatabase mDb;
    private final int mTaskId;

    public MiddleWorkTypeIdVmFactory(AppDatabase database, int taskId) {
        mDb = database;
        mTaskId = taskId;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        //noinspection unchecked
        return (T) new MiddleWorkTypeIdViewModel(mDb, mTaskId);
    }

}
