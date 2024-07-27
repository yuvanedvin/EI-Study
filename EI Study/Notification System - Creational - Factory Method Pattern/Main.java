public class Main {
    public static void main(String[] args) {
        Notification email = NotificationFactory.createNotification("EMAIL");
        email.send("Hello via Email");

        Notification sms = NotificationFactory.createNotification("SMS");
        sms.send("Hello via SMS");

        Notification push = NotificationFactory.createNotification("PUSH");
        push.send("Hello via Push Notification");
    }
}
