package ru.saptan.anonym.presentation.common.actions

interface ErrorRetryAction {

    fun getButtonText(): String

    fun getErrorMessageText(): String

    fun execute()
}