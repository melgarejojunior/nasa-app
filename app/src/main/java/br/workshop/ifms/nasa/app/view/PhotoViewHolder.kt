package br.workshop.ifms.nasa.app.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.workshop.ifms.nasa.app.databinding.ItemPhotoBinding
import br.workshop.ifms.nasa.app.remote.Photo
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class PhotoViewHolder private constructor(
    private val binding: ItemPhotoBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(photo: Photo) {
        Glide.with(binding.root.context)
            .setDefaultRequestOptions(
                RequestOptions()
                    .fitCenter()
                    .placeholder(android.R.drawable.ic_menu_report_image)
            )
            .load(photo.image)
            .into(binding.photo)
    }

    companion object {
        fun inflate(parent: ViewGroup): PhotoViewHolder {
            return PhotoViewHolder(ItemPhotoBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        }
    }
}