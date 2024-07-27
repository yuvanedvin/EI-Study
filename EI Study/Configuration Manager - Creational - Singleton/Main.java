public class Main {
    public static void main(String[] args) {
        ConfigurationManager configManager = ConfigurationManager.getInstance();
        configManager.setConfig("App Config");

        System.out.println(configManager.getConfig());
    }
}
