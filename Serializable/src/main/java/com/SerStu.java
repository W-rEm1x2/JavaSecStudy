package com;

import java.io.*;

/**
 * 序列化Student类
 */
public class SerStu {
    public static void main(String[] args) {
        Student stu1 = new Student();
        stu1.setName("a");
        stu1.setSno("1");
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("1.ser");//建立数据的输出通道,并写出文件为1.ser
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);//将输出通道传给ObjectOutputStream
            objectOutputStream.writeObject(stu1);// 将对象写入输出流
            objectOutputStream.close();
            fileOutputStream.close();
            System.out.println("stu1对象已序列化到1.txt");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
