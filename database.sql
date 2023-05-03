#drop tables
drop table tbl_review_item;
drop table tbl_ordered_item;
drop table tbl_payment_method;
#drop table tbl_cart;
drop table tbl_item;
drop table tbl_order;
drop table tbl_user;
#This code only works with derby database.
CREATE TABLE `tbl_user` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(20) NOT NULL,
  `last_name` varchar(20) NOT NULL,
  `street` varchar(20) NOT NULL,
  `city` varchar(20) NOT NULL,
  `zip` varchar(6) NOT NULL,
  `phone` varchar(10) NOT NULL,
  `email` varchar(50) NOT NULL,
  `password` varchar(256) NOT NULL,
  `user_type` varchar(10) NOT NULL,
  `admin` tinyint(1) NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `tbl_order` (
  `order_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `user_last_name` varchar(20) NOT NULL,
  `to_street` varchar(20) NOT NULL,
  `to_city` varchar(20) NOT NULL,
  `to_zip` varchar(6) NOT NULL,
  `order_date` date NOT NULL,
  `ship_date` date NOT NULL,
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `tbl_payment_method` (
  `method_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `card_number` varchar(16) NOT NULL,
  `cvv` varchar(3) NOT NULL,
  `card_holder` varchar(20) NOT NULL,
  `street` varchar(20) NOT NULL,
  `city` varchar(20) NOT NULL,
  `zip` varchar(6) NOT NULL,
  `phone` varchar(10) NOT NULL,
  `exp_date` date NOT NULL,
  PRIMARY KEY (`method_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `tbl_item` (
  `item_id` int NOT NULL AUTO_INCREMENT,
  `item_name` varchar(50) NOT NULL,
  `quantity` int NOT NULL,
  `item_type` varchar(10) NOT NULL,
  `brand` varchar(30) NOT NULL,
  `price` float NOT NULL,
  `item_description` varchar(100) NOT NULL,
  `item_image` blob NOT NULL,
  PRIMARY KEY (`item_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `tbl_ordered_item` (
  `id` int NOT NULL AUTO_INCREMENT,
  `item_id` int NOT NULL,
  `order_id` int NOT NULL,
  `item_name` varchar(10) NOT NULL,
  `quantity` int NOT NULL,
  `item_type` varchar(10) NOT NULL,
  `brand` varchar(10) NOT NULL,
  `price` float NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*
CREATE TABLE tbl_cart (
	cart_id INT NOT NULL GENERATED ALWAYS AS IDENTITY,
	item_id INT NOT NULL REFERENCES tbl_item(item_id),
	item_name VARCHAR (10) NOT NULL ,
	quantity INT NOT NULL,
	price INT NOT NULL,
	PRIMARY KEY (cart_id)
);
*/

#comment has a limit of 500 chars
CREATE TABLE `tbl_review_item` (
  `review_id` int NOT NULL AUTO_INCREMENT,
  `item_id` int NOT NULL,
  `user_id` int NOT NULL,
  `user_name` varchar(20) NOT NULL,
  `comment` varchar(500) NOT NULL,
  `review_date` date NOT NULL,
  `review_time` time NOT NULL,
  `review_stars` int NOT NULL,
  PRIMARY KEY (`review_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


#Insert Statmenets Items
INSERT INTO `db_teamm`.`tbl_item`
(
`item_name`,
`quantity`,
`item_type`,
`brand`,
`price`,
`item_description`,
`item_image`)
VALUES
(
'Bedbug',
55,
'Spray',
'ORTH',
10.99,
'Bedbug spray',
'blob');

INSERT INTO `db_teamm`.`tbl_item`
(
`item_name`,
`quantity`,
`item_type`,
`brand`,
`price`,
`item_description`,
`item_image`)
VALUES
(

'Strong Ducktape',
78,
'Ducktape',
'ORTH',
6.99,
'Gray Ducktape',
'blob');

INSERT INTO `db_teamm`.`tbl_item`
(
`item_name`,
`quantity`,
`item_type`,
`brand`,
`price`,
`item_description`,
`item_image`)
VALUES
(
'Chair',
100,
'Chair',
'ORTH',
55.50,
'Chair',
'blob');

#Insert Statmenets Items
INSERT INTO `db_teamm`.`tbl_user`
(
`first_name`,
`last_name`,
`street`,
`city`,
`zip`,
`phone`,
`email`,
`password`,
`user_type`,
`admin`)
VALUES
(
'Mario',
'One',
'Mario\'s Street',
'SomeCity',
'6667',
'7777777777',
'mario@m.com',
'ef92b778bafe771e89245b89ecbc08a44a4e166c06659911881f383d4473e94f',
'',
false);

