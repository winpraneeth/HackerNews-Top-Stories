package com.assignment.alchemy.hackernewsstories.ui.view

import android.os.Bundle
import android.os.Handler
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.assignment.alchemy.hackernewsstories.AbstractFragment

import com.assignment.alchemy.hackernewsstories.R
import com.assignment.alchemy.hackernewsstories.adapter.TopStoriesAdapter
import com.assignment.alchemy.hackernewsstories.dagger.TopStoriesComponentProvider
import com.assignment.alchemy.hackernewsstories.model.Story
import com.assignment.alchemy.hackernewsstories.service.DetachableResultsReceiver
import com.assignment.alchemy.hackernewsstories.service.TopStoriesIntentService
import com.assignment.alchemy.hackernewsstories.ui.presenter.TopStoriesContract
import kotlinx.android.synthetic.main.fragment_top_stories.*
import javax.inject.Inject
import android.webkit.WebView
import android.webkit.WebViewClient
import com.assignment.alchemy.hackernewsstories.PaddedItemDecoration
import com.assignment.alchemy.hackernewsstories.ui.controller.CommentsActivity


class TopStoriesFragment : AbstractFragment(), TopStoriesContract.View, DetachableResultsReceiver.Receiver, TopStoriesAdapter.Listener {

    @Inject
    lateinit var topStoriesPresenter: TopStoriesContract.Presenter

    private lateinit var resultsReceiver: DetachableResultsReceiver
    var storiesAdapter: TopStoriesAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        daggerInject()
        topStoriesPresenter.attachView(this)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Inflate the layout for this fragment
        setRecyclerView()
        resultsReceiver = DetachableResultsReceiver(Handler())
        resultsReceiver.setReceiver(this)

        getTopStories()

    }

    private fun setRecyclerView() {

        stories_list_recycler_view.layoutManager = LinearLayoutManager(this.context)
        storiesAdapter = TopStoriesAdapter(this)
        stories_list_recycler_view?.adapter = storiesAdapter
        this.context?.let {
           stories_list_recycler_view.addItemDecoration(PaddedItemDecoration(it, R.dimen.margin16dp))
        }
    }

    private fun getTopStories() {
        context?.let {
            context?.let {
                TopStoriesIntentService.buildGetTopStoriesIntent(
                        it,
                        resultsReceiver
                )
            }?.let { it1 ->
                TopStoriesIntentService.enqueueWork(
                        it,
                        it1)
            }
        }

    }

    override fun onReceiveResult(resultCode: Int, resultData: Bundle?) {
        if (resultCode == TopStoriesIntentService.SUCCESS) {
            if (resultData != null && resultData.containsKey(TopStoriesIntentService.EXTRA_RESULTS)) run {
                val storiesList = resultData.getSerializable(TopStoriesIntentService.EXTRA_RESULTS) as List<Story>

                storiesAdapter?.stories = storiesList
                storiesAdapter?.notifyDataSetChanged()
            }
        }
    }

    override fun onItemPressed(view: View?, story: Story, adapterPosition: Int) {
        topStoriesPresenter.onStoryItemPressed(story, adapterPosition)
    }

    override fun navigateToWebView(url: String) {
        webview.visibility = View.VISIBLE
        webview.clearCache(true)
        webview.settings.javaScriptEnabled = true
        webview.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                view?.loadUrl(url)
                return false
            }
        }
        webview.loadUrl(url)

    }

    override fun navigateToCommentsActivity(story: Story) {
        context?.startActivity(CommentsActivity.buildCommentsIntent(context!!, story))
    }

     fun onBackPressed() {
        if (webview.canGoBack()) {
            webview.goBack()
        } else {
            webview.clearCache(true)
            webview.visibility = View.GONE
        }
    }


    companion object {
        fun buildTopStoriesFragment(): TopStoriesFragment {
            val frag = TopStoriesFragment()
            val args = Bundle()
            frag.arguments = args

            return frag
        }

        var FRAGMENT_TAG = "TopStoriesFragment"
    }

    private fun daggerInject() {
        TopStoriesComponentProvider.INSTANCE.getComponent().inject(this)
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_top_stories
    }

}
