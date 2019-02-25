package com.assignment.alchemy.hackernewsstories.ui.view

import android.os.Bundle
import android.os.Handler
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.assignment.alchemy.hackernewsstories.AbstractFragment
import com.assignment.alchemy.hackernewsstories.PaddedItemDecoration
import com.assignment.alchemy.hackernewsstories.R
import com.assignment.alchemy.hackernewsstories.adapter.CommentsAdapter
import com.assignment.alchemy.hackernewsstories.dagger.TopStoriesComponentProvider
import com.assignment.alchemy.hackernewsstories.model.Comment
import com.assignment.alchemy.hackernewsstories.model.Story
import com.assignment.alchemy.hackernewsstories.service.DetachableResultsReceiver
import com.assignment.alchemy.hackernewsstories.service.TopStoriesIntentService
import com.assignment.alchemy.hackernewsstories.ui.presenter.CommentsContract
import kotlinx.android.synthetic.main.fragment_comments.*
import java.util.ArrayList
import javax.inject.Inject

/**
 * A placeholder fragment containing a simple view.
 */
class CommentsActivityFragment : AbstractFragment(), DetachableResultsReceiver.Receiver {

    @Inject
    lateinit var commentsPresenter: CommentsContract.Presenter

    private var commentsIds: ArrayList<Int>? = null
    private var story: Story? = null
    private lateinit var resultsReceiver: DetachableResultsReceiver
    var commentsAdapter: CommentsAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        daggerInject()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Inflate the layout for this fragment
        setRecyclerView()
        resultsReceiver = DetachableResultsReceiver(Handler())
        resultsReceiver.setReceiver(this)

        context?.let { commentsPresenter.getComments(it, commentsIds, resultsReceiver) }

    }

    private fun setRecyclerView() {

        comments_list_recycler_view.layoutManager = LinearLayoutManager(this.context)
        commentsAdapter = CommentsAdapter()
        comments_list_recycler_view?.adapter = commentsAdapter
        this.context?.let {
            comments_list_recycler_view.addItemDecoration(PaddedItemDecoration(it, R.dimen.margin16dp))
        }
    }

    override fun onReceiveResult(resultCode: Int, resultData: Bundle?) {
        if (resultCode == TopStoriesIntentService.SUCCESS) {
            if (resultData != null && resultData.containsKey(TopStoriesIntentService.EXTRA_RESULTS)) run {
                val commentsList = resultData.getSerializable(TopStoriesIntentService.EXTRA_RESULTS) as List<Comment>
                story_title.text = story?.title
                commentsAdapter?.comments = commentsList
                commentsAdapter?.notifyDataSetChanged()
            }
        }
    }

    companion object {
        fun buildCommentsFragment(story: Story, commentsIds: ArrayList<Int>?): CommentsActivityFragment {
            val frag = CommentsActivityFragment()
            val args = Bundle()
            frag.arguments = args
            frag.commentsIds = commentsIds
            frag.story = story

            return frag
        }

        var FRAGMENT_TAG = "CommentsActivityFragment"
    }

    private fun daggerInject() {
        TopStoriesComponentProvider.INSTANCE.getComponent().inject(this)
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_comments
    }
}
