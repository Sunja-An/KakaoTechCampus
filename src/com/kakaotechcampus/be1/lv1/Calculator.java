package com.kakaotechcampus.be1.lv1;

import java.util.Scanner;

public class Calculator {
    Scanner sc;

    public Calculator(){
        this.sc = new Scanner(System.in);
    }

    public void Calculate(){
        while(true) {
            int fv = 0;
            char operator;
            int sv = 0;

            fv = CheckInteger(sc);
            operator = CheckCalculatorText(sc);
            sv = CheckInteger(sc);

            System.out.println("결과: " + Calculate(fv, operator, sv));

            System.out.println("더 계산하시겠습니까? (exit 입력 시 종료)");
            String comment = sc.next();

            if(comment.equals("exit")) {
                break;
            }
        }
        sc.close();
    }

    private int CheckInteger(Scanner sc){
        String fv = sc.next();
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
                System.out.println("다시 입력해주세요.");
                fv = sc.next();
            }
        }
        return value;
    }

    private int CheckInteger(Scanner sc, boolean isDivide){
        String fv = sc.next();
        boolean flag = false;
        int value = 0;
        while(!flag) {
            try {
                value = Integer.parseInt(fv);
                if (value < 0) {
                    throw new RuntimeException("음수 값은 받을 수 없습니다.");
                }
                if(value == 0 && isDivide){
                    throw new RuntimeException("0 으로 나눌 수 없습니다.");
                }
                flag = true;
            } catch (NumberFormatException e) {
                System.out.println("1번째 값에 대한 입력이 잘못되었습니다.");
                System.out.println("다시 입력해주세요.");
                fv = sc.next();
            }
        }
        return value;
    }

    private char CheckCalculatorText(Scanner sc){
        String value = sc.next();
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
                    value = sc.next();
            }
        }
        return value.charAt(0);
    }

    private int Calculate(int fv, char operator, int sv){
        if(operator == '+'){
            return fv + sv;
        }else if(operator == '-'){
            return fv - sv;
        }else if(operator == '*'){
            return fv * sv;
        }else if(operator == '/'){
            return fv / sv;
        }else{
            return 0;
        }
    }
}
