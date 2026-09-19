-- ScratchPOS demo schema. Product names and tickets are fictional coursework stock.
DROP DATABASE IF EXISTS `lottery_pos`;
CREATE DATABASE IF NOT EXISTS `lottery_pos` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `lottery_pos`;

DROP TABLE IF EXISTS `order_detail`;
DROP TABLE IF EXISTS `sale_order`;
DROP TABLE IF EXISTS `product`;

CREATE TABLE `product` (
  `product_id` varchar(20) NOT NULL,
  `category` varchar(50) NOT NULL,
  `name` varchar(150) NOT NULL,
  `price` int(11) NOT NULL,
  `photo` varchar(200) NOT NULL,
  `description` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `product` (`product_id`, `category`, `name`, `price`, `photo`, `description`) VALUES
	('p-2k-101', '2k', '金庫示範券', 2000, '2k-1.jpg', '虛構商品，僅供課程示範'),
	('p-2k-102', '2k', '紅包示範券', 2000, '2k-2.jpg', '虛構商品，僅供課程示範'),
	('p-1k-103', '1k', '行運示範券', 1000, '1k-1.jpg', '虛構商品，僅供課程示範'),
	('p-1k-104', '1k', '吉利示範券', 1000, '1k-2.jpg', '虛構商品，僅供課程示範'),
	('p-5-105', '1k', '年終示範券', 1000, '1k-3.jpg', '虛構商品，僅供課程示範'),
	('p-5-106', '500', '玉門示範券', 500, '5-1.jpg', '虛構商品，僅供課程示範'),
	('p-5-107', '500', '五福示範券', 500, '5-2.jpg', '虛構商品，僅供課程示範'),
	('p-5-108', '500', '發袋示範券', 500, '5-3.jpg', '虛構商品，僅供課程示範'),
	('p-5-109', '500', '金線示範券', 500, '5-4.jpg', '虛構商品，僅供課程示範'),
	('p-5-110', '500', '財路示範券', 500, '5-5.jpg', '虛構商品，僅供課程示範'),
	('p-2-111', '200', '起手示範券', 200, '2-1.jpg', '虛構商品，僅供課程示範'),
	('p-2-112', '200', '三星示範券', 200, '2-2.jpg', '虛構商品，僅供課程示範'),
	('p-2-113', '200', '新歲示範券', 200, '2-3.jpg', '虛構商品，僅供課程示範'),
	('p-2-114', '200', '滿屋示範券', 200, '2-4.jpg', '虛構商品，僅供課程示範'),
	('p-2-115', '200', '連線示範券', 200, '2-5.jpg', '虛構商品，僅供課程示範'),
	('p-2-116', '200', '方城示範券', 200, '2-6.jpg', '虛構商品，僅供課程示範'),
	('p-2-117', '200', '連發示範券', 200, '2-7.jpg', '虛構商品，僅供課程示範'),
	('p-1-118', '100', '聚寶示範券', 100, '1-1.jpg', '虛構商品，僅供課程示範'),
	('p-1-119', '100', '旺來示範券', 100, '1-2.jpg', '虛構商品，僅供課程示範'),
	('p-1-120', '100', '新春示範券', 100, '1-3.jpg', '虛構商品，僅供課程示範'),
	('p-1-121', '100', '金鼓示範券', 100, '1-4.jpg', '虛構商品，僅供課程示範'),
	('p-1-122', '100', '來訪示範券', 100, '1-5.jpg', '虛構商品，僅供課程示範'),
	('p-1-123', '100', '小兔示範券', 100, '1-6.jpg', '虛構商品，僅供課程示範');

CREATE TABLE `sale_order` (
  `order_num` varchar(20) NOT NULL,
  `order_date` datetime NOT NULL DEFAULT current_timestamp(),
  `total_price` double(22,0) NOT NULL DEFAULT 0,
  `customer_name` varchar(150) DEFAULT NULL,
  `customer_address` varchar(250) DEFAULT NULL,
  `customer_phone` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`order_num`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `sale_order` (`order_num`, `order_date`, `total_price`, `customer_name`, `customer_address`, `customer_phone`) VALUES
('ord-101', '2023-05-04 10:12:00', 2000, '示範顧客甲', '示範地址', '0900000001'),
('ord-102', '2023-05-04 16:40:00', 5000, '示範顧客乙', '示範地址', '0900000002'),
('ord-103', '2023-05-05 11:05:00', 800, '示範顧客丙', '示範地址', '0900000003');

CREATE TABLE `order_detail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_num` varchar(20) NOT NULL,
  `product_id` varchar(20) NOT NULL,
  `quantity` int(11) NOT NULL DEFAULT 0,
  `product_price` int(11) DEFAULT NULL,
  `product_name` varchar(150) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_order_detail_product` (`product_id`),
  KEY `FK_order_detail_order` (`order_num`),
  CONSTRAINT `FK_order_detail_sale_order` FOREIGN KEY (`order_num`) REFERENCES `sale_order` (`order_num`),
  CONSTRAINT `FK_order_detail_product` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `order_detail` (`order_num`, `product_id`, `quantity`, `product_price`, `product_name`) VALUES
('ord-101', 'p-2k-101', 1, 2000, '金庫示範券'),
('ord-102', 'p-2k-102', 1, 2000, '紅包示範券'),
('ord-102', 'p-1k-103', 3, 1000, '行運示範券'),
('ord-103', 'p-2-111', 2, 200, '起手示範券'),
('ord-103', 'p-1-118', 4, 100, '聚寶示範券');
