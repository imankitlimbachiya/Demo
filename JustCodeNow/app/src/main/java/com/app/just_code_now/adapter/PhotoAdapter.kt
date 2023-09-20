package com.app.just_code_now.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.just_code_now.R
import com.app.just_code_now.databinding.ItemPhotoBinding
import com.app.just_code_now.model.Photo
import com.bumptech.glide.Glide

class PhotoAdapter(
    private var ctx: Context, private var data: MutableList<Photo>
) : RecyclerView.Adapter<RecyclerView.ViewHolder?>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return VhPhoto(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_photo, parent, false
            )
        )
    }

    override fun onBindViewHolder(m: RecyclerView.ViewHolder, position: Int) {
        val model: Photo = data[position]
        val post: VhPhoto?
        post = m as VhPhoto
        post.b.model = model

        Glide.with(ctx).load(model.url).into(post.b.imgPhoto)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    internal class VhPhoto(b: ItemPhotoBinding) : RecyclerView.ViewHolder(b.root) {

        val b: ItemPhotoBinding

        init {
            this.b = b
        }
    }
}