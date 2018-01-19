package com.logpie.framework.log.util;

public enum LogColor {

	BLUE, CYAN, GREEN, GREY, PURPLE, RED, WHITE, YELLOW;

	private static final String ANSI_BLUE = "\u001B[34m";
	private static final String ANSI_CYAN = "\u001B[36m";
	private static final String ANSI_GREEN = "\u001B[32m";
	private static final String ANSI_GREY = "\u001B[90m";
	private static final String ANSI_PURPLE = "\u001B[35m";
	private static final String ANSI_RED = "\u001B[31m";
	private static final String ANSI_WHITE = "\u001B[37m";
	private static final String ANSI_YELLOW = "\u001B[33m";
	private static final String ANSI_RESET = "\u001B[0m";

	public static String setColor(String msg, LogColor color) {
		switch (color) {
			case BLUE: return setBlue(msg);
			case CYAN: return setCyan(msg);
			case GREEN: return setGreen(msg);
			case GREY: return setGrey(msg);
			case PURPLE: return setPurple(msg);
			case RED: return setRed(msg);
			case WHITE: return setWhite(msg);
			case YELLOW: return setYellow(msg);
			default: return "";
		}
	}

	private static String setGrey(String msg) {
		return setHelper(msg, ANSI_GREY);
	}

	private static String setRed(String msg) {
		return setHelper(msg, ANSI_RED);
	}

	private static String setGreen(String msg) {
		return setHelper(msg, ANSI_GREEN);
	}

	private static String setYellow(String msg) {
		return setHelper(msg, ANSI_YELLOW);
	}

	private static String setBlue(String msg) {
		return setHelper(msg, ANSI_BLUE);
	}

	private static String setPurple(String msg) {
		return setHelper(msg, ANSI_PURPLE);
	}

	private static String setCyan(String msg) {
		return setHelper(msg, ANSI_CYAN);
	}

	private static String setWhite(String msg) {
		return setHelper(msg, ANSI_WHITE);
	}

	private static String setHelper(String msg, String color) {
		return color + msg + ANSI_RESET;
	}
}
