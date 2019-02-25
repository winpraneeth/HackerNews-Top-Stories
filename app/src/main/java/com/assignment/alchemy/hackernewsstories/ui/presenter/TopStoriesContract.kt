package com.assignment.alchemy.hackernewsstories.ui.presenter

import android.content.Context
import com.assignment.alchemy.hackernewsstories.AbstractPresenter
import com.assignment.alchemy.hackernewsstories.model.Story
import com.assignment.alchemy.hackernewsstories.service.DetachableResultsReceiver

class TopStoriesContract {
    interface  View {
        fun navigateToWebView(url: String)
        fun navigateToCommentsActivity(story: Story)
    }

    abstract class Presenter : AbstractPresenter<View>() {

        abstract fun getTopStories(topStoriesContext: Context, resultsReceiver: DetachableResultsReceiver)
        abstract fun onStoryItemPressed(story: Story, adapterPosition: Int)

    }
}
