package com.eg.notes

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Movie(
    val title: String,
    @SerialName("vote_average")
    val rating: Float,
    @SerialName("release_date")
    val year: String,
    @SerialName("overview")
    val description: String
)