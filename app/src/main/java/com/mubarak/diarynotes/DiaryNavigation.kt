package com.mubarak.diarynotes

import kotlinx.serialization.Serializable

@Serializable
data class DiaryHomeDestination(
    val message: Int = 0
)

@Serializable
object ArchiveDestination

@Serializable
object DeletedDestination

@Serializable
object SearchDestination

@Serializable
data class AddEditDestination(
    val noteId: String?
)