package com.test.myreflect.phone;

import com.test.myreflect.phone.ms.MobieService;
import com.test.myreflect.phone.ms.hms.HMS;
import com.test.myreflect.phone.os.OperateSystem;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Properties;

public class PhoneImpl {
    public static void main(String[] args) throws IOException, ClassNotFoundException, NoSuchFieldException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        // 创建输入流
        String phoneconfigFileName = "phoneconfig.properties";
        InputStream inputStream = PhoneImpl.class.getClassLoader().getResourceAsStream(phoneconfigFileName);

        // 创建propertier实例来加载输入流
        Properties prop = new Properties();
        prop.load(inputStream);

        // 获取配置信息
        String msClassName = prop.getProperty("phoneMS");
        String osClassName = prop.getProperty("phoneOS");
        String phoneName = prop.getProperty("Name");

        // 获取ms、os以及phone 类的实例
        Class<MobieService> mobieServiceClass =(Class<MobieService>) Class.forName(msClassName);  // 使用强制类型转换转为Class<MobileService>
        Class<OperateSystem> operateSystemClass = (Class<OperateSystem>) Class.forName(osClassName);
        Class<Phone> phoneClass = Phone.class;

        // 获取phone 的属性 ms、os、phoneName
        Field msField = phoneClass.getDeclaredField("ms");
        Field osField = phoneClass.getDeclaredField("os");
        msField.setAccessible(true);   // 让私有属性可以被读写
        osField.setAccessible(true);   // 让私有属性可以被读写


        // 获取方法
        Method printMethod = phoneClass.getDeclaredMethod("showMS_OS");

        // 对象实例化
//        Constructor phoneConstructor = phoneClass.getConstructor();
//        Phone phone = (Phone) phoneConstructor.newInstance(phoneName);
        Phone phone = phoneClass.newInstance();
        Constructor<MobieService> mobieServiceConstructor = mobieServiceClass.getConstructor();
        MobieService mobieService =mobieServiceConstructor.newInstance();
        Constructor<OperateSystem> operateSystemConstructor = operateSystemClass.getConstructor();
        OperateSystem operateSystem = operateSystemConstructor.newInstance();

        // 组装
        msField.set(phone,mobieService);
        osField.set(phone,operateSystem);
        phone.setPhoneName(phoneName);

        // 应答
        printMethod.invoke(phone);




    }
}
