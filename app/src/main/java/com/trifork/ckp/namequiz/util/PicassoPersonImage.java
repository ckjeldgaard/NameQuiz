package com.trifork.ckp.namequiz.util;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.trifork.ckp.namequiz.R;

public final class PicassoPersonImage implements PersonImage {

    private final Context context;

    public PicassoPersonImage(Context context) {
        this.context = context;
    }

    @Override
    public void loadImage(String source, ImageView target) {
        int width = Math.round(context.getResources().getDimension(R.dimen.person_image_width));
        int height = Math.round(context.getResources().getDimension(R.dimen.person_image_height));

        Picasso.with(context)
                .load(source)
                .placeholder(R.drawable.progress_animation)
                .resize(width, height)
                .centerCrop()
                .into(target);
    }
}
