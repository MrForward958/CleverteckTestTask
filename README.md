# CleverteckTestTask
web application for creating a cash receipt
//check
1.you can open a new check:
  http://localhost:8080/marketCheck/new
2.see check:
  http://localhost:8080/marketCheck/{id}
3.add products to it:
  method = patch, action = marketCheck/{id}
4.close the check:
  method = patch, action = marketCheck/close/{id}
5.see the full list of checks:
  http://localhost:8080/marketCheck
  
//product
1.see the full list of products:
  http://localhost:8080/product
2.add a new product to the list
  http://localhost:8080/product/new?
  
Notes:
If the check is closed, adding products is not possible.
If the card is not presented (0 is left in the card number field), there will be no discount.
