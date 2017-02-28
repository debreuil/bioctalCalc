package com.debreuil.bioctalcalc;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.widget.Button;
import android.graphics.drawable.Drawable;

public class CalcButton extends Button {

    private int glyphId;

    private static Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public CalcButton(Context context)
    {
        super(context);
    }

    public CalcButton(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        init(context, attrs);
    }

    public CalcButton(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    public int getGlyphId() {
        return glyphId;
    }
    public void setGlyphId(int glyphId) {
        this.glyphId = glyphId;
        invalidate();
        requestLayout();
    }

    public void init(Context context, AttributeSet attrs) {
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CalcButton, 0, 0);
        try {
            this.glyphId = a.getResourceId(R.styleable.CalcButton_glyphId, R.drawable.ic_glyph_mul);
        } finally {
            a.recycle();
        }
        Resources res = getResources();

        int rowSpan = attrs.getAttributeIntValue(
                "http://schemas.android.com/apk/res/android",
                "layout_rowSpan", 1);
        int colSpan = attrs.getAttributeIntValue(
                "http://schemas.android.com/apk/res/android",
                "layout_columnSpan", 1);
        int resId = (rowSpan > 1) ? R.drawable.ic_bkg_tall : (colSpan > 1) ?
                R.drawable.ic_bkg_wide : R.drawable.ic_bkg_norm;
        Log.i("tag", colSpan + " : " + resId);
        this.setBackground(res.getDrawable(resId, null));

        this.setGravity(Gravity.CENTER);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Rect r = new Rect(0,0,this.getWidth(), this.getHeight());
        Resources res = getResources();
        Drawable vectorDrawable = res.getDrawable(this.glyphId, null);
        vectorDrawable.setBounds(r);
        vectorDrawable.draw(canvas);
    }
}
