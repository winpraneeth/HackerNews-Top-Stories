package com.assignment.alchemy.hackernewsstories.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import com.assignment.alchemy.hackernewsstories.model.Comment
import kotlinx.android.synthetic.main.comments_view.view.*

class CommentsItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private var comment: Comment? = null

    fun bindComment(comment: Comment) {
        this.comment = comment
        itemView.comment_text.text = comment.text
    }


}
