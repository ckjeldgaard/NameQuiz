package com.trifork.ckp.namequiz.util;

import android.widget.ImageView;

import com.trifork.ckp.namequiz.R;

public class FakePersonImage implements PersonImage {

    @Override
    public void loadImage(String source, ImageView target) {
        target.setImageResource(R.drawable.person);
    }
}
