package com.erkutaras.showcaseview

import android.graphics.Rect
import android.opengl.Visibility
import android.os.Parcel
import android.os.Parcelable
import android.view.View
import androidx.annotation.DrawableRes
import kotlinx.android.parcel.IgnoredOnParcel
import kotlinx.android.parcel.Parceler
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

/**
 * Created by erkutaras on 25.02.2018.
 */
@Parcelize
class ShowcaseModel(
    @DrawableRes val descriptionImageRes: Int,
    val descriptionTitle: String? = null,
    val descriptionText: String? = null,
    val buttonText: String? = null,
    val buttonVisibility: Boolean = true,
    val moveButtonsVisibility: Boolean = false,
    val cancelButtonVisibility: Boolean = true,
    val cancelButtonColor: Int = 0,
    val selectedMoveButtonColor: Int = 0,
    val unSelectedMoveButtonColor: Int = 0,
    val colorDescTitle: Int = 0,
    val colorDescText: Int = 0,
    val colorButtonText: Int = 0,
    val colorButtonBackground: Int = 0,
    val colorBackground: Int = 0,
    val alphaBackground: Int = 0,
    val colorFocusArea: Int = 0,
    val cxFocusArea: Float = 0.toFloat(),
    val cyFocusArea: Float = 0.toFloat(),
    val radiusFocusArea: Float = 0.toFloat(),
    val rect: Rect? = null,
    val type: ShowcaseType = ShowcaseType.CIRCLE,
    val gradientFocusEnabled: Boolean = false
) : Parcelable {
    @IgnoredOnParcel
    var isBtnNextSelected: Boolean = false

    @IgnoredOnParcel
    var isBtnPreviousSelected: Boolean = false
}
