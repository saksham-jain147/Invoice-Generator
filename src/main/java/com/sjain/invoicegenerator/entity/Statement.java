package com.sjain.invoicegenerator.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author sakshamjain
 *
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Statement {
    private String prefix;
    private String accountHolderName;
    private String contactAddress;
    private String stateOfOrigin;
    private String country;
    private String bankBranch;
    private String routingNumber;
    private String currency;
    private String contactEmail;
    private String accountNumber;
    private String accountOpenDate;
    private String accountType;
    private String ifscCode;
    private String branchCode;
    private String fromDate;
    private String toDate;
    private List<Transaction> transactions;
    private BigDecimal openingBalance;
    private String debitCount;
    private String creditCount;
    private BigDecimal totalDebits;
    private BigDecimal totalCredits;
    private BigDecimal closingBalance;
    private String generatedOn;
}
