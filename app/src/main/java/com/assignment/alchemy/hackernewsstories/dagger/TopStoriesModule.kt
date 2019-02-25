package com.assignment.alchemy.hackernewsstories.dagger

import com.assignment.alchemy.hackernewsstories.ui.presenter.TopStoriesContract
import com.assignment.alchemy.hackernewsstories.ui.presenter.TopStoriesPresenter
import dagger.Module
import dagger.Provides

@Module
class TopStoriesModule {
    @Module
    companion object {

        @JvmStatic
        @Provides
        internal fun provideTopStoriesPresenter(): TopStoriesContract.Presenter {
            return TopStoriesPresenter()
        }

    }
}