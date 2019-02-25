package com.assignment.alchemy.hackernewsstories.dagger

import com.assignment.alchemy.hackernewsstories.ui.view.CommentsActivityFragment
import com.assignment.alchemy.hackernewsstories.ui.view.TopStoriesFragment
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [(TopStoriesModule::class)])
interface TopStoriesComponent {

    fun inject(topStoriesFragment: TopStoriesFragment)

    fun inject(commentsActivityFragment: CommentsActivityFragment)

}
