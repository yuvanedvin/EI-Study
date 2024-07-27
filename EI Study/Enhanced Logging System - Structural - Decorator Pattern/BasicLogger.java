public class BasicLogger implements Logger {
    @Override
    public void log(String message) {
        System.out.println(message);
    }
}
