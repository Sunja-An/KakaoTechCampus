package com.kakaotechcampus.be1.lv1;

import com.kakaotechcampus.be1.utils.Calculator;
import com.kakaotechcampus.be1.utils.CalculatorInput;

import java.util.Scanner;

public class CalculatorWithoutClass implements Calculator{
    private final Scanner in;

    public CalculatorWithoutClass(Scanner in){
        this.in = in;
    }

    @Override
    public void Calculate() {
        while(true) {
            int fv = 0;
            char operator;
            int sv = 0;
            try {
                System.out.print("첫 번째 숫자를 입력하세요:");
                fv = CalculatorInput.CheckInteger(in, false);

                System.out.print("두 번째 숫자를 입력하세요:");
                sv = CalculatorInput.CheckInteger(in, true);

                System.out.print("사칙 연산을 입력하세요:");
                operator = CalculatorInput.CheckCalculatorText(in);

            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
                continue;
            }
            System.out.println("결과: " + getResult(fv, operator, sv));
            System.out.println("더 계산하시겠습니까? (exit 입력 시 종료)");
            String comment = in.next();
            if (comment.equals("exit")) {
                break;
            }
        }
    }

    @Override
    public int getResult(int fv, char operator, int sv) {
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
