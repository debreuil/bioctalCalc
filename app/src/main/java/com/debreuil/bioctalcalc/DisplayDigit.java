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

    public static boolean isDigit(int value) {
        boolean result = false;
        switch (value) {
            case R.drawable.ic_glyph_0 :
            case R.drawable.ic_glyph_1 :
            case R.drawable.ic_glyph_2 :
            case R.drawable.ic_glyph_3 :
            case R.drawable.ic_glyph_4 :
            case R.drawable.ic_glyph_5 :
            case R.drawable.ic_glyph_6 :
            case R.drawable.ic_glyph_7 :
            case R.drawable.ic_glyph_8 :
            case R.drawable.ic_glyph_9 :
            case R.drawable.ic_glyph_a :
            case R.drawable.ic_glyph_b :
            case R.drawable.ic_glyph_c :
            case R.drawable.ic_glyph_d :
            case R.drawable.ic_glyph_e :
            case R.drawable.ic_glyph_f :
                result = true;
                break;
        }
        return result;
    }
    public static boolean isOp(int value) {
        boolean result = false;
        switch (value) {
            case R.drawable.ic_glyph_add :
            case R.drawable.ic_glyph_sub :
            case R.drawable.ic_glyph_mul :
            case R.drawable.ic_glyph_div :
            case R.drawable.ic_glyph_shl :
            case R.drawable.ic_glyph_shr :
            case R.drawable.ic_glyph_pow :
            case R.drawable.ic_glyph_pow2 :
                result = true;
                break;
        }
        return result;
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
            if(isDigit(this.glyphId)) {
                md.setTint(0xFF222255);
            } else {
                md.setTint(0xFF663322);
            }
            md.draw(canvas);
        }
    }
}
