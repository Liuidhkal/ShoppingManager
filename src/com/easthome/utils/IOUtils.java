package com.easthome.utils;

import java.io.*;
import java.util.UUID;

public class IOUtils {
    public static final String FILENAME = "D:\\JAVALianxi\\MySQLConfig\\";
    public static String imgLoad(InputStream inputStream, String path) throws IOException {
        byte[] bytes = new byte[1024];

        File file = new File(FILENAME);
        //检查是否存在这个目录
        if (!file.exists()){
            //不存在这个目录，创建
            file.mkdirs();
        }

        //设置一个随机名字
        String uuName = UUID.randomUUID().toString();

        //输出流
        FileOutputStream out =
                new FileOutputStream( FILENAME+uuName+ path);
        int count = 0;
        while ( (count = inputStream.read(bytes)) != -1 ){
            out.write(bytes,0,count);
        }
        out.close();
        inputStream.close();
        return uuName+path;
    }
}
