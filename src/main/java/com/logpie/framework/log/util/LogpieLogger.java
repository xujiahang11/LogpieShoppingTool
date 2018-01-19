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
		LogBuffer logBuffer = LogpieLoggerFactory.getLogBufferOnCurrentThread();

		if (isLogEnabled(level.getLevel())) {
			String formattedDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S")
					.format(new Date());
			logBuffer.append(formattedDate, LogColor.GREY, 23);
			logBuffer.append("  ");

			logBuffer.append(level);
			logBuffer.append(" --- ", LogColor.GREY);
			logBuffer.append("[Thread #" + String.valueOf(Thread.currentThread().getId()) + "]", LogColor.GREY, 14);

			logBuffer.append(className, LogColor.CYAN);
			logBuffer.append(" : ", LogColor.GREY);

			logBuffer.append(msg);
			logBuffer.append("\n");
		}
	}

	private boolean isLogEnabled(int level) {
		if (LogpieLoggerFactory.getGlobalLogEnvironment() == null) {
			if (classLevel == null || level >= classLevel.getLevel()) {
				return true;
			}
		} else if (level >= LogpieLoggerFactory.getGlobalLogEnvironment().getLevel()) {
			return true;
		}
		return false;
	}
}
