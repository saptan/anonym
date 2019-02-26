package ru.saptan.anonym.routing

import android.content.Context

interface IActivitiesRouter {
    fun openPostDetailActivity(context: Context, postId: Int)
}