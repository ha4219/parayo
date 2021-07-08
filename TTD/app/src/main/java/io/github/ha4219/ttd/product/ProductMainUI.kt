package io.github.ha4219.ttd.product

import android.view.Gravity
import android.view.MenuItem.SHOW_AS_ACTION_ALWAYS
import androidx.appcompat.widget.Toolbar
import org.jetbrains.anko.appcompat.v7.toolbar
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import io.github.ha4219.ttd.R
import io.github.ha4219.ttd.view.borderBottom
import org.jetbrains.anko.*
import org.jetbrains.anko.design.navigationView
import org.jetbrains.anko.support.v4.drawerLayout


class ProductMainUI(
    private val viewModel: ProductMainViewModel
) : AnkoComponent<ProductMainActivity> {

    lateinit var navigationView: NavigationView
    lateinit var toolBar: Toolbar
    lateinit var drawerLayout: DrawerLayout

    override fun createView(ui: AnkoContext<ProductMainActivity>) =
        ui.drawerLayout {
            drawerLayout = this
            verticalLayout {
                toolBar = toolbar{
                    title = "Search"
                    bottomPadding = dip(1)
                    background = borderBottom(width=dip(1))
                    menu.add("Search")
                        .setIcon(R.drawable.magnify)
                        .setShowAsAction(SHOW_AS_ACTION_ALWAYS)
                }.lparams(matchParent, wrapContent)
            }.lparams(matchParent, matchParent)


            navigationView = navigationView {
            }.lparams(wrapContent, matchParent) {
                gravity = Gravity.START
            }
        }
}