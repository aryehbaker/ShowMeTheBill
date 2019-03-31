package com.example.showmethebill;


import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import static android.content.ContentValues.TAG;


public class MainViewController {
    public Context mContext;

    public MainViewController(Context context) {
        this.mContext = context;
    }
    public void onGeneralClicked( int id){
        Log.d(TAG, "onGeneralClicked: "+ String.valueOf(id));

    }
}
