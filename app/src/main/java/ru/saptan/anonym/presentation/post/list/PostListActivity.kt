package ru.saptan.anonym.presentation.post.list

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import ru.saptan.anonym.R
import ru.saptan.anonym.app.ComponentProvider
import ru.saptan.anonym.domain.model.data.Post
import ru.saptan.anonym.presentation.common.Layout
import ru.saptan.anonym.presentation.common.SpaceItemDecoration
import ru.saptan.anonym.presentation.common.activities.ABaseSwipeListActivity
import ru.saptan.anonym.presentation.common.list.AListAdapter
import javax.inject.Inject

@Layout(id = R.layout.activity_post_list)
class PostListActivity : ABaseSwipeListActivity<Post, AListAdapter.DefaultViewHolder<Post>>(), IPostListView<Post> {

    @InjectPresenter
    @Inject
    lateinit var presenter: PostListPresenter

    @ProvidePresenter
    fun providePresenter() = presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTitleToolbar(R.string.app_name)

        val layoutManager = recyclerView.layoutManager as LinearLayoutManager
        recyclerView.addOnScrollListener(presenter.initScrollListener(layoutManager))
    }

    override fun inject() {
        ComponentProvider.providePostComponent().inject(this)
        presenter.context = this
    }

    override val emptyViewText: Int
        get() = R.string.message_empty_view_posts

    override fun initAdapter(): AListAdapter<Post, AListAdapter.DefaultViewHolder<Post>> {
        val adapter = PostListAdapter()
        adapter.onDataClickListener = { presenter.onPostClicked(it) }
        return adapter
    }

    override fun initLayoutManager() = LinearLayoutManager(this)

    override fun initItemDecorator() = SpaceItemDecoration(this, 6F, 8F)

    override fun onRefresh() {
        presenter.onRefresh()
    }

    override fun setData(dataSet: List<Post>) {
        (adapter as PostListAdapter).setData(dataSet)
    }

    override fun showLoadingFooter() {
        (adapter as PostListAdapter).showLoadingFooter()
    }

    override fun hideLoadingFooter() {
        (adapter as PostListAdapter).hideLoadingFooter()
    }
}