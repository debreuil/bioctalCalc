package com.debreuil.bioctalcalc;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.VectorDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.graphics.drawable.Drawable;
import android.widget.GridLayout;
import android.widget.LinearLayout;

public class CalcButton extends Button implements View.OnClickListener {

//    private String mButtonName;
    private int glyphId;
//    private static int idIndex = 1000;

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

//        int row = attrs.getAttributeIntValue(
//                "http://schemas.android.com/apk/res/android",
//                "layout_row", 0);
//        int col = attrs.getAttributeIntValue(
//                "http://schemas.android.com/apk/res/android",
//                "layout_column", 0);
//        int rowSpan = attrs.getAttributeIntValue(
//                "http://schemas.android.com/apk/res/android",
//                "layout_rowSpan", 1);
//        int colSpan = attrs.getAttributeIntValue(
//                "http://schemas.android.com/apk/res/android",
//                "layout_columnSpan", 1);
//        //GridLayout.LayoutParams params = (GridLayout.LayoutParams) this.getLayoutParams();
//        GridLayout.LayoutParams params = new GridLayout.LayoutParams();
//        params.width = 0;
//        params.height = 0;
//        params.rowSpec = GridLayout.spec(row, rowSpan, 1f);
//        params.columnSpec = GridLayout.spec(col, colSpan, 1f);
//        params.setGravity(Gravity.FILL_HORIZONTAL | Gravity.FILL_VERTICAL);
//        this.setLayoutParams(params);

        this.setGravity(Gravity.CENTER);

//        this.setShadowLayer(7, 5, 5, 0xFFFF0000);

//        mPaint.setColor(0xFFFF0000);
//        mPaint.setStyle(Paint.Style.FILL);
        //mPaint.setMaskFilter(new BlurMaskFilter(8, BlurMaskFilter.Blur.NORMAL));
        this.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Log.i("-", "onClick: " + this.glyphId);
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
