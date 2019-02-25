package com.assignment.alchemy.hackernewsstories.ui.presenter

import com.assignment.alchemy.hackernewsstories.model.Story
import javax.inject.Inject

class TopStoriesPresenter @Inject constructor(): TopStoriesContract.Presenter() {

    override fun onStoryItemPressed(story: Story, adapterPosition: Int) {
        if (story.url != null) {
            view?.navigateToWebView(story.url!!)
        } else {
            view?.navigateToCommentsActivity(story)
        }
    }


}