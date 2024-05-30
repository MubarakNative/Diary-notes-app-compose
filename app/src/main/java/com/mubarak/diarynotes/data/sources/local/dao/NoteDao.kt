package com.mubarak.diarynotes.data.sources.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import com.mubarak.diarynotes.data.sources.local.model.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: Note)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllNote(note: List<Note>)

    @Upsert
    suspend fun upsertNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)

    @Query("SELECT * FROM note ORDER BY Title DESC")
    fun getAllNotes(): Flow<List<Note>>

    @Query("DELETE FROM note WHERE note_id = :noteId")
    suspend fun deleteNoteById(noteId: Long)

    @Query("DELETE FROM note")
    suspend fun deleteAllNotes()

    @Query("SELECT * FROM note WHERE note_id =:noteId ")
    fun getNoteStream(noteId: Long): Flow<Note>

    @Query("SELECT * FROM note WHERE note_id =:noteId ")
    suspend fun getNoteById(noteId: Long): Note

}