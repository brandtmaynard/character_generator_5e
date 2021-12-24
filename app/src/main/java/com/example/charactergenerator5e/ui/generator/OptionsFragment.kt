package com.example.charactergenerator5e.ui.generator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.charactergenerator5e.R
import com.example.charactergenerator5e.databinding.OptionsFragmentBinding

class OptionsFragment : Fragment() {

    private val viewModel: GeneratorViewModel by activityViewModels()
    private lateinit var binding: OptionsFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.options_fragment, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.generatorViewModel = viewModel

        binding.racesOption.setOnClickListener { viewModel.settings = viewModel.raceSettings; findNavController().navigate(R.id.action_optionsFragment_to_settingsFragment) }

        binding.classesOption.setOnClickListener { viewModel.settings = viewModel.classSettings; findNavController().navigate(R.id.action_optionsFragment_to_settingsFragment) }

        binding.backgroundsOption.setOnClickListener { viewModel.settings = viewModel.backgroundSettings; findNavController().navigate(R.id.action_optionsFragment_to_settingsFragment) }

        binding.alignmentsOption.setOnClickListener { viewModel.settings = viewModel.alignmentSettings; findNavController().navigate(R.id.action_optionsFragment_to_alignmentsFragment) }

        binding.backButton.setOnClickListener { findNavController().navigateUp() }

        binding.lifecycleOwner = viewLifecycleOwner
    }
}