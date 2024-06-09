package com.mubarak.diarynotes.data.sources.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("note")
data class Note(

    /** We pass this id back and forth to the screen via nav arguments
     * sometime we pass null, if we Int for id we can't use nullable
     * Checkout: https://developer.android.com/guide/navigation/use-graph/pass-data#supported_argument_types*/

    @PrimaryKey
    @ColumnInfo("note_id")
    var id: String,

    @ColumnInfo("Title")
    val title: String,

    @ColumnInfo("Description")
    val description: String
)
