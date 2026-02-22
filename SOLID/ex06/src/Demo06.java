public class Demo06 {
    public static void main(String[] args) {
        System.out.println("=== Notification Demo ===");
        AuditLog audit = new AuditLog();
        ConsolePreview preview = new ConsolePreview();
        SenderConfig config = new SenderConfig();

        Notification n = new Notification("Welcome", "Hello and welcome to SST!", "riya@sst.edu", "9876543210");

        NotificationSender email = new EmailSender(audit, preview);
        NotificationSender sms = new SmsSender(audit, preview, config);
        NotificationSender wa = new WhatsAppSender(audit, preview);

        email.send(n);
        sms.send(n);
        try {
            wa.send(n);
        } catch (RuntimeException ex) {
            System.out.println("WA ERROR: " + ex.getMessage());
            audit.add("WA failed");
        }

        System.out.println("AUDIT entries=" + audit.size());
    }
}