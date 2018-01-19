package com.logpie.framework.log.util;

import java.io.IOException;
import java.util.HashMap;

import com.logpie.framework.log.annotation.LogEnvironment.LogLevel;

public class LogpieLoggerFactory {
	private static HashMap<Long, LogBuffer> map = new HashMap<>();
	private static LogLevel globalLevel;

	public static LogpieLogger getLogger(Class<?> c) {
		return new LogpieLogger(c);
	}

	public static LogLevel getGlobalLogEnvironment() {
		return globalLevel;
	}

	public static void setGlobalLogEnvironment(String setting) {
		String formattedSetting = setting.toUpperCase();
		switch (formattedSetting) {
		case "TRACE":
			globalLevel = LogLevel.TRACE;
			break;
		case "DEBUG":
			globalLevel = LogLevel.DEBUG;
			break;
		case "INFO":
			globalLevel = LogLevel.INFO;
			break;
		case "ERROR":
			globalLevel = LogLevel.ERROR;
			break;
		default:
			return;
		}
	}

	public static void logToConsole(boolean clearPreviousLog) {
		StringBuffer buffer = getLogBufferOnCurrentThread().getFormattedBuffer();
		System.out.println(getLog(buffer));
		if(clearPreviousLog) clearLog();
	}

	public static void logToFile(String path, boolean clearPreviousLog) throws IOException{
		StringBuffer buffer = getLogBufferOnCurrentThread().getBuffer();
		LogpieLoggerWriter.writeLogToFile(path, getLog(buffer));
		if(clearPreviousLog) clearLog();
	}

	public static void logToFile(boolean clearPreviousLog) throws IOException{
		StringBuffer buffer = getLogBufferOnCurrentThread().getBuffer();
		LogpieLoggerWriter.writeLogToFile("", getLog(buffer));
		if(clearPreviousLog) clearLog();
	}

	public static void mergeLog(Thread thread) {
		LogBuffer preBuffer = map.get(thread.getId());
		preBuffer.append(("Merge Log of Thread #"
				+ Thread.currentThread().getId() + " into Thread #"
				+ thread.getId() + "...").toUpperCase());
		preBuffer.append("\n");
		currentThread.set(preBuffer);
	}

	public static void clearLog() {
		currentThread.remove();
	}

	static String getLog(StringBuffer buffer) {
		StringBuilder builder = new StringBuilder(buffer);
		builder.append(getSeparatorLine());
		return builder.append("\n").toString();
	}

	static LogBuffer getLogBufferOnCurrentThread() {
		return currentThread.get();
	}

	private static String getSeparatorLine() {
		StringBuilder builder = new StringBuilder("\n");
		for (int i = 0; i < 120; i++) builder.append(".");
		return builder.append("\n").toString();
	}

	private static final ThreadLocal<LogBuffer> currentThread = new ThreadLocal<LogBuffer>() {
		@Override
		protected LogBuffer initialValue() {
			LogBuffer buffer = new LogBuffer();
			map.put(Thread.currentThread().getId(), buffer);

			if (globalLevel != null) {
				buffer.append("global log environment --- "
						.toUpperCase() + globalLevel + "\n", LogColor.PURPLE);
			}
			buffer.append("\n");
			return buffer;
		}
	};
}
