public abstract class NotificationSender {
    protected final AuditLog audit;
    protected final ConsolePreview preview;

    protected NotificationSender(AuditLog audit, ConsolePreview preview) { 
        this.audit = audit; 
        this.preview = preview;
    }

    // The Template Method explicitly enforces validation before sending!
    public final void send(Notification n) {
        validate(n);
        String formatted = format(n);
        preview.preview(formatted);
        logAudit();
    }

    protected abstract void validate(Notification n);
    protected abstract String format(Notification n);
    protected abstract void logAudit();
}