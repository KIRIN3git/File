/*!
 @file IOUtils.java
 ITS AP Forum FUKUOKA

 @author
    Created by Nagakura Hideharu.
 @copyright
    Copyright (c) 2018 Jorudan Co.,Ltd. All rights reserved.
 */
package com.example.shinji_win.file.util;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.SyncFailedException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;

public class IOUtils {

    public static final Charset CHARSET_UTF8 = Charset.forName("UTF-8");

    public static final String FILE_BOOTSTRAP_DATA = "bootstrap_data";

    public static void writeToFile(Context context, String data, String filename) {
        writeToFile(context, data.getBytes(CHARSET_UTF8), filename);
    }

    public static void writeToFile(Context context, byte[] data, String filename) {
        File file = new File(context.getFilesDir(), filename);
        try {
            FileOutputStream os = new FileOutputStream(file);

            os.write(data);
            os.flush();
            // Perform an fsync on the FileOutputStream.
            os.getFD().sync();
            if (os != null) {
                os.close();
            }
        } catch (FileNotFoundException e) {
            Log.w( "DEBUG_DATA", "FileNotFoundException e = " + e );
        } catch (SyncFailedException e) {
            Log.w( "DEBUG_DATA", "SyncFailedException e = " + e );
        } catch (IOException e) {
            Log.w( "DEBUG_DATA", "IOException e = " + e );
        }
    }

    public static String readAsString(Context context, String filename) {
        File file = new File(context.getFilesDir(), filename);
        StringBuilder sb = new StringBuilder();
        try {
            String line;
            InputStream is = new FileInputStream(file);
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, CHARSET_UTF8));
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            if (reader != null) {
                reader.close();
            }
        } catch (FileNotFoundException e) {
            Log.w( "DEBUG_DATA", "FileNotFoundException e = " + e );
        } catch (IOException e) {
            Log.w( "DEBUG_DATA", "IOException e = " + e );
        }

        return sb.toString();
    }


    public static String encode(String text) {
        String encodedString = text;
        try {
            encodedString = URLEncoder.encode(encodedString, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            encodedString = text;
        }

        return encodedString;
    }


}
