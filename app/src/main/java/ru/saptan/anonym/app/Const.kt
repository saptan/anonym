package ru.saptan.anonym.app

object Const {
    const val CONNECTION_TIMEOUT: Long = 10

    const val MAX_COUNT_POST_IN_REQUEST = 20

    // Доступные источники данных
    const val TYPE_SOURCE_ALL = 0
    const val TYPE_SOURCE_CACHE = 1
    const val TYPE_SOURCE_NETWORK = 2
}