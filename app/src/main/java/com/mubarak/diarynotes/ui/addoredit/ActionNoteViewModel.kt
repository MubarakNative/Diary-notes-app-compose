package com.mubarak.diarynotes.ui.addoredit

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mubarak.diarynotes.R
import com.mubarak.diarynotes.data.sources.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class AddEditNoteUiState(
    val message: Int? = null,
    val navigateToHome: Boolean = false
)

@HiltViewModel
class ActionNoteViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val noteRepository: NoteRepository
) : ViewModel() {

    private val noteId: String? = savedStateHandle["noteId"]

    private val _uiState = MutableStateFlow(AddEditNoteUiState())
    val uiState: StateFlow<AddEditNoteUiState> = _uiState.asStateFlow()

    init {
        noteId?.let {
            showNoteItem(noteId)
        }
    }

    // No outside of this class can't change the value
    var title by mutableStateOf("")
        private set

    var description by mutableStateOf("")
        private set

    fun saveNote() {
        if (title.isBlank() && description.isBlank()) {
            _uiState.update {
                it.copy(message = R.string.empty_note_message, navigateToHome = false)
            }
            return
        }

        if (noteId == null) {
            createNote()
        } else {
            updateNote()
        }
    }

    private fun updateNote() = viewModelScope.launch {
        _uiState.update {
            it.copy(
                navigateToHome = true
            )
        }
        noteId?.let {
            noteRepository.upsertNote(
                noteId,
                title,
                description
            )
        }
    }

    private fun createNote() = viewModelScope.launch {
        _uiState.update {
            it.copy(navigateToHome = true)
        }
        noteRepository.insertNote(
            title = title,
            description = description,
        )
    }

    fun updateTitle(title: String) {
        this.title = title
    }

    fun updateDescription(description: String) {
        this.description = description
    }

    private fun showNoteItem(noteId: String) {
        viewModelScope.launch {
            noteRepository.getNoteById(
                noteId
            ).let {
                title = it.title
                description = it.description
            }
        }
    }
}