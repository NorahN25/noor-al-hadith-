package com.norah.nooralhadith.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.norah.nooralhadith.data.local.getHadithMetaById
import com.norah.nooralhadith.domain.repository.HadithRepository
import com.norah.nooralhadith.ui.state.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HadithDetailsViewModel(
    private val repo: HadithRepository
) : ViewModel() {

    private val _state = MutableStateFlow<UiState<String>>(UiState.Loading)
    val state: StateFlow<UiState<String>> = _state.asStateFlow()

    fun load(id: Int) {
        val meta = getHadithMetaById(id)
        if (meta == null) {
            _state.value = UiState.Error("لم يتم العثور على الحديث محليًا")
            return
        }

        viewModelScope.launch {
            _state.value = UiState.Loading

            runCatching {
                repo.getHadithMatn(
                    collection = meta.collection,
                    number = meta.number
                )
            }.onSuccess { matn ->
                _state.value = UiState.Success(data = matn)
            }.onFailure { e ->
                e.printStackTrace()
                _state.value = UiState.Error(message = e.message ?: "تعذر جلب نص الحديث")
            }
        }
    }
}