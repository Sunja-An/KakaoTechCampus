package com.kakaotechcampus.be1.utils;

import java.util.Scanner;

public class CalculatorInput {
    public static int CheckInteger(Scanner in, boolean isDivide) throws RuntimeException{
        String fv = in.next();
        boolean flag = false;
        int value = 0;
        while(!flag) {
            try {
                value = Integer.parseInt(fv);
                if (value < 0) {
                    throw new RuntimeException("음수 값은 받을 수 없습니다.");
                }
                if(value == 0 && isDivide){
                    throw new RuntimeException("나눗셈에는 0 을 넣을 수 없습니다!.");
                }
                flag = true;
            } catch (NumberFormatException e) {
                System.out.println("값에 대한 입력이 잘못되었습니다.");
                throw new RuntimeException("🚀 다시 입력해주세요!!");
            }
        }
        return value;
    }

    public static char CheckCalculatorText(Scanner in){
        String value = in.next();
        boolean flag = false;
        while(!flag) {
            switch (value) {
                case "+":
                case "-":
                case "*":
                case "/":
                    flag = true;
                    break;
                default:
                    System.out.println("1번째 값에 대한 입력이 잘못되었습니다.");
                    System.out.println("다시 입력해주세요.");
                    value = in.next();
            }
        }
        return value.charAt(0);
    }
}
