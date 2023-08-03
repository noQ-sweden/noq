touch application-secret.properties
azure.serviceURI=
azure.primarySecretKey=

- Booking Document Format:

```
{
  "bookingId": "123456",
  "customerId": "789",
  "bookingDate": "2023-08-10",
  "status": "confirmed",
  "details": "book",
  "totalPrice": 500,
  "customerInfo": {
    "customerId": "789",
    "firstName": "John",
    "lastName": "Doe",
    "email": "john.doe@example.com",
    "phone": "+1234567890",
    "address": {
      "street": "123",
      "city": "Stockholm",
      "zip": "10001",
      "country": "Sweden"
    }
  }
}
```

- Customer Document Format:

```
{
  "customerId": "789",
  "firstName": "John",
  "lastName": "Doe",
  "email": "john.doe@example.com",
  "phone": "+1234567890",
  "address": {
    "street": "123",
    "city": "Stockholm",
    "zip": "10001",
    "country": "Sweden"
  }
}
```
