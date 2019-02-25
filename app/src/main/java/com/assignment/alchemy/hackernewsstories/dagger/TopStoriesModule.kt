package com.assignment.alchemy.hackernewsstories.dagger

import com.assignment.alchemy.hackernewsstories.ui.presenter.CommentsContract
import com.assignment.alchemy.hackernewsstories.ui.presenter.CommentsPresenter
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

        @JvmStatic
        @Provides
        internal fun provideCommentsPresenter(): CommentsContract.Presenter {
            return CommentsPresenter()
        }

    }
}