package com.debreuil.bioctalcalc;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridLayout;

public class DisplayDigit extends View {
    private int glyphId;
    private static Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public DisplayDigit(Context context, int row, int column)
    {
        super(context);
        GridLayout.LayoutParams params = new GridLayout.LayoutParams();
        params.width = 0;
        params.height = 0;
        params.rowSpec = GridLayout.spec(row, 1, 1f);
        params.columnSpec = GridLayout.spec(column, 1, 1f);
        params.setGravity(Gravity.FILL_HORIZONTAL | Gravity.FILL_VERTICAL);
        this.setLayoutParams(params);
        init(context, null);
    }
    public DisplayDigit(Context context)
    {
        super(context);
    }

    public DisplayDigit(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        init(context, attrs);
    }

    public DisplayDigit(Context context, AttributeSet attrs, int defStyle)
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
            this.glyphId = a.getResourceId(R.styleable.CalcButton_glyphId, 0);
            //this.glyphId = a.getResourceId(R.styleable.CalcButton_glyphId, R.drawable.ic_glyph_f);
        } finally {
            a.recycle();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Rect r = new Rect(0,0,this.getWidth(), this.getHeight());
        Resources res = getResources();

        Drawable bkg = res.getDrawable(R.drawable.ic_bkg_digit, null);
        bkg.setBounds(r);
        bkg.draw(canvas);

        if(this.glyphId > 0) {
            Drawable vectorDrawable = res.getDrawable(this.glyphId, null);
            vectorDrawable.setBounds(r);
            Drawable md = vectorDrawable.mutate();
            md.setTint(0xFF222255);
            md.draw(canvas);
        }
    }
}
