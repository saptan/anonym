package ru.saptan.anonym.domain.repositories.common.exceptions

import ru.saptan.anonym.domain.model.data.ErrorModel

class RepositoryException : Exception {

    override var message: String? = null
    var errorModel: ErrorModel? = null

    constructor(message: String) : super(message) {
        this.message = message
    }

    constructor(errorModel: ErrorModel) {
        this.errorModel = errorModel
        this.message = errorModel.message
    }

    constructor(cause: Throwable) : super(cause){
        message = cause.message
    }
}