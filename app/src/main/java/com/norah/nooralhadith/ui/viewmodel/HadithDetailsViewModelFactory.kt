package com.norah.nooralhadith.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.norah.nooralhadith.domain.repository.HadithRepository

class HadithDetailsViewModelFactory(
    private val repo: HadithRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HadithDetailsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return HadithDetailsViewModel(repo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }
}