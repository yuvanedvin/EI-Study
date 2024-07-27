import java.util.List;
import java.util.Scanner;

public class Main implements TaskObserver {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Main mainApp = new Main();
        ScheduleManager scheduleManager = ScheduleManager.getInstance();
        scheduleManager.addObserver(mainApp);

        while (true) {
            mainApp.showMenu();
        }
    }

    private void showMenu() {
        System.out.println("1. Add Task");
        System.out.println("2. Remove Task");
        System.out.println("3. View Tasks");
        System.out.println("4. Exit");
        System.out.print("Choose an option: ");

        try {
            int option = Integer.parseInt(scanner.nextLine());

            switch (option) {
                case 1:
                    addTask();
                    break;
                case 2:
                    removeTask();
                    break;
                case 3:
                    viewTasks();
                    break;
                case 4:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
        }
    }

    private void addTask() {
        try {
            System.out.print("Enter description: ");
            String description = scanner.nextLine();
            System.out.print("Enter start time (HH:MM) - 24 Hours Format: ");
            String startTime = scanner.nextLine();
            System.out.print("Enter end time (HH:MM) - 24 Hours Format: ");
            String endTime = scanner.nextLine();
            System.out.print("Enter priority (High/Medium/Low): ");
            String priority = scanner.nextLine();

            Task task = TaskFactory.createTask(description, startTime, endTime, priority);
            ScheduleManager.getInstance().addTask(task);
            System.out.println("Task added successfully.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void removeTask() {
        try {
            System.out.print("Enter task description to remove: ");
            String description = scanner.nextLine();
            ScheduleManager.getInstance().removeTask(description);
            System.out.println("Task removed successfully.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void viewTasks() {
        List<Task> tasks = ScheduleManager.getInstance().getTasks();
        if (tasks.isEmpty()) {
            System.out.println("No tasks scheduled for the day.");
        } else {
            for (Task task : tasks) {
                System.out.println(task);
            }
        }
    }

    @Override
    public void onTaskConflict(Task task) {
        System.out.println("Conflict detected: Task " + task + " conflicts with an existing task.");
    }

    @Override
    public void onTaskUpdate(Task task) {
        System.out.println("Task updated: " + task);
    }
}
