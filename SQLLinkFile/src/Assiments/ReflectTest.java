package Assiments;
import java.lang.reflect.*;
import java.util.*;

public class ReflectTest {

    public static void main(String [] args) {
        for (Method method : ReflectTest.class.getMethods()) {
            System.out.println(method.getName());
            Type type = method.getGenericReturnType();
            System.out.println("Return type: " + type.getTypeName());
            if (type instanceof ParameterizedType)
            {
                ParameterizedType pt = (ParameterizedType) type;
                System.out.println("Parameterized: " + pt.getRawType());
                for (Type arg : pt.getActualTypeArguments())
                {
                    System.out.println("  " + arg);
                }
            }
        }
    }

    public static int[] getNumbers() {
    	int [] tom = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14};
        return tom;
    }

    public static List<String> getStrings() {
        return null;
    }
}
