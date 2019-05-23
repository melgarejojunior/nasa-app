package br.workshop.ifms.nasa.app.remote

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiEndpoints {

    @GET("rovers/{rover_name}/photos")
    fun getPhotos(@Path("rover_name") roverName: String, @Query("earth_date") earthDate: String, @Query("api_key") apiKey: String): Call<PhotoList>
}