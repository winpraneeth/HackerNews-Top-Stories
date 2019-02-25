package com.assignment.alchemy.hackernewsstories.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.assignment.alchemy.hackernewsstories.model.Story
import com.assignment.alchemy.hackernewsstories.R

class TopStoriesAdapter(private var listener: Listener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    interface Listener {
        fun onItemPressed(view: View?, story: Story, adapterPosition: Int)

    }

    var stories: List<Story> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return TopStoriesItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.top_stories_view, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val story = stories[position]
        val listHolder = holder as TopStoriesItemViewHolder
        listHolder.bindStory(story, listener)
    }

    override fun getItemCount() = stories.size

}