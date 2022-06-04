package edu.school21.app;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

public class Program {
    public static void main(String[] args) {
        try {
            System.out.println("Classes:");
            List<Class<?>> classes = getClassesInPackage("edu.school21.classes.");
            for (Class<?> myClass : classes) {
                System.out.println(myClass.getSimpleName());
            }
            System.out.println("---------------------");

            System.out.println("Enter class name:");
            System.out.print("-> ");
            Scanner scanner = new Scanner(System.in);
            String name = scanner.nextLine();
            for (Class<?> myClass : classes) {
                if (myClass.getSimpleName().equals(name)) {
                    System.out.println("---------------------");
                    printClassStructure(myClass);
                    System.out.println("---------------------");

                    System.out.println("Let's create an object");
                    Object object = createObject(myClass, scanner);
                    System.out.println("Object created: " + object);
                    System.out.println("---------------------");

                    modifyObject(object, scanner);
                    System.out.println("Object updated: " + object);
                    System.out.println("---------------------");

                    Object returnValue = callMethod(object, scanner);
                    if (returnValue != null) {
                        System.out.println("Method returned: ");
                        System.out.println(returnValue);
                    }

                    return;
                }
            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    private static List<Class<?>> getClassesInPackage(String packageName) {
        try {
            URL resource = Thread.currentThread().getContextClassLoader().getResource(packageName.replace(".", "/"));
            if (resource == null) {
                throw new RuntimeException("Package not found");
            }

            Path packagePath = Paths.get(resource.toURI());
            List<String> classNames = getFileNamesInFolder(packagePath);

            List<Class<?>> classes = new LinkedList<>();
            for (String className : classNames) {
                classes.add(Class.forName(packageName + className.replace(".class", "")));
            }

            return classes;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static List<String> getFileNamesInFolder(Path folder) {
        List<String> list = new LinkedList<>();

        try (Stream<Path> stream = Files.walk(folder)) {
            stream.forEach(path -> {
                try {
                    if (path.toAbsolutePath() != folder.toAbsolutePath()) {
                        if (Files.isDirectory(path)) {
                            System.out.println(path.getFileName());
                        } else {
                            long bytes = Files.size(path);
                            long kilobytes = (long) Math.ceil((double) bytes / 1024);
                            //System.out.printf("%s %d KB\n", path.getFileName(), kilobytes);
                            list.add(path.getFileName().toString());
                        }
                    }
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
            });
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        return list;
    }

    private static void printClassStructure(Class<?> myClass) {
        System.out.println("fields:");
        Field[] fields = myClass.getDeclaredFields();
        for (Field field : fields) {
            System.out.println("\t" + field.getType().getSimpleName() + " " + field.getName());
        }

        System.out.println("methods:");
        Method[] methods = myClass.getDeclaredMethods();
        for (Method method : methods) {
            System.out.print("\t" + method.getReturnType().getSimpleName() + " " + method.getName());

            System.out.print("(");
            Class<?>[] parameterTypes = method.getParameterTypes();
            for (int i = 0; i < parameterTypes.length; i++) {
                System.out.print(parameterTypes[i].getSimpleName());
                if (i < parameterTypes.length - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println(")");
        }
    }

    private static Object convertValue(Class<?> myClass, String value) {
        try {
            return myClass.getMethod("valueOf", String.class).invoke(null, value);
        } catch (Exception e) {
            try {
                //System.out.println(e.getMessage());
                //System.out.println("Using hardcode parsing " + myClass.getSimpleName());
                switch (myClass.getSimpleName()) {
                    case "int":
                        return Integer.parseInt(value);
                    case "long":
                        return Long.parseLong(value);
                    case "boolean":
                        return Boolean.parseBoolean(value);
                    case "float":
                        return Float.parseFloat(value);
                    case "double":
                        return Double.parseDouble(value);
                    case "String":
                        return value;
                    default:
                        return null;
                }
            } catch (Exception e1) {
                throw new RuntimeException(e1);
            }
        }
    }

    private static Object createObject(Class<?> myClass, Scanner scanner) {
        try {
            Object object = myClass.newInstance();

            for (Field field : myClass.getDeclaredFields()) {
                System.out.println(field.getName() + ":");
                System.out.print("-> ");
                field.setAccessible(true);
                field.set(object, convertValue(field.getType(), scanner.nextLine()));
            }

            return object;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void modifyObject(Object object, Scanner scanner) {
        try {
            System.out.println("Enter name of the field for changing:");
            System.out.print("-> ");
            String name = scanner.nextLine();

            Field[] fields = object.getClass().getDeclaredFields();
            for (Field field : fields) {
                if (field.getName().equals(name)) {
                    System.out.println("Enter " + field.getType().getSimpleName() + " value:");
                    System.out.print("-> ");
                    field.setAccessible(true);
                    field.set(object, convertValue(field.getType(), scanner.nextLine()));
                    return;
                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static Object callMethod(Object object, Scanner scanner) {
        try {
            System.out.println("Enter name of the method for call:");
            System.out.print("-> ");
            String s = scanner.nextLine();
            String name = s.substring(0, s.indexOf("("));
            String[] argsToParse = s.substring(s.indexOf("(") + 1, s.indexOf(")")).split(", ");

            Method[] methods = object.getClass().getDeclaredMethods();
            for (Method method : methods) {
                if (method.getName().equals(name) && checkMethodArgs(method, argsToParse)) {
                    Class<?>[] parameterTypes = method.getParameterTypes();
                    Object[] args = new Object[method.getParameterCount()];
                    if (argsToParse.length == args.length) {
                        for (int i = 0; i < args.length; i++) {
                            System.out.println("Enter " + parameterTypes[i].getSimpleName() + " value:");
                            System.out.print("-> ");
                            args[i] = convertValue(parameterTypes[i], scanner.nextLine());
                        }

                        method.setAccessible(true);
                        return method.invoke(object, args);
                    }
                }
            }
            return null;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static boolean checkMethodArgs(Method method, String[] argsToParse) {
        if (method.getParameterCount() != argsToParse.length) {
            return false;
        }

        Class<?>[] parameterTypes = method.getParameterTypes();
        for (int i = 0; i < argsToParse.length; i++) {
            if (!parameterTypes[i].getSimpleName().equals(argsToParse[i])) {
                return false;
            }
        }

        return true;
    }
}
