package com.mubarak.diarynotes.ui.note

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mubarak.diarynotes.data.sources.NoteRepository
import com.mubarak.diarynotes.data.sources.local.model.Note
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class HomeNoteUiState(
    val notes: List<Note> = emptyList(),
    // TODO: later we add more features like sorting, filtering etc.
)

@HiltViewModel
class HomeNoteViewModel @Inject constructor(
    private val noteRepository: NoteRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeNoteUiState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            noteRepository.getAllNote().collect {
                _uiState.value = HomeNoteUiState(it)
            }
        }
    }
}
