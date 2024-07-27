import java.time.LocalDateTime;

public class TimestampLogger extends LoggerDecorator {
    public TimestampLogger(Logger decoratedLogger) {
        super(decoratedLogger);
    }

    @Override
    public void log(String message) {
        decoratedLogger.log(LocalDateTime.now() + " " + message);
    }
}
