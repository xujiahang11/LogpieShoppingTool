package com.logpie.framework.log.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LogpieLoggerWriter {

    private final static String directory = System.getProperty("user.dir");

    public static String getDefaultPath() {
        return directory + "/logs/";
    }

    public static String getFileName() {
        return new SimpleDateFormat("yyyyMMddHH'.txt'").format(new Date());
    }

    synchronized static void writeLogToFile(String path, String log) throws IOException {
        if(path == null || path.isEmpty()) {
            path = getDefaultPath();
        }
        File file = new File(path, getFileName());
        PrintWriter writer = new PrintWriter(new FileWriter(file, true));
        writer.print(log);
        writer.close();
    }
}
