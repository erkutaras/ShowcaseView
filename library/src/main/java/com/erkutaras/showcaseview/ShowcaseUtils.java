package com.erkutaras.showcaseview;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.util.TypedValue;

/**
 * Created by erkutaras on 25.02.2018.
 */

public final class ShowcaseUtils {

    public static float convertDpToPx(float dp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, Resources.getSystem().getDisplayMetrics());
    }

    public static boolean isNonNull(Object o) {
        return o != null;
    }

    public static boolean isNull(Object o) {
        return o == null;
    }

    public static boolean isNotZero(float f) {
        return f != 0;
    }

    public static final class ShowcaseSP {

        static final String SHARED_PREF_NAME = "intro";
        private static ShowcaseSP sp;

        private SharedPreferences sharedPreferences;
        private SharedPreferences.Editor editor;

        private ShowcaseSP(@NonNull Context appContext) {
            this.sharedPreferences = appContext.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
            this.editor = sharedPreferences.edit();
        }

        public static ShowcaseSP instance(@NonNull Context appContext) {
            if (isNull(sp)) sp = new ShowcaseSP(appContext);
            return sp;
        }

        public void show(String key) {
            editor.putBoolean(key, true);
            editor.commit();
        }

        public boolean getShowing(String key) {
            return sharedPreferences.getBoolean(key, false);
        }
    }
}
