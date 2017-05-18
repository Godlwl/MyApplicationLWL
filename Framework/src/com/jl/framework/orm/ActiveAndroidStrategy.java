package com.jl.framework.orm;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

/**
 * 序列化时忽略父类（com.activeandroid.Model）的属性，实现com.google.gson.ExclusionStrategy接口策略来构建gson
 * Created by lenovo on 2016/1/22.
 */
public class ActiveAndroidStrategy implements ExclusionStrategy {

    private Class<?> excludedThisClass;
    private Class<?> excludedThisClassFields;

    public ActiveAndroidStrategy(Class<?> excludedThisClass, Class<?> excludedThisClassFields) {
        this.excludedThisClass = excludedThisClass;
        this.excludedThisClassFields = excludedThisClassFields;
    }

    @Override
    public boolean shouldSkipField(FieldAttributes f) {
        return f.getDeclaringClass().equals(excludedThisClassFields);
    }

    @Override
    public boolean shouldSkipClass(Class<?> clazz) {
        if (clazz == null) {
            return false;
        }

        if (clazz.equals(excludedThisClass)) {
            return true;
        }
        return shouldSkipClass(clazz.getSuperclass());
    }
}