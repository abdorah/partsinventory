package com.partsinventory.service;

import static java.awt.Color.LIGHT_GRAY;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.ColumnText;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfPageEventHelper;
import com.lowagie.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class ReportsService {

    private static Font titleFont = new Font(Font.HELVETICA, 18, Font.BOLD);
    private static Font headerFont = new Font(Font.HELVETICA, 12, Font.BOLD);
    private static Font regularFont = new Font(Font.HELVETICA, 12, Font.NORMAL);
    private static Font smallFont = new Font(Font.HELVETICA, 10, Font.ITALIC);

    private static ReportsService instance = new ReportsService();
    private Map<String, Object> parameters = new HashMap<>();

    private ReportsService() {}

    public static ReportsService getInstance() {
        return instance;
    }

    public void resetParameters() {
        parameters = new HashMap<>();
    }

    public void setParameter(String key, Object value) {
        parameters.put(key, value);
    }

    public static void generateReceipt(Map<String, String> data, String outputFilePath)
            throws DocumentException, IOException {
        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(outputFilePath));
        writer.setPageEvent(new HeaderFooter());
        document.open();

        // Title
        Paragraph title = new Paragraph("Transaction Receipt", titleFont);
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);

        // Vendor details
        Paragraph vendorName = new Paragraph("Vendor: " + data.get("storeName"), headerFont);
        vendorName.setAlignment(Element.ALIGN_LEFT);
        document.add(vendorName);

        Paragraph vendorAddress =
                new Paragraph("Address: " + data.get("storeAddress"), regularFont);
        vendorAddress.setAlignment(Element.ALIGN_LEFT);
        document.add(vendorAddress);

        document.add(new Paragraph(" ")); // Adding a blank line

        // Transaction details in a table
        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);
        table.setSpacingBefore(10f);
        table.setSpacingAfter(10f);

        addTableHeader(table);
        addRows(table, data);
        document.add(table);
        document.close();
    }

    private static void addTableHeader(PdfPTable table) {
        Stream.of("Field", "Value")
                .forEach(
                        columnTitle -> {
                            PdfPCell header = new PdfPCell();
                            header.setBackgroundColor(LIGHT_GRAY);
                            header.setBorderWidth(2);
                            header.setPhrase(new Phrase(columnTitle, headerFont));
                            table.addCell(header);
                        });
    }

    private static void addRows(PdfPTable table, Map<String, String> data) {
        table.addCell("Transaction ID");
        table.addCell(data.get("transactionId"));

        table.addCell("Transaction Date");
        table.addCell(data.get("transactionDate"));

        table.addCell("Total Amount");
        table.addCell(data.get("totalAmount"));
    }

    static class HeaderFooter extends PdfPageEventHelper {
        @Override
        public void onEndPage(PdfWriter writer, Document document) {
            ColumnText.showTextAligned(
                    writer.getDirectContent(),
                    Element.ALIGN_CENTER,
                    new Phrase("My Store - Receipt", smallFont),
                    100,
                    100,
                    0);

            ColumnText.showTextAligned(
                    writer.getDirectContent(),
                    Element.ALIGN_CENTER,
                    new Phrase("Page " + document.getPageNumber(), smallFont),
                    100,
                    80,
                    0);
        }
    }
}
