package io.github.ha4219.ttd

import android.graphics.Typeface
import android.view.Gravity
import android.view.View
import org.jetbrains.anko.*


class IntroActivityUI : AnkoComponent<IntroActivity>{
    override fun createView(ui: AnkoContext<IntroActivity>): View {
        return ui.relativeLayout {
            gravity = Gravity.CENTER

            textView("Donglog") {
                textSize = 24f
                textColorResource = R.color.colorPrimary
                typeface = Typeface.DEFAULT_BOLD
            }
        }
    }
}