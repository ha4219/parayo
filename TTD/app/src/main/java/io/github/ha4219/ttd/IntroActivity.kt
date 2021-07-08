package io.github.ha4219.ttd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import io.github.ha4219.ttd.api.DongApi
import io.github.ha4219.ttd.common.Prefs
import io.github.ha4219.ttd.product.ProductMainActivity
import io.github.ha4219.ttd.signin.SigninActivity
import io.github.ha4219.ttd.signup.SignupActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.jetbrains.anko.setContentView
import org.jetbrains.anko.startActivity


class IntroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        IntroActivityUI().setContentView(this)

        GlobalScope.launch {
            delay(1000)
            if(Prefs.token.isNullOrEmpty()){
                startActivity<SigninActivity>()
            }else{
                startActivity<ProductMainActivity>()
            }

            finish()
        }
    }
}
