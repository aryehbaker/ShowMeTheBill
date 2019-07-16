package com.example.showmethebill.ui.middleworktypeeditor;

import androidx.databinding.ObservableField;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.showmethebill.AppDatabase;




public class MiddleWorkTypeEditorViewModel extends ViewModel {
    private LiveData<com.example.showmethebill.middleWorkType> middleWorkTypeLiveData;
    public ObservableField<com.example.showmethebill.middleWorkType> oWorkType;
    public MiddleWorkTypeEditorViewModel(){super();}
    public MiddleWorkTypeEditorViewModel(AppDatabase database, int id) {
        middleWorkTypeLiveData = database.middleDao().getMatchingMiddleTypes(id);
    }


    public LiveData<com.example.showmethebill.middleWorkType> getMiddleWorkType() {
        return middleWorkTypeLiveData;
    }
    // TODO: Implement the ViewModel
}
