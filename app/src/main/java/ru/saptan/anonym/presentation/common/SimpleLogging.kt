package ru.saptan.anonym.presentation.common

interface SimpleLogging {
    /**
     * Сокращенные методы для логгирования
     * ERROR
     */
    fun logE(message: String, e: Throwable)

    /**
     * INFO
     */
    fun logI(message: String)

    /**
     * DEBUG
     */
    fun logD(message: String)

    /**
     * WARNING
     */
    fun logW(message: String)
}