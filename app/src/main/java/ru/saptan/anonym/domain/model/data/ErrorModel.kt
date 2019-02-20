package ru.saptan.anonym.domain.model.data

data class ErrorModel(val error: Boolean = true, val code: Int = 401, val message: String? = null)