package com.mubarak.diarynotes.ui.addoredit

import androidx.lifecycle.ViewModel
import com.mubarak.diarynotes.data.sources.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

data class AddEditNoteUiState(
    val title:String = "",
    val description:String = "",
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

    }

}