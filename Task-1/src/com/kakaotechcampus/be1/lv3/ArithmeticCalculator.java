package com.kakaotechcampus.be1.lv3;

import java.util.ArrayList;
import java.util.List;

import static com.kakaotechcampus.be1.lv3.OperatorType.*;

public class ArithmeticCalculator {
    /* 정수 외에도 다른 Type 의 값을 받아들일 수 있도록 설정 */
    List<Double> resultList;

    public ArithmeticCalculator(){
        resultList = new ArrayList<>();
    }

    public <T extends Number> Double getResult(
            T fv, OperatorType operator, T sv
    ) throws RuntimeException{
        double result = 0.0;
        double firstValue = fv.doubleValue();
        double secondValue = sv.doubleValue();

        if(operator == PLUS){
            result = firstValue + secondValue;
        }else if(operator == MINUS){
            result = firstValue - secondValue;
        }else if(operator == MULTIPLY){
            result = firstValue * secondValue;
        }else if(operator == DIVIDE){
            if(secondValue == 0){
                throw new RuntimeException("나눗셈에 0 이 들어갈 수 없습니다.");
            }
            result = firstValue / secondValue;
        }

        resultList.add(result);

        return result;
    }

    public List<Double> getResultList() {
        return resultList;
    }

    public <T extends Number> List<Double> getResultList(T standard){
        double std = standard.doubleValue();
        return resultList.stream()
                .filter(i -> i > std)
                .toList();
    }
}
