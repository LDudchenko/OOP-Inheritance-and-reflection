import java.lang.annotation.Annotation;
import java.lang.reflect.*;


public class Main {
    public static void main(String[] args) {
        Class reflected = FractionComplexNumber.class;

        //Получаем суперкласс
        Class reflectedSuperclass = reflected.getSuperclass();

        //Задание №1
        //Инициализируем и вызываем конструкторы для базового и производного класса
        System.out.println("Task 1:");
        Fraction fraction = new Fraction(31, 39);
        FractionComplexNumber fractionComplexNumber = new FractionComplexNumber(12, 14, -12, 18);
        fraction.print();
        fractionComplexNumber.print();
        System.out.println();

        //Инициализируем и вызываем конструкторы для базового класса с помошью рефлексии
        Constructor fractionConstructor = null;
        try {
            fractionConstructor = reflectedSuperclass.getConstructor(int.class, int.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        Fraction fractionObject = null;
        try {
            fractionObject = (Fraction) fractionConstructor.newInstance(4, 2);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        //Инициализируем и вызываем конструкторы для производного класса с помощью рефлексии
        Constructor fractionComplexNumberConstructor = null;
        try {
            fractionComplexNumberConstructor = reflected.getConstructor(int.class, int.class, int.class, int.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        Fraction fractionComplexNumberObject = null;
        try {
            fractionComplexNumberObject = (Fraction) fractionComplexNumberConstructor.newInstance(4, 2, 3, 8);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        //Задание №2
        System.out.println("Task 2:");
        //Работаем со всеми методами и анотациями базового класса
        System.out.println("Methods with annotation for Fraction class: ");
        Method[] methods = reflectedSuperclass.getDeclaredMethods();
        for (Method method : methods) {
            Annotation annotation = method.getAnnotation(MyAnnotation.class);
            if (annotation != null && annotation.annotationType() == MyAnnotation.class) {
                Class<?>[] pType = method.getParameterTypes();
                Object[] params = new Object[pType.length];
                for (int i = 0; i < pType.length; i++) {
                    if (pType[i].toString().equals("class Fraction")) {
                        params[i] = new Fraction(2, 3);
                    }
                }
                try {
                    method.invoke(fractionObject, params);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }

        //Работаем со всеми методами и анотациями базового класса
        System.out.println("Methods with annotation for FractionComplexNumber class: ");
        Method[] methodsDerivedClass = reflected.getDeclaredMethods();
        for (Method method : methodsDerivedClass) {
            Annotation annotation = method.getAnnotation(MyAnnotation.class);
            if (annotation != null && annotation.annotationType() == MyAnnotation.class) {
                Class<?>[] pType = method.getParameterTypes();
                Object[] params = new Object[pType.length];
                for (int i = 0; i < pType.length; i++) {
                    if (pType[i].toString().equals("class FractionComplexNumber")) {
                        params[i] = new FractionComplexNumber(1, 1, 2, 3);
                    }
                }
                try {
                    method.invoke(fractionComplexNumberObject, params);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println();

        //Задание №3
        System.out.println("Task 3:");
        //Получаем имя  произвдного класса
        System.out.println("Reflected class name: " + reflected.getName());

        //Получаем имя базового класса
        System.out.println("\nFractionComplexNumber superclass: " + reflectedSuperclass.getName());

        //Получаем список конструкторов с их параметрами для базового класса
        Constructor[] constructorsSuperClass = reflectedSuperclass.getConstructors();
        for (Constructor constructor : constructorsSuperClass) {
            Class[] parametrType = constructor.getParameterTypes();
            System.out.print("\nFraction Constructors Parameters: ");
            for (Class parametr : parametrType) {
                System.out.print(parametr.getName() + " ");
            }
        }
        System.out.println();

        //Получаем список конструкторов с их параметрами для производного класса
        Constructor[] constructorsDerivedClass = reflected.getConstructors();
        for (Constructor constructor : constructorsDerivedClass) {
            Class[] parametrType = constructor.getParameterTypes();
            System.out.print("\nFractionComplexNumber Constructors Parameters: ");
            for (Class parametr : parametrType) {
                System.out.print(parametr.getName() + " ");
            }
        }
        System.out.println("\n");

        //Получаем модификаторы класса (базового и производного)
        int classSuperModifiers = reflectedSuperclass.getModifiers();
        System.out.println("Modifier of Fraction: " + Modifier.toString(classSuperModifiers));
        int classDerivedModifiers = reflected.getModifiers();
        System.out.println("Modifier of FractionComplexNumber: " + Modifier.toString(classDerivedModifiers));
    }
}