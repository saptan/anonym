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

    var urlBigPhoto: String? = null
    var photoBigWith: Int? = 0
    var photoBigHeight: Int? = 0

    var urlMediumPhoto: String? = null
    var photoMediumWith: Int? = 0
    var photoMediumHeight: Int? = 0

    var urlSmallPhoto: String? = null
    var photoSmallWith: Int? = 0
    var photoSmallHeight: Int? = 0
}
