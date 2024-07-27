public class ComputerDisplay implements Observer {
    @Override
    public void update(int temperature) {
        System.out.println("Computer display updated with temperature: " + temperature);
    }
}
