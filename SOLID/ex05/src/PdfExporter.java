import java.nio.charset.StandardCharsets;

public class PdfExporter extends Exporter {
    @Override
    public String contentType() { return "application/pdf"; }
    
    @Override
    protected ExportResult doExport(ExportRequest req) {
        String fakePdf = "PDF(" + req.title + "):" + req.body;
        return new ExportResult("application/pdf", fakePdf.getBytes(StandardCharsets.UTF_8));
    }
}