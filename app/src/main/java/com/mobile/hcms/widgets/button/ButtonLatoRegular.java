package com.mobile.hcms.widgets.button;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.Button;

/**
 * Created by Rio Swarawan on 11/16/2015.
 */
public class ButtonLatoRegular extends Button {
    public ButtonLatoRegular(Context context) {
        super(context);
    }

    public ButtonLatoRegular(Context context, AttributeSet attrs) {
        super(context, attrs);
        Typeface type = Typeface.createFromAsset(context.getAssets(), "fonts/HelveticaNeueLTPro-Lt.otf");
        setTypeface(type);
    }

}
