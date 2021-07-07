package io.github.ha4219.ttd.signin


import android.app.Application
import androidx.lifecycle.MutableLiveData
import net.codephobia.ankomvvm.lifecycle.BaseViewModel


class SigninViewModel(app:Application) : BaseViewModel(app) {
    val email = MutableLiveData("")
    val password = MutableLiveData("")
}