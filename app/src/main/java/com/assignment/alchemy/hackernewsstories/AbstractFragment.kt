package com.assignment.alchemy.hackernewsstories

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

open class AbstractFragment: Fragment(), AppContextProvider {

    private lateinit var topStoriesContext: TopStoriesContext
    private val NO_LAYOUT = -1

    open fun getLayoutId(): Int {
        return NO_LAYOUT
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return when {
            getLayoutId() != NO_LAYOUT -> {
                val view = inflater.inflate(getLayoutId(), container, false)
                view
            }
            else -> super.onCreateView(inflater, container, savedInstanceState)
        }
    }


    override fun getTopStoriesContext(): TopStoriesContext {
        return topStoriesContext
    }

}
