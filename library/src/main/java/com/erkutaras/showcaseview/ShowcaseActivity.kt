package com.erkutaras.showcaseview

import android.app.Activity
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

/**
 * Created by erkut.aras on 23.02.2018.
 */

class ShowcaseActivity : AppCompatActivity() {

    private var currentIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val extras = intent.extras ?: throw NullPointerException("Extras can not be null. " +
            "To pass the extras, you need to pass mandatory parameters to ShowcaseManager.")
        val showcaseModels = extras.getParcelableArrayList<ShowcaseModel>(EXTRAS_SHOWCASES)
        if (showcaseModels == null) {
            finish()
            return
        }
        val layout = ShowcaseView(this)
        layout.setOnClickListener {
            currentIndex += 1
            if (currentIndex < showcaseModels.size) {
                layout.updateView(showcaseModels[currentIndex])
            } else {
                setResult(Activity.RESULT_OK)
                finish()
            }
        }
        layout.updateView(showcaseModels[currentIndex])
        setContentView(layout)
    }

    override fun onResume() {
        super.onResume()
        val extras = intent.extras
        if (isSystemUIVisibilityFalse(extras)) {
            val decorView = window.decorView
            var uiOptions = 0
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN
            }
            decorView.systemUiVisibility = uiOptions
        }
    }

    private fun isSystemUIVisibilityFalse(extras: Bundle?) =
        extras == null || extras.getBoolean(EXTRAS_SYSTEM_UI_VISIBILITY, false).not()

    companion object {
        const val EXTRAS_SHOWCASES = "EXTRAS_SHOWCASES"
        const val EXTRAS_SYSTEM_UI_VISIBILITY = "EXTRAS_SYSTEM_UI_VISIBILITY"
    }
}
