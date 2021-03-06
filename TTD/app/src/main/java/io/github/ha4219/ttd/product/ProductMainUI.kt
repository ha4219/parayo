package io.github.ha4219.ttd.product

import android.view.Gravity
import android.view.Menu.NONE
import android.view.MenuItem
import android.view.MenuItem.SHOW_AS_ACTION_ALWAYS
import androidx.appcompat.widget.Toolbar
import org.jetbrains.anko.appcompat.v7.toolbar
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import io.github.ha4219.ttd.R
import io.github.ha4219.ttd.common.Prefs
import io.github.ha4219.ttd.signin.SigninActivity
import io.github.ha4219.ttd.view.borderBottom
import org.jetbrains.anko.*
import org.jetbrains.anko.design.floatingActionButton
import org.jetbrains.anko.design.navigationView
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.support.v4.drawerLayout


class ProductMainUI(
    private val viewModel: ProductMainViewModel
) : AnkoComponent<ProductMainActivity>,
    NavigationView.OnNavigationItemSelectedListener{

    lateinit var navigationView: NavigationView
    lateinit var toolBar: Toolbar
    lateinit var drawerLayout: DrawerLayout

    override fun createView(ui: AnkoContext<ProductMainActivity>) =
        ui.drawerLayout {
            drawerLayout = this

            frameLayout {
                verticalLayout {
                    toolBar = toolbar{
                        title = "Search"
                        bottomPadding = dip(1)
                        background = borderBottom(width=dip(1))
                        menu.add("Search")
                            .setIcon(R.drawable.magnify)
                            .setShowAsAction(SHOW_AS_ACTION_ALWAYS)
                    }.lparams(matchParent, wrapContent)

                    floatingActionButton {
                        imageResource = R.drawable.ic_add
                        onClick {viewModel.openRegistrationActivity()}
                    }.lparams {
                        bottomMargin = dip(20)
                        marginEnd = dip(20)
                        gravity = Gravity.END or Gravity.BOTTOM
                    }
                }.lparams(matchParent, matchParent)
            }



            navigationView = navigationView {
                ProductMainNavHeader()
                    .createView(AnkoContext.create(context, this))
                    .run(::addHeaderView)

                menu.apply {
                    add(NONE,MENU_ID_INQUIRY,NONE, "??? ??????").apply {
                        setIcon(R.drawable.ic_chat)
                    }
                    add(NONE,MENU_ID_LOGOUT,NONE, "????????????").apply {
                        setIcon(R.drawable.ic_logout)
                    }

                    setNavigationItemSelectedListener(this@ProductMainUI)
                }
            }.lparams(wrapContent, matchParent) {
                gravity = Gravity.START
            }
        }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            MENU_ID_INQUIRY->{viewModel.toast("??? ??????")}
            MENU_ID_LOGOUT->{
                Prefs.token = null
                Prefs.refreshToken = null
                viewModel.startActivityAndFinish<SigninActivity>()
            }
        }

        drawerLayout.closeDrawer(navigationView)

        return true
    }

    companion object{
        private const val MENU_ID_INQUIRY = 1
        private const val MENU_ID_LOGOUT = 2
    }
}