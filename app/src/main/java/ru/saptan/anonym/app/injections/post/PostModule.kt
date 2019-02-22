package ru.saptan.anonym.app.injections.post

import dagger.Module
import dagger.Provides
import ru.saptan.anonym.app.injections.scopes.PerActivity
import ru.saptan.anonym.domain.interactors.post.IPostInteractor
import ru.saptan.anonym.domain.interactors.post.PostInteractor
import ru.saptan.anonym.domain.repositories.post.IPostRepository

@Module
class PostModule {

    @Provides
    @PerActivity
    fun postInteractor(postRepository: IPostRepository): IPostInteractor {
        return PostInteractor(postRepository)
    }
}