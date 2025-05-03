package com.kakaotechcampus.be1.lv3;

public enum OperatorType {
    PLUS('+'),
    MINUS('-'),
    MULTIPLY('*'),
    DIVIDE('/');

    private final char operator;

    OperatorType(char operator){
        this.operator = operator;
    }

    public static OperatorType getOperatorType(char operator){
        if(operator == '+'){
            return PLUS;
        }else if(operator == '-'){
            return MINUS;
        }else if(operator == '*'){
            return MULTIPLY;
        }else if(operator == '/'){
            return DIVIDE;
        }else{
            return null;
        }
    }
}
