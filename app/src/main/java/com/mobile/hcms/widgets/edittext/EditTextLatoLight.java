package com.mobile.hcms.widgets.edittext;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * Created by Rio Swarawan on 11/16/2015.
 */
public class EditTextLatoLight extends EditText {
    public EditTextLatoLight(Context context) {
        super(context);
    }

    public EditTextLatoLight(Context context, AttributeSet attrs) {
        super(context, attrs);
        Typeface type = Typeface.createFromAsset(context.getAssets(), "fonts/HelveticaNeueLTPro-Lt.otf");
        setTypeface(type);
    }

}
