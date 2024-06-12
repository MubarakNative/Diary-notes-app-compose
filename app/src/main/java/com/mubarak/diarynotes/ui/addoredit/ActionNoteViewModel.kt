package com.mubarak.diarynotes.ui.addoredit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mubarak.diarynotes.data.sources.NoteRepository
import com.mubarak.diarynotes.data.sources.local.model.Note
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

data class AddEditNoteUiState(
    val title:String = "",
    val description:String = ""
)

@HiltViewModel
class ActionNoteViewModel @Inject constructor(
    private val noteRepository: NoteRepository
):ViewModel() {

    private val _uiState = MutableStateFlow(AddEditNoteUiState())
    val uiState:StateFlow<AddEditNoteUiState> = _uiState.asStateFlow()

    private var noteId:String? = null

    fun setNote(noteId:String?){
        this.noteId = noteId
    }
    fun saveNote(){
        if (uiState.value.title.isBlank() && uiState.value.description.isBlank()){
            _uiState.value = _uiState.value.copy(
               // TODO: notify the user he miss the title and description field
            )
        }

        if (noteId == null){
            createNote()
        }
        return
    }

    private fun createNote()= viewModelScope.launch {
        noteRepository.insertNote(
            Note(
                title = uiState.value.title,
                description = uiState.value.description,
                id = "thws"
            )

        )
    }

    fun updateTitle(title:String){
        _uiState.value = _uiState.value.copy(
            title = title
        )
    }

    fun updateDescription(description:String){
        _uiState.value = _uiState.value.copy(
            description = description
        )
    }

}