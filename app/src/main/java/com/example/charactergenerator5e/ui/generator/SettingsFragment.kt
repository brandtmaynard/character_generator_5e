package com.example.charactergenerator5e.ui.generator

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.charactergenerator5e.R
import com.example.charactergenerator5e.databinding.SettingsFragmentBinding

class SettingsFragment : Fragment() {

    private val viewModel: GeneratorViewModel by activityViewModels()
    private lateinit var binding: SettingsFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.settings_fragment, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.generatorViewModel = viewModel

        binding.settingsList.adapter = EntryAdapter(viewModel.settings.value!!)

        binding.backButton.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.selectAllButton.setOnClickListener {
            viewModel.settings.value?.forEach {
                it.enabled.set(true)
            }
        }

        binding.deselectAllButton.setOnClickListener {
            viewModel.settings.value?.forEach {
                it.enabled.set(false)
            }
        }

        binding.lifecycleOwner = viewLifecycleOwner
    }
}