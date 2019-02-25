package com.assignment.alchemy.hackernewsstories.service

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.ResultReceiver
import android.support.v4.app.JobIntentService

import com.assignment.alchemy.hackernewsstories.AppContextProvider
import com.assignment.alchemy.hackernewsstories.TopStoriesContext
import com.assignment.alchemy.hackernewsstories.model.Comment
import com.assignment.alchemy.hackernewsstories.model.Story

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.Serializable

class TopStoriesIntentService : JobIntentService(), AppContextProvider {

    private var storiesList: MutableList<Story> = ArrayList()
    private var commentsList: MutableList<Comment> = ArrayList()
    private var apiInterface: HackerNewsApi? = null

    override fun onHandleWork(intent: Intent) {
        val bundle = Bundle()
        val action = intent.getIntExtra(EXTRA_ACTION, -1)
        when (action) {
            ACTION_GET_STORIES -> {
                getHackerRankTopStories(intent, bundle)

            }
            ACTION_GET_COMMENTS -> {
                val commentsIds = intent.getIntegerArrayListExtra(EXTRA_COMMENTS)
                getCommentsDetails(commentsIds, intent, bundle)

            }
            else -> throw UnsupportedOperationException("GetStoriesIntent received unknown intent.")
        }
    }

    private fun getHackerRankTopStories(intent: Intent, bundle: Bundle) {
        apiInterface = RetrofitClient.getClient(this)?.create(HackerNewsApi::class.java)
        apiInterface?.topStories?.enqueue(object : Callback<List<Int>> {
            override fun onResponse(call: Call<List<Int>>, response: Response<List<Int>>) {
                val topStories = response.body()
                getStoryDetails(topStories, intent, bundle)

            }

            override fun onFailure(call: Call<List<Int>>, t: Throwable) {

            }
        })

    }

    private fun getStoryDetails(topStories: List<Int>?, intent: Intent, bundle: Bundle) {
        if (topStories != null) {
            for (storyId in topStories) {
                apiInterface?.getStory(storyId)?.enqueue(object : Callback<Story> {
                    override fun onResponse(call: Call<Story>, response: Response<Story>) {
                        val story = response.body()
                        story?.let { storiesList.add(it) }
                        bundle.putSerializable(EXTRA_RESULTS, storiesList as Serializable)
                        sendResults(intent, bundle, SUCCESS)


                    }

                    override fun onFailure(call: Call<Story>, t: Throwable) {

                    }
                })
            }


        }
    }

    private fun getCommentsDetails(commentIds: List<Int>?, intent: Intent, bundle: Bundle) {
        apiInterface = RetrofitClient.getClient(this)?.create(HackerNewsApi::class.java)
        if (commentIds != null) {
            for (commentId in commentIds) {
                apiInterface?.getComment(commentId)?.enqueue(object : Callback<Comment> {
                    override fun onResponse(call: Call<Comment>, response: Response<Comment>) {
                        val comment = response.body()
                        comment?.let { commentsList.add(it) }
                        bundle.putSerializable(EXTRA_RESULTS, commentsList as Serializable)
                        sendResults(intent, bundle, SUCCESS)
                    }

                    override fun onFailure(call: Call<Comment>, t: Throwable) {

                    }
                })
            }


        }
    }


    fun sendResults(intent: Intent, bundle: Bundle, resultCode: Int) {
        if (intent.hasExtra(EXTRA_RECEIVER)) {
            val rec = intent.getParcelableExtra<ResultReceiver>(EXTRA_RECEIVER)
            rec?.send(resultCode, bundle)
        }
    }

    override fun getTopStoriesContext(): TopStoriesContext {
        return (application as AppContextProvider).getTopStoriesContext()
    }

    companion object {

        private const val EXTRA_ACTION = "action"
        const val EXTRA_RECEIVER = "receiver"
        const val EXTRA_COMMENTS = "comments"
        const val EXTRA_RESULTS = "results"
        private const val JOB_ID = 1000
        const val SUCCESS = 31
        private const val ACTION_GET_STORIES = 0
        private const val ACTION_GET_COMMENTS = 1


        fun buildGetTopStoriesIntent(context: Context, receiver: DetachableResultsReceiver): Intent {
            return Intent(context, TopStoriesIntentService::class.java)
                    .putExtra(EXTRA_ACTION, ACTION_GET_STORIES)
                    .putExtra(EXTRA_RECEIVER, receiver)
        }

        fun buildGetCommentsIntent(context: Context, commentsIds: ArrayList<Int>?, resultsReceiver: DetachableResultsReceiver): Intent {
            return Intent(context, TopStoriesIntentService::class.java)
                    .putExtra(EXTRA_ACTION, ACTION_GET_COMMENTS)
                    .putIntegerArrayListExtra(EXTRA_COMMENTS, commentsIds)
                    .putExtra(EXTRA_RECEIVER, resultsReceiver)
        }

        fun enqueueWork(context: Context, work: Intent) {
            JobIntentService.enqueueWork(context, TopStoriesIntentService::class.java, JOB_ID, work)
        }
    }
}
