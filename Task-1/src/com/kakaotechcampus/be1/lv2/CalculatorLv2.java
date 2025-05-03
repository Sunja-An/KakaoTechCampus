package com.kakaotechcampus.be1.lv2;

import com.kakaotechcampus.be1.utils.CalculatorInput;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CalculatorLv2 {
    private final Scanner in;
    private List<Double> resultList;

    public CalculatorLv2(Scanner in){
        this.in = in;
        this.resultList = new ArrayList<>();
    }

    public void Calculate() {
        while(true) {
            int fv = 0;
            char operator = '+';
            int sv = 0;
            double result = 0.0;
            try {
                System.out.print("첫 번째 숫자를 입력하세요:");
                fv = CalculatorInput.InputInteger(in);

                System.out.print("두 번째 숫자를 입력하세요:");
                sv = CalculatorInput.InputInteger(in);

                System.out.print("사칙 연산을 입력하세요:");
                operator = CalculatorInput.InputArithmeticOperation(in);

                result = getResult(fv, operator, sv);
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
                continue;
            }

            System.out.println("결과: " + result);
            resultList.add(result);

            System.out.println("다음 과정에 대해서 선택해주세요! ( Ex: 1 )");
            System.out.println("0. 아무 키나 입력하면, 계산기가 계속 실행됩니다!");
            System.out.println("1. 가장 오래된 기록 삭제");
            System.out.println("2. 기록 열람하기");
            System.out.println("3. 계산기 종료하기");
            String comment = in.next();

            if(comment.equals("1")){
                DeleteFirstResult();
                System.out.println("[SUCCESS] 값이 삭제되었습니다.");
            } else if (comment.equals("2")) {
                System.out.println("[INFO] 기록 열람");
                getResultList().forEach(System.out::println);
                System.out.println("[INFO] 열람 종료");
            }else if(comment.equals("3")){
                break;
            }
        }
    }

    public double getResult(int fv, char operator, int sv) {
        if(operator == '+'){
            return fv + sv;
        }else if(operator == '-'){
            return fv - sv;
        }else if(operator == '*'){
            return fv * sv;
        }else if(operator == '/'){
            if(sv == 0){
                throw new RuntimeException("0 으로 나눌 수 없습니다.");
            }
            return (double)fv / sv;
        }else{
            return 0;
        }
    }

    public void DeleteFirstResult(){
        if(resultList.isEmpty()){
            return;
        }
        resultList.remove(0);
    }

    public List<Double> getResultList() {
        return resultList;
    }

    public void setResultList(List<Double> resultList) {
        this.resultList = resultList;
    }
}
