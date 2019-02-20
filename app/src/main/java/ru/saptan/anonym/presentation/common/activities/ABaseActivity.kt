package ru.saptan.anonym.presentation.common.activities

import android.content.Context
import android.os.Bundle
import android.support.annotation.StringRes
import android.support.design.widget.Snackbar
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import com.arellomobile.mvp.MvpAppCompatActivity
import ru.saptan.anonym.presentation.common.Layout
import ru.saptan.anonym.presentation.common.actions.Action
import ru.saptan.anonym.presentation.common.actions.NetworkErrorActionView


abstract class ABaseActivity : MvpAppCompatActivity() {

    val tag: String = this.javaClass.simpleName
    private var networkErrorView: NetworkErrorActionView? = null


    private var toolbar: Toolbar? = null
    private var toolbarTitle: TextView? = null
    private var loadingView: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        inject()
        super.onCreate(savedInstanceState)
        val layout = javaClass.getAnnotation(Layout::class.java) as Layout
        setContentView(layout.id)
        initViews()
        initAnimations()
        initListeners()
    }

    abstract fun inject()

    protected open fun initViews() {
        toolbar = findViewById(R.id.toolbar)
        if (toolbar != null)
            setSupportActionBar(toolbar)

        toolbarTitle = findViewById(R.id.tvTitleToolbar)
        networkErrorView = findViewById(R.id.networkErrorView)
        loadingView = findViewById(R.id.flLoader)
    }

    protected open fun initAnimations() {}

    protected open fun initListeners() {}

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }

    open fun showLoadingProgress(show: Boolean) {
        loadingView?.let {
            if (show)
                it.visibility = View.VISIBLE
            else
                it.visibility = View.GONE

        }
    }

    fun showToast(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    open fun showToast(@StringRes messageId: Int) {
        Toast.makeText(this, messageId, Toast.LENGTH_SHORT).show()
    }

    fun showSnack(message: String?, action: Action) {
        showSnackMessage(message, action)
    }

    fun showSnack(messageId: Int, action: Action) {
        showSnackMessage(getString(messageId), action)
    }

    private fun showSnackMessage(message: String?, snackAction: Action) {

        val view = (this.findViewById<View>(android.R.id.content) as ViewGroup).getChildAt(0)

        val text = message ?: getString(R.string.message_error_while_loading)

        Snackbar.make(view, text, Snackbar.LENGTH_SHORT)
                .setDuration(3000)
                .setAction(snackAction.getButtonText(), { snackAction.execute() })
                .show()
    }

    fun hideKeyboard() {
        val v = currentFocus ?: return
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
        imm?.hideSoftInputFromWindow(v.windowToken, 0)
    }

    fun showKeyBoard() {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
        imm?.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, 0)
    }

    fun showBackArrowInToolBar(show: Boolean) {
        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(show)
            actionBar.setDisplayShowHomeEnabled(show)
        }
    }

    fun showCloseInToolBar(show: Boolean) {
        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(show)
            actionBar.setHomeAsUpIndicator(R.drawable.ic_close)
        }
    }

    fun showLogoInToolBar(show: Boolean) {
        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(show)
            actionBar.setHomeAsUpIndicator(R.drawable.ic_logo_toolbar)
        }
    }

    fun showErrorActionView(message: String?, action: () -> Unit) {
        networkErrorView?.let {
            it.visibility = View.VISIBLE
            it.bind(message, action)
        }
    }

    fun hideErrorActionView() {
        networkErrorView?.let {
            it.visibility = View.GONE
        }
    }

    fun setTitleToolbar(titleId: Int) {
        val title = getString(titleId)
        setTitleToolbar(title)
    }

    fun setTitleToolbar(title: String) {
        val toolbar = supportActionBar
        if (toolbar != null) {
            toolbar.title = null
        }

        toolbarTitle?.let {
            it.text = title
        }
    }


}