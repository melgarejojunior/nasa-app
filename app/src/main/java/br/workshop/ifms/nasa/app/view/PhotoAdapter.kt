package br.workshop.ifms.nasa.app.view

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.workshop.ifms.nasa.app.remote.Photo

class PhotoAdapter : RecyclerView.Adapter<PhotoViewHolder>() {

    var photos: List<Photo> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        return PhotoViewHolder.inflate(parent)
    }

    override fun getItemCount(): Int {
        return photos.size
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        holder.bind(photos[position])
    }

    fun setItems(photos: List<Photo>) {
        this.photos = photos
        notifyDataSetChanged()
    }
}