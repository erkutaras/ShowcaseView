package com.erkutaras.showcaseview

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Rect
import android.os.Parcelable
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.view.Window

import java.util.ArrayList

/**
 * Created by erkut.aras on 23.02.2018.
 */

class ShowcaseManager private constructor(private val builder: Builder) {

    private val context: Context = builder.context
    private val key: String = builder.key

    fun show() {
        if (hasNullParameters(builder)) {
            return
        }

        if (!builder.isDevelopMode && ShowcaseUtils.ShowcaseSP.instance(context).getShowing(key)) {
            return
        }

        val intent = Intent(context, ShowcaseActivity::class.java)
        intent.putParcelableArrayListExtra(ShowcaseActivity.EXTRAS_SHOWCASES, builder.showcaseModelList as ArrayList<out Parcelable>)
        intent.putExtra(ShowcaseActivity.EXTRAS_SYSTEM_UI_VISIBILITY, getSystemUiVisibility())
        (context as Activity).startActivityForResult(intent, REQUEST_CODE_SHOWCASE)
        ShowcaseUtils.ShowcaseSP.instance(context).show(key)
    }

    private fun hasNullParameters(builder: Builder?): Boolean {
        if (builder == null) {
            Log.e(TAG, "Builder can not be null.")
            return true
        }

        if (ShowcaseUtils.isNull(builder.context)) {
            Log.e(TAG, "Context can not be null.")
            return true
        }

        if (builder.context !is Activity) {
            Log.e(TAG, "Context must be instance of Activity.")
            return true
        }

        if (ShowcaseUtils.isNull(builder.key)) {
            Log.e(TAG, "Key can not be null.")
            return true
        }
        if (ShowcaseUtils.isNull(builder.view)) {
            Log.e(TAG, "View can not be null.")
            return true
        }
        if (ShowcaseUtils.isNull(builder.descriptionTitle)) {
            Log.e(TAG, "Description title can not be null.")
            return true
        }
        if (ShowcaseUtils.isNull(builder.descriptionText)) {
            Log.e(TAG, "Description text can not be null")
            return true
        }
        return false
    }

    private fun getSystemUiVisibility(): Boolean {
        val window = (context as AppCompatActivity).window
        val decorView = window.decorView
        return decorView.systemUiVisibility == View.VISIBLE
    }

    class Builder {
        internal lateinit var context: Context
        internal lateinit var view: View
        internal val showcaseModelList: MutableList<ShowcaseModel>
        internal lateinit var key: String
        private var descriptionImageRes: Int = 0
        internal lateinit var descriptionTitle: String
        internal lateinit var descriptionText: String
        private lateinit var buttonText: String
        private var colorDescTitle: Int = 0
        private var colorDescText: Int = 0
        private var colorButtonText: Int = 0
        private var colorButtonBackground: Int = 0
        private var colorBackground: Int = 0
        private var alphaBackground: Int = 0
        private var colorFocusArea: Int = 0
        internal var isDevelopMode: Boolean = false
        private var marginFocusArea: Int = 0

        init {
            showcaseModelList = ArrayList()
        }

        fun context(context: Context): Builder {
            this.context = context
            return this
        }

        fun view(view: View): Builder {
            this.view = view
            return this
        }

        fun key(key: String): Builder {
            this.key = key
            return this
        }

        fun descriptionImageRes(descriptionImageRes: Int): Builder {
            this.descriptionImageRes = descriptionImageRes
            return this
        }

        fun descriptionTitle(descriptionTitle: String): Builder {
            this.descriptionTitle = descriptionTitle
            return this
        }

        fun descriptionText(descriptionText: String): Builder {
            this.descriptionText = descriptionText
            return this
        }

        fun buttonText(buttonText: String): Builder {
            this.buttonText = buttonText
            return this
        }

        fun colorDescTitle(colorDescTitle: Int): Builder {
            this.colorDescTitle = colorDescTitle
            return this
        }

        fun colorDescText(colorDescText: Int): Builder {
            this.colorDescText = colorDescText
            return this
        }

        fun colorButtonText(colorButtonText: Int): Builder {
            this.colorButtonText = colorButtonText
            return this
        }

        fun colorButtonBackground(colorButtonBackground: Int): Builder {
            this.colorButtonBackground = colorButtonBackground
            return this
        }

        fun colorBackground(colorBackground: Int): Builder {
            this.colorBackground = colorBackground
            return this
        }

        fun alphaBackground(alphaBackground: Int): Builder {
            this.alphaBackground = alphaBackground
            return this
        }

        fun colorFocusArea(colorFocusArea: Int): Builder {
            this.colorFocusArea = colorFocusArea
            return this
        }

        fun developerMode(isDeveloperMode: Boolean): Builder {
            this.isDevelopMode = isDeveloperMode
            return this
        }

        fun marginFocusArea(marginFocusArea: Int): Builder {
            this.marginFocusArea = marginFocusArea
            return this
        }

        fun add(): Builder {
            this.showcaseModelList.add(createShowcaseModel())
            return this
        }

        fun build(): ShowcaseManager {
            if (showcaseModelList.isEmpty()) {
                throw IllegalStateException("add() must be invoked.")
            }
            return ShowcaseManager(this)
        }

        private fun createShowcaseModel(): ShowcaseModel {
            val viewPositionRect = Rect()
            view.getGlobalVisibleRect(viewPositionRect)
            val circleCenterX = getCircleCenterX(viewPositionRect).toFloat()
            val circleCenterY = getCircleCenterY(viewPositionRect)
            val circleCenterRadius = calculateRadius(marginFocusArea)

            return ShowcaseModel.Builder()
                .descriptionImageRes(descriptionImageRes)
                .descriptionTitle(descriptionTitle)
                .descriptionText(descriptionText)
                .buttonText(buttonText)
                .colorDescTitle(colorDescTitle)
                .colorDescText(colorDescText)
                .colorButtonText(colorButtonText)
                .colorButtonBackground(colorButtonBackground)
                .colorBackground(colorBackground)
                .alphaBackground(alphaBackground)
                .colorFocusArea(colorFocusArea)
                .cxFocusArea(circleCenterX)
                .cyFocusArea(circleCenterY)
                .radiusFocusArea(circleCenterRadius)
                .build()

        }

        private fun getCircleCenterX(viewPositionRect: Rect): Int {
            return viewPositionRect.left + view.width / 2
        }

        private fun getCircleCenterY(viewPositionRect: Rect): Float {
            return (viewPositionRect.top + view.height / 2).toFloat()
        }

        /**
         * @return finds out smallest radius of a circle that contains target view
         */
        private fun calculateRadius(marginFocusArea: Int): Float {
            val x = (view.width / 2).toFloat()
            val y = (view.height / 2).toFloat()
            val radius = Math.sqrt(Math.pow(x.toDouble(), 2.0) + Math.pow(y.toDouble(), 2.0)).toFloat()
            return radius + ShowcaseUtils.convertDpToPx(marginFocusArea.toFloat())
        }
    }

    companion object {

        @JvmField
        val REQUEST_CODE_SHOWCASE = 7032
        private val TAG = "ShowcaseManager"
    }
}
