public class LegacySystemAdapter implements ModernSystem {
    private LegacySystem legacySystem;

    public LegacySystemAdapter(LegacySystem legacySystem) {
        this.legacySystem = legacySystem;
    }

    @Override
    public void modernRequest() {
        legacySystem.legacyRequest();
    }
}
