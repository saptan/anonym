package ru.saptan.anonym.presentation.common

import android.content.Context
import android.util.Log
import com.arellomobile.mvp.MvpPresenter
import com.arellomobile.mvp.MvpView
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import ru.saptan.anonym.domain.repositories.common.exceptions.RepositoryException
import java.net.UnknownHostException


abstract class ABasePresenter<View : MvpView> : MvpPresenter<View>(), SimpleLogging {
    lateinit var context: Context
    private val tag: String = this.javaClass.simpleName
    private val disposables = CompositeDisposable()

    protected fun addDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }

    override fun onDestroy() {
        disposables.clear()
        super.onDestroy()
    }

    override fun detachView(view: View) {
        disposables.clear()
        super.detachView(view)
    }

    fun getErrorText(t: Throwable): String {
        logE("getErrorText", t)
        t.printStackTrace()
        if (t is RepositoryException) {
            val e = t as RepositoryException
            val errorMessage = e.errorModel?.message

            if (errorMessage != null) {
                return errorMessage
            }
        }

        if (t is UnknownHostException) {
            return "Нет доступа к Интернету"
        }

        return "Ошибка сети"
    }

    override fun logD(message: String) {
        Log.d(tag, message)
    }

    override fun logE(message: String, e: Throwable) {
        Log.e(tag, message, e)
    }

    override fun logI(message: String) {
        Log.i(tag, message)
    }

    override fun logW(message: String) {
        Log.w(tag, message)
    }
}