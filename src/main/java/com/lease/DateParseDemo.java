package com.lease;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.LineIterator;
import org.apache.commons.lang3.CharSet;
import org.apache.commons.lang3.StringUtils;

import java.io.*;

/**
 * Description:
 * author: yu.hb
 * Date: 2019-02-15
 */
public class DateParseDemo {

    public static void main(String[] args) {
        File src = new File("D://from.txt");
        String srcAbsolutePath = src.getAbsolutePath();
        String targetPath = srcAbsolutePath.substring(0,srcAbsolutePath.lastIndexOf(".")).concat("_parse.txt");
        try {
            File target = new File(targetPath);
            if (!target.exists()) {
                target.createNewFile();
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(src)));
            for(LineIterator lineIterator = IOUtils.lineIterator(reader); lineIterator.hasNext();){
                StringBuilder dataStr = new StringBuilder();
                // 读取源文件中每一行
                String[] parseDataArray = StringUtils.split(lineIterator.nextLine(),"\\|");
                for (String data : parseDataArray) {
                    dataStr.append(data).append("\r\n");
                }
                // 写入目标文件
                FileUtils.writeStringToFile(target, dataStr.toString(),"UTF-8",true);
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
