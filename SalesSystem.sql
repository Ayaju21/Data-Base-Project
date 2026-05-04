DROP DATABASE IF EXISTS sales;
show databases;
create database sales;
use sales;
show tables;
# The Shop Owner Table
CREATE TABLE `sales`.`theshopowner` (
 `ShopId` INT PRIMARY KEY NOT NULL AUTO_INCREMENT ,
 `ShopName` VARCHAR(128),
 `ShopPhone` VARCHAR(13) ,
 `ShopAddress` VARCHAR(128) ,
 `ShopEmail` VARCHAR(128) ,
 `ShopDateofbirth` DATE
 );
 ALTER TABLE theshopowner AUTO_INCREMENT = 1;
# EMPLOYEE Table
CREATE TABLE `sales`.`employee` (
 `empId` INT PRIMARY KEY NOT NULL AUTO_INCREMENT ,
 `empName` VARCHAR(45) ,
 `empPhone` VARCHAR(13) ,
 `empAddress` VARCHAR(128) ,
 `empEmail` VARCHAR(128) ,
 `empDateofbirth` DATE ,
 `empHourly_wage` REAL
 );
 ALTER TABLE employee AUTO_INCREMENT = 1;
# RELATIONSHIP Between Employee and worksFor
CREATE TABLE `sales`.`employee_worksfor` (
 `employeeId` INT NOT NULL,
 `ShopOwnerId` INT Not NULL ,
 `employeePhone` VARCHAR(13) ,
 `employeeAddress` VARCHAR(128) ,
 `employeeDateofbirth` DATE ,
 `employeeName` VARCHAR(45) ,
 `employeeEmail` VARCHAR(128) ,
 `employeeHorulyWage` REAL,
 PRIMARY KEY (`employeeId`),
Foreign key (shopOwnerId) references theshopowner(ShopId)
);
# CUSTOMERS Table
 CREATE TABLE `sales`.`customers` (
 `cusId` INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
 `cusName` VARCHAR(45),
 `cusPhone` VARCHAR(13),
 `cusAddress` VARCHAR(128),
 `cusDateofbirth` DATE,
 `cusBankempIdAccount` INT
 );
 ALTER TABLE customers AUTO_INCREMENT = 100;
# RELATIONSHIP Between Employee and Cutomers
CREATE TABLE `sales`.`emp_cus` (
 `employeeId` INT,
 `customersId` INT,
 PRIMARY KEY (`employeeId`, `customersId`),
 foreign key (employeeId) references employee (empId),
 foreign key (customersId) references customers (cusId)
 );


 # PAYMENT Table
 CREATE TABLE `sales`.`payment` (
 `payId` INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
 `payDate` DATE ,
 `payAmount` REAL,
 `payType` VARCHAR(45)
 );
 ALTER TABLE payment AUTO_INCREMENT = 121;
#RELATIONSHIP Between Customers and Payment
CREATE TABLE `sales`.`cus_pay` (
 `customersId` INT,
 `paymentId` INT,
 PRIMARY KEY (`customersId`, `paymentId`),
foreign key (customersId) references customers(cusId),
foreign key (paymentId) references payment(payId)
);
# PRODUCTS Table
CREATE TABLE `sales`.`products` (
 `proId` INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
 `proPRODDate` DATE ,
 `proEXPDate` DATE ,
 `proProduce_Place` VARCHAR(128) ,
 `proSupplier_Company` VARCHAR(128) ,
 `proType` VARCHAR(128) ,
 `proBrand` VARCHAR(128) ,
 `proWeight` Real
 );
 ALTER TABLE products AUTO_INCREMENT = 120;
# RELATIONSHIP Between Customers and Products
CREATE TABLE `sales`.`customers_products` (
 `CustomersId` INT ,
 `ProductsID` INT ,
 PRIMARY KEY (`CustomersID`, `ProductsID`),
 foreign key (customersId) references customers(cusId),
foreign key (ProductsID) references products(proId)
);
#SUPPLIER_COMPANY Table
CREATE TABLE `sales`.`supplier_company` (
 `supId` INT PRIMARY KEY NOT NULL AUTO_INCREMENT ,
 `supName` VARCHAR(45) ,
 `supPhone` VARCHAR(13) ,
 `subAddress` VARCHAR(128) ,
 `subEmail` VARCHAR(128)
 );
 ALTER TABLE supplier_company AUTO_INCREMENT = 118;


 # RELATIONSHIP Between Products and Supplier_Company
CREATE TABLE `sales`.`products_suppliercompany` (
 `productsID` INT,
 `suppliercompanyID` INT ,
 PRIMARY KEY (`productsID`, `suppliercompanyID`),
foreign key (productsID) references products(proId),
foreign key (suppliercompanyID) references supplier_company(supId));



insert into theshopowner (ShopName, ShopPhone, ShopAddress, ShopEmail,
ShopDateofbirth)
value ("Jabr",'05977583',"Der Ammar", "Jabr@gmail.com",'1992-04-5');
select * from sales.theshopowner;


