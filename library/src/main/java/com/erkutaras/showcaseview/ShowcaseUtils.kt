package com.erkutaras.showcaseview

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.content.res.Resources
import android.util.TypedValue

/**
 * Created by erkutaras on 25.02.2018.
 */

class ShowcaseUtils {

    companion object {

        @JvmStatic
        fun convertDpToPx(dp: Float): Float {
            return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, Resources.getSystem().displayMetrics)
        }

        @JvmStatic
        fun isNonNull(o: Any?): Boolean {
            return o != null
        }

        @JvmStatic
        fun isNull(o: Any?): Boolean {
            return o == null
        }

        @JvmStatic
        fun isNotZero(f: Float): Boolean {
            return f != 0f
        }
    }

    internal class ShowcaseSP @SuppressLint("CommitPrefEdits")
    private constructor(appContext: Context) {

        private val SHARED_PREF_NAME = "intro"

        private val sharedPreferences: SharedPreferences
        private val editor: SharedPreferences.Editor

        private val sp by lazy { ShowcaseSP(appContext) }

        init {
            this.sharedPreferences = appContext.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
            this.editor = sharedPreferences.edit()
        }

        fun show(key: String) {
            editor.putBoolean(key, true)
            editor.commit()
        }

        fun getShowing(key: String): Boolean {
            return sharedPreferences.getBoolean(key, false)
        }

        companion object {

            @JvmStatic
            fun instance(appContext: Context): ShowcaseSP {
                return ShowcaseSP(appContext).sp
            }
        }
    }
}
