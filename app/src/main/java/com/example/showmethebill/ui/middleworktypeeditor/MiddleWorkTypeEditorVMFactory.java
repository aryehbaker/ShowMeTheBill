package com.example.showmethebill.ui.middleworktypeeditor;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.showmethebill.AppDatabase;

public class MiddleWorkTypeEditorVMFactory extends ViewModelProvider.NewInstanceFactory {
    private final AppDatabase mDb;
    private final int mTaskId;

    public MiddleWorkTypeEditorVMFactory(AppDatabase database, int taskId) {
        mDb = database;
        mTaskId = taskId;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        //noinspection unchecked
        return (T) new MiddleWorkTypeEditorViewModel(mDb, mTaskId);
    }

}
