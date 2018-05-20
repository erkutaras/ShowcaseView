package com.erkutaras.showcaseview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RadialGradient;
import android.graphics.RectF;
import android.graphics.Shader;
import android.os.Build;
import android.support.annotation.DrawableRes;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by erkut.aras on 23.02.2018.
 */
public class ShowcaseView extends RelativeLayout {

    public static final int FOCUS_AREA_TOP_MARGIN_IN_DP = 50;
    public static final int FOCUS_AREA_BOTTOM_MARGIN_IN_DP = 20;

    private Bitmap bitmap;
    private View descriptionView;
    private OnClickListener onClickListener;
    // updatable fields for descriptionView
    private @DrawableRes int descriptionImageRes;
    private String descriptionTitle;
    private String descriptionText;
    private String buttonText;
    private int colorDescTitle;
    private int colorDescText;
    private int colorButtonText;
    private int colorButtonBackground;
    // updatable fields for focused area
    private int colorBackground;
    private int alphaBackground;
    private int colorFocusArea;
    // can not be updated fields for focused area
    private float radiusFocusArea;
    private int xDescView;
    private int yDescView;
    private float marginInDp;
    private float cxFocusArea;
    private float cyFocusArea;

    public ShowcaseView(Context context) {
        super(context);
        init(context);
    }

    public ShowcaseView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ShowcaseView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public ShowcaseView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    public void init(Context context) {
        descriptionView = inflate(context, R.layout.layout_intro_description, null);
        addView(descriptionView);

        // default values
        colorBackground = Color.BLACK;
        alphaBackground = 204;
        colorFocusArea = Color.TRANSPARENT;
        radiusFocusArea = 150.0f;
        xDescView = 0;
        yDescView = 0;
        marginInDp = 20;
    }

    public void updateView(ShowcaseModel showcaseModel) {
        descriptionImageRes = showcaseModel.getDescriptionImageRes();
        descriptionTitle = showcaseModel.getDescriptionTitle();
        descriptionText = showcaseModel.getDescriptionText();
        buttonText = showcaseModel.getButtonText();
        colorDescTitle = showcaseModel.getColorDescTitle();
        colorDescText = showcaseModel.getColorDescText();
        colorButtonText = showcaseModel.getColorButtonText();
        colorButtonBackground = showcaseModel.getColorButtonBackground();

        // update descriptionView
        ImageView imageView = descriptionView.findViewById(R.id.imageView_description);
        if (ShowcaseUtils.isNotZero(descriptionImageRes)) {
            imageView.setImageResource(descriptionImageRes);
            imageView.setVisibility(VISIBLE);
        } else {
            imageView.setVisibility(GONE);
        }

        TextView textViewTitle = descriptionView.findViewById(R.id.textView_description_title);
        textViewTitle.setText(descriptionTitle);
        if (ShowcaseUtils.isNotZero(colorDescTitle)) {
            textViewTitle.setTextColor(colorDescTitle);
        }

        TextView textViewText = descriptionView.findViewById(R.id.textView_description);
        textViewText.setText(descriptionText);
        if (ShowcaseUtils.isNotZero(colorDescText)) {
            textViewText.setTextColor(colorDescText);
        }

        Button button = descriptionView.findViewById(R.id.button_done);
        if (ShowcaseUtils.isNonNull(buttonText)&& !buttonText.isEmpty()) {
            button.setText(buttonText);
        }
        if (ShowcaseUtils.isNotZero(colorButtonText)) {
            button.setTextColor(colorButtonText);
        }
        if (ShowcaseUtils.isNotZero(colorButtonBackground)) {
            button.setBackgroundColor(colorButtonText);
        }
        if (ShowcaseUtils.isNonNull(onClickListener)) {
            button.setOnClickListener(onClickListener);
        }

        // update custom view
        if (ShowcaseUtils.isNotZero(showcaseModel.getColorBackground())) {
            colorBackground = showcaseModel.getColorBackground();
        }
        if (ShowcaseUtils.isNotZero(showcaseModel.getAlphaBackground())) {
            alphaBackground = showcaseModel.getAlphaBackground();
        }
        if (ShowcaseUtils.isNotZero(showcaseModel.getColorFocusArea())) {
            colorFocusArea = showcaseModel.getColorFocusArea();
        }
        cxFocusArea = showcaseModel.getCxFocusArea();
        cyFocusArea = showcaseModel.getCyFocusArea();
        radiusFocusArea = showcaseModel.getRadiusFocusArea();
    }

    @Override
    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        if (cxFocusArea == 0 || cyFocusArea == 0) {
            cxFocusArea = radiusFocusArea + ShowcaseUtils.convertDpToPx(marginInDp);
            cyFocusArea = descriptionView.getY() + descriptionView.getHeight() + radiusFocusArea
                    + ShowcaseUtils.convertDpToPx(marginInDp);
        }

        // background
        RectF rectF = new RectF(0, 0, getWidth(), getHeight());
        bitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(colorBackground);
        paint.setAlpha(alphaBackground);
        canvas.drawRect(rectF, paint);
        // focus area
        paint.setColor(colorFocusArea);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        canvas.drawCircle(cxFocusArea, cyFocusArea, radiusFocusArea, paint);
        // shadow for focus area
        Paint shadowPaint = new Paint();
        shadowPaint.setColor(colorBackground);
        shadowPaint.setAlpha(alphaBackground);
        shadowPaint.setStrokeWidth(1.0f);
        shadowPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        shadowPaint.setShader(new RadialGradient(cxFocusArea, cyFocusArea, radiusFocusArea,
                colorFocusArea, shadowPaint.getColor(), Shader.TileMode.CLAMP));
        canvas.drawCircle(cxFocusArea, cyFocusArea, radiusFocusArea, shadowPaint);
        // draw all views and update location of descriptionView
        canvas.drawBitmap(bitmap, 0, 0, null);
        // descriptionView relocate related to focusArea
        float topMarginFocusArea = ShowcaseUtils.convertDpToPx(FOCUS_AREA_TOP_MARGIN_IN_DP);
        float bottomMarginFocusArea = ShowcaseUtils.convertDpToPx(FOCUS_AREA_BOTTOM_MARGIN_IN_DP);
        float topFocusArea = cyFocusArea - radiusFocusArea;
        float bottomFocusArea = cyFocusArea + radiusFocusArea;
        if (topFocusArea >= descriptionView.getHeight() + topMarginFocusArea) {
            yDescView = (int) (topFocusArea - topMarginFocusArea - descriptionView.getHeight());
        } else {
            yDescView = (int) (bottomFocusArea + bottomMarginFocusArea);
        }
        descriptionView.setX(xDescView);
        descriptionView.setY(yDescView);
        super.dispatchDraw(canvas);
    }

}
