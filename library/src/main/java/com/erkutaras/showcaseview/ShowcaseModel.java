package com.erkutaras.showcaseview;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.DrawableRes;

/**
 * Created by erkutaras on 25.02.2018.
 */

public class ShowcaseModel implements Parcelable {

    private @DrawableRes int descriptionImageRes;
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
    private float cxFocusArea;
    private float cyFocusArea;
    private float radiusFocusArea;

    private ShowcaseModel() {
    }

    private ShowcaseModel(Parcel in) {
        descriptionImageRes = in.readInt();
        descriptionTitle = in.readString();
        descriptionText = in.readString();
        buttonText = in.readString();
        colorDescTitle = in.readInt();
        colorDescText = in.readInt();
        colorButtonText = in.readInt();
        colorButtonBackground = in.readInt();
        colorBackground = in.readInt();
        alphaBackground = in.readInt();
        colorFocusArea = in.readInt();
        cxFocusArea = in.readFloat();
        cyFocusArea = in.readFloat();
        radiusFocusArea = in.readFloat();
    }

    public static final Creator<ShowcaseModel> CREATOR = new Creator<ShowcaseModel>() {
        @Override
        public ShowcaseModel createFromParcel(Parcel in) {
            return new ShowcaseModel(in);
        }

        @Override
        public ShowcaseModel[] newArray(int size) {
            return new ShowcaseModel[size];
        }
    };

    int getDescriptionImageRes() {
        return descriptionImageRes;
    }

    String getDescriptionTitle() {
        return descriptionTitle;
    }

    String getDescriptionText() {
        return descriptionText;
    }

    String getButtonText() {
        return buttonText;
    }

    int getColorDescTitle() {
        return colorDescTitle;
    }

    int getColorDescText() {
        return colorDescText;
    }

    int getColorButtonText() {
        return colorButtonText;
    }

    int getColorButtonBackground() {
        return colorButtonBackground;
    }

    int getColorBackground() {
        return colorBackground;
    }

    int getAlphaBackground() {
        return alphaBackground;
    }

    int getColorFocusArea() {
        return colorFocusArea;
    }

    float getCxFocusArea() {
        return cxFocusArea;
    }

    float getCyFocusArea() {
        return cyFocusArea;
    }

    float getRadiusFocusArea() {
        return radiusFocusArea;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(descriptionImageRes);
        dest.writeString(descriptionTitle);
        dest.writeString(descriptionText);
        dest.writeString(buttonText);
        dest.writeInt(colorDescTitle);
        dest.writeInt(colorDescText);
        dest.writeInt(colorButtonText);
        dest.writeInt(colorButtonBackground);
        dest.writeInt(colorBackground);
        dest.writeInt(alphaBackground);
        dest.writeInt(colorFocusArea);
        dest.writeFloat(cxFocusArea);
        dest.writeFloat(cyFocusArea);
        dest.writeFloat(radiusFocusArea);
    }

    static final class Builder {
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
        private float cxFocusArea;
        private float cyFocusArea;
        private float radiusFocusArea;

        Builder() {
        }

        Builder descriptionImageRes(int descriptionImageRes) {
            this.descriptionImageRes = descriptionImageRes;
            return this;
        }

        Builder descriptionTitle(String descriptionTitle) {
            this.descriptionTitle = descriptionTitle;
            return this;
        }

        Builder descriptionText(String descriptionText) {
            this.descriptionText = descriptionText;
            return this;
        }

        Builder buttonText(String buttonText) {
            this.buttonText = buttonText;
            return this;
        }

        Builder colorDescTitle(int colorDescTitle) {
            this.colorDescTitle = colorDescTitle;
            return this;
        }

        Builder colorDescText(int colorDescText) {
            this.colorDescText = colorDescText;
            return this;
        }

        Builder colorButtonText(int colorButtonText) {
            this.colorButtonText = colorButtonText;
            return this;
        }

        Builder colorButtonBackground(int colorButtonBackground) {
            this.colorButtonBackground = colorButtonBackground;
            return this;
        }

        Builder colorBackground(int colorBackground) {
            this.colorBackground = colorBackground;
            return this;
        }

        Builder alphaBackground(int alphaBackground) {
            this.alphaBackground = alphaBackground;
            return this;
        }

        Builder colorFocusArea(int colorFocusArea) {
            this.colorFocusArea = colorFocusArea;
            return this;
        }

        Builder cxFocusArea(float cxFocusArea) {
            this.cxFocusArea = cxFocusArea;
            return this;
        }

        Builder cyFocusArea(float cyFocusArea) {
            this.cyFocusArea = cyFocusArea;
            return this;
        }

        Builder radiusFocusArea(float radiusFocusArea) {
            this.radiusFocusArea = radiusFocusArea;
            return this;
        }

        ShowcaseModel build() {
            ShowcaseModel showcaseModel = new ShowcaseModel();
            showcaseModel.colorButtonText = this.colorButtonText;
            showcaseModel.colorDescTitle = this.colorDescTitle;
            showcaseModel.colorButtonBackground = this.colorButtonBackground;
            showcaseModel.cxFocusArea = this.cxFocusArea;
            showcaseModel.descriptionImageRes = this.descriptionImageRes;
            showcaseModel.colorBackground = this.colorBackground;
            showcaseModel.descriptionTitle = this.descriptionTitle;
            showcaseModel.colorDescText = this.colorDescText;
            showcaseModel.descriptionText = this.descriptionText;
            showcaseModel.alphaBackground = this.alphaBackground;
            showcaseModel.cyFocusArea = this.cyFocusArea;
            showcaseModel.buttonText = this.buttonText;
            showcaseModel.colorFocusArea = this.colorFocusArea;
            showcaseModel.radiusFocusArea = this.radiusFocusArea;
            return showcaseModel;
        }
    }
}
