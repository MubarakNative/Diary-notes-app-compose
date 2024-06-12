package com.mubarak.diarynotes.data.sources


import com.mubarak.diarynotes.data.sources.local.NoteDatabase
import com.mubarak.diarynotes.data.sources.local.model.Note
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

/** Concrete implementation of [NoteRepository]*/
class NoteRepositoryImpl @Inject constructor(
    private val notesDatabase: NoteDatabase,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
): NoteRepository {
    override suspend fun insertNote(note: Note) = withContext(dispatcher){
        notesDatabase.getDao.insertNote(note)
    }

    override suspend fun upsertNote(note: Note) = withContext(dispatcher){
        notesDatabase.getDao.upsertNote(note)
    }

    override fun getAllNote(): Flow<List<Note>> {
        return notesDatabase.getDao.getAllNotes()
    }

    override suspend fun deleteNote(note: Note) = withContext(dispatcher){
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