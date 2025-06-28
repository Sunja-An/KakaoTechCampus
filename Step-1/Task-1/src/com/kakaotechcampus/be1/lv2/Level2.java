package com.kakaotechcampus.be1.lv2;

import java.util.Scanner;

public class Level2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("[과제Lv.2] 계산기를 실행합니다.");
        CalculatorLv2 level2 = new CalculatorLv2(in);
        level2.Calculate();
        System.out.println("[과제Lv.2] 계산기를 종료합니다.");
    }
}
