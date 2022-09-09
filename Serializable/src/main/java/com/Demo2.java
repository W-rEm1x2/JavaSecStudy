package com;

import java.io.*;

public class Demo2 {
    public static class Student implements Serializable {
        private String name;

        public Student(String name) {
            this.name = name;
        }

        private void readObject(java.io.ObjectInputStream objectInput) throws IOException, ClassNotFoundException {
            objectInput.defaultReadObject();

            ProcessBuilder processBuilder = new ProcessBuilder("ipconfig", "/all");
            Process process = processBuilder.start();
            InputStream inputStream = process.getInputStream();
            //gbk转码
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "gbk"));

            String rs;
            while ((rs= bufferedReader.readLine())!=null){
                System.out.println(rs);
            }

        }

    }
    public static void main(String[] args) {
        //序列化
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("a.ser");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(new Student("zxc"));
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //反序列化
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("a.ser"));
            Student student = (Student) objectInputStream.readObject();
            System.out.println(student.name);


        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
