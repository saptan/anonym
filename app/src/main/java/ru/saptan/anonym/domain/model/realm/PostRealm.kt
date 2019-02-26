package ru.saptan.anonym.domain.model.realm

import io.realm.RealmList
import io.realm.RealmModel
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass
import ru.saptan.anonym.domain.model.rest.PostListRequestParams

@RealmClass
open class PostRealm : RealmModel {

    @PrimaryKey
    var id: Int = 0
    var type: Int = PostListRequestParams.POPULAR
    var text: String? = null
    var date: Long? = 0L
    var category: Int? = 0

    var ownerName: String? = ""
    var ownerId: Int? = 0
    var ownerPhoto: String? = ""

    var countReposts: Int = 0
    var countViews: Int = 0
    var countLikes: Int = 0
    var countComments: Int = 0

    var attachments: RealmList<PostAttachmentRealm>? = null

    var createdAt: Long = 0L
}