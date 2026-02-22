public class SanitizingExporter extends Exporter {
    private final Exporter delegate;

    public SanitizingExporter(Exporter delegate) {
        this.delegate = delegate;
    }

    @Override
    public String contentType() {
        return delegate.contentType();
    }

    @Override
    protected ExportResult doExport(ExportRequest req) {
        // Explicitly sanitizes data so the wrapped exporter doesn't have to guess or silently corrupt data
        String cleanBody = req.body == null ? "" : req.body.replace("\n", " ").replace(",", " ");
        ExportRequest cleanReq = new ExportRequest(req.title, cleanBody);
        return delegate.export(cleanReq);
    }
}