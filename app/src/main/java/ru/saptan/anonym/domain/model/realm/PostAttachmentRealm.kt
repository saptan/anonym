package ru.saptan.anonym.domain.model.realm

import io.realm.RealmModel
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

@RealmClass
open class PostAttachmentRealm : RealmModel {
    @PrimaryKey
    var postId: Int = 0
    var link: String? = null
    var type: String? = "photo"
}