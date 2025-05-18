package com.example.practical14quiz.utils;

import com.example.practical14quiz.R;

public class IconPicker {

    private static final int[] icons = {
            R.drawable.ic_icon1,
            R.drawable.ic_icon2,
            R.drawable.ic_icon3,
            R.drawable.ic_icon4,
            R.drawable.ic_icon5,
            R.drawable.ic_icon6,
            R.drawable.ic_icon7,
            R.drawable.ic_icon8,
            R.drawable.ic_icon9,
            R.drawable.ic_icon10
    };

    private static int currentIcon = 0;

    public static int getIcon() {
        currentIcon = (currentIcon + 1) % icons.length;
        return icons[currentIcon];
    }
}
