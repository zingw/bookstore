package com.example.book.store.utils;

import java.util.Random;

public class KeyGen {
    public  static Integer gen6digitKey(){
        StringBuilder str = new StringBuilder();
        for(int i = 1;i<=6;i++){
            str.append(new Random().nextInt(9));
        }
        return Integer.valueOf(str.toString());
    }
}