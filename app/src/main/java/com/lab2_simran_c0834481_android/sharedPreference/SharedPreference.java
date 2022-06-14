package com.lab2_simran_c0834481_android.sharedPreference;

import android.content.Context;
import android.content.SharedPreferences;

import com.lab2_simran_c0834481_android.R;

public class SharedPreference {
    private static final String IS_UPDATED = "IS_UPDATED";
    private final SharedPreferences mSharedPreferences;
    public SharedPreference(Context context) {
        mSharedPreferences = context.getSharedPreferences(context.getString( R.string.shared_preference_file),
                Context.MODE_PRIVATE);
    }


    public void setIsUpdated(boolean status) {
        mSharedPreferences.edit()
                .putBoolean(IS_UPDATED, status)
                .apply();
    }



    public boolean getIsUpdated() {
        return mSharedPreferences.getBoolean(IS_UPDATED, false);
    }


}
