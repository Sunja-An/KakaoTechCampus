package com.kakaotechcampus.be1.lv3;

import com.kakaotechcampus.be1.utils.CalculatorInput;

import java.util.List;
import java.util.Scanner;

/* Level 3 과제 */
public class Level3 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Number fv;
        Number sv;
        char opearator;
        Number result;

        ArithmeticCalculator arithmeticCalculator = new ArithmeticCalculator();
        while(true){
            try {
                System.out.println("[과제Lv.3] 계산기를 실행합니다.");
                System.out.print("첫 번째 숫자를 입력하세요!");
                fv = CalculatorInput.inputNumber(in);

                System.out.print("두 번째 숫자를 입력하세요:");
                sv = CalculatorInput.inputNumber(in);

                System.out.print("사칙 연산을 입력하세요!");
                System.out.print("Ex) +, -, *, /");
                opearator = CalculatorInput.InputArithmeticOperation(in);

                result = arithmeticCalculator.getResult(
                        fv,
                        OperatorType.getOperatorType(opearator),
                        sv
                );

                System.out.println("결과: " + result);

                System.out.println("다음 과정에 대해서 선택해주세요! ( Ex: 1 )");
                System.out.println("0. 아무 키나 입력하면, 계산기가 계속 실행됩니다!");
                System.out.println("1. 입력한 값보다 큰 기록을 가져옵니다!");
                System.out.println("2. 기록 열람하기");
                System.out.println("3. 계산기 종료하기");

                String comment = in.next();
                if(comment.equals("1")){
                    System.out.println("[INFO] 값을 입력해주세요");
                    int std = CalculatorInput.InputInteger(in);
                    List<Double> resultList = arithmeticCalculator.getResultList(std);
                    System.out.println(resultList);
                }else if(comment.equals("2")){
                    System.out.println("[INFO] 전체 기록 열람");
                    System.out.println(arithmeticCalculator.getResultList());
                }else if(comment.equals("3")){
                    break;
                }
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }
        }

        System.out.println("[과제Lv.3] 계산기를 종료합니다.");
    }
}
