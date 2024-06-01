package com.mubarak.diarynotes.ui.addoredit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mubarak.diarynotes.data.sources.NoteRepository
import com.mubarak.diarynotes.data.sources.local.model.Note
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ActionNoteViewModel @Inject constructor(
    private val noteRepository: NoteRepository
):ViewModel() {

    private fun createNote(note: Note) = viewModelScope.launch {
        noteRepository.insertNote(note)
    }
}