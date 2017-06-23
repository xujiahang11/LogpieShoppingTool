package com.logpie.framework.log.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.logpie.framework.log.annotation.LogEnvironment;
import com.logpie.framework.log.annotation.LogEnvironment.LogLevel;

public class LogpieLogger {
	private final String className;
	private LogLevel classLevel;

	LogpieLogger(Class<?> c) {
		className = c.getName();
		if (c.isAnnotationPresent(LogEnvironment.class)) {
			classLevel = c.getAnnotation(LogEnvironment.class).classLevel();
		}
	}

	public void trace(String msg) {
		appendLog(msg, LogLevel.TRACE);
	}

	public void debug(String msg) {
		appendLog(msg, LogLevel.DEBUG);
	}

	public void info(String msg) {
		appendLog(msg, LogLevel.INFO);
	}

	public void error(String msg) {
		appendLog(msg, LogLevel.ERROR);
	}

    public void error(final String msg, final Throwable throwable) {
        appendLog(msg, LogLevel.ERROR);
        if (throwable != null) {
            final StackTraceElement[] elements = throwable.getStackTrace();
            for (final StackTraceElement element : elements) {
                appendLog(element.toString(), LogLevel.ERROR);
            }
        }

    }

	private void appendLog(String msg, LogLevel level) {
		StringBuffer logBuffer = LogpieLoggerFactory
				.getLogBufferOnCurrentThread();
		if (isLogEnabled(level.getLevel())) {
			appendFormattedLog(logBuffer, msg, new Date(),
					level.getLevelName(), Thread.currentThread().getId(),
					className);
		}
	}

	private void appendFormattedLog(StringBuffer logBuffer, String msg,
			Date date, String levelName, long threadId, String className) {

		String formattedDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S")
				.format(date);
		logBuffer.append(LogColor.setGrey(leftPadString(formattedDate, 23)));
		logBuffer.append(LogColor.setGrey("  "));

		logBuffer.append(leftPadString(levelName, 14));
		logBuffer.append(LogColor.setGrey(" --- "));

		logBuffer.append(LogColor.setGrey("[Thread #"));
		logBuffer.append(LogColor.setGrey(String.valueOf(threadId)));
		logBuffer.append(LogColor.setGrey("] "));

		logBuffer.append(LogColor.setCyan(className));
		logBuffer.append(LogColor.setGrey(": "));

		logBuffer.append(msg);
		logBuffer.append("\n");
	}

	private boolean isLogEnabled(int level) {
		if (LogpieLoggerFactory.getGlobalLogEnvironment() == null) {
			if (classLevel == null || level >= classLevel.getLevel()) {
				return true;
			}
		} else if (level >= LogpieLoggerFactory.getGlobalLogEnvironment()
				.getLevel()) {
			return true;
		}
		return false;
	}

	private String leftPadString(String string, int width) {
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
