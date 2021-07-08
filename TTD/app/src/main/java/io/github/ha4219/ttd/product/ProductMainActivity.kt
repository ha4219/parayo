package io.github.ha4219.ttd.product


import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.lifecycle.ViewModel
import io.github.ha4219.ttd.R
import net.codephobia.ankomvvm.components.BaseActivity
import org.jetbrains.anko.setContentView

class ProductMainActivity:
    BaseActivity<ProductMainViewModel>(){

    private val ui by lazy {ProductMainUI(getViewModel())}

    override val viewModelType = ProductMainViewModel::class

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ui.setContentView(this)
        setupDrawerListener()
    }

    private fun setupDrawerListener() {
        val toggle = ActionBarDrawerToggle(
            this,
            ui.drawerLayout,
            ui.toolBar,
            R.string.open_drawer_description,
            R.string.close_drawer_description
        )
        ui.drawerLayout.addDrawerListener(toggle)

        toggle.syncState()
    }
}