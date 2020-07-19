package com;

import org.apache.log4j.Logger;

public class Logging {
    private static final Logger logger = Logger.getLogger("Log");

    enum Level {Error, Warn, Fatal, Info, Debug}

    private Logging() {/* do nothing */}

    ;

    public static void logError(Class className, String msg) {
        log(Level.Error, className, msg, null);
    }

    public static void logWarn(Class className, String msg) {
        log(Level.Warn, className, msg, null);
    }

    public static void logFatal(Class className, String msg) {
        log(Level.Fatal, className, msg, null);
    }

    public static void logInfo(Class className, String msg) {
        log(Level.Info, className, msg, null);
    }

    public static void logDebug(Class className, String msg) {
        log(Level.Debug, className, msg, null);
    }


    public static void logError(Class className, String msg, Throwable throwable) {
        log(Level.Error, className, msg, throwable);
    }


    public static void logWarn(Class className, String msg, Throwable throwable) {
        log(Level.Warn, className, msg, throwable);
    }

    public static void logFatal(Class className, String msg, Throwable throwable) {
        log(Level.Fatal, className, msg, throwable);
    }

    public static void logInfo(Class className, String msg, Throwable throwable) {
        log(Level.Info, className, msg, throwable);
    }

    public static void logDebug(Class className, String msg, Throwable throwable) {
        log(Level.Debug, className, msg, throwable);
    }

    private static void log(Level level, Class className, String msg, Throwable throwable) {
        String message = String.format("[%s] : %s", className.getSimpleName(), msg);
        switch (level) {
            case Info:
                logger.info(message, throwable);
                break;
            case Warn:
                logger.warn(message, throwable);
                break;
            case Error:
                logger.error(message, throwable);
                break;
            case Fatal:
                logger.fatal(message, throwable);
                break;
            default:
            case Debug:
                logger.debug(message, throwable);
        }
    }

}