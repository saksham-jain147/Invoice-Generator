package com.sjain.invoicegenerator.controller;

import com.sjain.invoicegenerator.entity.Invoice;
import com.sjain.invoicegenerator.service.DataMapper;
import com.sjain.invoicegenerator.service.DocumentGenerator;
import com.sjain.invoicegenerator.service.FileNameGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class DocumentControllerTest {
    @Mock
    private SpringTemplateEngine springTemplateEngine;

    @Mock
    private DataMapper dataMapper;

    @Mock
    private DocumentGenerator documentGenerator;

    @Mock
    private FileNameGenerator fileNameGenerator;

    @InjectMocks
    private DocumentController documentController;

    @BeforeEach
    public void setup() {
//        MockitoAnnotations.initMocks(this);
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void testGenerateDocument_FileDoesNotExists() throws NoSuchAlgorithmException, IOException {
        // Mocking invoice
        Invoice invoice = new Invoice();
        invoice.setSeller("Test Seller");
        invoice.setSellerAddress("Seller Address");
        invoice.setSellerGstin("Seller GSTIN");
        invoice.setBuyer("Test Buyer");
        invoice.setBuyerAddress("Buyer Address");
        invoice.setBuyerGstin("Buyer GSTIN");

        // Mocking behavior of FileNameGenerator
        when(fileNameGenerator.generateFileName(any())).thenReturn("testFileName");

        // Mocking behavior of DocumentGeneratorService
        when(documentGenerator.isFileExist("testFileName")).thenReturn(false);

        // Mocking behavior of SpringTemplateEngine
        when(springTemplateEngine.process((String) any(), any())).thenReturn("<html><body>Test HTML</body></html>");

        // Call the method under test
        ResponseEntity<String> response = documentController.generateDocument(invoice);

        // Verify interactions and assertions
        verify(documentGenerator, times(1)).convertHtmlToPdf("<html><body>Test HTML</body></html>", "testFileName");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Success", response.getBody());
    }

    @Test
    public void testGenerateDocument_FileExists() throws NoSuchAlgorithmException, IOException {
        // Mocking invoice
        Invoice invoice = new Invoice();
        invoice.setSeller("Test Seller");
        invoice.setSellerAddress("Seller Address");
        invoice.setSellerGstin("Seller GSTIN");
        invoice.setBuyer("Test Buyer");
        invoice.setBuyerAddress("Buyer Address");
        invoice.setBuyerGstin("Buyer GSTIN");

        // Mocking behavior of FileNameGenerator
        when(fileNameGenerator.generateFileName(any())).thenReturn("testFileName");

        // Mocking behavior of DocumentGeneratorService
        when(documentGenerator.isFileExist("testFileName")).thenReturn(true);

        // Call the method under test
        ResponseEntity<String> response = documentController.generateDocument(invoice);

        // Verify interactions and assertions
        verify(documentGenerator, times(1)).downloadExistingPDF("testFileName");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Success", response.getBody());
    }
}