insert into employee (empName, empPhone, empAddress, empEmail,
empDateofbirth, empHourly_wage)
values ( "Ahmad", "0597843", "Ramllah" , "Ahmad@gamil.com" , '2000-02-4'
, 8.0),
("Omar", "05978964", "Nablus" , "Omar@gamil.com" , '2001-08-4' , 9.0),
("Ali", "05971234", "Bethlehem", "Ali@gmail.com", '1998-05-15', 10.0),
("Khaled", "05973456", "Hebron", "Khaled@gmail.com", '1997-04-12',
11.0);
select * from sales.employee;



insert into customers (cusName ,cusPhone, cusAddress, cusDateofbirth,
cusBankempIdAccount)
values ("Kareem", "0596754" , "Der Ammar", '1996-05-4', 1234),
("Mohammed","05964634" , "Der Ammar", '1998-05-8', 5436),
("Adam" , " 05968476" , "Ramllah" , '2000-04-5', 678),
("Ameer", "05962198", "Nablus", '1997-08-23', 3456);
select * from sales.customers;



insert into payment (payDate , payAmount , payType)
values ('2023-01-12', 294.5 , "Cash"),
('2023-01-31', 20.5 , "Cash"),
( '2023-02-15', 1500.0, "Credit Card"),
( '2023-05-11' , 600.0, "Credit Card");
select * from sales.payment;



insert into products (proPRODDate , proEXPDate, proProduce_Place ,proSupplier_Company , proType , proBrand , proWeight)
values
('2023-01-15', '2025-01-15', "Palestine", "Izhiman for coffe", "Coffe",
"Izhiman", 2.5),
 ('2023-02-20', '2024-02-20', "Jordan", "Al Kasih Food Production Co",
"sugar", "AL kasih", 1.0),
 ('2023-03-25', '2025-03-25', "Egypt", "Lipton Company ", "Tea",
"Lipton", 0.5),
 ('2023-04-30', '2024-04-30', " Palestine", "Snabel Company", "white
flour", "Snabel", 3.0);
 SELECT * FROM sales.products;
 
 
 
insert into supplier_company (supName , supPhone , subAddress , subEmail)
values
( "Candia" , "0549384" , "Ramllah" , "Candia@gmail.com"),
( "AL Gebreni" , "0549384 ", "Hebron" , "gebreni@gmail.com"),
("GreenFields", "0567890", "Nablus", "greenfields@gmail.com"),
 ( "Saleh Khalaf", "0598765", "Bethlehem", "sunriseimports@gmail.com");
select * from sales.supplier_company;


insert into  theshopowner (ShopName, ShopPhone, ShopAddress, ShopEmail, ShopDateofbirth) 
value ("Jabr",'05977583',"Der Ammar", "Jabr@gmail.com",'1992-04-5');
select * from sales.theshopowner;


insert into employee (empName, empPhone, empAddress, empEmail, empDateofbirth, empHourly_wage)
values ( "Ahmad", "0597843", "Ramllah" , "Ahmad@gamil.com" , '2000-02-4' , 8.0),
 ("Omar", "05978964", "Nablus" , "Omar@gamil.com" , '2001-08-4' , 9.0),
 ("Ali", "05971234", "Bethlehem", "Ali@gmail.com", '1998-05-15', 10.0),
 ("Khaled", "05973456", "Hebron", "Khaled@gmail.com", '1997-04-12', 11.0);
select * from sales.employee;



insert into customers (cusName ,cusPhone, cusAddress, cusDateofbirth, cusBankempIdAccount)
values ("Kareem", "0596754" , "Der Ammar", '1996-05-4', 1234),
 ("Mohammed","05964634" , "Der Ammar", '1998-05-8', 5436),
 ("Adam" , " 05968476" , "Ramllah" , '2000-04-5', 678),
 ("Ameer", "05962198", "Nablus", '1997-08-23', 3456);
select * from sales.customers;



insert into payment (payDate , payAmount , payType)
values ('2023-01-12', 294.5 , "Cash"),
 ('2023-01-31', 20.5 , "Cash"),
 ( '2023-02-15', 1500.0, "Credit Card"),
( '2023-05-11' ,  600.0, "Credit Card");
select * from sales.payment;



insert into products (proPRODDate , proEXPDate, proProduce_Place , proSupplier_Company , proType , proBrand , proWeight)
values
('2023-01-15', '2025-01-15', "Palestine", "Izhiman for coffe", "Coffe", "Izhiman", 2.5),
  ('2023-02-20', '2024-02-20', "Jordan", "Al Kasih Food Production Co", "sugar", "AL kasih", 1.0),
  ('2023-03-25', '2025-03-25', "Egypt", "Lipton Company ", "Tea", "Lipton", 0.5),
  ('2023-04-30', '2024-04-30', " Palestine", "Snabel Company", "white flour", "Snabel", 3.0);
  SELECT * FROM sales.products;



insert into supplier_company (supName , supPhone , subAddress , subEmail)
values 
( "Candia" , "0549384" , "Ramllah" , "Candia@gmail.com"),
( "AL Gebreni" , "0549384 ", "Hebron" , "gebreni@gmail.com"),
("GreenFields", "0567890", "Nablus", "greenfields@gmail.com"),
  ( "Saleh Khalaf", "0598765", "Bethlehem", "sunriseimports@gmail.com");
select * from sales.supplier_company;

