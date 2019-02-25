package ru.saptan.anonym.presentation.post.list

import android.content.Context
import android.util.AttributeSet
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.post_item_view.view.*
import ru.saptan.anonym.R
import ru.saptan.anonym.domain.model.data.Post
import ru.saptan.anonym.presentation.common.ABaseView
import ru.saptan.anonym.presentation.common.Layout
import ru.saptan.anonym.presentation.common.list.IListItemView
import ru.saptan.anonym.presentation.common.setVisibility

@Layout(R.layout.post_item_view)
class PostItemView @JvmOverloads constructor(context: Context,
                                             attrs: AttributeSet? = null,
                                             defStyleAttr: Int = 0
) : ABaseView(context, attrs, defStyleAttr), IListItemView<Post> {

    override fun bind(item: Post) {
        tvDescription.text = item.text
        val url: String? = item.getPreviewPhotoUrl()
        ivPhotoAttachment.setVisibility(url != null)
        ivPhotoAttachment.setImageDrawable(null)
        Glide.with(this)
                .load(url)
                .placeholder(R.drawable.ic_placeholder)
                .into(ivPhotoAttachment)

        csavLikes.setCountActions(item.getCountLikes())
        csavComments.setCountActions(item.getCountComments())
        csavReposts.setCountActions(item.getCountReposts())
    }
}