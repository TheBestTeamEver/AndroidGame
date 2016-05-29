package com.thebestteamever.game.activities;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.preference.PreferenceFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.thebestteamever.game.R;
import com.thebestteamever.game.fragments.SettingsFragment;

public class PreferenceActivity extends AppCompatActivity {


    Boolean inSettings = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SettingsFragment.updateTheme(this);

        // Display the fragment as the main content.
        FragmentManager mFragmentManager = getFragmentManager();
        FragmentTransaction mFragmentTransaction = mFragmentManager
                .beginTransaction();
        PrefsFragment mPrefsFragment = new PrefsFragment();
        mFragmentTransaction.replace(android.R.id.content, mPrefsFragment);
        mFragmentTransaction.addToBackStack("settings");
        mFragmentTransaction.commit();

    }

    public static class PrefsFragment extends SettingsFragment {

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

        }
    }


    @Override
    public void onBackPressed()
    {
        if (inSettings)
        {
            backFromSettingsFragment();
            return;
        }
        super.onBackPressed();
    }


    private void backFromSettingsFragment()
    {
        inSettings = false;
        Intent refresh = new Intent(this, NawActivity.class);
        startActivity(refresh);
        this.finish();
    }
}
