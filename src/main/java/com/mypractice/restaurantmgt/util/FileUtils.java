package com.mypractice.restaurantmgt.util;

import com.mypractice.restaurantmgt.enums.DocumentType;
import com.mypractice.restaurantmgt.exception.RestaurantException;
import org.apache.tomcat.util.codec.binary.Base64;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileUtils {
    private FileUtils() {
    }

    public static final String createFile(String fileContent, String outFilePath, String fileName, DocumentType type) {
        byte[] data = Base64.decodeBase64(fileContent);
        String name = CommonUtil.getUniqueNumber() + "_" + fileName+"."+type;
        try (OutputStream stream = new FileOutputStream(outFilePath + File.separator + name)) {
            stream.write(data);
        } catch (Exception ex) {
            throw new RestaurantException(ex.getMessage(), ex);
        }
        return name;
    }

    public static String encodeFileToBase64(String filePath) {
        try {
            return Base64.encodeBase64String(Files.readAllBytes(Paths.get(filePath)));
        } catch (Exception e) {
            throw new RestaurantException(e.getMessage(), e);
        }
    }
}
