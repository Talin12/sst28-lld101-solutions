public abstract class Exporter {
    // Template method: Guarantees consistent null handling for ALL exporters
    public final ExportResult export(ExportRequest req) {
        if (req == null) {
            return new ExportResult(contentType(), new byte[0]);
        }
        return doExport(req);
    }
    
    public abstract String contentType();
    protected abstract ExportResult doExport(ExportRequest req);
}