package ru.saptan.anonym.presentation.post.list

import android.support.v7.widget.LinearLayoutManager
import com.arellomobile.mvp.InjectViewState
import ru.saptan.anonym.app.Const
import ru.saptan.anonym.app.injections.scopes.PerActivity
import ru.saptan.anonym.domain.interactors.post.IPostInteractor
import ru.saptan.anonym.domain.model.data.Post
import ru.saptan.anonym.domain.model.rest.PostListRequestParams
import ru.saptan.anonym.presentation.common.ABasePresenter
import ru.saptan.anonym.presentation.common.list.PaginationScrollListener
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
        isLoadingData = true
        viewState.showLoadingProgress(true)
        addDisposable(interactor.getPosts(requestParams).subscribe(
                { posts ->
                    viewState.setData(posts)
                    viewState.showLoadingProgress(false)
                    isLoadingData = false

                    // Если с бэка еще приходят посты, значит нужно показывать подгрузку данных
                    if (requestParams.isLastPage()) viewState.showLoadingFooter()
                    else isLastPage = true
                },
                { error ->
                    isLoadingData = false
                    viewState.showToast(getErrorText(error))
                    viewState.showLoadingProgress(false)

                }
        ))
    }

    fun onPostClicked(post: Post) {
        viewState.showToast("Post with id = ${post.id}")
    }

    fun onRefresh() {
        isLoadingData = true
        requestParams.prepareFirstPage().withDataSource(Const.TYPE_SOURCE_NETWORK)
        addDisposable(interactor.getPosts(requestParams).subscribe(
                { posts ->
                    isLoadingData = false
                    viewState.setData(posts)
                    viewState.stopRefreshing()

                    // Если с бэка еще приходят посты, значит нужно показывать подгрузку данных
                    if (requestParams.isLastPage()) viewState.showLoadingFooter()
                    else isLastPage = true
                },
                { error ->
                    isLoadingData = false
                    viewState.showToast(getErrorText(error))
                    viewState.stopRefreshing()
                }
        ))
    }

    var isLoadingData = false
    var isLastPage = false

    fun initScrollListener(layoutManager: LinearLayoutManager): PaginationScrollListener {
        return object : PaginationScrollListener(layoutManager) {
            override fun isLastPage() = isLastPage

            override fun isLoading() = isLoadingData

            override fun loadMoreItems() {
                isLoadingData = true
                loadNextPage()
            }
        }
    }

    private fun loadNextPage() {
        requestParams.prepareNextPage().withDataSource(Const.TYPE_SOURCE_NETWORK)
        addDisposable(interactor.getPosts(requestParams).subscribe(
                { posts ->
                    viewState.hideLoadingFooter()
                    isLoadingData = false

                    viewState.addData(posts)

                    // Если с бэка еще приходят посты, значит нужно показывать подгрузку данных
                    if (requestParams.isLastPage()) viewState.showLoadingFooter()
                    else isLastPage = true
                },
                { error ->
                    viewState.hideLoadingFooter()
                    viewState.showToast(getErrorText(error))
                }
        ))
    }
}