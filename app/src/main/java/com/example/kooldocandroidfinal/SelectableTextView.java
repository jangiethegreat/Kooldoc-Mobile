package com.example.kooldocandroidfinal;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

public class SelectableTextView extends androidx.appcompat.widget.AppCompatTextView implements View.OnClickListener {
    private static SelectableTextView selectedTextView = null;

    public SelectableTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (selectedTextView != null) {
            selectedTextView.setSelected(false);
        }

        setSelected(true);
        selectedTextView = this;
    }
}
