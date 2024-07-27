public class Main {
    public static void main(String[] args) {
        WeatherStation weatherStation = new WeatherStation();
        PhoneDisplay phoneDisplay = new PhoneDisplay();
        ComputerDisplay computerDisplay = new ComputerDisplay();

        weatherStation.addObserver(phoneDisplay);
        weatherStation.addObserver(computerDisplay);

        weatherStation.setTemperature(25);
        weatherStation.setTemperature(30);
    }
}
