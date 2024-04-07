package com.sjain.invoicegenerator.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class DocumentGeneratorTest {
    @Mock
    private Logger logger;

    @InjectMocks
    private DocumentGenerator documentGenerator;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetUserDownloadsFolder() {
        // Mocking system property for operating system
        System.setProperty("os.name", "windows");

        String downloadsFolder = documentGenerator.getUserDownloadsFolder();

        assertEquals(System.getenv("userprofile") + "\\Downloads\\", downloadsFolder);
    }
}
