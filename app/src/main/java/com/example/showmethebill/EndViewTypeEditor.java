package com.example.showmethebill;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.showmethebill.ui.endviewtypeeditor.EndViewTypeEditorFragment;

public class EndViewTypeEditor extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.end_view_type_editor_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, EndViewTypeEditorFragment.newInstance())
                    .commitNow();
        }
    }
}
