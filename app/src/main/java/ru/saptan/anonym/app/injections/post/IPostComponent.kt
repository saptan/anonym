package ru.saptan.anonym.app.injections.post

import dagger.Subcomponent
import ru.saptan.anonym.app.injections.scopes.PerActivity
import ru.saptan.anonym.presentation.post.list.PostListActivity

@Subcomponent(modules = [(PostModule::class)])
@PerActivity
interface IPostComponent {
    fun inject(activity: PostListActivity)
}