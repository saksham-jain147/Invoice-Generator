package com.sjain.invoicegenerator.service;

import com.sjain.invoicegenerator.entity.Invoice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FileNameGeneratorTest {
    private FileNameGenerator fileNameGenerator;

    @BeforeEach
    public void setUp() {
        fileNameGenerator = new FileNameGenerator();
    }

    @Test
    public void testGenerateFileName() throws NoSuchAlgorithmException {
        // Mocking invoice data
        Invoice invoice = mock(Invoice.class);
        when(invoice.toString()).thenReturn("Invoice{seller='Test Seller', sellerGstin='Seller GSTIN', sellerAddress='Seller Address', buyer='Test Buyer', buyerGstin='', buyerAddress='', items=[]}");

        // Expected hash for "Test Invoice Data" using SHA-256
        String expectedHash = "d69298a7b46a4ebe24211483d7c4755cad7060cef7cb79d64a8b95e7d881c0d4";

        // Call the method under test
        String generatedFileName = fileNameGenerator.generateFileName(invoice);

        // Assert the generated file name
        assertEquals(expectedHash, generatedFileName);
    }

    @Test
    public void testGenerateFileName_NullInput() {
        // Call the method under test with null input
        assertThrows(NullPointerException.class, () -> fileNameGenerator.generateFileName(null));
    }
}
