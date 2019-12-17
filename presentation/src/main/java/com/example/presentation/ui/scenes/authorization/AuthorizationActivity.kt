package com.example.presentation.ui.scenes.authorization

import android.content.Context
import android.os.Bundle
import android.os.PersistableBundle
import com.example.presentation.R
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter

class AuthorizationActivity : MvpAppCompatActivity(R.layout.authorization_activity),
    AuthorizationView {

    @InjectPresenter
    lateinit var presenter: AuthorizationPresenter


    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        ContextOfApplication = applicationContext
    }

    companion object {
        const val TOKENS = "TOKENS"
        const val ACCESS_TOKEN = "ACCESS_TOKEN"
        const val REFRESH_TOKEN = "REFRESH_TOKEN"
        lateinit var ContextOfApplication: Context
    }
}