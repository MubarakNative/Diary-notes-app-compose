package com.mubarak.diarynotes.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mubarak.diarynotes.data.sources.NoteRepository
import com.mubarak.diarynotes.data.sources.local.model.Note
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class SearchNoteUiState(
    val notes: List<Note> = emptyList()
)

@HiltViewModel
class SearchNoteViewModel @Inject constructor(
    private val noteRepository: NoteRepository
):ViewModel() {

    private val _uiState = MutableStateFlow(SearchNoteUiState())
    val uiState: StateFlow<SearchNoteUiState> = _uiState.asStateFlow()

    private var searchJob: Job? = null

    fun searchNote(searchQuery: String){
        val filteredQuery = SearchQueryFilter.filterQuery(searchQuery)
        searchJob = viewModelScope.launch {
            delay(150)
            noteRepository.getNoteBySearch(filteredQuery).catch {
                emptyList<Note>()
            }.collect{ notes ->
                _uiState.update {
                    it.copy(
                        notes = notes
                    )
                }
            }
        }
    }
}