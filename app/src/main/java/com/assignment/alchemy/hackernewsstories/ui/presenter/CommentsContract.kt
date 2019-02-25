package com.assignment.alchemy.hackernewsstories.ui.presenter

import android.content.Context
import com.assignment.alchemy.hackernewsstories.AbstractPresenter
import com.assignment.alchemy.hackernewsstories.service.DetachableResultsReceiver
import java.util.ArrayList

class CommentsContract {
    interface  View {



    }

    abstract class Presenter : AbstractPresenter<View>() {

        abstract fun getComments(context: Context, commentsIds: ArrayList<Int>?, resultsReceiver: DetachableResultsReceiver)


    }
}