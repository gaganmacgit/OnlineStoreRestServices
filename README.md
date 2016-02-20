# OnlineStoreRestServices

This is a spring based RESTful Webservice project. The technology stack used are:
1. Spring 4.x
2. JPA 2.0 
3. HSQL (Embedded DB)
4. Junit 

The project can be cloned using git clone command on local machine.Once cloned, go to the folder where
the code is downloaded and run mvn clean install.

This will build the WAR file for the project which can be deployed on any web server like Tomcat.

Once deployed over Tomcat, start the tomcat instance.This will open up the Embedded DB (Embedded HSQL) window reflecting 4 tables for holding the master and transactional data.

## Endpoints

 For viewing all the product categories , GET URL : /rest/productcategories
 For viewing a specific product category, GET URL : /rest/productcategories/id
 For viewing all the products in DB, GET URL :/rest/products
 For viewing a specific product based on productcode, GET URL : /rest/products/productcode
 For creating an empty user cart for a user, POST URL : /rest/user/cart sample payload is shown below:

```
<user>
  <name>dummyuser</name>
  <email>dummyuser@gmail.com</email>
</user>
```
 For Adding an Item to the cart , POST URL : /rest/user/emailid/cart/product
 emailId is the user emailid , uniquely identifying the user in application sample payload
   
```xml
    <cartitem>
      <product_code>PC201</product_code>
      <product_category>B</product_category>
      <product_quantity>2</product_quantity>
    </cartitem>
   ```
   
 For viewing the products present in the user cart at any time , GET URL : /rest/user/emailid/cart/product
 For finally checkout the cart, POST URL /rest/user/emailid/cart/checkout


For all the above URL, support for both JSON and XML type of data in Response is supported.This can be done by changing the Accept header to either application/json or application/xml.Once the application is deployed on tomcat , user can use POSTMAN chrome plugin for verifying the above functionalities.

## JUNIT test cases for testing all the above functionalities
1. From command prompt/terminal run mvn test or run the individual class OnlineStoreRestServicesTest.java as JUNIT test.
2. The test case for checkout functionality covered by test method performCartCheckoutTest() - does print out the itemized bill for each product and also mentions the sales tax for each product , based on its product category
