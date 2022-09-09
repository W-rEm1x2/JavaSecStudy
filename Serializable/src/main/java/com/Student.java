package com;

import java.io.IOException;
import java.io.Serializable;

public class Student implements Serializable {//实现序列化类
    private String name;
    private String sno;
    public Student(){

    }
    public Student(String name, String sno) {
        this.name = name;
        this.sno = sno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", sno='" + sno + '\'' +
                '}';
    }
    public void readObject(java.io.ObjectInputStream inputStream)throws IOException,ClassNotFoundException {
        //执行默认的readObject()方法
        inputStream.defaultReadObject();
        Runtime.getRuntime().exec("calc.exe");
    }
}
