public class LevelLogger extends LoggerDecorator {
    private String level;

    public LevelLogger(Logger decoratedLogger, String level) {
        super(decoratedLogger);
        this.level = level;
    }

    @Override
    public void log(String message) {
        decoratedLogger.log(level + ": " + message);
    }
}
