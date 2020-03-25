# Design BarCode scanner

## Secnairo 

- Merchant scan customer's barcode and enter the amount
- Customer gets the notification to approve/reject the payment


## Service

Think it is sync or async

MerchantService

Scan and request Payment

`requestPayment(String customerId, BigDecimal amount)`
 
PaymentService

/payment

/feedback

Send notification to the customer and merchant

`sendNotification(String customerMobileID, String mechantName, BigDecimal amount)`

Send notification to merchant

`sendNotification(String merchant)

CustomerService

`sendFeedback(String transactionID, boolean approval)`

Follow up

Q: What if the client sends multiple request for one transaction?

A: Create a unique fingerprint, such as timestamp + customerId + merchantId + random

Q: Should we make the request waiting for response or a seperate call

A: We should make it async so that it will not block the merchant or customer

## Storage

Table

* Customer table

  | CustomerID | Amount |
  | ---------- | ------ |

* Customer - CustomerDevice

  | CustomerID | Amount |
  | ---------- | ------ |

* Merchant table

  | MerchantID | MerchantName | Amount |
  | ---------- | ------------ | ------ |

* Transaction history

Use NoSQL, for each transaction, we save 2 records, for the customer and merchant

  | UserID | History |
  | ---------- | ------------ | ------ |


Follow up

Q. If the table is too large

A: Sharing based on UserId (?)

## Scale



