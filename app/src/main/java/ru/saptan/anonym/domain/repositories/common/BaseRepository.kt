package ru.saptan.anonym.domain.repositories.common

import android.text.TextUtils
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import ru.saptan.anonym.app.ComponentProvider
import ru.saptan.anonym.domain.model.data.ErrorModel
import ru.saptan.anonym.domain.repositories.common.exceptions.RepositoryException
import java.io.IOException

open class BaseRepository  {

    fun <T> createErrorObservable(throwable: Throwable): Observable<T> {
        return Observable.error(parseException(throwable))
    }

    fun createErrorCompletable(throwable: Throwable): Completable {
        return Completable.error(parseException(throwable))
    }

    fun <T> createErrorSingle(throwable: Throwable): Single<T> {
        return Single.error(parseException(throwable))
    }

    open fun parseException(exception: Throwable): RepositoryException {
        exception.printStackTrace()
        if (exception is HttpException) {
            return try {
                var json = exception.response().errorBody()?.string()
                if (TextUtils.isEmpty(json))
                    json = "{}"
                val e = ComponentProvider.provideGson().fromJson(json, ErrorModel::class.java)
                RepositoryException(e)
            } catch (e1: IOException) {
                e1.printStackTrace()
                RepositoryException(exception)
            }
        }
        return RepositoryException(exception)
    }
}
