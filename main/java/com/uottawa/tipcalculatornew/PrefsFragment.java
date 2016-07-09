package com.uottawa.tipcalculatornew;

import android.os.Bundle;
import android.preference.PreferenceFragment;

/**
 * Created by Bluer on 28/02/2016.
 */
public class PrefsFragment extends PreferenceFragment {

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
    }
}
