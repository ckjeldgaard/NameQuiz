package com.trifork.ckp.namequiz.util;

import android.widget.ImageView;

public interface PersonImage {
    void loadImage(String source, ImageView target);
    void loadThumbnail(String source, ImageView target);
}
