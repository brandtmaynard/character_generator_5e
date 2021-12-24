package com.example.charactergenerator5e.ui.generator

import android.content.Context
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.charactergenerator5e.R
import com.example.charactergenerator5e.databinding.GeneratorFragmentBinding

class GeneratorFragment : Fragment() {

    private val viewModel: GeneratorViewModel by activityViewModels()
    private lateinit var binding: GeneratorFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.generator_fragment, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.generatorViewModel = viewModel

        viewModel.updateOptions()

        binding.generateButton.setOnClickListener { viewModel.generate() }

        binding.settingsButton.setOnClickListener { findNavController().navigate(R.id.action_generatorFragment_to_optionsFragment) }

        binding.lifecycleOwner = viewLifecycleOwner
    }
}