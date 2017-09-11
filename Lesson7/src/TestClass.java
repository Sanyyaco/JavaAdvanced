import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class TestClass {

    public void start(Class type) throws IllegalAccessException, InstantiationException, InvocationTargetException {
        Class<BeforeSuite> beforeSuite = BeforeSuite.class;
        Class<AfterSuite> afterSuite = AfterSuite.class;

        runMethodWithSpecificAnnotation(beforeSuite, type);
        runTestMethods(type);
        runMethodWithSpecificAnnotation(afterSuite, type);

    }

    public void runMethodWithSpecificAnnotation(Class annotation, Class type) throws IllegalAccessException, InstantiationException, InvocationTargetException {
        int counter1 = 0;
        int counter2 = 0;
        Object instance = type.newInstance();
        Method[] methods = type.getDeclaredMethods();
        for (Method m : methods) {
            Annotation methodAnnotation = m.getAnnotation(annotation);
            if(methodAnnotation != null){
                if(m.getAnnotation(BeforeSuite.class) != null)counter1++;
                if(m.getAnnotation(AfterSuite.class) != null)counter2++;
                if(counter1 >= 2 || counter2 >=2 )throw new RuntimeException("Ошибка аннотирования: BeforeSuite " +
                        "или AfterSuite использовано больше 1-го раза");
            }
        }

        for (Method m : methods) {
            Annotation methodAnnotation = m.getAnnotation(annotation);
            if(methodAnnotation != null){
                m.invoke(instance);
            }
        }
    }

    public void runTestMethods(Class type) throws IllegalAccessException, InstantiationException {
        Object instance = type.newInstance();
        Method[] methods = type.getDeclaredMethods();

        Map <Integer, Method> map = new TreeMap<>();

        for (Method m : methods) {
            if(m.getAnnotation(Test.class) != null ){
                Test test = m.getAnnotation(Test.class);
                int index =test.priority();
                map.put(index,m);
            }
        }

        for(Map.Entry<Integer, Method> entry : map.entrySet()){
            try {
                entry.getValue().invoke(instance);
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }

    }
}
