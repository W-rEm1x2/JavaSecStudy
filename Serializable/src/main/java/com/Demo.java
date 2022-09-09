package com;

import java.io.*;

public class Demo {
    public static void main(String args[]) throws Exception{
        //序列化
        //定义myObj对象
        MyObject myObj = new MyObject();
        myObj.setName("1");
        //创建一个包含对象进行反序列化信息的”object”数据文件
        ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("object.ser"));
        //writeObject()方法将myObj对象写入object文件
        os.writeObject(myObj);
        os.close();

        //反序列化
        //从文件中反序列化obj对象
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("object.ser"));

        //恢复对象
        MyObject myObject2=(MyObject)ois.readObject();
       // System.out.println(myObject2.getName());


        ois.close();
    }

    static class MyObject implements Serializable {
        public String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        //重写readObject()方法
        private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException{
            //执行默认的readObject()方法
            in.defaultReadObject();
            //执行打开计算器程序命令
            Runtime.getRuntime().exec("calc.exe");
        }
    }
}
