package com.example.movielistdemo.Utils;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

/**
 * Created by Nowfal on 5/18/16.
 */
public class PhenomenaTextView extends AppCompatTextView {

    private Context context;
    private AttributeSet attrs;
    private int defStyle;

    public PhenomenaTextView(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public PhenomenaTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        this.attrs = attrs;
        init();
    }

    public PhenomenaTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;
        this.attrs = attrs;
        this.defStyle = defStyle;
        init(defStyle);
    }

    private void init() {
        Typeface regularFont = Typeface.createFromAsset(getContext().getAssets(), "fonts/Phenomena-Regular.otf");
        Typeface boldFont = Typeface.createFromAsset(getContext().getAssets(), "fonts/Phenomena-Bold.otf");

        Typeface currentTypeFace = this.getTypeface();
        if (currentTypeFace != null && currentTypeFace.getStyle() == Typeface.BOLD) {
            this.setTypeface(boldFont);
        } else {
            this.setTypeface(regularFont);
        }

    }

    private void init(int style) {
        Typeface regularFont = Typeface.createFromAsset(getContext().getAssets(), "fonts/Phenomena-Regular.otf");
        Typeface boldFont = Typeface.createFromAsset(getContext().getAssets(), "fonts/Phenomena-Bold.otf");

        Typeface currentTypeFace = this.getTypeface();
        if (currentTypeFace != null && currentTypeFace.getStyle() == Typeface.BOLD) {
            this.setTypeface(boldFont, style);
        } else {
            this.setTypeface(regularFont, style);
        }
    }

    @Override
    public void setTypeface(Typeface tf, int style) {
        super.setTypeface(tf, style);
    }

    @Override
    public void setTypeface(Typeface tf) {
        super.setTypeface(tf);
    }

}
