package ru.saptan.anonym.domain.repositories.post.local

import io.reactivex.Observable
import io.realm.Realm
import ru.saptan.anonym.domain.model.data.Post
import ru.saptan.anonym.domain.model.realm.PostRealm
import ru.saptan.anonym.domain.repositories.common.local.BaseLocalStorageRealm

class PostLocalStorage: BaseLocalStorageRealm(), IPostLocalStorage {
    override fun getPostsFromCache(type: Int): Observable<List<Post>> {
        Realm.getDefaultInstance().use { realm ->
            val results = realm
                    .where(PostRealm::class.java)
                    .equalTo("sensor.id", sensorId)
                    .findAll()
            return realm.copyFromRealm(results).map2DataList()
        }
    }
}