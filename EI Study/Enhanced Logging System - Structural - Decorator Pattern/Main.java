public class Main {
    public static void main(String[] args) {
        Logger logger = new BasicLogger();
        Logger timestampLogger = new TimestampLogger(logger);
        Logger levelLogger = new LevelLogger(timestampLogger, "INFO");

        levelLogger.log("This is a log message.");
    }
}
