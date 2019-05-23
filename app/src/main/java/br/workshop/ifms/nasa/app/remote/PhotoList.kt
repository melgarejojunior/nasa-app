package br.workshop.ifms.nasa.app.remote

import com.google.gson.annotations.SerializedName

data class PhotoList(
    @SerializedName("photos")
    val photos: List<Photo>
)