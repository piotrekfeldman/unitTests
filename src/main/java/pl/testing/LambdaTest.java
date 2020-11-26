package pl.testing;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LambdaTest {

    public static void main(String[] args) {
        Calc calc = (a, b) -> a + b;
        Calc calc2 = (a, b) -> a * b;

        System.out.println(calc.calculate(2,5));
        System.out.println(calc2.calculate(2,5));

        BigInteger bg = new BigInteger("2");
        //System.out.println(bg.pow(3));

        Factor factor1 = a -> a * a;
        factor1.factor(2);

        List<Integer> integerList = Arrays.asList(2,5,8);
        integerList.forEach(el -> System.out.println(el));

        List<String> stringList = new ArrayList<>();
        stringList.add("pit");
        stringList.forEach((System.out::println));
        System.out.println("******");
        integerList.forEach(s -> {

            int i = 5;
            s += i;
            System.out.println(s * 10);

        });



    }
}
