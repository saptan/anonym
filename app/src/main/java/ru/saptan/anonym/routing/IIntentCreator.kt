package ru.saptan.anonym.routing

import android.content.Context
import android.content.Intent

interface IIntentCreator {
    fun getPostDetailActivityIntent(context: Context, postId: Int): Intent
}