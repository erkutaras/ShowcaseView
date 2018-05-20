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

    protected ShowcaseModel(Parcel in) {
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

    public int getDescriptionImageRes() {
        return descriptionImageRes;
    }

    public String getDescriptionTitle() {
        return descriptionTitle;
    }

    public String getDescriptionText() {
        return descriptionText;
    }

    public String getButtonText() {
        return buttonText;
    }

    public int getColorDescTitle() {
        return colorDescTitle;
    }

    public int getColorDescText() {
        return colorDescText;
    }

    public int getColorButtonText() {
        return colorButtonText;
    }

    public int getColorButtonBackground() {
        return colorButtonBackground;
    }

    public int getColorBackground() {
        return colorBackground;
    }

    public int getAlphaBackground() {
        return alphaBackground;
    }

    public int getColorFocusArea() {
        return colorFocusArea;
    }

    public float getCxFocusArea() {
        return cxFocusArea;
    }

    public float getCyFocusArea() {
        return cyFocusArea;
    }

    public float getRadiusFocusArea() {
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

    public static final class Builder {
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
        private boolean isDevelopMode;

        public Builder() {
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

        public Builder cxFocusArea(float cxFocusArea) {
            this.cxFocusArea = cxFocusArea;
            return this;
        }

        public Builder cyFocusArea(float cyFocusArea) {
            this.cyFocusArea = cyFocusArea;
            return this;
        }

        public Builder radiusFocusArea(float radiusFocusArea) {
            this.radiusFocusArea = radiusFocusArea;
            return this;
        }

        public ShowcaseModel build() {
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
