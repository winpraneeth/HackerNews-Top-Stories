package com.assignment.alchemy.hackernewsstories.ui.controller

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.assignment.alchemy.hackernewsstories.R
import com.assignment.alchemy.hackernewsstories.ui.view.TopStoriesFragment

class TopStoriesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_top_stories)

        showTopStoriesFragment()
    }

    private fun showTopStoriesFragment() {
        val topStoriesFragment = getTopStoriesFragment()
                ?: TopStoriesFragment.buildTopStoriesFragment()
        supportFragmentManager.beginTransaction().replace(R.id.content_container, topStoriesFragment, TopStoriesFragment.FRAGMENT_TAG).commit()
    }

    private fun getTopStoriesFragment(): TopStoriesFragment? {
        return supportFragmentManager.findFragmentByTag(TopStoriesFragment.FRAGMENT_TAG) as TopStoriesFragment?
    }

    override fun onBackPressed() {
        getTopStoriesFragment()?.onBackPressed()
    }
}
