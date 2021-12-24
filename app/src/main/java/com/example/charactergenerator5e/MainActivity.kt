package com.example.charactergenerator5e

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.charactergenerator5e.ui.generator.GeneratorViewModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val viewModel: GeneratorViewModel by viewModels()
//      pass sharedPreferences to ViewModel
        viewModel.sharedPref = getPreferences(Context.MODE_PRIVATE)
//      generate Entries with "enabled" read from sharedPreferences, default true
        viewModel.generateAllEntries()
//      populate Options once Settings are initialized
        viewModel.updateOptions()
//      generate a character immediately on start-up
        viewModel.generate()
    }
}