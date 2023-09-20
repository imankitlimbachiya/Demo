package com.app.just_code_now.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.just_code_now.R
import com.app.just_code_now.databinding.ItemCommentBinding
import com.app.just_code_now.databinding.ItemPostBinding
import com.app.just_code_now.interfaces.PostInterface
import com.app.just_code_now.model.Comment
import com.app.just_code_now.model.Post
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions

class CommentAdapter(
    private var ctx: Context, private var data: MutableList<Comment>
) : RecyclerView.Adapter<RecyclerView.ViewHolder?>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return VhComment(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_comment, parent, false
            )
        )
    }

    override fun onBindViewHolder(m: RecyclerView.ViewHolder, position: Int) {
        val model: Comment = data[position]
        val post: VhComment?
        post = m as VhComment
        post.b.model = model
    }

    override fun getItemCount(): Int {
        return data.size
    }

    internal class VhComment(b: ItemCommentBinding) : RecyclerView.ViewHolder(b.root) {

        val b: ItemCommentBinding

        init {
            this.b = b
        }
    }
}