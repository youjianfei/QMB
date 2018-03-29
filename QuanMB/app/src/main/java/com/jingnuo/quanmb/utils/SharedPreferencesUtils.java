package com.jingnuo.quanmb.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesUtils {
    private static SharedPreferences sharedPreferences;

    public static void putString(Context context, String xmlName, String key, String value) {
        sharedPreferences = context.getSharedPreferences(xmlName, Context.MODE_PRIVATE);
        sharedPreferences.edit().putString(key, value).commit();
    }

    public static String getString(Context context, String xmlName, String key) {
        sharedPreferences = context.getSharedPreferences(xmlName, Context.MODE_PRIVATE);
        return sharedPreferences.getString(key, "");
    }

    public static void putInt(Context context, String xmlName, String key, int value) {
        sharedPreferences = context.getSharedPreferences(xmlName, Context.MODE_PRIVATE);
        sharedPreferences.edit().putInt(key, value).commit();
    }

    public static int getInt(Context context, String xmlName, String key) {
        sharedPreferences = context.getSharedPreferences(xmlName, Context.MODE_PRIVATE);
        return sharedPreferences.getInt(key, -1);
    }

    public static void putBoolean(Context context, String xmlName, String key, boolean value) {
        sharedPreferences = context.getSharedPreferences(xmlName, Context.MODE_PRIVATE);
        sharedPreferences.edit().putBoolean(key, value).commit();
    }

    public static boolean getBoolean(Context context, String xmlName, String key) {
        sharedPreferences = context.getSharedPreferences(xmlName, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(key, true);
    }

    public static void putFloat(Context context, String xmlName, String key, float value) {
        sharedPreferences = context.getSharedPreferences(xmlName, Context.MODE_PRIVATE);
        sharedPreferences.edit().putFloat(key, value).commit();
    }

    public static float getFloat(Context context, String xmlName, String key) {
        sharedPreferences = context.getSharedPreferences(xmlName, Context.MODE_PRIVATE);
        return sharedPreferences.getFloat(key, -1);
    }

    public static void putLong(Context context, String xmlName, String key, long value) {
        sharedPreferences = context.getSharedPreferences(xmlName, Context.MODE_PRIVATE);
        sharedPreferences.edit().putLong(key, value).commit();
    }

    public static long getLong(Context context, String xmlName, String key) {
        sharedPreferences = context.getSharedPreferences(xmlName, Context.MODE_PRIVATE);
        return sharedPreferences.getLong(key, -1);
    }

}
