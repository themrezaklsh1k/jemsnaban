package com.mobile.hcms.widgets.edittext;

import android.content.Context;
import android.graphics.Typeface;
import android.support.design.widget.TextInputLayout;
import android.util.AttributeSet;

/**
 * Created by Rio Swarawan on 11/16/2015.
 */
public class CustomITextInputLayoutRegular extends TextInputLayout {
    public CustomITextInputLayoutRegular(Context context) {
        super(context);
    }

    public CustomITextInputLayoutRegular(Context context, AttributeSet attrs) {
        super(context, attrs);
        Typeface type = Typeface.createFromAsset(context.getAssets(), "fonts/HelveticaNeueLTPro-Lt.otf");
        setTypeface(type);
    }

}
