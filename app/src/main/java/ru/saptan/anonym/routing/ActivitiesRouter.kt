package ru.saptan.anonym.routing

import android.content.Context


internal class ActivitiesRouter(private var creator: IIntentCreator) : IActivitiesRouter {
    private val tag: String = this.javaClass.simpleName

    override fun openPostDetailActivity(context: Context, postId: Int) {
        context.startActivity(creator.getPostDetailActivityIntent(context, postId))
    }
}