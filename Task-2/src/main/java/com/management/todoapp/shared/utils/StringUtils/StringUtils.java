package com.management.todoapp.shared.utils.StringUtils;

public class StringUtils {
    public static String makeSnakeCase(String input){
        if(input == null || input.isEmpty()) return null;

        return input
                .replaceAll("([a-z])([A-Z])", "$1_$2") // 소문자와 대문자 사이에 '_' 추가
                .toLowerCase();
    }
}
