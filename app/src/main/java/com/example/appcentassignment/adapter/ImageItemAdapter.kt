package com.example.appcentassignment.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.appcentassignment.R
import com.example.appcentassignment.extenssions.loadImage
import com.example.appcentassignment.models.response.Photo

class ImageItemAdapter(private val onItemClickListener: OnItemClickListener) :
    RecyclerView.Adapter<ImageItemAdapter.MyViewHolder>() {

    private val imageItemList: ArrayList<Photo> = ArrayList()

    fun setItems(list: List<Photo>) {
        list.apply {
            imageItemList.clear()
            imageItemList.addAll(this)
            notifyDataSetChanged()
        }
    }

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var image = view.findViewById(R.id.image_item) as ImageView

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageItemAdapter.MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_list, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ImageItemAdapter.MyViewHolder, position: Int) {
        val card = imageItemList[position]
        holder.image.loadImage(card.img_src)
        holder.image.setOnClickListener {
            onItemClickListener.clickListener(card)
        }
    }

    override fun getItemCount(): Int {
        return imageItemList.size
    }

    interface OnItemClickListener {
        fun clickListener(photo: Photo)
    }

}