package com.centennial.deanpinlac_sanjibsaha_comp304sec002_Lab4_Ex1.utils;

import com.google.gson.Gson;

import java.lang.reflect.Type;

public class Common {
    public static <T> String convertToJson(T object){
        Gson gson = new Gson();
        return gson.toJson(object);
    }

    public static <T> T convertJsonToObject(String jsonStr, Type type){
        Gson gson = new Gson();
        return gson.fromJson(jsonStr, type);
    }
}
