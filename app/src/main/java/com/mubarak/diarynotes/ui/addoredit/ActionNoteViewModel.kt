package com.mubarak.diarynotes.ui.addoredit

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mubarak.diarynotes.data.sources.NoteRepository
import com.mubarak.diarynotes.utils.NoteStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class AddEditNoteUiState(
    val noteType: NoteStatus = NoteStatus.ACTIVE
    // TODO: add more fields like send a error msg to the user if field is empty, and more
)

@HiltViewModel
class ActionNoteViewModel @Inject constructor(
    private val noteRepository: NoteRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(AddEditNoteUiState())
    val uiState: StateFlow<AddEditNoteUiState> = _uiState.asStateFlow()

    // No outside of this class can't change the value
    var title by mutableStateOf("")
        private set

    var description by mutableStateOf("")
        private set

    private var noteId: String? = null

    fun setNote(noteId: String?) {
        this.noteId = noteId
    }

    fun saveNote() {
        if (title.isBlank() && description.isBlank()) {
            _uiState.value = _uiState.value.copy(
                // TODO: notify the user he miss the title and description field
            )
        }

        if (noteId == null) {
            createNote()
        }
        return
    }

    private fun createNote() = viewModelScope.launch {
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

}