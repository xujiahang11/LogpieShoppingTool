package com.logpie.framework.log.util;

import java.util.HashMap;

import com.logpie.framework.log.annotation.LogEnvironment.LogLevel;

public class LogpieLoggerFactory {
	private static HashMap<Long, StringBuffer> map = new HashMap<Long, StringBuffer>();
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

	public static void outputLog() {
		StringBuffer buffer = currentThread.get();
		buffer.append("\n");
		for (int i = 0; i < 100; i++) {
			buffer.append(".");
		}
		buffer.append("\n");
		System.out.println(buffer.toString());
	}

	public static void outputLogAndClear() {
		outputLog();
		clearLog();
	}

	public static void clearLog() {
		currentThread.remove();
	}

	public static void mergeLog(Thread thread) {
		StringBuffer preBuffer = map.get(thread.getId());
		preBuffer.append(("Merge Log of Thread #"
				+ Thread.currentThread().getId() + " into Thread #"
				+ thread.getId() + "...").toUpperCase());
		preBuffer.append("\n");
		currentThread.set(preBuffer);
	}

	static StringBuffer getLogBufferOnCurrentThread() {
		return currentThread.get();
	}

	private static final ThreadLocal<StringBuffer> currentThread = new ThreadLocal<StringBuffer>() {
		@Override
		protected StringBuffer initialValue() {
			StringBuffer buffer = new StringBuffer();
			map.put(Thread.currentThread().getId(), buffer);

			if (globalLevel == null) {
				buffer.append(LogColor
						.setPurple("Global log environment is not defined..."
								.toUpperCase()));
			} else {
				buffer.append(LogColor.setPurple("Global log environment --- "
						.toUpperCase() + globalLevel));
			}
			buffer.append("\n\n");
			return buffer;
		}
	};
}
