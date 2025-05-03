package com.kakaotechcampus.be1.utils;

import java.util.Scanner;

public class CalculatorInput {
    public static int InputInteger(Scanner in) throws RuntimeException{
        String fv = in.next();
        boolean flag = false;
        int value = 0;
        while(!flag) {
            try {
                value = Integer.parseInt(fv);
                if (value < 0) {
                    throw new RuntimeException("음수 값은 받을 수 없습니다.");
                }
                flag = true;
            } catch (NumberFormatException e) {
                System.out.println("값에 대한 입력이 잘못되었습니다.");
                throw new RuntimeException("🚀 다시 입력해주세요!!");
            }
        }
        return value;
    }

    public static char InputArithmeticOperation(Scanner in) throws RuntimeException{
        String value = in.next();
        boolean flag = false;
        while(!flag) {
            flag = switch (value) {
                case "+", "-", "*", "/" -> true;
                default -> {
                    System.out.println("사칙연산 값에 대한 입력이 잘못되었습니다.");
                    throw new RuntimeException("다시 입력해주세요.");
                }
            };
        }
        return value.charAt(0);
    }

    public static double getResult(int fv, char operator, int sv){
        double result = 0.0;
        if(operator == '+'){
            result = fv + sv;
        }else if(operator == '-'){
            result = fv - sv;
        }else if(operator == '*'){
            result = fv * sv;
        }else if(operator == '/'){
            if(sv == 0){
                throw new RuntimeException("0 으로 나눌 수 없습니다.");
            }
            result = (double)fv / sv;
        }
        return result;
    }

    public static Number inputNumber(Scanner in) throws RuntimeException{
        if(in.hasNextInt()){
            return in.nextInt();
        }else if(in.hasNextDouble()){
            return in.nextDouble();
        }
        throw new RuntimeException("변환 가능한 값이 아닙니다!");
    }


}
