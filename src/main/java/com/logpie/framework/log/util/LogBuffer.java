package com.logpie.framework.log.util;

import com.logpie.framework.log.annotation.LogEnvironment;

public class LogBuffer {
    private StringBuffer buffer;
    private StringBuffer formattedBuffer;

    public LogBuffer() {
        buffer = new StringBuffer();
        formattedBuffer = new StringBuffer();
    }

    public StringBuffer getBuffer() {
        return buffer;
    }

    public StringBuffer getFormattedBuffer() {
        return formattedBuffer;
    }

    public void append(String msg) {
        buffer.append(msg);
        formattedBuffer.append(msg);
    }

    public void append(String msg, int width) {
        String fixedLengthMsg = leftAlignString(msg, width);
        append(fixedLengthMsg);
    }

    public void append(String msg, LogColor color) {
        buffer.append(msg);
        formattedBuffer.append(LogColor.setColor(msg, color));
    }

    public void append(String msg, LogColor color, int width) {
        String fixedLengthMsg = leftAlignString(msg, width);
        append(fixedLengthMsg, color);
    }

    public void append(LogEnvironment.LogLevel level) {
        String msg = level.getLevelName();
        int width = 5;
        switch (level) {
            case TRACE:
                append(msg, LogColor.GREY, width);
                break;
            case DEBUG:
                append(msg, LogColor.YELLOW, width);
                break;
            case INFO:
                append(msg, LogColor.GREEN, width);
                break;
            case ERROR:
                append(msg, LogColor.RED, width);
                break;
            default:
                return;
        }
    }

    private String leftAlignString(String string, int width) {
        int length = string.length();
        if (length >= width) {
            return string;
        }
        StringBuffer buffer = new StringBuffer();
        buffer.append(string);
        for (int i = 0; i < width - length; i++) {
            buffer.append(" ");
        }
        return buffer.toString();
    }
}
