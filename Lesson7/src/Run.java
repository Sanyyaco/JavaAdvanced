import java.lang.reflect.InvocationTargetException;

public class Run {
    public static void main(String[] args) throws InstantiationException, IllegalAccessException, InvocationTargetException {
        TestClass testClass = new TestClass();
        testClass.start(MainClass.class);
    }
}
