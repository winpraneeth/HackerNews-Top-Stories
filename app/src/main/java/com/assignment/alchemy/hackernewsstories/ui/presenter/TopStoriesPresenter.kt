package com.assignment.alchemy.hackernewsstories.ui.presenter

import android.content.Context
import com.assignment.alchemy.hackernewsstories.model.Story
import com.assignment.alchemy.hackernewsstories.service.DetachableResultsReceiver
import com.assignment.alchemy.hackernewsstories.service.TopStoriesIntentService
import javax.inject.Inject

class TopStoriesPresenter @Inject constructor(): TopStoriesContract.Presenter() {
    override fun getTopStories(topStoriesContext: Context, resultsReceiver: DetachableResultsReceiver) {
        TopStoriesIntentService.enqueueWork(
                topStoriesContext,
                TopStoriesIntentService.buildGetTopStoriesIntent(
                        topStoriesContext,
                        resultsReceiver
                ))

    }

    override fun onStoryItemPressed(story: Story, adapterPosition: Int) {
        if (story.url != null) {
            view?.navigateToWebView(story.url!!)
        } else {
            view?.navigateToCommentsActivity(story)
        }
    }


}