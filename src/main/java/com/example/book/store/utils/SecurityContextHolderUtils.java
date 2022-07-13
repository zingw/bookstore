package com.example.book.store.utils;

import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityContextHolderUtils {

    public static String getUserName(){
        return (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}
