package org.example;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        try {
            // Create a new document
            File originalPdf = new File("ogrenci_dilekce_sablon.pdf");
            PDDocument document = PDDocument.load(originalPdf);
            //PDDocument document = new PDDocument();

           /* PDPage page = new PDPage();
            document.addPage(page);
            */
            PDPage page = document.getPage(0);

            // Create a content stream
            //PDPageContentStream contentStream = new PDPageContentStream(document, page);

            PDPageContentStream contentStream = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.APPEND, true, true);

            // Set font and size
            contentStream.setFont(PDType1Font.TIMES_ROMAN, 10);

            // Add date to the top right corner
            addText(contentStream, getCurrentDate(), 530, 780);

            // Add user inputs to the PDF
            addText(contentStream, "Mail " + getUserInputFromDatabase("Body"), 70, 550);
            addText(contentStream, ".... " + getUserInputFromDatabase("...."), 70, 530);
            // Add more fields as needed

            // Close the content stream
            contentStream.close();

            // Save the document
            document.save("formalRequest.pdf");

            // Close the document
            document.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getUserInputFromDatabase(String fieldName) {
        // Retrieve user input from the database based on the field name
        // Replace this with your actual database retrieval logic
        return "Sample " + fieldName + " Value";
    }

    private static void addText(PDPageContentStream contentStream, String text, float x, float y) throws IOException {
        contentStream.beginText();
        contentStream.newLineAtOffset(x, y);
        contentStream.showText(text);
        contentStream.endText();
    }

    private static String getCurrentDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(new Date());
    }



    }
