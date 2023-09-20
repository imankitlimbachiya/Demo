package com.app.just_code_now.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.just_code_now.R
import com.app.just_code_now.databinding.ItemPostBinding
import com.app.just_code_now.interfaces.PostInterface
import com.app.just_code_now.model.Post
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions

class PostAdapter(
    private var ctx: Context, private var data: MutableList<Post>, private var cb: PostInterface?
) : RecyclerView.Adapter<RecyclerView.ViewHolder?>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return VhPost(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_post, parent, false
            )
        )
    }

    override fun onBindViewHolder(m: RecyclerView.ViewHolder, position: Int) {
        val model: Post = data[position]
        val post: VhPost?
        post = m as VhPost
        post.b.model = model

        post.b.root.setOnClickListener {
            cb!!.onClicked(
                data[position], position
            )
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    internal class VhPost(b: ItemPostBinding) : RecyclerView.ViewHolder(b.root) {

        val b: ItemPostBinding

        init {
            this.b = b
        }
    }
}