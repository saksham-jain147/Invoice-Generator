package com.sjain.invoicegenerator.service;

import com.sjain.invoicegenerator.entity.Invoice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.thymeleaf.context.Context;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class DataMapperTest {
    @Mock
    private Invoice invoice;

    @InjectMocks
    private DataMapper dataMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void testSetData() {

        // Mocking invoice
        Invoice invoice = new Invoice();
        invoice.setSeller("Seller");
        invoice.setSellerAddress("Seller Address");
        invoice.setSellerGstin("Seller GSTIN");
        invoice.setBuyer("Buyer");
        invoice.setBuyerAddress("Buyer Address");
        invoice.setBuyerGstin("Buyer GSTIN");

        // Call the method under test
        Context context = dataMapper.setData(invoice);

        // Verify that the context contains the expected data
        assertEquals("Seller", ((Invoice) context.getVariable("invoice")).getSeller());
        assertEquals("Seller Address", ((Invoice) context.getVariable("invoice")).getSellerAddress());
        assertEquals("Seller GSTIN", ((Invoice) context.getVariable("invoice")).getSellerGstin());
        assertEquals("Buyer", ((Invoice) context.getVariable("invoice")).getBuyer());
        assertEquals("Buyer Address", ((Invoice) context.getVariable("invoice")).getBuyerAddress());
        assertEquals("Buyer GSTIN", ((Invoice) context.getVariable("invoice")).getBuyerGstin());
    }
}
