package com.assignment.alchemy.hackernewsstories.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import com.assignment.alchemy.hackernewsstories.model.Story
import kotlinx.android.synthetic.main.top_stories_view.view.*

class TopStoriesItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener{

    private var story: Story? = null
    private var listener: TopStoriesAdapter.Listener? = null

    init {
        itemView.story_layout.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view) {
            itemView.story_layout ->  {
                story?.let { listener?.onItemPressed(view, it, adapterPosition) }
            }

        }

    }

    fun bindStory(story: Story, listener: TopStoriesAdapter.Listener) {
        this.story = story
        this.listener = listener
        itemView.story_title.text = story.title
        itemView.story_url.text = story.url
        itemView.comments_count.text = story.descendants.toString() + " comments"

    }

}