import java.time.LocalTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.time.format.DateTimeParseException;

public class TaskFactory {
    private static final Logger logger = Logger.getLogger(TaskFactory.class.getName());

    public static Task createTask(String description, String startTime, String endTime, String priority) {
        try {
            validateTimeFormat(startTime);
            validateTimeFormat(endTime);

            LocalTime start = LocalTime.parse(startTime);
            LocalTime end = LocalTime.parse(endTime);

            if (start.isAfter(end)) {
                throw new IllegalArgumentException("Start time must be before end time.");
            }
            return new Task(description, start, end, priority);
        } catch (DateTimeParseException e) {
            logger.log(Level.SEVERE, "Error creating task: {0}", e.getMessage());
            throw new IllegalArgumentException("Invalid time format. Please use HH:MM format.");
        } catch (IllegalArgumentException e) {
            logger.log(Level.SEVERE, "Error creating task: {0}", e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error creating task: {0}", e.getMessage());
            throw new IllegalArgumentException("An unexpected error occurred.");
        }
    }

    private static void validateTimeFormat(String time) {
        if (!time.matches("^[0-2][0-9]:[0-5][0-9]$")) {
            throw new IllegalArgumentException("Invalid time format. Please use HH:MM format.");
        }
    }
}
