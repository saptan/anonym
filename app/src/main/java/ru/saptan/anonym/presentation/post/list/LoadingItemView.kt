package ru.saptan.anonym.presentation.post.list

import android.content.Context
import android.util.AttributeSet
import ru.saptan.anonym.R
import ru.saptan.anonym.domain.model.data.Post
import ru.saptan.anonym.presentation.common.ABaseView
import ru.saptan.anonym.presentation.common.Layout
import ru.saptan.anonym.presentation.common.list.IListItemView

@Layout(R.layout.loader_item_view)
class LoadingItemView @JvmOverloads constructor(context: Context,
                                                attrs: AttributeSet? = null,
                                                defStyleAttr: Int = 0
) : ABaseView(context, attrs, defStyleAttr), IListItemView<Post> {

    override fun bind(item: Post) {    }
}