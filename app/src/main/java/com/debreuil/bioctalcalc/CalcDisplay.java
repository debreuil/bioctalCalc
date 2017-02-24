package com.debreuil.bioctalcalc;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.GridLayout;
import java.util.ArrayList;

public class CalcDisplay extends GridLayout {
    private int display_rows;
    private int display_columns;
    private static Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private ArrayList<ArrayList<DisplayDigit>> grid = new ArrayList<ArrayList<DisplayDigit>>();

    public CalcDisplay(Context context)
    {
        super(context);
    }

    public CalcDisplay(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        init(context, attrs);
    }

    public CalcDisplay(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
        init(context, attrs);
    }
    public int getDisplay_rows() {
        return display_rows;
    }
    public void setDisplay_rows(int display_rows) {
        this.display_rows = display_rows;
        invalidate();
        requestLayout();
    }
    public int getDisplay_columns() {
        return display_columns;
    }
    public void setDisplay_columns(int display_columns) {
        this.display_columns = display_columns;
        invalidate();
        requestLayout();
    }

    public void init(Context context, AttributeSet attrs) {
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CalcButton, 0, 0);
        try {
            this.display_rows = a.getResourceId(R.styleable.CalcDisplay_display_rows,6);
            this.display_columns = a.getResourceId(R.styleable.CalcDisplay_display_columns, 7);
        } finally {
            a.recycle();
        }
        this.setRowCount(this.display_rows);
        this.setColumnCount(this.display_columns);
        this.grid.clear();
        for (int i = 0; i < this.display_rows; i++) {
            ArrayList<DisplayDigit> row = new ArrayList<DisplayDigit>();
            this.grid.add(row);
            for (int j = 0; j < this.display_columns; j++) {
                DisplayDigit dd = new DisplayDigit(context, i, j);
                row.add(dd);
                this.addView(dd);
            }
        }

        this.grid.get(display_rows - 1).get(display_columns - 1).setGlyphId(R.drawable.ic_glyph_0);
    }

//    @Override
//    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
//        Rect r = new Rect(0,0,this.getWidth(), this.getHeight());
//        Resources res = getResources();
//        Drawable vectorDrawable = res.getDrawable(this.glyphId, null);
//        vectorDrawable.setBounds(r);
//        vectorDrawable.draw(canvas);
//    }
}
