package ru.saptan.anonym.domain.repositories.post.local

import io.realm.Realm
import ru.saptan.anonym.domain.model.data.Post
import ru.saptan.anonym.domain.model.extensions.map2DataList
import ru.saptan.anonym.domain.model.extensions.map2RealmList
import ru.saptan.anonym.domain.model.realm.PostRealm
import ru.saptan.anonym.domain.repositories.common.local.BaseLocalStorageRealm

class PostLocalStorage : BaseLocalStorageRealm(), IPostLocalStorage {
    override fun getPostsFromCache(type: Int): List<Post> {
        Realm.getDefaultInstance().use { realm ->
            val results = realm
                    .where(PostRealm::class.java)
                    .equalTo("type", type)
                    .findAll()
            return realm.copyFromRealm(results).map2DataList()
        }
    }


    override fun savePosts(posts: List<Post>) {
        Realm.getDefaultInstance().use { realm ->
            delete(PostRealm::class.java, realm)
            saveChanges(posts.map2RealmList(), realm)
        }
    }

}