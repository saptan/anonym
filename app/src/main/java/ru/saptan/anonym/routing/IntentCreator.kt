package ru.saptan.anonym.routing

import android.content.Context
import android.content.Intent
import ru.saptan.anonym.presentation.post.detail.PostDetailActivity

class IntentCreator(private var packageName: String) : IIntentCreator {

    companion object {
        const val POST_ID_KEY = "postId"
    }

    override fun getPostDetailActivityIntent(context: Context, postId: Int): Intent {
        val intent = Intent(context, PostDetailActivity::class.java)
        intent.putExtra(POST_ID_KEY, postId)
        return intent
    }

}