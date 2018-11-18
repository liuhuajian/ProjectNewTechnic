package com.project.common.core.utils;

import com.project.common.core.BaseApp;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;


/**
 * Created julyzeng
 */
public class FileUtils {

    public static String getCacheDir() {
        return new File(BaseApp.mContext.getExternalCacheDir(), "yyh").getAbsolutePath();
    }

    public static String readTextFromSDcard(String fileName) {

        File file = new File(fileName);
        if (!file.exists()) {
            return null;
        }
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            int availableLength = fileInputStream.available();
            byte[] buffer = new byte[availableLength];
            fileInputStream.read(buffer);
            fileInputStream.close();

            return new String(buffer, "UTF-8");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean saveText2Sdcard(String fileName, String text) {

        File file = new File(fileName);
        File parentFile = file.getParentFile();
        if (!parentFile.exists()) {
            parentFile.mkdirs();
        }
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            bw.write(text);
            bw.flush();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
            file.delete();
            return false;
        }
        return true;
    }

    public static void mkdirs(String path) {
        new File(path).mkdirs();
        try {
            new File(path).createNewFile();
        } catch (IOException e) {

            e.printStackTrace();
        }
    }
}
