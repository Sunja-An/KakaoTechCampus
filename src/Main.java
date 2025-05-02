import com.kakaotechcampus.be1.lv1.CalculatorWithoutClass;
import com.kakaotechcampus.be1.lv2.CalculatorWithClass;
import com.kakaotechcampus.be1.lv3.ArithmeticCalculator;
import com.kakaotechcampus.be1.utils.Calculator;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        /* Level 1 과제 */
        System.out.println("[과제Lv.1] 계산기를 실행합니다.");
        Calculator level1 = new CalculatorWithoutClass(in);
        level1.Calculate();
        System.out.println("[과제Lv.1] 계산기를 종료합니다.");

        /* Level 2 과제 */
        System.out.println("[과제Lv.2] 계산기를 실행합니다.");
        Calculator level2 = new CalculatorWithClass(in);
        level2.Calculate();
        System.out.println("[과제Lv.2] 계산기를 종료합니다.");

        /* Level 3 과제 */
        System.out.println("[과제Lv.3] 계산기를 실행합니다.");
        Calculator level3 = new ArithmeticCalculator(in);
        level3.Calculate();
        System.out.println("[과제Lv.3] 계산기를 종료합니다.");

        in.close();
    }
}
