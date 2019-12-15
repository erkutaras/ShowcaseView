package com.erkutaras.showcaseview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.graphics.RadialGradient
import android.graphics.RectF
import android.graphics.Shader
import android.os.Build
import androidx.annotation.RequiresApi
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView

/**
 * Created by erkut.aras on 23.02.2018.
 */
open class ShowcaseView : RelativeLayout {

    private val FOCUS_AREA_TOP_MARGIN_IN_DP = 50
    private val FOCUS_AREA_BOTTOM_MARGIN_IN_DP = 20

    private val descriptionView: View =
        View.inflate(context, R.layout.layout_intro_description, null)
    private var onClickListener: OnClickListener? = null
    // updatable fields for focused area
    private var colorBackground: Int = 0
    private var alphaBackground: Int = 0
    private var colorFocusArea: Int = 0
    // can not be updated fields for focused area
    private var radiusFocusArea: Float = 0.toFloat()
    private var xDescView: Int = 0
    private var yDescView: Int = 0
    private var marginInDp: Float = 0.toFloat()
    private var cxFocusArea: Float = 0.toFloat()
    private var cyFocusArea: Float = 0.toFloat()

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    private fun init() {
        addView(descriptionView)

        // default values
        colorBackground = Color.BLACK
        alphaBackground = 204
        colorFocusArea = Color.TRANSPARENT
        radiusFocusArea = 150.0f
        xDescView = 0
        yDescView = 0
        marginInDp = 20f
    }

    @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
    fun updateView(showcaseModel: ShowcaseModel) {
        // updatable fields for descriptionView
        val descriptionImageRes = showcaseModel.descriptionImageRes
        val descriptionTitle = showcaseModel.descriptionTitle
        val descriptionText = showcaseModel.descriptionText
        val buttonText = showcaseModel.buttonText
        val colorDescTitle = showcaseModel.colorDescTitle
        val colorDescText = showcaseModel.colorDescText
        val colorButtonText = showcaseModel.colorButtonText
        val colorButtonBackground = showcaseModel.colorButtonBackground

        // update descriptionView
        val imageView = descriptionView.findViewById<ImageView>(R.id.imageView_description)
        if (ShowcaseUtils.isNotZero(descriptionImageRes.toFloat())) {
            imageView.setImageResource(descriptionImageRes)
            imageView.visibility = View.VISIBLE
        } else {
            imageView.visibility = View.GONE
        }

        val textViewTitle = descriptionView.findViewById<TextView>(R.id.textView_description_title)
        textViewTitle.text = descriptionTitle
        if (ShowcaseUtils.isNotZero(colorDescTitle.toFloat())) {
            textViewTitle.setTextColor(colorDescTitle)
        }

        val textViewText = descriptionView.findViewById<TextView>(R.id.textView_description)
        textViewText.text = descriptionText
        if (ShowcaseUtils.isNotZero(colorDescText.toFloat())) {
            textViewText.setTextColor(colorDescText)
        }

        val button = descriptionView.findViewById<Button>(R.id.button_done)
        if (buttonText?.isNotEmpty() == true) {
            button.text = buttonText
        }
        if (ShowcaseUtils.isNotZero(colorButtonText.toFloat())) {
            button.setTextColor(colorButtonText)
        }
        if (ShowcaseUtils.isNotZero(colorButtonBackground.toFloat())) {
            button.setBackgroundColor(colorButtonBackground)
        }
        if (ShowcaseUtils.isNonNull(onClickListener)) {
            button.setOnClickListener(onClickListener)
        }

        // update custom view
        if (ShowcaseUtils.isNotZero(showcaseModel.colorBackground.toFloat())) {
            colorBackground = showcaseModel.colorBackground
        }
        if (ShowcaseUtils.isNotZero(showcaseModel.alphaBackground.toFloat())) {
            alphaBackground = showcaseModel.alphaBackground
        }
        if (ShowcaseUtils.isNotZero(showcaseModel.colorFocusArea.toFloat())) {
            colorFocusArea = showcaseModel.colorFocusArea
        }
        cxFocusArea = showcaseModel.cxFocusArea
        cyFocusArea = showcaseModel.cyFocusArea
        radiusFocusArea = showcaseModel.radiusFocusArea
    }

    override fun setOnClickListener(onClickListener: OnClickListener?) {
        this.onClickListener = onClickListener
    }

    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    override fun dispatchDraw(canvas: Canvas) {
        if (radiusFocusArea <= 0) {
            Log.d(TAG, "radius must be > 0. Use ShowcaseManager after view inflation.")
            return
        }
        if (cxFocusArea == 0f || cyFocusArea == 0f) {
            cxFocusArea = radiusFocusArea + ShowcaseUtils.convertDpToPx(marginInDp)
            cyFocusArea = (descriptionView.y + descriptionView.height.toFloat() + radiusFocusArea
                + ShowcaseUtils.convertDpToPx(marginInDp))
        }

        // background
        val rectF = RectF(0f, 0f, width.toFloat(), height.toFloat())
        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
        paint.color = colorBackground
        paint.alpha = alphaBackground
        canvas.drawRect(rectF, paint)
        // focus area
        paint.color = colorFocusArea
        paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR)
        canvas.drawCircle(cxFocusArea, cyFocusArea, radiusFocusArea, paint)
        // shadow for focus area
        val shadowPaint = Paint()
        shadowPaint.color = colorBackground
        shadowPaint.alpha = alphaBackground
        shadowPaint.strokeWidth = 1.0f
        shadowPaint.style = Paint.Style.FILL_AND_STROKE
        shadowPaint.shader = RadialGradient(cxFocusArea, cyFocusArea, radiusFocusArea,
            colorFocusArea, shadowPaint.color, Shader.TileMode.CLAMP)
        canvas.drawCircle(cxFocusArea, cyFocusArea, radiusFocusArea, shadowPaint)
        // descriptionView relocate related to focusArea
        val topMarginFocusArea = ShowcaseUtils.convertDpToPx(FOCUS_AREA_TOP_MARGIN_IN_DP.toFloat())
        val bottomMarginFocusArea = ShowcaseUtils.convertDpToPx(FOCUS_AREA_BOTTOM_MARGIN_IN_DP.toFloat())
        val topFocusArea = cyFocusArea - radiusFocusArea
        val bottomFocusArea = cyFocusArea + radiusFocusArea
        if (topFocusArea >= descriptionView.height + topMarginFocusArea) {
            yDescView = (topFocusArea - topMarginFocusArea - descriptionView.height.toFloat()).toInt()
        } else {
            yDescView = (bottomFocusArea + bottomMarginFocusArea).toInt()
        }
        descriptionView.x = xDescView.toFloat()
        descriptionView.y = yDescView.toFloat()
        super.dispatchDraw(canvas)
    }

    companion object {
        private const val TAG = "ShowcaseView"
    }
}
