package com.assignment.alchemy.hackernewsstories.ui.presenter

import com.assignment.alchemy.hackernewsstories.AbstractPresenter
import com.assignment.alchemy.hackernewsstories.model.Story


class TopStoriesContract {
    interface  View {
        fun navigateToWebView(url: String)
        fun navigateToCommentsActivity(story: Story)
    }

    abstract class Presenter : AbstractPresenter<View>() {

        abstract fun onStoryItemPressed(story: Story, adapterPosition: Int)

    }
}
