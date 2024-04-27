# Dynamic PDF Generator

## Description

This Spring Boot application provides a REST API for generating PDF documents based on the provided data. It utilizes a Java Template Engine (Thymeleaf) to generate PDFs and stores them locally. If the same data is provided again, the stored PDF will be retrieved instead of regenerating it.

## Technology Stack

- Spring Boot
- Java Template Engine (Thymeleaf)
- Maven

## Installation and Setup

1. Clone this repository:

   ```bash
   git clone https://github.com/saksham-jain147/Invoice-Generator.git

## Usage
To interact with the API, you can use tools like **Postman**. The following section details the available API endpoints.

# API Endpoints

## Generate Invoice

### Description

Generates an invoice in PDF format based on the provided data.

- **Method:** POST
- **Endpoint:** `/api/v1/generateInvoice`
- **Base URL:** `http://localhost:9090`

### Request Body

| Field         | Type   | Description                |
|---------------|--------|----------------------------|
| seller        | string | Name of the seller         |
| sellerGstin   | string | GSTIN of the seller        |
| sellerAddress | string | Address of the seller      |
| buyer         | string | Name of the buyer          |
| buyerGstin    | string | GSTIN of the buyer         |
| buyerAddress  | string | Address of the buyer       |
| items         | array  | List of items in the invoice |

Each item in the `items` array has the following fields:

| Field    | Type    | Description                |
|----------|---------|----------------------------|
| name     | string  | Name of the product        |
| quantity | string  | Quantity of the product    |
| rate     | float   | Rate of the product        |
| amount   | float   | Total amount for the item  |

### Example Request Body

```json
{
    "seller": "XYZ Pvt. Ltd.",
    "sellerGstin": "29AABBCCDD121ZD",
    "sellerAddress": "New Delhi, India",
    "buyer": "Vedant Computers",
    "buyerGstin": "29AABBCCDD131ZD",
    "buyerAddress": "New Delhi, India",
    "items": [
        {
            "name": "Product 1",
            "quantity": "12 Nos",
            "rate": 123.00,
            "amount": 1476.00
        },
        {
            "name": "Product 2",
            "quantity": "10 Nos",
            "rate": 100.00,
            "amount": 1000.00
        }
    ]
}
```
You can find the output file for above request body here : [Sample Invoice.pdf](sampleOutput/afddc827b70dc3be9092be670f04917655e4d6c3ab818dbf087d0a6960ba958a.pdf).


## Export Account Statement

### Description

Exports an account statement in PDF format based on the provided data.

- **Method:** POST
- **Endpoint:** `/api/v2/exportStatement`
- **Base URL:** `http://localhost:9090`

### Request Body

| Field             | Type   | Description                                                              |
|-------------------|--------|--------------------------------------------------------------------------|
| prefix            | string | Prefix for the statement number (e.g., "MR")                             |
| accountHolderName | string | Name of the account holder                                               |
| contactAddress    | string | Contact address of the account holder                                    |
| stateOfOrigin     | string | State where the account is originated                                    |
| country           | string | Country where the account is originated                                  |
| bankBranch        | string | Bank branch name                                                         |
| routingNumber     | string | Routing number associated with the account                               |
| currency          | string | Currency used in the statement (e.g., "INR")                             |
| contactEmail      | string | Email address of the account holder                                      |
| accountNumber     | string | Account number associated with the statement                             |
| accountOpenDate   | string | Date when the account was opened (YYYY-MM-DD format)                     | 
| accountType       | string | Type of account (e.g., "Salary", "Savings")                              |
| ifscCode          | string | IFSC code of the bank branch                                             |
| branchCode        | string | Branch code of the bank branch                                           |
| fromDate          | string | Start date of the statement period (YYYY-MM-DD format)                   |
| toDate            | string | End date of the statement period (YYYY-MM-DD format)                     |
| transactions      | array  | List of transactions within the statement period                         |
| openingBalance    | float  | Opening balance of the account at the beginning of the statement period  |
| debitCount        | string | Total number of debit transactions during the period                     |
| creditCount       | string | Total number of credit transactions during the period                    |
| totalDebits       | float  | Total amount debited from the account during the period                  |
| totalCredits      | float  | Total amount credited to the account during the period                   |
| closingBalance    | float  | Account balance at the end of the statement period                       |

Each item in the `tansactions` array has the following fields:

