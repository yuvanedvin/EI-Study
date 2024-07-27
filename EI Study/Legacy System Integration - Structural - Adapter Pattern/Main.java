public class Main {
    public static void main(String[] args) {
        LegacySystem legacySystem = new LegacySystem();
        ModernSystem modernSystem = new LegacySystemAdapter(legacySystem);

        modernSystem.modernRequest();
    }
}
