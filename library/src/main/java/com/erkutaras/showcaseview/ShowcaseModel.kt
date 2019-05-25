package com.erkutaras.showcaseview

import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.DrawableRes

/**
 * Created by erkutaras on 25.02.2018.
 */

class ShowcaseModel : Parcelable {

    @DrawableRes
    internal var descriptionImageRes: Int = 0
        private set
    internal var descriptionTitle: String? = null
        private set
    internal var descriptionText: String? = null
        private set
    internal var buttonText: String? = null
        private set
    internal var colorDescTitle: Int = 0
        private set
    internal var colorDescText: Int = 0
        private set
    internal var colorButtonText: Int = 0
        private set
    internal var colorButtonBackground: Int = 0
        private set
    internal var colorBackground: Int = 0
        private set
    internal var alphaBackground: Int = 0
        private set
    internal var colorFocusArea: Int = 0
        private set
    internal var cxFocusArea: Float = 0.toFloat()
        private set
    internal var cyFocusArea: Float = 0.toFloat()
        private set
    internal var radiusFocusArea: Float = 0.toFloat()
        private set

    private constructor() {}

    private constructor(`in`: Parcel) {
        descriptionImageRes = `in`.readInt()
        descriptionTitle = `in`.readString()
        descriptionText = `in`.readString()
        buttonText = `in`.readString()
        colorDescTitle = `in`.readInt()
        colorDescText = `in`.readInt()
        colorButtonText = `in`.readInt()
        colorButtonBackground = `in`.readInt()
        colorBackground = `in`.readInt()
        alphaBackground = `in`.readInt()
        colorFocusArea = `in`.readInt()
        cxFocusArea = `in`.readFloat()
        cyFocusArea = `in`.readFloat()
        radiusFocusArea = `in`.readFloat()
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeInt(descriptionImageRes)
        dest.writeString(descriptionTitle)
        dest.writeString(descriptionText)
        dest.writeString(buttonText)
        dest.writeInt(colorDescTitle)
        dest.writeInt(colorDescText)
        dest.writeInt(colorButtonText)
        dest.writeInt(colorButtonBackground)
        dest.writeInt(colorBackground)
        dest.writeInt(alphaBackground)
        dest.writeInt(colorFocusArea)
        dest.writeFloat(cxFocusArea)
        dest.writeFloat(cyFocusArea)
        dest.writeFloat(radiusFocusArea)
    }

    internal class Builder {
        private var descriptionImageRes: Int = 0
        private var descriptionTitle: String? = null
        private var descriptionText: String? = null
        private var buttonText: String? = null
        private var colorDescTitle: Int = 0
        private var colorDescText: Int = 0
        private var colorButtonText: Int = 0
        private var colorButtonBackground: Int = 0
        private var colorBackground: Int = 0
        private var alphaBackground: Int = 0
        private var colorFocusArea: Int = 0
        private var cxFocusArea: Float = 0.toFloat()
        private var cyFocusArea: Float = 0.toFloat()
        private var radiusFocusArea: Float = 0.toFloat()

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

        fun cxFocusArea(cxFocusArea: Float): Builder {
            this.cxFocusArea = cxFocusArea
            return this
        }

        fun cyFocusArea(cyFocusArea: Float): Builder {
            this.cyFocusArea = cyFocusArea
            return this
        }

        fun radiusFocusArea(radiusFocusArea: Float): Builder {
            this.radiusFocusArea = radiusFocusArea
            return this
        }

        fun build(): ShowcaseModel {
            val showcaseModel = ShowcaseModel()
            showcaseModel.colorButtonText = this.colorButtonText
            showcaseModel.colorDescTitle = this.colorDescTitle
            showcaseModel.colorButtonBackground = this.colorButtonBackground
            showcaseModel.cxFocusArea = this.cxFocusArea
            showcaseModel.descriptionImageRes = this.descriptionImageRes
            showcaseModel.colorBackground = this.colorBackground
            showcaseModel.descriptionTitle = this.descriptionTitle
            showcaseModel.colorDescText = this.colorDescText
            showcaseModel.descriptionText = this.descriptionText
            showcaseModel.alphaBackground = this.alphaBackground
            showcaseModel.cyFocusArea = this.cyFocusArea
            showcaseModel.buttonText = this.buttonText
            showcaseModel.colorFocusArea = this.colorFocusArea
            showcaseModel.radiusFocusArea = this.radiusFocusArea
            return showcaseModel
        }
    }
    
    companion object CREATOR : Parcelable.Creator<ShowcaseModel> {
        override fun createFromParcel(parcel: Parcel): ShowcaseModel {
            return ShowcaseModel(parcel)
        }

        override fun newArray(size: Int): Array<ShowcaseModel?> {
            return arrayOfNulls(size)
        }
    }
}
