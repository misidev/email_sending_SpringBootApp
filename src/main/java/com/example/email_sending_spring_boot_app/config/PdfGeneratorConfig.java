package com.example.email_sending_spring_boot_app.config;

import org.xhtmlrenderer.pdf.ITextRenderer;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class PdfGeneratorConfig {

    public void generatePdf(String htmlContent, String outputPath) throws Exception {
        try (OutputStream outputStream = new FileOutputStream(outputPath)) {
            ITextRenderer renderer = new ITextRenderer();
            renderer.setDocumentFromString(htmlContent);
            renderer.layout();
            renderer.createPDF(outputStream);
        }
    }
}
