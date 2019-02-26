package ru.saptan.anonym.presentation.post.detail

import com.arellomobile.mvp.InjectViewState
import ru.saptan.anonym.app.injections.scopes.PerActivity
import ru.saptan.anonym.domain.interactors.post.IPostInteractor
import ru.saptan.anonym.presentation.common.ABasePresenter
import ru.saptan.anonym.routing.IActivitiesRouter
import javax.inject.Inject

@InjectViewState
@PerActivity
class PostDetailPresenter @Inject constructor(
        private val interactor: IPostInteractor,
        private val router: IActivitiesRouter
) : ABasePresenter<IPostDetailView>() {

    var postId = -1

    override fun attachView(view: IPostDetailView?) {
        super.attachView(view)
        loadPostInformation()
    }

    private fun loadPostInformation() {
        viewState.showLoadingProgress(true)
        addDisposable(interactor.getPostById(postId).subscribe(
                { post ->
                    viewState.showLoadingProgress(false)
                    if (post == null) {
                        logD("Error -> post isd null")
                        viewState.showToast("Ошибка при загрузке данных")
                        return@subscribe
                    }

                    viewState.showInfo(post)
                },
                { error ->
                    viewState.showToast(getErrorText(error))
                    viewState.showLoadingProgress(false)

                }
        ))
    }
}