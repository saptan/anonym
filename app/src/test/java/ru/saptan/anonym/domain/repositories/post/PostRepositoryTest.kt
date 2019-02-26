package ru.saptan.anonym.domain.repositories.post

import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertThat
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mockito
import org.mockito.Mockito.any
import org.mockito.Mockito.verify
import ru.saptan.anonym.app.Const
import ru.saptan.anonym.base.ABaseRepositoryTest
import ru.saptan.anonym.base.POST_MOCK_PATH
import ru.saptan.anonym.domain.model.rest.PostListRequestParams
import ru.saptan.anonym.domain.repositories.post.local.IPostLocalStorage
import java.util.concurrent.TimeUnit

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

        assertThat(listResult.size, `is`(Const.MAX_COUNT_POST_IN_REQUEST))
    }
}