package com.example.charactergenerator5e.ui.generator

import androidx.databinding.ObservableBoolean

sealed class Entry {
    abstract val name: String
    abstract val enabled: ObservableBoolean
}

data class CategoryEntry(
    override val name: String,
    val startState: Boolean,
    val numberOfChildren: Int = 0
    ): Entry() {override val enabled = ObservableBoolean(startState)}

data class SubcategoryEntry(
    override val name: String,
    val startState: Boolean,
    val parentIndex: Int,
    val parentName: String
    ): Entry() {override val enabled = ObservableBoolean(startState)}