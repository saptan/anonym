package ru.saptan.anonym.domain.repositories.post

import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertThat
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mockito
import ru.saptan.anonym.app.Const
import ru.saptan.anonym.base.ABaseRepositoryTest
import ru.saptan.anonym.base.POST_MOCK_PATH
import ru.saptan.anonym.domain.model.rest.PostListRequestParams
import ru.saptan.anonym.domain.repositories.post.local.IPostLocalStorage
import java.util.concurrent.TimeUnit
import org.mockito.Mockito.*
import ru.saptan.anonym.domain.model.data.Post
import ru.saptan.anonym.domain.repositories.common.exceptions.RepositoryException


class PostRepositoryTest : ABaseRepositoryTest<PostRepository, PostRestApiStub>() {

    private val localStorage = Mockito.mock(IPostLocalStorage::class.java)

    override fun initRestApi(): PostRestApiStub = PostRestApiStub()

    override fun initRepository(): PostRepository = PostRepository(localStorage, restApi)

    @Test
    fun `success loaded 20 posts from server and save to cache`() {
        POST_MOCK_PATH = "get_posts_success_200"
        val requestParams = PostListRequestParams()
        val testObserver = repository.getPosts(requestParams)
                .test()
                .awaitDone(1, TimeUnit.SECONDS)
                .assertComplete()
                .assertNoErrors()

        val listResult = testObserver.values()[1]
        // Проверить что с сервера пришло ровно столько записей, сколько и запрашивалось
        assertThat(listResult.size, `is`(Const.MAX_COUNT_POST_IN_REQUEST))
        assertThat(listResult[0].id, `is`(402459))

        // После запуска приложения, если с бэкенда были успешно получены все данные, необходимо почистить кеш
        verify(localStorage).savePosts(ArgumentMatchers.anyList(), ArgumentMatchers.eq(true))
    }

    @Test
    fun `success loaded next page with posts from server`() {
        POST_MOCK_PATH = "get_posts_success_200"
        val requestParams = PostListRequestParams()
                .prepareNextPage()
                .withDataSource(Const.TYPE_SOURCE_NETWORK)

        val testObserver = repository.getPosts(requestParams)
                .test()
                .awaitDone(1, TimeUnit.SECONDS)
                .assertComplete()
                .assertNoErrors()

        val listResult = testObserver.values()[0]
        // Проверить что с сервера пришло ровно столько записей, сколько и запрашивалось
        assertThat(listResult.size, `is`(Const.MAX_COUNT_POST_IN_REQUEST))
        assertThat(listResult[0].id, `is`(402459))

        // Проверить что после получения следующей порции данных они сохраняются в кеш (без принудительной чистки)
        verify(localStorage).savePosts(ArgumentMatchers.anyList(), ArgumentMatchers.eq(false))
    }


    @Test
    fun `success loaded post list from cache when network error`() {
        POST_MOCK_PATH = "get_posts_success_200"
        val requestParams = PostListRequestParams()
        restApi.enableNetworkError = true

        // Предположим что в кеше были сохранены всего 2 поста
        `when`(localStorage.getPostsFromCache(ArgumentMatchers.anyInt())).thenReturn(listOf(Post(id = 1), Post( id = 2)))

        val testObserver = repository.getPosts(requestParams)
                .test()
                .awaitDone(1, TimeUnit.SECONDS)
                .assertNotComplete()
                .assertError(RepositoryException::class.java)

        val listResult = testObserver.values()[0]
        // Проверить что были получены только данные из кеша
        assertThat(listResult.size, `is`(2))
        assertThat(listResult[0].id, `is`(1))

        // Проверить что из-за ошибки сети данные в кеш не попали
        verify(localStorage, never()).savePosts(ArgumentMatchers.anyList(), ArgumentMatchers.anyBoolean())
    }
}