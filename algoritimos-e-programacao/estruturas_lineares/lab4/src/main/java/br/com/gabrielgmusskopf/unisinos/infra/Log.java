package br.com.gabrielgmusskopf.unisinos.infra;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Log {

	private static LogLevel level = LogLevel.INFO;
	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT);

	public static void setLevel(LogLevel level) {
		Log.level = level;
	}

	public static void debug(String s) {
		if (level.equals(LogLevel.DEBUG))
		 	System.out.println(LocalDateTime.now().format(FORMATTER) + " [DEBUG]: " + s);
	}

	public static void info(String s) {
		info(s, false);
	}

	public static void info(String s, boolean curto) {
		if (level.equals(LogLevel.INFO) || level.equals(LogLevel.DEBUG)) {
			var x = curto ? s : LocalDateTime.now().format(FORMATTER) + " [DEBUG]: " + s;
			System.out.println(x);
		}
	}

	private Log() {}

}
