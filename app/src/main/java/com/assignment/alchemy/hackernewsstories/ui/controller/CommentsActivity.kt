package com.assignment.alchemy.hackernewsstories.ui.controller

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.assignment.alchemy.hackernewsstories.R
import com.assignment.alchemy.hackernewsstories.model.Story
import com.assignment.alchemy.hackernewsstories.ui.view.CommentsActivityFragment

import kotlinx.android.synthetic.main.activity_comments.*
import java.util.ArrayList

class CommentsActivity : AppCompatActivity() {

    private var commentsIds: ArrayList<Int>? = null
    private var story: Story? = null

    companion object {
        val STORY = "story"

        @JvmStatic
        fun buildCommentsIntent(context: Context, story: Story): Intent {
            val intent = Intent(context, CommentsActivity::class.java)
            intent.putExtra(STORY, story)
            return intent

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comments)
        setSupportActionBar(toolbar)
        title = getString(R.string.comments)

        story = intent.getSerializableExtra(STORY) as Story?

        showCommentsFragment()

    }

    private fun showCommentsFragment() {
        val commentsFragment = getCommentsFragment()?: story?.let { CommentsActivityFragment.buildCommentsFragment(it, story?.kids as ArrayList<Int>?) }
        commentsFragment?.let { supportFragmentManager.beginTransaction().replace(R.id.content_container, it, CommentsActivityFragment.FRAGMENT_TAG).commit() }
    }

    private fun getCommentsFragment(): CommentsActivityFragment? {
        return supportFragmentManager.findFragmentByTag(CommentsActivityFragment.FRAGMENT_TAG) as CommentsActivityFragment?
    }

}
