package ru.saptan.anonym.presentation.post.list

import com.arellomobile.mvp.InjectViewState
import ru.saptan.anonym.app.injections.scopes.PerActivity
import ru.saptan.anonym.domain.interactors.post.IPostInteractor
import ru.saptan.anonym.domain.model.data.Post
import ru.saptan.anonym.presentation.common.ABasePresenter
import ru.saptan.anonym.routing.IActivitiesRouter
import javax.inject.Inject

@InjectViewState
@PerActivity
class PostListPresenter @Inject constructor(
        private val interactor: IPostInteractor,
        private val router: IActivitiesRouter
) : ABasePresenter<IPostListView<Post>>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        loadPost()
    }

    private fun loadPost() {
        viewState.showLoadingProgress(true)
        addDisposable(
                interactor.getPosts().subscribe(
                        {
                            viewState.setData(it)
                            viewState.showLoadingProgress(false)
                        },
                        {
                            viewState.showToast(getErrorText(it))
                            viewState.showLoadingProgress(false)
                        }
                )
        )
    }

    fun onPostClicked(post: Post) {

    }

    fun onRefresh() {

    }
}