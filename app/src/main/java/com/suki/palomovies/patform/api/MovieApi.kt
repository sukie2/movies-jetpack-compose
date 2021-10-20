package com.suki.palomovies.patform.api

import com.suki.palomovies.patform.api.response.MovieListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {
    companion object {
        const val END_POINT = "https://www.omdbapi.com/"
        const val API_KEY = "b9bd48a6" //FIXME: Secure API_KEY
    }

    @GET(".")
    suspend fun searchMovie(
        @Query("apikey") token: String = API_KEY,
        @Query("s") query: String,
        @Query("type") type: String,
        @Query("page") page: Int,
    ): MovieListResponse
}
