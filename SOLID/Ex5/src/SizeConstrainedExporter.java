public class SizeConstrainedExporter extends Exporter {
    private final Exporter delegate;
    private final int maxLen;
    private final String formatName;

    public SizeConstrainedExporter(Exporter delegate, int maxLen, String formatName) {
        this.delegate = delegate;
        this.maxLen = maxLen;
        this.formatName = formatName;
    }

    @Override
    public String contentType() {
        return delegate.contentType();
    }

    @Override
    protected ExportResult doExport(ExportRequest req) {
        if (req.body != null && req.body.length() > maxLen) {
            throw new IllegalArgumentException(formatName + " cannot handle content > " + maxLen + " chars");
        }
        return delegate.export(req);
    }
}