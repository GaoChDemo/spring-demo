package top.gaoch.service;

import org.junit.Test;
import top.gaoch.annotation.MyAutowired;
import top.gaoch.controller.UserController;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Stream;

public class DemoTest {

  @Test
  public void testSetField() throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
    UserController userController = new UserController();
    Class<? extends UserController> clazz = userController.getClass();
    //    Field[] declaredFields = clazz.getDeclaredFields();
    //    Arrays.asList(declaredFields).stream().forEach(System.out::println);
    Field userServiceField = clazz.getDeclaredField("userService");
    //    方法一:
    UserService userService = new UserService();
    userServiceField.setAccessible(true);
    userServiceField.set(userController, userService);
    // 方法二:
    //    String name = userServiceField.getName();
    //    String setMethodName = "set" + name.substring(0,1).toUpperCase() + name.substring(1,name.length());
    //    System.out.println("userServiceField: " + userServiceField);
    //    Method method = clazz.getMethod(setMethodName, UserService.class);
    //    UserService userService = new UserService();
    //    method.invoke(userController, userService);

    System.out.println("userController.getUserService(): " + userController.getUserService());
  }

  @Test
  public void testSetField1() throws NoSuchFieldException, IllegalAccessException {
    UserController userController = new UserController();
    Class<? extends UserController> clazz = userController.getClass();
    Field userServiceField = clazz.getDeclaredField("userService");
    UserService userService = new UserService();
    userServiceField.setAccessible(true);
    userServiceField.set(userController, userService);
    System.out.println("userController.getUserService(): " + userController.getUserService());
  }


  @Test
  public void testSetField2() throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
    UserController userController = new UserController();
    Class<? extends UserController> clazz = userController.getClass();
    Field userServiceField = clazz.getDeclaredField("userService");
    String name = userServiceField.getName();
    //依赖set方法
    String setMethodName = "set" + name.substring(0, 1).toUpperCase() + name.substring(1, name.length());
    Method method = clazz.getMethod(setMethodName, UserService.class);
    UserService userService = new UserService();
    method.invoke(userController, userService);
    System.out.println("userController.getUserService(): " + userController.getUserService());
  }



  @Test
  public void testnnotation()  {
    UserController userController = new UserController();
    Class<? extends UserController> clazz = userController.getClass();

    Stream.of(clazz.getDeclaredFields()).forEach(field -> {
      MyAutowired annotation = field.getAnnotation(MyAutowired.class);
      if (annotation != null) {
        field.setAccessible(true);
        Class<?> type = field.getType();
        try {
          Object object = type.getConstructor().newInstance();
          field.set(userController, object);
        } catch (InstantiationException e) {
          e.printStackTrace();
        } catch (IllegalAccessException e) {
          e.printStackTrace();
        } catch (InvocationTargetException e) {
          e.printStackTrace();
        } catch (NoSuchMethodException e) {
          e.printStackTrace();
        }
      }
    });
    System.out.println("userController.getUserService(): " + userController.getUserService());
    System.out.println("userController.getUserService2(): " + userController.getUserService2());
  }
}
