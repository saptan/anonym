package ru.saptan.anonym.domain.model.extensions

import io.realm.RealmList
import ru.saptan.anonym.domain.model.data.AttachmentPhoto
import ru.saptan.anonym.domain.model.data.PhotoSizes
import ru.saptan.anonym.domain.model.data.PostAttachment
import ru.saptan.anonym.domain.model.data.SizeItem
import ru.saptan.anonym.domain.model.realm.PostAttachmentRealm


fun PostAttachment.map2realm(postId: Int): PostAttachmentRealm {
    val postAttachmentRealm = PostAttachmentRealm()
    with(this) {
        postAttachmentRealm.postId = postId
        postAttachmentRealm.link = link
        postAttachmentRealm.type = type

        postAttachmentRealm.urlBigPhoto = photo?.urlBigPhoto
        postAttachmentRealm.photoBigWith = photo?.size?.photoBig?.width
        postAttachmentRealm.photoBigHeight = photo?.size?.photoBig?.height

        postAttachmentRealm.urlMediumPhoto = photo?.urlMediumPhoto
        postAttachmentRealm.photoMediumWith = photo?.size?.photoMedium?.width
        postAttachmentRealm.photoMediumHeight = photo?.size?.photoMedium?.height

        postAttachmentRealm.urlSmallPhoto = photo?.urlSmallPhoto
        postAttachmentRealm.photoSmallWith = photo?.size?.photoSmall?.width
        postAttachmentRealm.photoSmallHeight = photo?.size?.photoSmall?.height
    }

    return postAttachmentRealm
}

fun PostAttachmentRealm.map2Data(): PostAttachment {
    val postAttachment = PostAttachment()
    with(this) {
        val photoBig = SizeItem(photoBigWith, photoBigHeight)
        val photoMedium = SizeItem(photoMediumWith, photoMediumHeight)
        val photoSmall = SizeItem(photoSmallWith, photoSmallHeight)
        val size = PhotoSizes(photoBig, photoMedium, photoSmall)

        val photo = AttachmentPhoto(urlBigPhoto, urlMediumPhoto, urlSmallPhoto, size)

        postAttachment.link = link
        postAttachment.type = type
        postAttachment.photo = photo
    }

    return postAttachment
}

fun List<PostAttachment>.map2RealmList(postId: Int): RealmList<PostAttachmentRealm> {
    val rl = RealmList<PostAttachmentRealm>()
    this.forEach { rl.add(it.map2realm(postId)) }
    return rl
}

fun MutableList<PostAttachmentRealm>.map2DataList(): List<PostAttachment> {
    val l = ArrayList<PostAttachment>()
    this.forEach { l.add(it.map2Data()) }
    return l
}