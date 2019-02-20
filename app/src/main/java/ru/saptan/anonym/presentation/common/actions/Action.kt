package ru.saptan.anonym.presentation.common.actions

interface Action {
    fun getButtonText(): String

    fun execute()
}