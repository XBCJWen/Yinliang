package com.example.a17374.myapplication;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

public class title extends LinearLayout {
    public title(final Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        LayoutInflater.from(context).inflate(R.layout.title, this);

        ImageButton imageButton = findViewById(R.id.img_title);
        imageButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "小可爱茵量器", Toast.LENGTH_LONG).show();
            }
        });

    }
}