package com.erkutaras.showcaseview

import android.content.Context
import android.graphics.*
import android.os.Build
import androidx.annotation.RequiresApi
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout

/**
 * Created by erkut.aras on 23.02.2018.
 */
open class ShowcaseView : RelativeLayout {

    private val FOCUS_AREA_TOP_MARGIN_IN_DP = 50
    private val FOCUS_AREA_BOTTOM_MARGIN_IN_DP = 20

    private val descriptionView: View =
            View.inflate(context, R.layout.layout_intro_description, null)
    private var onCustomButtonClickListener: OnClickListener? = null
    private var onCancelClickListener: OnClickListener? = null
    private var onNextClickListener: OnClickListener? = null
    private var onPreviousClickListener: OnClickListener? = null

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
    private var rect: Rect = Rect()
    private var type: ShowcaseType = ShowcaseType.CIRCLE
    private var gradientFocusEnabled: Boolean = false

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
        val buttonVisibility: Boolean = showcaseModel.buttonVisibility
        val moveButtonsVisibility: Boolean = showcaseModel.moveButtonsVisibility
        val cancelButtonVisibility: Boolean = showcaseModel.cancelButtonVisibility
        val buttonText = showcaseModel.buttonText
        val colorDescTitle = showcaseModel.colorDescTitle
        val cancelBtnColor = showcaseModel.cancelButtonColor
        val selectedMoveButtonColor = showcaseModel.selectedMoveButtonColor
        val unSelectedMoveButtonColor = showcaseModel.unSelectedMoveButtonColor
        val colorDescText = showcaseModel.colorDescText
        val colorButtonText = showcaseModel.colorButtonText
        val colorButtonBackground = showcaseModel.colorButtonBackground
        val isBtnNextSelected: Boolean = showcaseModel.isBtnNextSelected
        val isBtnPreviousSelected: Boolean = showcaseModel.isBtnPreviousSelected

        //update descriptionView
        updateImageDescription(descriptionImageRes)

        //update Title And Description
        updateTitleAndDescription(descriptionTitle,colorDescTitle,descriptionText,colorDescText)

        //update custom button
        updateCustomButton(buttonText,buttonVisibility,colorButtonText,colorButtonBackground)

        //To update cancel button
        updateCancelButton(cancelButtonVisibility,cancelBtnColor)

        //update the container of next and previous buttons
        updateNextPreviousButtonsContainer(moveButtonsVisibility)

        //To update next button
        updateNextButton(isBtnNextSelected,selectedMoveButtonColor,unSelectedMoveButtonColor)

