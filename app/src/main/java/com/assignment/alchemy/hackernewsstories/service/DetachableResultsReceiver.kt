package com.assignment.alchemy.hackernewsstories.service

import android.os.Bundle
import android.os.Handler
import android.os.ResultReceiver
import android.util.Log

import javax.inject.Inject

class DetachableResultsReceiver : ResultReceiver {

    private var mReceiver: Receiver? = null

    @Inject
    constructor() : super(Handler())

    constructor(handler: Handler) : super(handler)

    fun clearReceiver() {
        mReceiver = null
    }

    fun setReceiver(receiver: Receiver) {
        mReceiver = receiver
    }

    interface Receiver {
        fun onReceiveResult(resultCode: Int, resultData: Bundle?)
    }

    override fun onReceiveResult(resultCode: Int, resultData: Bundle?) {
        if (mReceiver != null) {
            mReceiver!!.onReceiveResult(resultCode, resultData)
        }
    }

    companion object {
        private val TAG = "DetachableResultReceiver"
    }
}
