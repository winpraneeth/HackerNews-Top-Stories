package com.assignment.alchemy.hackernewsstories.dagger

import java.lang.annotation.RetentionPolicy
import java.lang.annotation.Retention

import javax.inject.Scope

    @Scope
    @Retention(RetentionPolicy.RUNTIME)
    annotation class TopStoriesScope
