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
- **Endpoint:** `/api/generateInvoice`
- **Base URL:** `http://localhost:8080`

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

## Contributing

Contributions are welcome! If you find any issues or have suggestions for improvements, feel free to open an issue or submit a pull request.
