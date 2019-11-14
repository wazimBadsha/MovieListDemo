package com.example.movielistdemo.fragments

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat

import com.example.movielistdemo.R

class SettingsFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences, rootKey)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.setTheme(R.style.SettingsFragmentStyle)
    }


}
