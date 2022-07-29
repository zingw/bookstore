package com.example.book.store.utils;

import java.util.Random;
import java.util.UUID;

public class RandomIdUtils {
    public static String getRanDomId(){
        String abcd = "abcdefghijklmoupwrtszuv";
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0;i<8;i++){
            stringBuilder.append(abcd.charAt(new Random().nextInt(abcd.length() -1 )));
        }
        return stringBuilder.toString();
    }
}
