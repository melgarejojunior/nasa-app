package br.workshop.ifms.nasa.app.remote

import com.google.gson.annotations.SerializedName

data class Photo(
    @SerializedName("id")
    val id: Long,
    @SerializedName("img_src")
    val image: String
)