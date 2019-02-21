package ru.saptan.anonym.domain.model.rest

import ru.saptan.anonym.app.Const

data class PostListRequest(var type: Int = TYPE_POST_NEW, var offset: Int = 0,
                           var count: Int = Const.MAX_COUNT_POST_IN_REQUEST) {

    companion object {
        const val TYPE_POST_NEW = 1
        const val TYPE_POST_POPULAR = 2
    }

    /**
     * Сдвигает значение переменной @offset на значение, равное @count чтобы запросить новую порцию
     * постов
     */
    fun incrementOffset() {
        offset += count
    }
}