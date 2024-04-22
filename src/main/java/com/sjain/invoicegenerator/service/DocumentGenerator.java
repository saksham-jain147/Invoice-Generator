package com.sjain.invoicegenerator.service;

import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.kernel.pdf.PdfWriter;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;

@Service
public class DocumentGenerator {

    Logger logger = LoggerFactory.getLogger(DocumentGenerator.class);

    /**
     * Converts HTML content to PDF and saves it to the user's Downloads folder.
     *
     * @param processedHtml The processed HTML string to convert to PDF.
     * @param fileName The desired filename for the generated PDF.
     */
    public void convertHtmlToPdf(String processedHtml, String fileName){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        try{
            // Creating a PDF writer using the ByteArrayOutputStream
            PdfWriter pdfWriter = new PdfWriter(byteArrayOutputStream);

            // Converting the HTML to PDF using HtmlConverter
            HtmlConverter.convertToPdf(processedHtml, pdfWriter);

            // Creating a FileOutputStream to save the PDF to the downloads folder
//            FileOutputStream fout = new FileOutputStream(env.getProperty("invoice.filepath") + fileName + ".pdf");
            FileOutputStream fout = new FileOutputStream(getUserDownloadsFolder() + fileName + ".pdf");
//            System.out.println("File saved to: " + getUserDownloadsFolder() + fileName + ".pdf");
            logger.info("File saved to: " + getUserDownloadsFolder() + fileName + ".pdf");

            // Writing the PDF content to the file
            byteArrayOutputStream.writeTo(fout);

            // Closing streams to release resources
            byteArrayOutputStream.close();
            byteArrayOutputStream.flush();
            fout.close();

        } catch (Exception e){
            System.out.println("Exception occurred : " + e);
        }
    }

    /**
     * Downloads an existing PDF file from the user's Downloads folder.
     *
     * @param fileName The name of the PDF file to download.
     * @return A ResponseEntity containing the PDF content as byte array and appropriate content type.
     * @throws IOException If an error occurs while reading the file.
     */
    public void downloadExistingPDF(String fileName) throws IOException {
        File pdfFile = new File(getUserDownloadsFolder() + fileName + ".pdf");
        // Read the existing PDF content
        byte[] pdfData = Files.readAllBytes(pdfFile.toPath());

        // Overwriting the existing file
        Files.write(pdfFile.toPath(), pdfData);

        logger.info("Downloaded existing PDF: " + getUserDownloadsFolder() + fileName + ".pdf");
    }

    /**
     * Reads the content of an existing PDF file from the user's Downloads folder.
     *
     * @param fileName The name of the PDF file to read.
     * @return A byte array containing the content of the PDF file.
     * @throws IOException If an error occurs while reading the file.
     */
    public byte[] readFile(String fileName) throws IOException {
        // Construct a File object representing the PDF file
        File pdfFile = new File(getUserDownloadsFolder() + fileName + ".pdf");

        // Use FileUtils.readFileToByteArray to read the entire file content into a byte array
        return FileUtils.readFileToByteArray(pdfFile);
    }

    /**
     * Checks if a PDF file with the given name exists in the user's Downloads folder.
     *
     * @param fileName The name of the PDF file to check.
     * @return True if the file exists, false otherwise.
     */
    public boolean isFileExist(String fileName){
        // Construct a File object representing the PDF file after retrieving userr's Downloads folder
        File pdfFile = new File(getUserDownloadsFolder() + fileName + ".pdf");
//        if(pdfFile.exists()) System.out.println("File already exists at: " + getUserDownloadsFolder() + fileName + ".pdf");
        if(pdfFile.exists()) logger.info("File already exists at: " + getUserDownloadsFolder() + fileName + ".pdf");

        return pdfFile.exists();
    }

    /**
     * Determines the user's Downloads folder path based on the operating system.
     *
     * @return The path to the user's Downloads folder.
     */
    public String getUserDownloadsFolder() {
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("win")) {
            return System.getenv("userprofile") + "\\Downloads\\";
        } else if (os.contains("mac")) {
            return System.getProperty("user.home") + "/Downloads/";
        } else {
            return "/";
        }
    }
}
