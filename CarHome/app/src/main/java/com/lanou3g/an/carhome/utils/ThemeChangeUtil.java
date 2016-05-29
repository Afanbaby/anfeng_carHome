package com.lanou3g.an.carhome.utils;

import android.app.Activity;

import com.lanou3g.an.carhome.R;

/**
 * Created by anfeng on 16/5/24.
 */
public class ThemeChangeUtil {
    //用来记录一下当前的主题的状态
    public static boolean isChange = false;

    public static void changeTheme(Activity activity) {
        if (isChange) {
            activity.setTheme(R.style.NightTheme);
        }
    }
}
