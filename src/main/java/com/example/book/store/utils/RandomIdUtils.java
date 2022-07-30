package com.example.book.store.utils;

import java.util.Random;
import java.util.UUID;

public class RandomIdUtils {
    public static String getRanDomId(){
        return UUID.randomUUID().toString().replace("-","");
    }
}
