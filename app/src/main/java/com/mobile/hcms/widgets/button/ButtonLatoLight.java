package com.mobile.hcms.widgets.button;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.Button;

/**
 * Created by Rio Swarawan on 11/16/2015.
 */
public class ButtonLatoLight extends Button {
    public ButtonLatoLight(Context context) {
        super(context);
    }

    public ButtonLatoLight(Context context, AttributeSet attrs) {
        super(context, attrs);
        Typeface type = Typeface.createFromAsset(context.getAssets(), "fonts/HelveticaNeueLTPro-Lt.otf");
        setTypeface(type);
    }

}
