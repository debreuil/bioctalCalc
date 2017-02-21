package com.debreuil.bioctalcalc;
import android.graphics.Rect;

public class CalcStyle {
    public final Rect buttonBounds;
    public final int spacing = 4;
    public CalcStyle(Rect buttonBounds) {
        this.buttonBounds = buttonBounds;
    }

    public String getWidthDP() {
        return Integer.toString(this.buttonBounds.width()) + "dp";
    }
    public String getHeightDP() {
        return Integer.toString(this.buttonBounds.height()) + "dp";
    }
}
