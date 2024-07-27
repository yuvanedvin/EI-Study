import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ScheduleManager {
    private static final Logger logger = Logger.getLogger(ScheduleManager.class.getName());
    private static ScheduleManager instance;
    private List<Task> tasks = new ArrayList<>();
    private List<TaskObserver> observers = new ArrayList<>();

    private ScheduleManager() {
        logger.log(Level.INFO, "ScheduleManager instance created.");
    }

    public static ScheduleManager getInstance() {
        if (instance == null) {
            synchronized (ScheduleManager.class) {
                if (instance == null) {
                    instance = new ScheduleManager();
                }
            }
        }
        return instance;
    }

    public void addTask(Task task) {
        if (!validateTask(task)) {
            notifyObserversOfConflict(task);
            throw new IllegalArgumentException("Task conflicts with existing tasks.");
        }
        tasks.add(task);
        notifyObserversOfUpdate(task);
        logger.log(Level.INFO, "Task added: {0}", task);
    }

    public void removeTask(String description) {
        Task task = findTask(description);
        if (task == null) {
            logger.log(Level.WARNING, "Task not found: {0}", description);
            throw new IllegalArgumentException("Task not found.");
        }
        tasks.remove(task);
        logger.log(Level.INFO, "Task removed: {0}", description);
    }

    public List<Task> getTasks() {
        tasks.sort((t1, t2) -> t1.getStartTime().compareTo(t2.getStartTime()));
        return tasks;
    }

    public void addObserver(TaskObserver observer) {
        observers.add(observer);
        logger.log(Level.INFO, "Observer added: {0}", observer.getClass().getName());
    }

    public void removeObserver(TaskObserver observer) {
        observers.remove(observer);
        logger.log(Level.INFO, "Observer removed: {0}", observer.getClass().getName());
    }

    private boolean validateTask(Task newTask) {
        for (Task task : tasks) {
            if (task.getStartTime().isBefore(newTask.getEndTime()) && newTask.getStartTime().isBefore(task.getEndTime())) {
                return false;
            }
        }
        return true;
    }

    private void notifyObserversOfConflict(Task task) {
        for (TaskObserver observer : observers) {
            observer.onTaskConflict(task);
        }
    }

    private void notifyObserversOfUpdate(Task task) {
        for (TaskObserver observer : observers) {
            observer.onTaskUpdate(task);
        }
    }

    private Task findTask(String description) {
        for (Task task : tasks) {
            if (task.getDescription().equals(description)) {
                return task;
            }
        }
        return null;
    }
}
