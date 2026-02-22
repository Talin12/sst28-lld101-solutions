public class SmsSender extends NotificationSender {
    private final SenderConfig config;

    public SmsSender(AuditLog audit, ConsolePreview preview, SenderConfig config) { 
        super(audit, preview); 
        this.config = config;
    }

    @Override
    protected void validate(Notification n) {
        if (n.body != null && n.body.length() > config.maxLen) {
            throw new IllegalArgumentException("SMS body exceeds " + config.maxLen + " chars");
        }
    }

    @Override
    protected String format(Notification n) {
        return "SMS -> to=" + n.phone + " body=" + n.body;
    }

    @Override
    protected void logAudit() { audit.add("sms sent"); }
}