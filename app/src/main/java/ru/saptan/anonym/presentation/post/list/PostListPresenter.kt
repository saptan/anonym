package ru.saptan.anonym.presentation.post.list

import com.arellomobile.mvp.InjectViewState
import ru.saptan.anonym.app.Const
import ru.saptan.anonym.app.injections.scopes.PerActivity
import ru.saptan.anonym.domain.interactors.post.IPostInteractor
import ru.saptan.anonym.domain.model.data.Post
import ru.saptan.anonym.domain.model.rest.PostListRequestParams
import ru.saptan.anonym.presentation.common.ABasePresenter
import ru.saptan.anonym.routing.IActivitiesRouter
import javax.inject.Inject

@InjectViewState
@PerActivity
class PostListPresenter @Inject constructor(
        private val interactor: IPostInteractor,
        private val router: IActivitiesRouter
) : ABasePresenter<IPostListView<Post>>() {

    var requestParams = PostListRequestParams()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        loadPost()
    }

    private fun loadPost() {
        viewState.showLoadingProgress(true)
        addDisposable(interactor.getPosts(requestParams).subscribe(
                { posts ->
                    viewState.setData(posts)
                    viewState.showLoadingProgress(false)
                },
                { error ->
                    viewState.showToast(getErrorText(error))
                    viewState.showLoadingProgress(false)

                }
        ))
    }

    fun onPostClicked(post: Post) {
        viewState.showToast("Post with id = ${post.id}")
    }

    fun onRefresh() {
        requestParams.prepareFirstPage().withDataSource(Const.TYPE_SOURCE_NETWORK)
        addDisposable(interactor.getPosts(requestParams).subscribe(
                { posts ->
                    viewState.setData(posts)
                    viewState.stopRefreshing()
                },
                { error ->
                    viewState.showToast(getErrorText(error))
                    viewState.stopRefreshing()
                }
        ))
    }
}