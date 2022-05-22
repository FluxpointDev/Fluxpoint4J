package ch.andre601.fluxpoint4j;

import java.util.Objects;

public class CheckUtil{
    
    public static void noneEmpty(String name, String... texts){
        for(String text : texts)
            notEmpty(text, name);
    }
    
    public static void notEmpty(String text, String name){
        check(!text.isEmpty(), name + " may not be empty.");
    }
    
    public static void notNull(Object obj, String name){
        check(obj != null, name + " may not be null.");
    }
    
    public static void notNullOrEmpty(String text, String name){
        check(text != null && !text.isEmpty(), name + " may not be null nor empty.");
    }
    
    public static void isPositive(double value, String name){
        check(value >= 1, name + " may not be less than 1.");
    }
    
    public static void isPositive(int value, String name){
        check(value >= 0, name + " may not be less than 0.");
    }
    
    public static void largerThan(int value, int minValue, String name){
        check(value >= minValue, name + " may not be less than " + minValue + ".");
    }
    
    public static void inRange(int value, int minValue, int maxValue, String name){
        check(value >= minValue && value <= maxValue, name + " may not be less than " + minValue + " or larger than " + maxValue + ".");
    }
    
    public static void check(boolean condition, String message){
        if(!condition)
            throw new IllegalArgumentException(message);
    }
}
