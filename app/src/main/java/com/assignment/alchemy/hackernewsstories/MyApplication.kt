package com.assignment.alchemy.hackernewsstories

import android.app.Application
import com.assignment.alchemy.hackernewsstories.dagger.TopStoriesComponent
import com.assignment.alchemy.hackernewsstories.dagger.TopStoriesComponentProvider

class MyApplication: Application() {

    private var instance: MyApplication? = null

    private var mModuleComponent: TopStoriesComponent? = null

    fun getInstance(): MyApplication? {
        return instance
    }

    override fun onCreate() {
        super.onCreate()
        initSelf()
        initDagger()
    }

    private fun initSelf() {
        instance = this
    }

    private fun initDagger() {
        mModuleComponent = TopStoriesComponentProvider.INSTANCE.getComponent()
    }

    fun getStoriesComponent(): TopStoriesComponent? {
        return mModuleComponent
    }
}