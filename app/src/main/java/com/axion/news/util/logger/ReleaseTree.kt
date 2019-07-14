package com.axion.news.util.logger

import android.util.Log
import timber.log.Timber

class ReleaseTree: Timber.Tree() {
    override fun log(priority: Int, tag: String?, message: String?, t: Throwable?) {
        if (priority == Log.VERBOSE || priority == Log.DEBUG || priority == Log.INFO) {
            return
        }
        //TODO: Sending crash report to Firebase CrashAnalytics
    }
}