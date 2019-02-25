package com.assignment.alchemy.hackernewsstories

interface AppContextProvider {
    abstract fun getTopStoriesContext(): TopStoriesContext
}