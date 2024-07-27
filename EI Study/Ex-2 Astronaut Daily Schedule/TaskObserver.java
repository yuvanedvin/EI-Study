public interface TaskObserver {
    void onTaskConflict(Task task);
    void onTaskUpdate(Task task);
}
