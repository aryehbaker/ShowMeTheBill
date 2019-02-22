package com.example.showmethebill;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.showmethebill.ui.generalworktypeeditor.GeneralWorkTypeEditorFragment;

public class GeneralWorkTypeEditor extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.general_work_type_editor_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, GeneralWorkTypeEditorFragment.newInstance())
                    .commitNow();
        }
    }
}
