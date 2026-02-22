public class WhatsAppSender extends NotificationSender {
    public WhatsAppSender(AuditLog audit, ConsolePreview preview) { super(audit, preview); }

    @Override
    protected void validate(Notification n) {
        if (n.phone == null || !n.phone.startsWith("+")) {
            throw new IllegalArgumentException("phone must start with + and country code");
        }
    }

    @Override
    protected String format(Notification n) {
        return "WA -> to=" + n.phone + " body=" + n.body;
    }

    @Override
    protected void logAudit() { audit.add("wa sent"); }
}