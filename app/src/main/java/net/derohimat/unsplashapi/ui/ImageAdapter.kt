package net.derohimat.unsplashapi.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_image.view.*
import net.derohimat.unsplashapi.R
import net.derohimat.unsplashapi.model.ImageResponse
import net.derohimat.unsplashapi.utils.getProgressDrawable
import net.derohimat.unsplashapi.utils.loadImage

class ImageAdapter(var imageResponses: ArrayList<ImageResponse>) :
    RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {

    fun updateData(newImageResponses: List<ImageResponse>) {
        imageResponses.addAll(newImageResponses)
        notifyDataSetChanged()
    }

    fun addData(newImageResponses: List<ImageResponse>) {
        imageResponses.clear()
        imageResponses.addAll(newImageResponses)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int) = ImageViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_image, parent, false)
    )

    override fun getItemCount() = imageResponses.size

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bind(imageResponses[position])
    }

    class ImageViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val imageView = view.imageView
        private val progressDrawable = getProgressDrawable(view.context)

        fun bind(imageResponse: ImageResponse) {
            imageView.loadImage(imageResponse.urls?.small, progressDrawable)
        }
    }
}