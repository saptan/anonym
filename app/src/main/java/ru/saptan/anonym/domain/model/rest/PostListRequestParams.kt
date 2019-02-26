package ru.saptan.anonym.domain.model.rest

import ru.saptan.anonym.app.Const

data class PostListRequestParams(var type: Int = POPULAR, var offset: Int = 0,
                                 var count: Int = Const.MAX_COUNT_POST_IN_REQUEST,
                                 @Transient var sourceData: Int = Const.TYPE_SOURCE_ALL) {


    companion object TypePost {
        const val NEW = 1
        const val POPULAR = 2 // по умолчанию всегда грузятся посты, набирающие попуряность
    }


    /**
     * Указать конкретный источник загружаемых данных, по умолчания грузится со всех (сперва отображаются
     * данные ез кэша, а потом подгружаются данные с сервера). Но при загрузке следующей страницы постов
     * или событии onRefresh необходимо подгружать данные только с сервера
     */
    fun withDataSource(source: Int) = apply { sourceData = source }

    /**
     * Подготовить параметры запроса для загрузки первой страницы постов
     */
    fun prepareFirstPage() = apply { offset = 0 }

    /**
     * Подготовить параметры запроса для загрузки следующей страницы постов
     */
    fun prepareNextPage() = { offset += count }


    fun isFirstPage() = offset == 0
}