        //To update previous button
        updatePreviousButton(isBtnPreviousSelected,selectedMoveButtonColor,unSelectedMoveButtonColor)


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
        rect = showcaseModel.rect ?: Rect()
        type = showcaseModel.type
        gradientFocusEnabled = showcaseModel.gradientFocusEnabled
    }

    /**
     * update Title And Description
     * To Initialize title and description texts
     * Set the text of title and description texts
     * Set the color of title and description texts
     * */
    private fun updateTitleAndDescription(descriptionTitle :String?,colorDescTitle :Int
                                          ,descriptionText :String?,colorDescText:Int){
        //Initialize title text
        val textViewTitle = descriptionView.findViewById<TextView>(R.id.textView_description_title)

        //update the title text and color
        textViewTitle.text = descriptionTitle
        if (ShowcaseUtils.isNotZero(colorDescTitle.toFloat())) {
            textViewTitle.setTextColor(colorDescTitle)
        }

        //Initialize description text
        val textViewText = descriptionView.findViewById<TextView>(R.id.textView_description)

        //update the description text and color
        textViewText.text = descriptionText
        if (ShowcaseUtils.isNotZero(colorDescText.toFloat())) {
            textViewText.setTextColor(colorDescText)
        }
    }

    /**
     * update ImageDescription
     * Set the visibility of the image
     * */
    private fun updateImageDescription(descriptionImageRes : Int){
        //Initialize ImageDescription
        val imageView = descriptionView.findViewById<ImageView>(R.id.imageView_description)

        //set the visibility of the image
        if (ShowcaseUtils.isNotZero(descriptionImageRes.toFloat())) {
            imageView.setImageResource(descriptionImageRes)
            imageView.visibility = View.VISIBLE
        } else {
            imageView.visibility = View.GONE
        }
    }

    /**
     * update custom button
     * To Initialize custom button
     * Add on click listener to custom button
     * Set the text of custom button
     * Set the text color of custom button
     * Set the background color of custom button
     * Set visibility of custom button
     * */
    private fun updateCustomButton(buttonText :String?, buttonVisibility:Boolean,
                                  colorButtonText : Int, colorButtonBackground :Int){
        //Initialize customButton cancel
        val button = descriptionView.findViewById<Button>(R.id.button_done)

        //add onClickListener to custom button
        if (ShowcaseUtils.isNonNull(onCustomButtonClickListener)) {
            button.setOnClickListener(onCustomButtonClickListener)
        }

        //set custom button text
        if (buttonText?.isNotEmpty() == true) {
            button.text = buttonText
        }

        //set custom button text color
        if (ShowcaseUtils.isNotZero(colorButtonText.toFloat())) {
            button.setTextColor(colorButtonText)
        }
        //set custom button backgroundColor
        if (ShowcaseUtils.isNotZero(colorButtonBackground.toFloat())) {
            button.setBackgroundColor(colorButtonBackground)
        }

        //visibility of custom button
        button.visibility = if (buttonVisibility) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }

    /**
     * update cancel button
     * To Initialize cancel button
     * Add on click listener to cancel button
     * Set the tint color of cancel button
     * */
    private fun updateCancelButton(cancelButtonVisibility :Boolean, cancelBtnColor : Int ){
        //Initialize imgBtn cancel
        val imgBtnCancel = descriptionView.findViewById<ImageButton>(R.id.img_cancel)

        //Set visibility of cancel button
        imgBtnCancel.visibility = if (cancelButtonVisibility) {
            View.VISIBLE
        } else {
            View.GONE
        }

        //add onClickListener to cancel button
        if (ShowcaseUtils.isNonNull(onCancelClickListener)) {
            imgBtnCancel.setOnClickListener(onCancelClickListener)
        }

        //set the color of cancel button
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ShowcaseUtils.isNotZero(cancelBtnColor.toFloat())) {
                resources.getDrawable(R.drawable.ic_outline_cancel, null).setTint(cancelBtnColor)
            } else {
                resources.getDrawable(R.drawable.ic_outline_cancel, null).setTint(Color.WHITE)
            }
            imgBtnCancel.setImageResource(R.drawable.ic_outline_cancel)
        }
    }

    /**
     * update the container of next and previous buttons
     * Set the visibility of the container
     * */
    private fun updateNextPreviousButtonsContainer(moveButtonsVisibility :Boolean){
        //Initialize the container
        val buttonsContainer = descriptionView.findViewById<ConstraintLayout>(R.id.button_container)

        //Set visibility of container button
        buttonsContainer.visibility = if (moveButtonsVisibility) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }

    /**
     * update next button
     * To Initialize next button
     * Add on click listener to next button
     * Set the selection of button next
     * */
    private fun updateNextButton(isBtnNextSelected : Boolean,selectedMoveButtonColor:Int,
                                 unSelectedMoveButtonColor:Int){
        //Initialize imgBtn next
        val imgBtnNext = descriptionView.findViewById<ImageButton>(R.id.img_next)

        //add onClickListener to next button
        if (ShowcaseUtils.isNonNull(onNextClickListener)) {
            imgBtnNext.setOnClickListener(onNextClickListener)
        }
        //set the selection of button next
        if(isBtnNextSelected){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (ShowcaseUtils.isNotZero(selectedMoveButtonColor.toFloat())) {
                    resources.getDrawable(R.drawable.ic_next_arrow, null).setTint(selectedMoveButtonColor)
                    resources.getDrawable(R.drawable.rect_background,null).setTint(selectedMoveButtonColor)

                } else {
                    resources.getDrawable(R.drawable.rect_background,null).setTint(Color.WHITE)
                    imgBtnNext.setImageResource(R.drawable.selector_next_arrow)
                    imgBtnNext.setBackgroundResource(R.drawable.rect_background)

                }
            }
            imgBtnNext.setImageResource(R.drawable.ic_next_arrow)
            imgBtnNext.setBackgroundResource(R.drawable.rect_background)

        }else{
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (ShowcaseUtils.isNotZero(unSelectedMoveButtonColor.toFloat())) {
                    resources.getDrawable(R.drawable.ic_next_arrow_fade, null).setTint(unSelectedMoveButtonColor)
                    resources.getDrawable(R.drawable.unselected_rect,null).setTint(unSelectedMoveButtonColor)
                } else {
                    resources.getDrawable(R.drawable.unselected_rect,null).setTint(resources.getColor(R.color.fade_white,null))
                    imgBtnNext.setImageResource(R.drawable.selector_next_arrow)
                    imgBtnNext.setBackgroundResource(R.drawable.unselected_rect)
                }
            }

            imgBtnNext.setImageResource(R.drawable.ic_next_arrow_fade)
            imgBtnNext.setBackgroundResource(R.drawable.unselected_rect)
        }


    }

    /**
     * update previous button
     * To Initialize previous button
     * Add on click listener to previous button
     * Set the selection of previous next
     * */
    private fun updatePreviousButton(isBtnPreviousSelected : Boolean,selectedMoveButtonColor:Int,
                                     unSelectedMoveButtonColor:Int){
        //Initialize imgBtn previous
        val imgBtnPrevious = descriptionView.findViewById<ImageButton>(R.id.img_previous)

        //add onClickListener to previous button
        if (ShowcaseUtils.isNonNull(onPreviousClickListener)) {
            imgBtnPrevious.setOnClickListener(onPreviousClickListener)
        }


        //set the selection of button previous
        if(isBtnPreviousSelected){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (ShowcaseUtils.isNotZero(selectedMoveButtonColor.toFloat())) {
                    resources.getDrawable(R.drawable.ic_previous_arrow, null).setTint(selectedMoveButtonColor)
                    resources.getDrawable(R.drawable.rect_background,null).setTint(selectedMoveButtonColor)

                } else {
                    resources.getDrawable(R.drawable.rect_background,null).setTint(Color.WHITE)
                    imgBtnPrevious.setImageResource(R.drawable.selector_previous_arrow)
                    imgBtnPrevious.setBackgroundResource(R.drawable.rect_background)

                }
            }
            imgBtnPrevious.setImageResource(R.drawable.ic_previous_arrow)
            imgBtnPrevious.setBackgroundResource(R.drawable.rect_background)

        }else{
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (ShowcaseUtils.isNotZero(unSelectedMoveButtonColor.toFloat())) {
                    resources.getDrawable(R.drawable.ic_previous_arrow_fade, null).setTint(unSelectedMoveButtonColor)
                    resources.getDrawable(R.drawable.unselected_rect,null).setTint(unSelectedMoveButtonColor)
                } else {
                    resources.getDrawable(R.drawable.unselected_rect,null).setTint(resources.getColor(R.color.fade_white,null))
                    imgBtnPrevious.setImageResource(R.drawable.selector_previous_arrow)
                    imgBtnPrevious.setBackgroundResource(R.drawable.unselected_rect)
                }

            }

            imgBtnPrevious.setImageResource(R.drawable.ic_previous_arrow_fade)
            imgBtnPrevious.setBackgroundResource(R.drawable.unselected_rect)
        }

    }


    /**
     * To Initialize onCustomButtonClickListener
     * */
    fun setOnCustomButtonClickListener(onClickListener: OnClickListener?) {
        this.onCustomButtonClickListener = onClickListener
    }

    /**
     * To Initialize onCancelClickListener
     * */
    fun setOnCancelClickListener(onClickListener: OnClickListener?) {
        this.onCancelClickListener = onClickListener
    }

    /**
     * To Initialize onNextClickListener
     * */
    fun setOnNextClickListener(onClickListener: OnClickListener?) {
        this.onNextClickListener = onClickListener
    }

    /**
     * To Initialize setOnPreviousClickListener
     * */
    fun setOnPreviousClickListener(onClickListener: OnClickListener?) {
        this.onPreviousClickListener = onClickListener
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
        drawFocusArea(paint, canvas)

        // shadow for focus area
        val shadowPaint = Paint()
        shadowPaint.color = colorBackground
        shadowPaint.alpha = alphaBackground
        shadowPaint.strokeWidth = 1.0f
        shadowPaint.style = Paint.Style.FILL_AND_STROKE
        shadowPaint.shader = Shader()
        shadowPaint.shader = RadialGradient(cxFocusArea, cyFocusArea, radiusFocusArea,
                colorFocusArea, if (gradientFocusEnabled) shadowPaint.color else colorFocusArea, Shader.TileMode.CLAMP)
        drawFocusArea(shadowPaint, canvas)

        // descriptionView relocate related to focusArea
        val topMarginFocusArea = ShowcaseUtils.convertDpToPx(FOCUS_AREA_TOP_MARGIN_IN_DP.toFloat())
        val bottomMarginFocusArea = ShowcaseUtils.convertDpToPx(FOCUS_AREA_BOTTOM_MARGIN_IN_DP.toFloat())
        val topFocusArea = cyFocusArea - radiusFocusArea
        val bottomFocusArea = cyFocusArea + radiusFocusArea
        yDescView = if (topFocusArea >= descriptionView.height + topMarginFocusArea) {
            (topFocusArea - topMarginFocusArea - descriptionView.height.toFloat()).toInt()
        } else {
            (bottomFocusArea + bottomMarginFocusArea).toInt()
        }
        descriptionView.x = xDescView.toFloat()
        descriptionView.y = yDescView.toFloat()
        super.dispatchDraw(canvas)
    }

    private fun drawFocusArea(paint: Paint, canvas: Canvas) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            when (type) {
                ShowcaseType.CIRCLE -> canvas.drawCircle(cxFocusArea, cyFocusArea, radiusFocusArea, paint)
                ShowcaseType.RECTANGLE -> canvas.drawRect(rect, paint)
                ShowcaseType.ROUND_RECTANGLE -> {
                    val radius = ShowcaseUtils.convertDpToPx(5f)
                    canvas.drawRoundRect(rect.left.toFloat(), rect.top.toFloat(), rect.right.toFloat(), rect.bottom.toFloat(), radius, radius, paint)
                }
            }
        } else {
            when (type) {
                ShowcaseType.CIRCLE -> canvas.drawCircle(cxFocusArea, cyFocusArea, radiusFocusArea, paint)
                else -> canvas.drawRect(rect, paint)
            }
        }
    }

    companion object {
        private const val TAG = "ShowcaseView"
    }
}
