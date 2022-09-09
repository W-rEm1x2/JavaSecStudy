package com;

import java.io.IOException;
import java.io.InputStream;

public class M {
    public static void main(String[] args) {
        try {
            Process process = Runtime.getRuntime().exec("ipconfig");

            InputStream inputStream = process.getInputStream();
            int c;
            StringBuilder cmdReturn=new StringBuilder();
            while ((c=inputStream.read())!=-1){
                cmdReturn.append((char)c);
            }
            System.out.println(cmdReturn);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
