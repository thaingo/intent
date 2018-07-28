package com.example.thaingo.criminalintent;

import android.support.v4.app.Fragment;

public class CrimeListFragment extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new CrimeFragment();
    }
}
