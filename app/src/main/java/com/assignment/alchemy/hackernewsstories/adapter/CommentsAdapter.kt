package com.assignment.alchemy.hackernewsstories.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.assignment.alchemy.hackernewsstories.R
import com.assignment.alchemy.hackernewsstories.model.Comment

class CommentsAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var comments: List<Comment> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return CommentsItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.comments_view, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val comment = comments[position]
        val listHolder = holder as CommentsItemViewHolder
        listHolder.bindComment(comment)
    }

    override fun getItemCount() = comments.size
}