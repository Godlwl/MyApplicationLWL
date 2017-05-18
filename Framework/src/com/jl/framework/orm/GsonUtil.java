package com.jl.framework.orm;

import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * 构建Gson，解决activeAndroid与Gson冲突问题
 * Created by lenovo on 2016/1/22.
 */
public class GsonUtil {
    @NonNull
    public static Gson buildGson() {
        return new GsonBuilder().setExclusionStrategies(new ActiveAndroidStrategy(null, Model.class)).serializeNulls().create();
    }
}
