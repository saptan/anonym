package ru.saptan.anonym.domain.model.extensions

import io.realm.RealmList
import ru.saptan.anonym.domain.model.data.Post
import ru.saptan.anonym.domain.model.data.PostCountStat
import ru.saptan.anonym.domain.model.realm.PostRealm
import ru.saptan.anonym.domain.model.rest.PostListRequestParams

fun Post.map2realm(): PostRealm {
    val postRealm = PostRealm()
    with(this) {
        postRealm.id = id
        postRealm.type = type ?: PostListRequestParams.POPULAR
        postRealm.text = text
        postRealm.date = date
        postRealm.category = category
        postRealm.ownerId = ownerId
        postRealm.ownerName = ownerName
        postRealm.ownerPhoto = ownerPhoto
        postRealm.countReposts = countReposts ?: 0
        postRealm.countViews = postviews?.count ?: 0
        postRealm.countLikes = likes?.count ?: 0
        postRealm.countComments = comments?.count ?: 0
        postRealm.attachments = attachments?.map2RealmList(id)
        postRealm.createdAt = createdAt
        postRealm.tags = tags?.mapStrings2RealmList()

    }
    return postRealm
}

fun PostRealm.map2Data(): Post {
    val post = Post()
    with(this) {
        post.id = id
        post.text = text
        post.date = date
        post.category = category
        post.type = type
        post.ownerId = ownerId
        post.ownerName = ownerName
        post.ownerPhoto = ownerPhoto
        post.countReposts = countReposts
        post.postviews = PostCountStat(countViews)
        post.likes = PostCountStat(countLikes)
        post.comments = PostCountStat(countComments)
        post.attachments = attachments?.map2DataList()
        post.createdAt = createdAt
        post.tags = tags
    }
    return post
}


fun List<Post>.map2RealmList(): RealmList<PostRealm> {
    val rl = RealmList<PostRealm>()
    this.forEach { rl.add(it.map2realm()) }
    return rl
}

fun List<String>.mapStrings2RealmList(): RealmList<String> {
    val rl = RealmList<String>()
    this.forEach { rl.add(it) }
    return rl
}

fun MutableList<PostRealm>.map2DataList(): List<Post> {
    val l = ArrayList<Post>()
    this.forEach { l.add(it.map2Data()) }
    return l
}


