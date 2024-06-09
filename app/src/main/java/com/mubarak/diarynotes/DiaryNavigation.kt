package com.mubarak.diarynotes

import kotlinx.serialization.Serializable

@Serializable
object DiaryHomeDestination
@Serializable
object ArchiveDestination
@Serializable
object DeletedDestination
@Serializable
object SearchDestination
@Serializable
data class AddEditDestination(
    val noteId: Int
)