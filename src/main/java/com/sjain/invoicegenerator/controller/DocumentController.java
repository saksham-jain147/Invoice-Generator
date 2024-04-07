package com.sjain.invoicegenerator.controller;

import com.sjain.invoicegenerator.entity.Invoice;
import com.sjain.invoicegenerator.service.DataMapper;
import com.sjain.invoicegenerator.service.DocumentGenerator;
import com.sjain.invoicegenerator.service.FileNameGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/api")
public class DocumentController {
    @Autowired
    private SpringTemplateEngine springTemplateEngine;

    @Autowired
    private DataMapper dataMapper;

    @Autowired
    private DocumentGenerator documentGeneratorService;

    @Autowired
    private FileNameGenerator fileNameGenerator;

    /**
     * Handles a POST request to generate an invoice PDF.
     *
     * @param invoice The invoice object containing the invoice data.
     * @return A ResponseEntity with a success message and HTTP status code 200 (OK).
     * @throws NoSuchAlgorithmException If the hashing algorithm is not available.
     * @throws IOException If an error occurs while generating or reading the PDF file.
     */
    @PostMapping("/generateInvoice")
    public ResponseEntity<String> generateDocument(@RequestBody Invoice invoice) throws NoSuchAlgorithmException, IOException {
        // Checking if PDF with same data exists
        String fileName = fileNameGenerator.generateFileName(invoice);
        if(documentGeneratorService.isFileExist(fileName)){
            documentGeneratorService.downloadExistingPDF(fileName);
            return new ResponseEntity<>("Success", HttpStatus.OK);
        }

        // Generating PDF content using Thymeleaf template when PDF is not found
        String finalHtml = null;
        Context dataContext = dataMapper.setData(invoice);
        finalHtml = springTemplateEngine.process("template", dataContext);
        documentGeneratorService.convertHtmlToPdf(finalHtml, fileName);

        return new ResponseEntity<>("Success", HttpStatus.OK);
    }
}
