package com.kakaotechcampus.be1.lv1;

import com.kakaotechcampus.be1.lv2.CalculatorLv2;
import com.kakaotechcampus.be1.utils.CalculatorInput;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
  <Level 1 과제>
  클래스를 사용하지 않고, 요구사항을 해결
*/

public class Level1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("[과제Lv.1] 계산기를 실행합니다.");
        while(true) {
            int fv = 0;
            char operator;
            int sv = 0;
            double result = 0.0;
            try {
                System.out.print("첫 번째 숫자를 입력하세요:");
                fv = CalculatorInput.InputInteger(in);

                System.out.print("두 번째 숫자를 입력하세요:");
                sv = CalculatorInput.InputInteger(in);

                System.out.print("사칙 연산을 입력하세요:");
                operator = CalculatorInput.InputArithmeticOperation(in);

                result = CalculatorInput.getResult(fv, operator, sv);
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
                continue;
            }

            System.out.println("결과: " + result);
            System.out.println("더 계산하시겠습니까? (exit 입력 시 종료)");
            String comment = in.next();
            if (comment.equals("exit")) {
                break;
            }
        }
        System.out.println("[과제Lv.1] 계산기를 종료합니다.");

        System.out.println("[과제Lv.2] 계산기를 테스트합니다.");
        CalculatorLv2 calculatorLv2 = new CalculatorLv2(in);

        // * 본래 기능이 잘 수행되는지 Check
        calculatorLv2.Calculate();
        System.out.println("[INFO] 현재까지 결과 출력");
        calculatorLv2.getResultList().forEach(System.out::println);

        /*
            추가적으로 해당 주석을 해제할 경우, 값에 대해서 접근이 안되는 것을 확인할 수 있다. ( 캡슐화 )
            System.out.println(calculatorLv2.resultList);
        */

        // * setter 와 getter 의 기능이 제대로 동작하는지 Check
        System.out.println("[INFO] Getter 기능 확인");
        calculatorLv2.getResultList().forEach(System.out::println);

        System.out.println("[INFO] Setter 기능 확인");
        List<Double> resultList = new ArrayList<>();
        resultList.add(10.0);
        calculatorLv2.setResultList(resultList);
        calculatorLv2.getResultList().forEach(System.out::println);

        System.out.println("[과제Lv.2] 계산기를 종료합니다.");

        in.close();
    }
}
