package com.debreuil.bioctalcalc;

import android.content.Context;
import android.util.ArrayMap;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.GridLayout;

import java.util.ArrayList;
import java.util.Collection;

public class CalcButtons extends GridLayout {
    private ArrayMap<Integer, CalcButton> map = new ArrayMap<Integer, CalcButton>();

    public CalcButtons(Context context) { super(context); init(context, null);}
    public CalcButtons(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        init(context, attrs);
    }
    public CalcButtons(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    public void init(Context context, AttributeSet attrs) { }

    public void initialize()
    {
        super.onAttachedToWindow();
        final int count = this.getChildCount();
        for(int i = 0; i < count; i++) {
            View vg = this.getChildAt(i);
            if(vg instanceof CalcButton) {
                CalcButton cb = (CalcButton)vg;
                map.put(cb.getGlyphId(), cb);
            }
        }
    }

    public Collection<CalcButton> getButtons() {
        return map.values();
    }
}
