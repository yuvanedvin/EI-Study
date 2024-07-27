import java.time.LocalTime;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Task {
    private static final Logger logger = Logger.getLogger(Task.class.getName());

    private String description;
    private LocalTime startTime;
    private LocalTime endTime;
    private String priority;

    public Task(String description, LocalTime startTime, LocalTime endTime, String priority) {
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.priority = priority;
        logger.log(Level.INFO, "Task created: {0}", this.toString());
    }

    public String getDescription() {
        return description;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public String getPriority() {
        return priority;
    }

    @Override
    public String toString() {
        return String.format("%s: %s [%s] from %s to %s", priority, description, priority, startTime, endTime);
    }
}
