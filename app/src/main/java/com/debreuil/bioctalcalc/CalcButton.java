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
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.Button;
import android.graphics.drawable.Drawable;
import android.widget.GridLayout;
import android.widget.LinearLayout;

public class CalcButton extends Button {

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
//
//        this.setId(idIndex++);
        Resources res = getResources();
        this.setBackground(res.getDrawable(R.drawable.ic_bkg_norm, null));
        //GridLayout.LayoutParams params = (GridLayout.LayoutParams) this.getLayoutParams();
        GridLayout.LayoutParams params = new GridLayout.LayoutParams();
        params.width = 0;
        params.height = 0;
        params.setGravity(Gravity.FILL_HORIZONTAL | Gravity.FILL_VERTICAL);
        params.rowSpec = new GridLayout.spec(GridLayout.UNDEFINED, 1.0f);
//        //params.rowSpec.weight - 1.0f;
        this.setLayoutParams(params);
//
        this.setGravity(Gravity.CENTER);

//        this.setShadowLayer(7, 5, 5, 0xFFFF0000);

        mPaint.setColor(0xFFFF0000);
        mPaint.setStyle(Paint.Style.FILL);
        //mPaint.setMaskFilter(new BlurMaskFilter(8, BlurMaskFilter.Blur.NORMAL));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Rect r = new Rect(0,0,this.getWidth(), this.getHeight());
        Resources res = getResources();
        Drawable vectorDrawable = res.getDrawable(this.glyphId, null);
        vectorDrawable.setBounds(r);
        vectorDrawable.draw(canvas);
        canvas.drawOval(
                new RectF(0f,0f,10f,10f),
                mPaint
        );
    }
}