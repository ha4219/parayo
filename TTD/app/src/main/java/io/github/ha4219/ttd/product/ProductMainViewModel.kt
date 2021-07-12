package io.github.ha4219.ttd.product

import android.app.Application
import android.content.Intent
import io.github.ha4219.ttd.product.registration.ProductRegistrationActivity
import net.codephobia.ankomvvm.lifecycle.BaseViewModel


class ProductMainViewModel(app: Application): BaseViewModel(app) {

    fun openRegistrationActivity(){
        toast("openRegistrationActivity")
        startActivity<ProductRegistrationActivity> {
            flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
        }
    }
}