package com.assignment.alchemy.hackernewsstories.dagger

enum class TopStoriesComponentProvider {
    INSTANCE;

    private var component: TopStoriesComponent? = null

    fun getComponent(): TopStoriesComponent {
        if (component == null) {
            component = DaggerTopStoriesComponent.builder()
                    .topStoriesModule(TopStoriesModule())
                    .build()
        }

        return component as TopStoriesComponent
    }
}