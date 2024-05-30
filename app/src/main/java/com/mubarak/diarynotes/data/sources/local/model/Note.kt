package com.mubarak.diarynotes.data.sources.local.model

iandroidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("note")
data class Note(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("note_id")
    var id: Long,

    @ColumnInfo("Title")
    val title: String,

    @ColumnInfo("Description")
    val description: String
)
