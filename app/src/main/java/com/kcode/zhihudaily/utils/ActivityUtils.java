package com.kcode.zhihudaily.utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

/**
 * Created by caik on 2017/3/8.
 */

public class ActivityUtils {

    public static void addFragment(FragmentManager manager, Fragment fragment,
                                   int containerViewId) {
        manager.beginTransaction()
                .add(containerViewId, fragment, fragment.getClass().getSimpleName())
                .commit();
    }

    public static void replaceFragment(FragmentManager manager, Fragment fragment,
                                   int containerViewId) {
        manager.beginTransaction()
                .replace(containerViewId, fragment, fragment.getClass().getSimpleName())
                .commit();
    }
}
