package com.example.showmethebill.ui.middleworktypeeditor;

import androidx.databinding.ObservableField;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.showmethebill.AppDatabase;
import com.example.showmethebill.middleWorkType;



public class MiddleWorkTypeEditorViewModel extends ViewModel {
    private LiveData<middleWorkType> middleWorkTypeLiveData;
    public ObservableField<middleWorkType> oWorkType;
    public MiddleWorkTypeEditorViewModel(){super();}
    public MiddleWorkTypeEditorViewModel(AppDatabase database, int id) {
        middleWorkTypeLiveData = database.middleDao().getMatchingMiddleTypes(id);
    }


    public LiveData<middleWorkType> getMiddleWorkType() {
        return middleWorkTypeLiveData;
    }
    // TODO: Implement the ViewModel
}
