package ru.saptan.anonym.base

import org.junit.Before
import org.mockito.MockitoAnnotations
import ru.saptan.anonym.domain.repositories.common.BaseRepository
import ru.saptan.anonym.domain.repositories.common.rest.IRestApi

abstract class ABaseRepositoryTest<Repository : BaseRepository, RestApi : IRestApi> {

    protected lateinit var repository: Repository
    protected lateinit var restApi: RestApi

    @Before
    open fun setUp() {
        MockitoAnnotations.initMocks(this)
        restApi = initRestApi()
        repository = initRepository()
    }

    protected abstract fun initRestApi(): RestApi

    protected abstract fun initRepository(): Repository
}