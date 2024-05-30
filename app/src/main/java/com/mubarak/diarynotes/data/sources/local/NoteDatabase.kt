package com.mubarak.diarynotes.data.sources.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mubarak.diarynotes.data.sources.local.dao.NoteDao
import com.mubarak.diarynotes.data.sources.local.model.Note

@Database(
    entities = [Note::class],
    version = 1,
    exportSchema = false
)
abstract class NoteDatabase:RoomDatabase() {
    abstract val getDao: NoteDao

}