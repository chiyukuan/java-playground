package my;

import java.util.function.Function;

public class FIFunction {

    public static void main(String[] args) {
        FIFunctionApply();
        FIFunctionCompose();
        FIFunctionAndThen();
        FIFunctionIdentity();
    }

    public static void FIFunctionApply() {
        System.out.println("FIFunctionApply");
        Function<Integer,String> converter = (i) -> Integer.toString(i);

        System.out.println(converter.apply(3).length());
        System.out.println(converter.apply(30).length());
    }

    public static void FIFunctionCompose() {
        System.out.println("FIFunctionCompose");
        Function<Integer,String> converter = (i)-> Integer.toString(i);
        Function<String, Integer> reverse = (s)-> Integer.parseInt(s);

        System.out.println(converter.apply(3).length());
        System.out.println(converter.compose(reverse).apply("30").length());
    }

    public static void FIFunctionAndThen() {
        System.out.println("FIFunctionAndThen");
        Function<Integer,String> converter = (i)-> Integer.toString(i);
        Function<String, Integer> reverse = (s)-> Integer.parseInt(s);

        System.out.println(converter.apply(3).length());
        System.out.println(converter.andThen(reverse).apply(30).byteValue());
    }

    public static void FIFunctionIdentity() {
        System.out.println("FIFunctionIdentity");
        Function<Integer,Integer> id = Function.identity();
        System.out.println(id.apply(3));
    }
}
