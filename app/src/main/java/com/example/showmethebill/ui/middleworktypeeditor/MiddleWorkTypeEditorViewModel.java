package com.example.showmethebill.ui.middleworktypeeditor;

import androidx.databinding.ObservableField;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.showmethebill.AppDatabase;
import com.example.showmethebill.MiddleWorkType;


public class MiddleWorkTypeEditorViewModel extends ViewModel {
    private LiveData<MiddleWorkType> middleWorkTypeLiveData;
    public ObservableField<MiddleWorkType> oWorkType;
    public MiddleWorkTypeEditorViewModel(){super();}
    public MiddleWorkTypeEditorViewModel(AppDatabase database, int id) {
        middleWorkTypeLiveData = database.middleDao().getMatchingMiddleTypes(id);
    }


    public LiveData<MiddleWorkType> getMiddleWorkType() {
        return middleWorkTypeLiveData;
    }
    // TODO: Implement the ViewModel
}
