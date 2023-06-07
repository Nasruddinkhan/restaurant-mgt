package com.mypractice.restaurantmgt.util;

import org.apache.tomcat.util.codec.binary.Base64;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class FileUtils {
    public static final String createFile(String fileContent, String outFilePath, String fileName)  {
        byte[] data = Base64.decodeBase64(fileContent);
        try (OutputStream stream = new FileOutputStream(outFilePath + File.separator+fileName)) {
            stream.write(data);
        }catch (Exception ex){
            throw new RuntimeException(ex.getMessage());
        }
        return fileName;
    }
}
