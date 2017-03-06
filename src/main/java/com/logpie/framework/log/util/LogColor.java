package com.logpie.framework.log.util;

public class LogColor {
	private static final String ANSI_RESET = "\u001B[0m";
	private static final String ANSI_GREY = "\u001B[90m";
	private static final String ANSI_RED = "\u001B[31m";
	private static final String ANSI_GREEN = "\u001B[32m";
	private static final String ANSI_YELLOW = "\u001B[33m";
	private static final String ANSI_BLUE = "\u001B[34m";
	private static final String ANSI_PURPLE = "\u001B[35m";
	private static final String ANSI_CYAN = "\u001B[36m";
	private static final String ANSI_WHITE = "\u001B[37m";

	public static String setGrey(String msg) {
		return setHelper(msg, ANSI_GREY);
	}

	public static String setRed(String msg) {
		return setHelper(msg, ANSI_RED);
	}

	public static String setGreen(String msg) {
		return setHelper(msg, ANSI_GREEN);
	}

	public static String setYellow(String msg) {
		return setHelper(msg, ANSI_YELLOW);
	}

	public static String setBlue(String msg) {
		return setHelper(msg, ANSI_BLUE);
	}

	public static String setPurple(String msg) {
		return setHelper(msg, ANSI_PURPLE);
	}

	public static String setCyan(String msg) {
		return setHelper(msg, ANSI_CYAN);
	}

	public static String setWhite(String msg) {
		return setHelper(msg, ANSI_WHITE);
	}

	private static String setHelper(String msg, String color) {
		return color + msg + ANSI_RESET;
	}
}
