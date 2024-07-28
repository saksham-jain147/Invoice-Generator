package com.sjain.invoicegenerator.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author sakshamjain
 *
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
    private String date;
    private String comment;
    private String transactionId;
    private BigDecimal withdrawalAmount;
    private BigDecimal depositAmount;
    private BigDecimal closingBalance;
}
