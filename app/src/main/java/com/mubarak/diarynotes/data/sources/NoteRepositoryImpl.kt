package com.mubarak.diarynotes.data.sources


import com.mubarak.diarynotes.data.sources.local.NoteDatabase
import com.mubarak.diarynotes.data.sources.local.model.Note
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import java.util.UUID
import javax.inject.Inject

/** Concrete implementation of [NoteRepository]*/
class NoteRepositoryImpl @Inject constructor(
    private val notesDatabase: NoteDatabase,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
): NoteRepository {
    override suspend fun insertNote(title: String,description: String) = withContext(dispatcher){

        /**Generate a random UUID may take a while, so execute it on separate thread
         * without blocking the main thread*/

        val id = UUID.randomUUID().toString()
        val note = Note(
            id = id,
            title = title,
            description = description
        )
        notesDatabase.getDao.insertNote(
            note
        )
    }

    override suspend fun upsertNote(noteId: String,title: String,description: String) = withContext(dispatcher){
        val updateNote = getNoteById(noteId).copy(
            id = noteId,
            title = title,
            description = description
        )
        notesDatabase.getDao.upsertNote(updateNote)
    }

    override fun getAllNote(): Flow<List<Note>> {
        return notesDatabase.getDao.getAllNotes()
    }

    override fun getNoteBySearch(searchQuery: String): Flow<List<Note>> {
        return notesDatabase.getDao.getNoteBySearch(searchQuery)
    }

    override suspend fun deleteNote(noteId: String) = withContext(dispatcher){
        val note = getNoteById(noteId)
        notesDatabase.getDao.deleteNote(note)
    }

    override suspend fun deleteNoteById(noteId: String)  = withContext(dispatcher) {
        notesDatabase.getDao.deleteNoteById(noteId)
    }

    override suspend fun deleteAllNotes() {
        notesDatabase.getDao.deleteAllNotes()
    }

    override fun getNoteStreamById(noteId: String): Flow<Note> {
        return notesDatabase.getDao.getNoteStream(noteId)
    }

    override suspend fun getNoteById(noteId: String):Note{
        return withContext(dispatcher){
            notesDatabase.getDao.getNoteById(noteId)
        }
    }

}