package com.assignment.alchemy.hackernewsstories.ui.presenter

import android.content.Context
import com.assignment.alchemy.hackernewsstories.service.DetachableResultsReceiver
import com.assignment.alchemy.hackernewsstories.service.TopStoriesIntentService
import java.util.ArrayList
import javax.inject.Inject

class CommentsPresenter @Inject constructor(): CommentsContract.Presenter() {
    override fun getComments(context: Context, commentsIds: ArrayList<Int>?, resultsReceiver: DetachableResultsReceiver) {
        TopStoriesIntentService.enqueueWork(
                context,
                TopStoriesIntentService.buildGetCommentsIntent(
                        context,
                        commentsIds,
                        resultsReceiver
                ))

    }
}