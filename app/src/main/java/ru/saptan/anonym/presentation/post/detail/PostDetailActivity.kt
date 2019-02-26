package ru.saptan.anonym.presentation.post.detail

import android.os.Bundle
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_post_details.*
import kotlinx.android.synthetic.main.post_item_view.view.*
import ru.saptan.anonym.R
import ru.saptan.anonym.app.ComponentProvider
import ru.saptan.anonym.domain.model.data.Post
import ru.saptan.anonym.presentation.common.Layout
import ru.saptan.anonym.presentation.common.activities.ABaseActivity
import ru.saptan.anonym.presentation.common.setVisibility
import ru.saptan.anonym.routing.IntentCreator
import javax.inject.Inject

@Layout(id = R.layout.activity_post_details)
class PostDetailActivity : ABaseActivity(), IPostDetailView {

    @InjectPresenter
    @Inject
    lateinit var presenter: PostDetailPresenter

    @ProvidePresenter
    fun providePresenter() = presenter

    override fun inject() {
        ComponentProvider.providePostComponent().inject(this)
        presenter.context = this
        presenter.postId = intent.extras?.getInt(IntentCreator.POST_ID_KEY) ?: -1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTitleToolbar(R.string.app_name)
        showBackArrowInToolBar(true)

        csavComments.setOnClickListener { presenter.onCommentMoreClicked() }
        csavLikes.setOnClickListener { presenter.onLikeClicked() }
    }


    override fun showInfo(post: Post) {
        tvDescription.text = post.text

        csavLikes.setCountActions(post.getCountLikes())
        csavComments.setCountActions(post.getCountComments())
        csavReposts.setCountActions(post.getCountReposts())
        csavViews.setCountActions(post.getCountViews())

        val url: String? = post.getPreviewPhotoUrl()
        ivPhotoAttachment.setVisibility(url != null)
        ivPhotoAttachment.setImageDrawable(null)
        Glide.with(this)
                .load(url)
                .placeholder(R.drawable.ic_placeholder)
                .into(ivPhotoAttachment)

        tvTags.setVisibility(!post.tags.isNullOrEmpty())
        tvTags.text = post.tags?.joinToString("#", "#")
    }
}