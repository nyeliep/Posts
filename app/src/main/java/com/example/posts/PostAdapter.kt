package com.example.posts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.posts.R
import com.example.posts.model.Post

class PostAdapter(private var postList: List<Post>) : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {
    fun updatePosts(newPosts: List<Post>) {
        postList = newPosts
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = postList[position]
        holder.bind(post)
    }

    override fun getItemCount(): Int {
        return postList.size
    }

    inner class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        private val bodyTextView: TextView = itemView.findViewById(R.id.bodyTextView)

        fun bind(post: Post) {
            titleTextView.text = post.title
            bodyTextView.text = post.body
        }
    }
}

