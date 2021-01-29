package com.eg.notes

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import okhttp3.*
import java.io.IOException

class DataBaseAPI {
    private fun getRequest(path: String, params: Map<String, String>): Request {
        /**
         * Method to create GET request. Method doesn't send request, only returns it.
         *
         * path: path to specific location without base url
         * params: Map containing query parameters as keys and their values as values
         *
         */

        // Build url with given path and parameters
        val httpBuilder = HttpUrl.Builder()
            .scheme("https")
            .host("api.themoviedb.org")
            .addPathSegment("3")
            .addPathSegments(path)

        // add all query parameters to url
        for ((param, value) in params) {
            httpBuilder.addQueryParameter(param, value)
        }

        // create request and return it
        return Request.Builder().url(httpBuilder.build()).build()
    }

    fun getRandomMovie(params: Map<String, String>) {
        /**
         * Method to get a random movie with given parameters.
         *
         * params: desirable properties of the movies(i.e. rating, country, year etc.)
         */

        val path = "discover/movie"
        val client = OkHttpClient()
        val request = getRequest(path, params)

        // make call to DB with given request
        client.newCall(request).enqueue(object : Callback {

            // handle failure
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            // handle response
            override fun onResponse(call: Call, response: Response) {
                // using "use" to automatically close response after using it
                response.use {
                    if (!response.isSuccessful) throw IOException("Unexpected code $response")

                    val content = response.body?.string() ?: "no body"
                    val json = Json {ignoreUnknownKeys = true}
                    val movie = json.decodeFromString<MovieList>(content).movies[10]
                    println(movie)
                }
            }
        })
    }
}