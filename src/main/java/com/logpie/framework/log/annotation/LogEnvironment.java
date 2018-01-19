package com.logpie.framework.log.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.logpie.framework.log.util.LogColor;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogEnvironment {

	public LogLevel classLevel();

	public enum LogLevel {
		TRACE(0, "TRACE"), DEBUG(1, "DEBUG"), INFO(2, "INFO"), ERROR(3, "ERROR");

		private final int num;
		private final String name;

		private LogLevel(int num, String name) {
			this.num = num;
			this.name = name;
		}

		public int getLevel() {
			return num;
		}

		public String getLevelName() { return name; }
	}
}
