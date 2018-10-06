package com.erkutaras.showcaseview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.util.TypedValue;

/**
 * Created by erkutaras on 25.02.2018.
 */

final class ShowcaseUtils {

    static float convertDpToPx(float dp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, Resources.getSystem().getDisplayMetrics());
    }

    static boolean isNonNull(Object o) {
        return o != null;
    }

    static boolean isNull(Object o) {
        return o == null;
    }

    static boolean isNotZero(float f) {
        return f != 0;
    }

    static final class ShowcaseSP {

        static final String SHARED_PREF_NAME = "intro";
        private static ShowcaseSP sp;

        private SharedPreferences sharedPreferences;
        private SharedPreferences.Editor editor;

        @SuppressLint("CommitPrefEdits")
        private ShowcaseSP(@NonNull Context appContext) {
            this.sharedPreferences = appContext.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
            this.editor = sharedPreferences.edit();
        }

        static ShowcaseSP instance(@NonNull Context appContext) {
            if (isNull(sp)) sp = new ShowcaseSP(appContext);
            return sp;
        }

        void show(String key) {
            editor.putBoolean(key, true);
            editor.commit();
        }

        boolean getShowing(String key) {
            return sharedPreferences.getBoolean(key, false);
        }
    }
}
