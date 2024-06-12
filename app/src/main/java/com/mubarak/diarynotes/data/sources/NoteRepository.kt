package com.mubarak.diarynotes.data.sources

import com.mubarak.diarynotes.data.sources.local.model.Note
import kotlinx.coroutines.flow.Flow

interface NoteRepository {
    suspend fun insertNote(title: String,description:String)
    suspend fun upsertNote(noteId: String,title: String,description:String)
     fun getAllNote(): Flow<List<Note>>
    suspend fun deleteNote(noteId: String)
    suspend fun deleteNoteById(noteId:String)
    suspend fun deleteAllNotes()
    fun getNoteStreamById(noteId:String):Flow<Note>
    suspend fun getNoteById(noteId:String):Note

}