package com.eg.notes

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieList (
    @SerialName("results")
    val movies: List<Movie>
    )