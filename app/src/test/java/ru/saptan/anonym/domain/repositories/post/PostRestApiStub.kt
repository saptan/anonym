package ru.saptan.anonym.domain.repositories.post

import io.reactivex.Observable
import ru.saptan.anonym.base.ApiUtils
import ru.saptan.anonym.base.POST_MOCK_PATH
import ru.saptan.anonym.domain.model.data.ListPostsResponse
import ru.saptan.anonym.domain.model.rest.PostListRequestParams
import ru.saptan.anonym.domain.repositories.post.rest.IPostRestApi

class PostRestApiStub : IPostRestApi {

    override fun getRemotePosts(requestParams: PostListRequestParams): Observable<ListPostsResponse> {
        return Observable.fromCallable { ApiUtils.getUrl<ListPostsResponse>(POST_MOCK_PATH) }
    }

    override fun cancelAllRequests() {
    }

}