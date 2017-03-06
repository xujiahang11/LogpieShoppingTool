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
	public enum LogLevel {
		TRACE(0), DEBUG(1), INFO(2), ERROR(3);

		private final int num;

		private LogLevel(int num) {
			this.num = num;
		}

		public int getLevel() {
			return num;
		}

		public String getLevelName() {
			switch (num) {
			case 0:
				return LogColor.setGrey("TRACE");
			case 1:
				return LogColor.setYellow("DEBUG");
			case 2:
				return LogColor.setGreen("INFO");
			case 3:
				return LogColor.setRed("ERROR");
			}
			return null;
		}
	}

	public LogLevel classLevel();
}
