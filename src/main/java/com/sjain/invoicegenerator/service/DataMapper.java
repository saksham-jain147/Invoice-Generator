package com.sjain.invoicegenerator.service;

import com.sjain.invoicegenerator.entity.Invoice;
import com.sjain.invoicegenerator.entity.Statement;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;

import java.util.HashMap;
import java.util.Map;

@Service
public class DataMapper {

    /**
     * Creates a Context object with invoice data ready for templating.
     *
     * @param invoice The invoice object containing the data to be added to the context.
     * @return A Context object with the invoice data accessible under the "invoice" key.
     */
    public Context setData(Invoice invoice){

        // Creating a new Context object
        Context context = new Context();

        // Creating a HashMap to store the data for the context
        Map<String, Object> data= new HashMap<>();

        // Adding the invoice object to the HashMap with the key "invoice"
        data.put("invoice", invoice);

        // Setting the variables of the context using the HashMap
        context.setVariables(data);

        return context;
    }

    /**
     * Creates a Context object with statement data ready for templating.
     *
     * @param statement The statement object containing the data to be added to the context.
     * @return A Context object with the statement data accessible under the "statement" key.
     */
    public Context setData(Statement statement){

        // Creating a new Context object
        Context context = new Context();

        // Creating a HashMap to store the data for the context
        Map<String, Object> data= new HashMap<>();

        // Adding the invoice object to the HashMap with the key "invoice"
        data.put("statement", statement);

        // Setting the variables of the context using the HashMap
        context.setVariables(data);

        return context;
    }
}