| Field            | Type   | Description                                 |
|------------------|--------|---------------------------------------------|
| date             | string | Date of the transaction (YYYY-MM-DD format) |
| comment          | string | Description or comment for the transaction  |
| transactionId    | string | Unique identifier for the transaction       |
| withdrawalAmount | float  | Amount withdrawn from the account (if any)  |
| depositAmount    | float  | Amount deposited to the account (if any)    |
| closingBalance   | float  | Account balance after the transaction       |

### Example Request Body

```json
{
   "prefix": "MR",
   "accountHolderName": "JOHN DOE",
   "contactAddress": "XYZ APARTMENT, JAIPUR",
   "stateOfOrigin": "RAJASTHAN",
   "country": "INDIA",
   "bankBranch": "State Bank of Bharat - Jaipur Heritage Branch",
   "routingNumber": "State Bank of Bharat - MI Road, Jaipur Heritage, 302001 India",
   "currency": "INR",
   "contactEmail": "john.doe@email.com",
   "accountNumber": "1641961924941",
   "accountOpenDate": "2024-04-20",
   "accountType": "Salary",
   "ifscCode": "SBB9869",
   "branchCode": "4723",
   "fromDate": "2024-04-20",
   "toDate": "2024-04-27",
   "transactions": [
      {
         "date":"2024-04-20",
         "comment":"Self deposit",
         "transactionId":"859c455c-c626-44bf-a532-a123ad142797",
         "withdrawalAmount":"",
         "depositAmount":45000.00,
         "closingBalance":65000.00
      },
      {
         "date":"2024-04-21",
         "comment":"Paid to XYZ Apparels",
         "transactionId":"859c455c-c626-44bf-a532-a123ad142798",
         "withdrawalAmount":1200.00,
         "depositAmount":"",
         "closingBalance":63800.00
      },
      {
         "date":"2024-04-22",
         "comment":"Bill Payment (Electricity)",
         "transactionId":"859c455c-c626-44bf-a532-a123ad142799",
         "withdrawalAmount":700.00,
         "depositAmount":"",
         "closingBalance":63100.00
      },
      {
         "date":"2024-04-23",
         "comment":"Paid to Fashion Hut",
         "transactionId":"859c455c-c626-44bf-a532-a123ad142800",
         "withdrawalAmount":2700.00,
         "depositAmount":"",
         "closingBalance":60400.00
      },
      {
         "date":"2024-04-24",
         "comment":"Quarter 1 bonus credit - 2024",
         "transactionId":"859c455c-c626-44bf-a532-a123ad142801",
         "withdrawalAmount":"",
         "depositAmount":30000.00,
         "closingBalance":90400.00
      },
      {
         "date":"2024-04-24",
         "comment":"Money Transfer (Friend)",
         "transactionId":"859c455c-c626-44bf-a532-a123ad142802",
         "withdrawalAmount":45000.00,
         "depositAmount":"",
         "closingBalance":45400.00
      },
      {
         "date":"2024-04-25",
         "comment":"Uber Ride",
         "transactionId":"859c455c-c626-44bf-a532-a123ad142803",
         "withdrawalAmount":450.00,
         "depositAmount":"",
         "closingBalance":44950.00
      },
      {
         "date":"2024-04-26",
         "comment":"OMG Food Court",
         "transactionId":"859c455c-c626-44bf-a532-a123ad142804",
         "withdrawalAmount":560.00,
         "depositAmount":"",
         "closingBalance":44390.00
      },
      {
         "date":"2024-04-27",
         "comment":"ABC Medico",
         "transactionId":"859c455c-c626-44bf-a532-a123ad142805",
         "withdrawalAmount":780.00,
         "depositAmount":"",
         "closingBalance":43610.00
      }
   ],
   "openingBalance": 20000.00,
   "debitCount": "7",
   "creditCount": "2",
   "totalDebits": 51390.00,
   "totalCredits": 75000.00,
   "closingBalance": 43610.00,
   "generatedOn": "2024-04-27"
}
```

You can find the output file for above request body here : [Sample Statement.pdf](sampleOutput/82e40da3e2619c51bf8495492d3272be35fa5afdd47d373c6eeb8cd3eaaf694f.pdf).


## Contributing

Contributions are welcome! If you find any issues or have suggestions for improvements, feel free to open an issue or submit a pull request.
