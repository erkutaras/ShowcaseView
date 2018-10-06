package com.erkutaras.showcaseview;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.view.Window;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by erkut.aras on 23.02.2018.
 */

public final class ShowcaseManager {

    private static final int INNER_MARGIN_OF_FOCUS_AREA_ON_DP = 20;

    private Context context;
    private String key;
    private final Builder builder;
    private static final String TAG = "ShowcaseManager";

    private ShowcaseManager(Builder builder) {
        this.context = builder.context;
        this.key = builder.key;
        this.builder = builder;
    }

    public void show() {
        if (hasNullParameters(builder)) {
            return;
        }

        if (!builder.isDevelopMode && ShowcaseUtils.ShowcaseSP.instance(context).getShowing(key)) {
            return;
        }

        Intent intent = new Intent(context, ShowcaseActivity.class);
        intent.putParcelableArrayListExtra(ShowcaseActivity.EXTRAS_SHOWCASES, (ArrayList<? extends Parcelable>) builder.showcaseModelList);
        intent.putExtra(ShowcaseActivity.EXTRAS_SYSTEM_UI_VISIBILITY, getSystemUiVisibility());
        context.startActivity(intent);
        ShowcaseUtils.ShowcaseSP.instance(context).show(key);
    }

    private boolean hasNullParameters(Builder builder) {
        if (builder == null) {
            Log.e(TAG, "Builder can not be null.");
            return true;
        }

        if (ShowcaseUtils.isNull(builder.context)) {
            Log.e(TAG, "Context can not be null.");
            return true;
        }
        if (ShowcaseUtils.isNull(builder.key)) {
            Log.e(TAG, "Key can not be null.");
            return true;
        }
        if (ShowcaseUtils.isNull(builder.view)) {
            Log.e(TAG, "View can not be null.");
            return true;
        }
        if (ShowcaseUtils.isNull(builder.descriptionTitle)) {
            Log.e(TAG, "Description title can not be null.");
            return true;
        }
        if (ShowcaseUtils.isNull(builder.descriptionText)) {
            Log.e(TAG, "Description text can not be null");
            return true;
        }
        return false;
    }

    private boolean getSystemUiVisibility() {
        Window window = ((Activity) context).getWindow();
        View decorView = window.getDecorView();
        return decorView.getSystemUiVisibility() == View.VISIBLE;
    }

    public static final class Builder {
        private Context context;
        private View view;
        private List<ShowcaseModel> showcaseModelList;
        private String key;
        private int descriptionImageRes;
        private String descriptionTitle;
        private String descriptionText;
        private String buttonText;
        private int colorDescTitle;
        private int colorDescText;
        private int colorButtonText;
        private int colorButtonBackground;
        private int colorBackground;
        private int alphaBackground;
        private int colorFocusArea;
        private boolean isDevelopMode;

        public Builder() {
            showcaseModelList = new ArrayList<>();
        }

        public Builder context(Context context) {
            this.context = context;
            return this;
        }

        public Builder view(View view) {
            this.view = view;
            return this;
        }

        public Builder key(String key) {
            this.key = key;
            return this;
        }

        public Builder descriptionImageRes(int descriptionImageRes) {
            this.descriptionImageRes = descriptionImageRes;
            return this;
        }

        public Builder descriptionTitle(String descriptionTitle) {
            this.descriptionTitle = descriptionTitle;
            return this;
        }

        public Builder descriptionText(String descriptionText) {
            this.descriptionText = descriptionText;
            return this;
        }

        public Builder buttonText(String buttonText) {
            this.buttonText = buttonText;
            return this;
        }

        public Builder colorDescTitle(int colorDescTitle) {
            this.colorDescTitle = colorDescTitle;
            return this;
        }

        public Builder colorDescText(int colorDescText) {
            this.colorDescText = colorDescText;
            return this;
        }

        public Builder colorButtonText(int colorButtonText) {
            this.colorButtonText = colorButtonText;
            return this;
        }

        public Builder colorButtonBackground(int colorButtonBackground) {
            this.colorButtonBackground = colorButtonBackground;
            return this;
        }

        public Builder colorBackground(int colorBackground) {
            this.colorBackground = colorBackground;
            return this;
        }

        public Builder alphaBackground(int alphaBackground) {
            this.alphaBackground = alphaBackground;
            return this;
        }

        public Builder colorFocusArea(int colorFocusArea) {
            this.colorFocusArea = colorFocusArea;
            return this;
        }

        public Builder developerMode(boolean isDeveloperMode) {
            this.isDevelopMode = isDeveloperMode;
            return this;
        }

        public Builder add() {
            this.showcaseModelList.add(createShowcaseModel());
            return this;
        }

        public ShowcaseManager build() {
            if (showcaseModelList.isEmpty()) {
                throw new IllegalStateException("add() must be invoked.");
            }
            return new ShowcaseManager(this);
        }

        private ShowcaseModel createShowcaseModel() {
            Rect viewPositionRect = new Rect();
            view.getGlobalVisibleRect(viewPositionRect);
            float circleCenterX = getCircleCenterX(viewPositionRect);
            float circleCenterY = getCircleCenterY(viewPositionRect);
            float circleCenterRadius = calculateRadius();

            return new ShowcaseModel.Builder()
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
                    .build();

        }

        private int getCircleCenterX(Rect viewPositionRect) {
            return viewPositionRect.left + (view.getWidth() / 2);
        }

        private float getCircleCenterY(Rect viewPositionRect) {
            return viewPositionRect.top
                    + (view.getHeight() / 2);
        }

        /**
         * @return finds out smallest radius of a circle that contains target view
         */
        private float calculateRadius() {
            float x = view.getWidth() / 2;
            float y = view.getHeight() / 2;
            float radius = (float) Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
            return radius + ShowcaseUtils.convertDpToPx(INNER_MARGIN_OF_FOCUS_AREA_ON_DP);
        }
    }
}
