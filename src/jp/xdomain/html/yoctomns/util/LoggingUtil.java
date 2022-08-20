package jp.xdomain.html.yoctomns.util;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;

public class LoggingUtil {
    private static final String LOG_FILE_DIRECTORY = "/log/system/system.log";

    public static void println(String str) {
        try (BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(
                new FileOutputStream(LoggingUtil.class.getResource(LOG_FILE_DIRECTORY).getPath(), true)))) {
            LocalDateTime now = LocalDateTime.now();
            writer.write(String.format("(%s)\t%s", now, str));
            writer.newLine();
        } catch (FileNotFoundException e) {
            System.err.println("指定されたファイルが見つかりませんでした。");
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            System.err.println("文字のエンコーディングがサポートされていません。");
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("ファイルの書き込みエラーが発生しました。");
            e.printStackTrace();
        }
    }
}
