package com.assignment.alchemy.hackernewsstories

import java.util.Objects

abstract class AbstractPresenter<T> {
    protected var view: T? = null
        private set

    protected val viewOrThrow: T
        get() = this.requireView()

    fun attachView(view: T) {
        Objects.requireNonNull(view)
        this.view = view
        this.onViewAttached()
    }

    fun detachView() {
        this.view = null
        this.onViewDetached()
    }

    protected fun requireView(): T {
        return if (this.view != null) {
            this.view!!
        } else {
            throw IllegalStateException("This presenter's View should not have been null at this time.")
        }
    }

    protected fun onViewAttached() {}

    protected fun onViewDetached() {}
}
