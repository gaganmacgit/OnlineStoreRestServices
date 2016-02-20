CREATE TABLE category_product (
   category_code        VARCHAR(1) PRIMARY KEY,
   description VARCHAR(100),
   tax decimal(4,2) 
);

CREATE TABLE product (
   product_code        VARCHAR(10) PRIMARY KEY,
   id_category VARCHAR(1),
   product_name VARCHAR(50),
   product_info VARCHAR(250),
   price decimal(9,2),
   product_stock_qty integer
);

 
CREATE TABLE user_cart (
  id INTEGER IDENTITY PRIMARY KEY,
  username VARCHAR(30),
  useremail  VARCHAR(50),
  status VARCHAR(30),
  createddate timestamp,
  updateddate timestamp
);

CREATE TABLE user_cart_item (
  id INTEGER IDENTITY PRIMARY KEY,
  user_cart_id INTEGER  ,
  product_code VARCHAR(10),
  product_category VARCHAR(1),
  product_quantity integer,
  purchase_date  timestamp,
  product_price decimal(9,2),
  product_sales_tax decimal(9,2)
);