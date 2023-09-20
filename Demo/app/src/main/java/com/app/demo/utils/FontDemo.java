package com.app.demo.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

import java.util.Hashtable;

public class FontDemo {

    private final Context context;
    private Typeface mTypeface;

    public static final int NORMAL = Typeface.NORMAL;
    public static final int BOLD = Typeface.BOLD;

    public static final String POPPINS_BOLD = "poppins_bold.ttf";
    public static final String POPPINS_SEMI_BOLD = "poppins_semibold.ttf";
    public static final String POPPINS_EXTRA_BOLD = "poppins_extrabold.ttf";
    public static final String POPPINS_EXTRA_LIGHT = "poppins_extralight.ttf";
    public static final String POPPINS_MEDIUM = "poppins_medium.ttf";
    public static final String POPPINS_REGULAR = "poppins_regular.ttf";

    public static final String DEFAULT_FONT = POPPINS_REGULAR;

    private final String[] fontList = {
            POPPINS_BOLD,
            POPPINS_SEMI_BOLD,
            POPPINS_EXTRA_BOLD,
            POPPINS_EXTRA_LIGHT,
            POPPINS_MEDIUM,
            POPPINS_REGULAR
    };

    private static Hashtable<String, Typeface> sTypeFaces;

    public FontDemo(Context context) {
        this.context = context;
        sTypeFaces = new Hashtable<>();
        makeFonts();
    }

    private void makeFonts() {

        AssetManager assetManager = context.getAssets();

        for (String fontType : fontList) {

            Typeface mTypeface;

            try {
                mTypeface = Typeface.createFromAsset(assetManager, fontType);
                sTypeFaces.put(fontType, mTypeface);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private Typeface setMyFont(String fontType) {

        mTypeface = sTypeFaces.get(fontType);

        if (mTypeface == null) {
            mTypeface = Typeface.createFromAsset(context.getAssets(), fontType);
            sTypeFaces.put(fontType, mTypeface);
        }
        return mTypeface;
    }

    public Typeface setMyFont(TextView textView, String fontType) {
        return setFont(textView, NORMAL, fontType);
    }

    public void setFont(TextView textView) {
        setFont(textView, NORMAL);
    }

    public void setFont(TextView textView, int fontStyle) {
        setFont(textView, fontStyle, DEFAULT_FONT);
    }

    public Typeface setFont(TextView textView, String fontType) {
        return setFont(textView, NORMAL, fontType);
    }

    public Typeface setFont(TextView textView, int fontStyle, String fontType) {

        mTypeface = sTypeFaces.get(fontType);

        if (mTypeface == null) {
            mTypeface = Typeface.createFromAsset(context.getAssets(), fontType);
            sTypeFaces.put(fontType, mTypeface);
        }
        if (textView != null)
            textView.setTypeface(mTypeface, fontStyle);

        return mTypeface;
    }

    public void setAppFont(ViewGroup mContainer) {
        setAppFont(mContainer, setMyFont(DEFAULT_FONT));
    }

    public void setAppFont(ViewGroup mContainer, String mFont) {
        setAppFont(mContainer, setMyFont(mFont));
    }

    public void setAppFont(ViewGroup mContainer, String mFont, int typeface) {
        setAppFont(mContainer, setMyFont(mFont), typeface);
    }

    /*Recursively sets a {@link Typeface} to all
     {@link TextView}s in a {@link ViewGroup}*/
    private void setAppFont(ViewGroup mContainer, Typeface mFont) {
        setAppFont(mContainer, mFont, NORMAL);
    }

    private void setAppFont(ViewGroup mContainer, Typeface mFont, int thickness) {
        if (mContainer == null || mFont == null) return;
        final int mCount = mContainer.getChildCount();
        for (int i = 0; i < mCount; ++i) {
            final View mChild = mContainer.getChildAt(i);
            if (mChild instanceof TextView) {
                ((TextView) mChild).setTypeface(mFont, thickness);
            } else if (mChild instanceof ViewGroup) {
                setAppFont((ViewGroup) mChild, mFont);
            }
        }
    }

    public void setBold(TextView textView, String font) {
        setFont(textView, BOLD, font);
    }

    private void setBold(TextView textView) {
        textView.setTypeface(null, Typeface.BOLD);
    }

    public Typeface setFont(MaterialButton textView, int fontStyle, String fontType) {

        mTypeface = sTypeFaces.get(fontType);

        if (mTypeface == null) {
            mTypeface = Typeface.createFromAsset(context.getAssets(), fontType);
            sTypeFaces.put(fontType, mTypeface);
        }
        if (textView != null)
            textView.setTypeface(mTypeface, fontStyle);

        return mTypeface;
    }

    public Typeface setFont(MaterialButton textView, String fontType) {
        return setFont(textView, NORMAL, fontType);
    }

    public Typeface setFont(EditText textView, int fontStyle, String fontType) {

        mTypeface = sTypeFaces.get(fontType);

        if (mTypeface == null) {
            mTypeface = Typeface.createFromAsset(context.getAssets(), fontType);
            sTypeFaces.put(fontType, mTypeface);
        }

        if (textView != null)
            textView.setTypeface(mTypeface, fontStyle);

        return mTypeface;
    }

    public Typeface setFont(EditText textView, String fontType) {
        return setFont(textView, NORMAL, fontType);
    }
}