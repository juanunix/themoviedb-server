package com.juansanz.backend.models

import kotlinx.serialization.Serializable

@Serializable
data class Note(val id: Long, val title: String, val description: String, val type: com.juansanz.backend.models.Note.Type) {
    enum class Type { TEXT, AUDIO }
    companion object
}