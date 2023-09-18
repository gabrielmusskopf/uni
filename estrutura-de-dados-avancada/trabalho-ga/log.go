package main

import "fmt"

const (
	NONE  = "none"
	DEBUG = "debug"
	INFO  = "info"
)

var logLevel string

func isDebug() bool {
    return logLevel == DEBUG
}

func toggleDebug() {
    if logLevel == DEBUG {
        logLevel = NONE
    } else {
        logLevel = DEBUG
    }
}

func info(s string, v ...any) {
	if logLevel == INFO {
		str := fmt.Sprintf("[info] %s", s)
		fmt.Printf(str, v...)
	}
}

func debug(s string, v ...any) {
	if logLevel == DEBUG {
		str := fmt.Sprintf("[debug] %s", s)
		fmt.Printf(str, v...)
	}
}
