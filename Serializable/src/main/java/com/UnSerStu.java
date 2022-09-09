package com;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

/**
 * 反序列化stu
 */
public class UnSerStu {
    public static void main(String[] args) throws Exception {
        Student stu;
        FileInputStream fileInputStream = new FileInputStream("1.ser");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        stu =(Student) objectInputStream.readObject();
        System.out.println(stu.getName());
        objectInputStream.close();
        fileInputStream.close();

    }
}
