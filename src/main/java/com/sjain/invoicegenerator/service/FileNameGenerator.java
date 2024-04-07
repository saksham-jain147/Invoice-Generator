package com.sjain.invoicegenerator.service;

import com.sjain.invoicegenerator.entity.Invoice;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class FileNameGenerator {

    /**
     * Generates a unique filename based on the content of an Invoice object.
     *
     * @param invoice The Invoice object for which to generate a filename.
     * @return A unique filename string derived from the Invoice data.
     * @throws NoSuchAlgorithmException If the requested hashing algorithm ("SHA-256") is not available.
     */
    public String generateFileName(Invoice invoice) throws NoSuchAlgorithmException {
        // Converting the Invoice object to a String representation
        String dataString = invoice.toString();

        // Creating a MessageDigest object using the SHA-256 algorithm for hashing
        MessageDigest digest = MessageDigest.getInstance("SHA-256");

        // Calculating the hash of the Invoice data string using SHA-256
        byte[] hash = digest.digest(dataString.getBytes(StandardCharsets.UTF_8));

        // Constructing the file name by looping through each byte of the hash and convert it to a two-character hexadecimal string
        StringBuilder fileName = new StringBuilder();
        for (byte b : hash) {
            fileName.append(String.format("%02x", b)); // Formatting each byte as a hex string
        }
        return fileName.toString();
    }
}
