package com.debreuil.bioctalcalc;

import android.annotation.SuppressLint;
import android.databinding.DataBindingUtil;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.PersistableBundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;

import com.debreuil.bioctalcalc.databinding.ActivityCalcBinding;

import java.util.ArrayList;
import java.util.Collection;

public class CalcActivity extends AppCompatActivity implements View.OnClickListener {
    private CalcButtons mCalcButtons;
    private  CalcDisplay mCalcDispaly;
    private boolean isConnectedToButtons;

    private View mControlsView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityCalcBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_calc);
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        com.debreuil.bioctalcalc.CalcStyle calcStyle = new CalcStyle(new Rect(0, 0, (int)(size.x * 0.51), (int)(size.y * 0.51)));
        binding.setCalcStyle(calcStyle);

        setContentView(R.layout.activity_calc);

        mCalcButtons = (CalcButtons)this.findViewById(R.id.calcButtons);
        mCalcDispaly = (CalcDisplay) this.findViewById(R.id.calcDisplay);
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        mCalcButtons.initialize();

        Collection<CalcButton> buttons = mCalcButtons.getButtons();
        for(CalcButton cb : buttons) {
            cb.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        if(v instanceof CalcButton) {
            CalcButton cb = (CalcButton)v;
            Log.i("-", "onClick: " + cb.getGlyphId());
            int val = cb.getGlyphId();
            if(DisplayDigit.isDigit(val)) {
                mCalcDispaly.setDigit(val);
            }
            else if(DisplayDigit.isOp(val)) {
                mCalcDispaly.setOp(val);
            }
            else if(val == R.drawable.ic_glyph_clr) {
                mCalcDispaly.clearAll();
            }
        }
    }
}
