package com.example.showmethebill;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.showmethebill.ui.middleworktypeeditor.MiddleWorkTypeEditorFragment;

public class MiddleWorkTypeEditor extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.middle_work_type_editor_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, MiddleWorkTypeEditorFragment.newInstance())
                    .commitNow();
        }
    }
}
