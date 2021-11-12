package com.sun;

import java.io.*;

public class Test02 {
    public static void main(String[] args) throws IOException {
        String path = "D:\\javapoject\\YGBookStore_homework\\out\\artifacts\\YGBooks_war_exploded\\bookDe\\bid2.txt";
        File file = new File(path);
        Reader reader = new FileReader(file);
        char [] b = new char[1024];
        int  n= -1;
        StringBuffer sb = new StringBuffer("");
        while((n=reader.read(b))!=-1) {
            String str = new String(b,0,n);
            sb.append(str);
        }

        System.out.println(sb.toString());
        reader.close();
    }

}
