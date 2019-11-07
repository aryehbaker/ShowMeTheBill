package com.example.showmethebill;

import android.view.View;

import androidx.databinding.BindingAdapter;

public class MyHandlers {
   @BindingAdapter({"honClick"})
    public static void setOnClick(View view, View.OnClickListener clickListener) {
        view.setOnClickListener(clickListener);
        view.setClickable(true);
    }

}
