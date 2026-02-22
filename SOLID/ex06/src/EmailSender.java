public class EmailSender extends NotificationSender {
    public EmailSender(AuditLog audit, ConsolePreview preview) { super(audit, preview); }

    @Override
    protected void validate(Notification n) {
        if (n.body != null && n.body.length() > 40) {
            throw new IllegalArgumentException("Email body cannot exceed 40 chars");
        }
    }

    @Override
    protected String format(Notification n) {
        return "EMAIL -> to=" + n.email + " subject=" + n.subject + " body=" + n.body;
    }

    @Override
    protected void logAudit() { audit.add("email sent"); }
}