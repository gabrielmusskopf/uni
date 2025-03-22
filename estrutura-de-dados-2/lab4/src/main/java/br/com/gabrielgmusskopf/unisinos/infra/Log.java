package br.com.gabrielgmusskopf.unisinos.infra;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Log {

	private static LogLevel level = LogLevel.INFO;
	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyy hh:mm:ss");

	public static void setLevel(LogLevel level) {
		Log.level = level;
	}

	public static void debug(String s) {
		if (level.equals(LogLevel.DEBUG))
		 	System.out.println("[DEBUG]: " + LocalDateTime.now().format(FORMATTER) + " " + s);
	}

	private Log() {}

}
