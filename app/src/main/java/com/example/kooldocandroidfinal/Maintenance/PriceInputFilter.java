package com.example.kooldocandroidfinal.Maintenance;

import android.content.Context;
import android.text.InputFilter;
import android.text.Spanned;
import android.widget.Toast;

public class PriceInputFilter implements InputFilter {

    private Context mContext;

    public PriceInputFilter(Context context) {
        mContext = context;
    }
    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
        try {
            int input = Integer.parseInt(dest.toString() + source.toString());
            if (input > 50) {
                Toast.makeText(mContext, "Price should be less than or equal to 50", Toast.LENGTH_SHORT).show();
                return "";
            }
        } catch (NumberFormatException e) {
            // ignore
        }
        return null;
    }
}
