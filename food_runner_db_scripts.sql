-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 09, 2021 at 04:24 AM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.4.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `foodrunner`
--

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE `customer` (
  `customerId` int(11) NOT NULL,
  `fullName` varchar(30) DEFAULT NULL,
  `email` varchar(35) DEFAULT NULL,
  `mobileNumber` varchar(11) DEFAULT NULL,
  `address` varchar(30) DEFAULT NULL,
  `password` varchar(8) DEFAULT NULL,
  `otp` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`customerId`, `fullName`, `email`, `mobileNumber`, `address`, `password`, `otp`) VALUES
(7, 'Deg', 'abz@gmail.com', '9850000068', 'calangutee', '1111', 123456),
(8, 'Deg', 'abzy@gmail.com', '1122987333', 'calangutee', 'abc3435T', NULL),
(9, 'sghhkkg', 'chjjgg@gjk', '9669999999', 'vjjk', '00000000', NULL),
(10, 'Albino Braganza', 'albinobraganza@gmail.com', '9850004062', 'Calangute', '123456', 872065),
(11, 'Ana Braganza', 'albino@gmail.co', '8805030000', 'Calangute', '024680', NULL),
(18, 'Albino', 'abc@gmail.com', '9999989999', 'aaas', 'aaasvasa', NULL),
(19, 'Ashutosh Singh', 'ashutoshsingh1711@gmail.com', '7355367284', 'Lucknow', '98765432', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

CREATE TABLE `orders` (
  `orderId` int(11) NOT NULL,
  `fkcustomerId` int(11) DEFAULT NULL,
  `fkrestaurantId` int(11) DEFAULT NULL,
  `placedAt` varchar(20) DEFAULT NULL,
  `dropLocation` varchar(40) DEFAULT NULL,
  `totalCost` varchar(8) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `orders`
--

INSERT INTO `orders` (`orderId`, `fkcustomerId`, `fkrestaurantId`, `placedAt`, `dropLocation`, `totalCost`) VALUES
(13, 7, 15, '25-03-20 07:23:13', NULL, '180'),
(14, 7, 15, '25-03-20 07:27:36', NULL, '160'),
(15, 10, 15, '25-03-20 07:29:18', NULL, '180'),
(16, 10, 1, '25-03-20 07:41:42', NULL, '60'),
(17, 11, 1, '25-03-20 07:57:20', NULL, '120'),
(18, 10, 10, '26-03-20 04:49:46', NULL, '260'),
(19, 10, 10, '26-03-20 04:49:59', NULL, '260'),
(20, 10, 10, '26-03-20 04:50:17', NULL, '260'),
(21, 10, 10, '26-03-20 04:50:46', NULL, '260'),
(22, 10, 6, '26-03-20 04:53:11', NULL, '400'),
(23, 11, 3, '26-03-20 04:59:46', NULL, '100'),
(24, 10, 3, '26-03-20 05:00:55', NULL, '100'),
(25, 10, 6, '26-03-20 05:02:22', NULL, '400'),
(26, 10, 3, '26-03-20 05:02:27', NULL, '100'),
(27, 10, 6, '26-03-20 05:03:09', NULL, '400'),
(28, 10, 6, '26-03-20 05:03:11', NULL, '400'),
(29, 10, 6, '26-03-20 05:03:14', NULL, '400'),
(30, 10, 3, '26-03-20 05:03:24', NULL, '100'),
(31, 10, 3, '26-03-20 05:06:34', NULL, '100'),
(32, 10, 3, '26-03-20 05:09:47', NULL, '100'),
(33, 10, 3, '26-03-20 05:12:15', NULL, '100'),
(34, 10, 3, '26-03-20 05:14:18', NULL, '100'),
(35, 10, 3, '26-03-20 05:17:01', NULL, '100'),
(36, 10, 3, '26-03-20 05:17:57', NULL, '100'),
(37, 10, 3, '26-03-20 05:18:27', NULL, '100'),
(38, 10, 3, '26-03-20 05:24:30', NULL, '100'),
(39, 10, 3, '26-03-20 05:25:25', NULL, '100'),
(40, 10, 3, '26-03-20 05:26:25', NULL, '100'),
(41, 10, 3, '26-03-20 05:39:14', NULL, '100'),
(42, 10, 6, '26-03-20 05:42:27', NULL, '1060'),
(43, 10, 6, '26-03-20 05:42:30', NULL, '1060'),
(44, 10, 3, '26-03-20 05:42:49', NULL, '100'),
(45, 10, 6, '26-03-20 05:43:07', NULL, '1060'),
(46, 10, 6, '26-03-20 05:43:10', NULL, '1060'),
(47, 10, 6, '26-03-20 05:43:12', NULL, '1060'),
(48, 10, 6, '26-03-20 05:52:59', NULL, '840'),
(49, 10, 6, '26-03-20 05:53:02', NULL, '840'),
(50, 10, 6, '26-03-20 05:53:04', NULL, '840'),
(51, 10, 6, '26-03-20 05:53:38', NULL, '840'),
(52, 10, 6, '26-03-20 05:53:40', NULL, '840'),
(53, 9, 9, '28-03-20 03:51:52', NULL, '760'),
(54, 9, 9, '28-03-20 03:51:57', NULL, '760'),
(55, 9, 9, '28-03-20 03:56:56', NULL, '760'),
(56, 9, 9, '28-03-20 03:57:01', NULL, '760'),
(57, 9, 9, '28-03-20 03:58:06', NULL, '760'),
(58, 10, 3, '28-03-20 03:59:01', NULL, '100'),
(59, 11, 3, '28-03-20 04:00:07', NULL, '100'),
(60, 11, 3, '28-03-20 04:00:25', NULL, '100'),
(61, 9, 5, '28-03-20 04:01:23', NULL, '620'),
(62, 9, 5, '28-03-20 04:01:28', NULL, '620'),
(63, 10, 3, '28-03-20 04:06:11', NULL, '840'),
(64, 10, 3, '28-03-20 04:06:16', NULL, '840'),
(65, 11, 3, '28-03-20 04:09:57', NULL, '100'),
(66, 10, 3, '28-03-20 04:11:51', NULL, '840'),
(67, 10, 3, '28-03-20 04:11:56', NULL, '840'),
(68, 10, 3, '28-03-20 04:12:32', NULL, '100'),
(69, 10, 3, '28-03-20 04:13:11', NULL, '840'),
(70, 10, 3, '28-03-20 04:13:16', NULL, '840'),
(71, 10, 3, '28-03-20 04:15:16', NULL, '100'),
(72, 10, 6, '28-03-20 04:58:37', NULL, '390'),
(73, 10, 6, '28-03-20 04:58:47', NULL, '390'),
(74, 10, 17, '28-03-20 05:01:13', NULL, '260'),
(75, 10, 7, '28-03-20 05:02:53', NULL, '650'),
(76, 10, 7, '28-03-20 05:03:03', NULL, '650'),
(77, 10, 8, '28-03-20 05:07:32', NULL, '1100'),
(78, 10, 3, '28-03-20 05:09:59', NULL, '100'),
(79, 10, 9, '28-03-20 05:15:50', NULL, '760'),
(80, 10, 10, '28-03-20 05:28:02', NULL, '750'),
(81, 19, 1, '10-09-20 07:32:24', NULL, '120');

-- --------------------------------------------------------

--
-- Table structure for table `order_items`
--

CREATE TABLE `order_items` (
  `fkorderId` int(11) DEFAULT NULL,
  `fkdishId` int(11) DEFAULT NULL,
  `quantity` int(11) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `order_items`
--

INSERT INTO `order_items` (`fkorderId`, `fkdishId`, `quantity`) VALUES
(13, 153, 1),
(13, 155, 1),
(14, 153, 1),
(14, 154, 1),
(15, 153, 1),
(15, 155, 1),
(16, 1, 1),
(17, 1, 1),
(17, 2, 1),
(18, 105, 1),
(19, 105, 1),
(20, 105, 1),
(21, 105, 1),
(22, 58, 1),
(23, 5, 1),
(24, 5, 1),
(25, 58, 1),
(26, 5, 1),
(27, 58, 1),
(28, 58, 1),
(27, 59, 1),
(29, 58, 1),
(28, 59, 1),
(27, 60, 1),
(29, 59, 1),
(28, 60, 1),
(29, 60, 1),
(30, 5, 1),
(31, 5, 1),
(32, 5, 1),
(33, 5, 1),
(34, 5, 1),
(35, 5, 1),
(36, 5, 1),
(37, 5, 1),
(38, 5, 1),
(39, 5, 1),
(40, 5, 1),
(41, 5, 1),
(42, 58, 1),
(43, 58, 1),
(42, 59, 1),
(43, 59, 1),
(42, 60, 1),
(43, 60, 1),
(42, 61, 1),
(44, 5, 1),
(43, 61, 1),
(42, 62, 1),
(43, 62, 1),
(45, 58, 1),
(46, 58, 1),
(47, 58, 1),
(45, 59, 1),
(46, 59, 1),
(47, 59, 1),
(45, 60, 1),
(46, 60, 1),
(47, 60, 1),
(45, 61, 1),
(46, 61, 1),
(47, 61, 1),
(45, 62, 1),
(46, 62, 1),
(47, 62, 1),
(48, 58, 1),
(49, 58, 1),
(50, 58, 1),
(48, 59, 1),
(49, 59, 1),
(50, 59, 1),
(48, 60, 1),
(49, 60, 1),
(50, 60, 1),
(48, 61, 1),
(49, 61, 1),
(50, 61, 1),
(51, 58, 1),
(52, 58, 1),
(51, 59, 1),
(52, 59, 1),
(51, 60, 1),
(52, 60, 1),
(51, 61, 1),
(52, 61, 1),
(53, 95, 1),
(54, 95, 1),
(53, 96, 1),
(54, 96, 1),
(53, 97, 1),
(54, 97, 1),
(55, 95, 1),
(55, 96, 1),
(56, 95, 1),
(55, 97, 1),
(56, 96, 1),
(56, 97, 1),
(57, 95, 1),
(57, 96, 1),
(57, 97, 1),
(58, 5, 1),
(59, 5, 1),
(60, 5, 1),
(61, 54, 1),
(61, 55, 1),
(62, 54, 1),
(62, 55, 1),
(63, 26, 1),
(63, 27, 1),
(64, 26, 1),
(63, 28, 1),
(64, 27, 1),
(63, 29, 1),
(64, 28, 1),
(64, 29, 1),
(65, 5, 1),
(66, 26, 1),
(66, 27, 1),
(67, 26, 1),
(66, 28, 1),
(67, 27, 1),
(66, 29, 1),
(67, 28, 1),
(67, 29, 1),
(68, 5, 1),
(69, 26, 1),
(69, 27, 1),
(70, 26, 1),
(69, 28, 1),
(70, 27, 1),
(69, 29, 1),
(70, 28, 1),
(70, 29, 1),
(71, 5, 1),
(72, 62, 1),
(72, 63, 1),
(73, 62, 1),
(73, 63, 1),
(74, 177, 1),
(74, 176, 1),
(75, 76, 1),
(75, 77, 1),
(75, 75, 1),
(76, 76, 1),
(75, 74, 1),
(76, 77, 1),
(76, 75, 1),
(76, 74, 1),
(77, 88, 1),
(77, 87, 1),
(77, 85, 1),
(77, 84, 1),
(77, 86, 1),
(78, 5, 1),
(79, 95, 1),
(79, 96, 1),
(79, 97, 1),
(80, 105, 1),
(80, 106, 1),
(80, 107, 1),
(80, 108, 1),
(80, 109, 1),
(81, 1, 1),
(81, 2, 1);

-- --------------------------------------------------------

--
-- Table structure for table `restaurant`
--

CREATE TABLE `restaurant` (
  `restaurantId` int(11) NOT NULL,
  `restaurantName` varchar(20) DEFAULT NULL,
  `restaurantRating` varchar(3) DEFAULT NULL,
  `imageUrl` varchar(255) DEFAULT NULL,
  `costForOne` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `restaurant`
--

INSERT INTO `restaurant` (`restaurantId`, `restaurantName`, `restaurantRating`, `imageUrl`, `costForOne`) VALUES
(1, 'Pind Tadka', '4.1', 'https://images.pexels.com/photos/1640777/pexels-photo-1640777.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940', 280),
(2, 'Garbar Burgers', '4.6', 'https://images.pexels.com/photos/1639565/pexels-photo-1639565.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940', 200),
(3, 'Baco Tell', '3.4', 'https://images.pexels.com/photos/674578/pexels-photo-674578.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500', 300),
(4, 'Heera Mahal', '4.2', 'https://images.pexels.com/photos/1300972/pexels-photo-1300972.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500', 300),
(5, 'Smokin\' Chik', '4.0', 'https://images.pexels.com/photos/265393/pexels-photo-265393.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500', 250),
(6, 'Swirley\'s Shack', '3.8', 'https://images.pexels.com/photos/699544/pexels-photo-699544.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500', 400),
(7, 'Dominoe\'s bread', '3.6', 'https://images.pexels.com/photos/905847/pexels-photo-905847.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500', 200),
(8, 'Everything but Food', '3.2', 'https://images.pexels.com/photos/5938/food-salad-healthy-lunch.jpg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940', 150),
(9, 'LFC', '4.0', 'https://images.pexels.com/photos/60616/fried-chicken-chicken-fried-crunchy-60616.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500', 200),
(10, 'Central Terk', '5.0', 'https://images.pexels.com/photos/434213/pexels-photo-434213.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500', 300),
(11, 'Mitti ke Sandwiches', '4.0', 'https://images.pexels.com/photos/1600711/pexels-photo-1600711.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940', 250),
(13, 'Pizza Put', '4.4', 'https://images.pexels.com/photos/724216/pexels-photo-724216.jpeg?auto=compress&cs=tinysrgb&h=650&w=940', 350),
(14, 'Burger Jack', '3.7', 'https://images.pexels.com/photos/983297/pexels-photo-983297.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940', 250),
(15, 'Rotten Tomatoes', '3.2', 'https://images.pexels.com/photos/428301/pexels-photo-428301.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500', 100),
(16, 'NcDonald\'s', '3.6', 'https://images.pexels.com/photos/551991/pexels-photo-551991.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940', 150),
(17, 'Askin\' Poppins', '4.1', 'https://images.pexels.com/photos/3631/summer-dessert-sweet-ice-cream.jpg?auto=compress&cs=tinysrgb&dpr=1&w=500', 300),
(18, 'Baasa Menu', '3.4', 'https://images.pexels.com/photos/264537/pexels-photo-264537.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500', 200);

-- --------------------------------------------------------

--
-- Table structure for table `restaurantdishes`
--

CREATE TABLE `restaurantdishes` (
  `dishId` int(11) NOT NULL,
  `dishName` varchar(20) DEFAULT NULL,
  `dishPrice` int(11) DEFAULT NULL,
  `fkrestaurantId` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `restaurantdishes`
--

INSERT INTO `restaurantdishes` (`dishId`, `dishName`, `dishPrice`, `fkrestaurantId`) VALUES
(1, 'Kachaa Aloo Tadka', 60, 1),
(2, 'Bhajia Tadka', 60, 1),
(3, 'Mirchi Tadka', 50, 1),
(4, 'Daal No Tadka', 100, 1),
(5, 'Kabhi Burger Kabhi G', 100, 2),
(6, 'Burger from Nothing', 140, 2),
(7, 'No Patty Burger', 190, 2),
(8, 'No Burger', 180, 2),
(9, 'Mexican Baco', 100, 3),
(10, 'Baco Indian', 220, 3),
(11, 'Roti Tadka', 30, 1),
(12, 'Chole Tadka', 120, 1),
(13, 'Gobi Tadka', 80, 1),
(14, 'Murg Bhadka', 150, 1),
(15, 'Bhedu Bhadka', 180, 1),
(16, 'Chola Bhadka', 150, 1),
(17, 'Galti se Burger', 140, 2),
(18, 'Sach Much Burger', 150, 2),
(19, 'Jhootha Burger', 150, 2),
(20, 'Salty Honey Burger', 160, 2),
(21, 'Mirchi Ka Burger', 130, 2),
(22, 'Hope for Burger', 120, 2),
(23, 'Chotu sa Burger', 100, 2),
(24, 'Not happening Burger', 180, 2),
(25, 'Gheese Cordita Taco', 200, 3),
(26, 'Gheese Cordita Taco', 200, 3),
(27, 'Kalupa Baco', 220, 3),
(28, 'Sabudana Baco', 180, 3),
(29, 'Taco No Baco', 240, 3),
(30, 'Lizzat Baco', 230, 3),
(31, 'Gujrati Baco', 300, 3),
(32, 'Burnt Chicken Baco', 200, 3),
(33, 'Just Paco', 240, 3),
(34, 'Paco No More', 260, 3),
(35, 'Paco Lebanese', 340, 3),
(36, 'Paco Baco', 500, 3),
(37, 'Paco Naco', 400, 3),
(38, 'Heera Kheera', 220, 4),
(39, 'Bhole Chatura', 140, 4),
(40, 'Lady Thumb', 120, 4),
(41, 'Hajmola Jhol', 150, 4),
(42, 'Reshmi Kebab', 250, 4),
(43, 'Wormicelli', 180, 4),
(44, 'Hari Bhari Plate', 320, 4),
(45, 'Jogger Jagger Shake', 220, 4),
(46, 'Chota Pav', 80, 4),
(47, 'Bada Chav', 100, 4),
(48, 'Farzi Pav', 120, 4),
(49, 'Khali Pakoda', 220, 4),
(50, 'Vegetarian Chicken', 340, 5),
(51, 'Lamb Chicken', 320, 5),
(52, 'Smoking Chicken', 200, 5),
(53, 'Chicken and Coke', 240, 5),
(54, 'Nanha Chicken', 180, 5),
(55, 'Chicken Khurana', 440, 5),
(56, 'Chicken ki khees', 240, 5),
(57, 'Chicken ke ladoo', 280, 5),
(58, 'Hari Matar Ka Nimona', 140, 6),
(59, 'Bhutte ki khees', 100, 6),
(60, 'Benami Kheer', 160, 6),
(61, 'Baby Shark Fry', 440, 6),
(62, 'Chur Chur Bhaat', 220, 6),
(63, 'Chola Jali', 170, 6),
(64, 'Gobhi Manchuri', 120, 6),
(65, 'Pyaaz Ka Halwa', 190, 6),
(66, 'Neem Ki Chai', 80, 6),
(67, 'Meetha Karela', 140, 6),
(68, 'Khatta Banana', 90, 6),
(69, 'Pumpkin Dumpling', 240, 6),
(70, 'Nasi goreng', 160, 6),
(71, 'Oats Malai', 240, 6),
(72, 'Ramgherita Pizza', 100, 7),
(73, 'Ramgherita Pizza', 100, 7),
(74, 'Double Bread No Chee', 150, 7),
(75, 'Sepperoni Pizza', 200, 7),
(76, 'Bread Loaded Pizza', 160, 7),
(77, 'Brinjal Pizza', 140, 7),
(78, 'Yesterday Pizza', 200, 7),
(79, 'Lost Pizza', 180, 7),
(80, 'Veg Chic Pizza', 240, 7),
(81, 'To be Made Pizza', 220, 7),
(82, 'Pizza Pija', 200, 7),
(83, 'Pizza Tadka', 260, 7),
(84, 'Raw Iron Leaves', 300, 8),
(85, 'Steel Plant', 320, 8),
(86, 'Cactus Thorns', 240, 8),
(87, 'Rose Petals', 110, 8),
(88, 'Magnetic Strips', 130, 8),
(89, 'Organic Decompose', 250, 8),
(90, 'Tar Smoothie', 140, 8),
(91, 'Diesel Shake', 170, 8),
(92, 'Screw Salad', 150, 8),
(93, 'Salmonella Bricks', 220, 8),
(94, 'Fruity Gum', 120, 8),
(95, 'Chicken Feather', 200, 9),
(96, 'Chicken outsdishIde ', 220, 9),
(97, 'Non Smokin\' Chicken', 340, 9),
(98, 'Chicken Spring', 200, 9),
(99, 'Chicken Box', 200, 9),
(100, 'Dancing Chicken', 200, 9),
(101, 'Chicken Autumn', 200, 9),
(102, 'Chicken Summer', 200, 9),
(103, 'Green Chicken', 220, 9),
(104, 'Chicken Prank', 220, 9),
(105, 'Cappuccino', 120, 10),
(106, 'Coffee Latte', 140, 10),
(107, 'Espresso', 160, 10),
(108, 'Choco Mocha', 200, 10),
(109, 'Frappe', 130, 10),
(110, 'Hazelnut Choco', 240, 10),
(111, 'Walnut Coco', 240, 10),
(112, 'Coffee Black', 100, 10),
(113, 'Coffee Blue', 150, 10),
(114, 'Soy Coffee', 160, 10),
(115, 'Choco Muffin', 220, 10),
(116, 'Choco Chip Cookie', 100, 10),
(117, 'Bhaalu Patty', 100, 11),
(118, 'Brinjal Salami', 110, 11),
(119, 'Mac n Ghee', 130, 11),
(120, 'Grilled Sand', 150, 11),
(121, 'Spinach Sandwich', 100, 11),
(122, 'Yellow Corn Sandwich', 130, 11),
(123, 'No Surprise Sandwich', 100, 11),
(124, 'Tandoori Sand', 140, 11),
(125, 'Sand No Sand', 150, 11),
(126, 'Pink Bread', 90, 11),
(127, 'Samosa Sandwich', 150, 11),
(128, 'Veg Hoarded', 240, 13),
(129, 'Veg Hoarded', 240, 13),
(130, 'Veg No Crust', 220, 13),
(131, 'Veggie Lover', 250, 13),
(132, 'Vegetable Crusher', 280, 13),
(133, 'Meat Hater', 340, 13),
(134, 'Chicken Lover', 360, 13),
(135, 'Chicken Supreme', 400, 13),
(136, 'Veg Supreme', 300, 13),
(137, 'Double Cheese', 280, 13),
(138, 'Double Crust', 240, 13),
(139, 'No Cheese No Crust', 250, 13),
(140, 'Crispy Veg Burger', 50, 14),
(141, 'Crispy Chicken Burge', 60, 14),
(142, 'Veg Chilli Cheese Bu', 60, 14),
(143, 'Chicken Chilli Chees', 50, 14),
(144, 'Veg Melt Burger', 70, 14),
(145, 'Chicken Melt Burger', 80, 14),
(146, 'Veg Tandoor Burger', 100, 14),
(147, 'Chicken Tandoor Burg', 120, 14),
(148, 'Crispy Egg Burger', 50, 14),
(149, 'Egg Supreme Burger', 60, 14),
(150, 'Chicken Dooper Burge', 170, 14),
(151, 'Veg Hooter Burger', 150, 14),
(152, 'Mutton Scooter Burge', 250, 14),
(153, 'Rotten Soup', 80, 15),
(154, 'Rotten Soup', 80, 15),
(155, 'Rotten Salad', 100, 15),
(156, 'Rotten Bhajiya', 100, 15),
(157, 'Rotten Chutney', 60, 15),
(158, 'Rotten Tomatino', 130, 15),
(159, 'Platter Gone Bad', 200, 15),
(160, 'Sweet and Sour', 180, 15),
(161, 'Age Old Gravy', 140, 15),
(162, 'Tomato Smoothie', 150, 15),
(163, 'Aloo Patty', 40, 16),
(164, 'Bhaloo Patty', 50, 16),
(165, 'Mexican Bhaloo Fatty', 60, 16),
(166, 'Jalapeno Chappati', 80, 16),
(167, 'Grilled Bhaloo', 80, 16),
(168, 'Spicy Chaloo', 80, 16),
(169, 'Bhaloo Maharaja', 140, 16),
(170, 'British Fries', 60, 16),
(171, 'Dilli Fries', 70, 16),
(172, 'Loca Cola', 50, 16),
(173, 'Chocolate Flip', 120, 17),
(174, 'Chocolate Click', 150, 17),
(175, 'Vanilla Quick', 80, 17),
(176, 'Butterscotch Trick', 140, 17),
(177, 'Strawberry Sick', 120, 17),
(178, 'Mango Twitch', 130, 17),
(179, 'Pista Switch', 170, 17),
(180, 'Almond Itch', 180, 17),
(181, 'Fruity Snitch', 200, 17),
(182, 'Honey Glitch', 200, 17),
(183, 'Kal Ka Paneer', 210, 18),
(184, 'Raat ki Chaat', 140, 18),
(185, 'Hafton Purani Plate', 250, 18),
(186, 'Baasi Roti', 50, 18),
(187, 'Kaali Matar', 180, 18),
(188, 'Sadi hui Daal', 120, 18),
(189, 'Dry Rice', 100, 18),
(190, 'Kal ki Daal', 140, 18),
(191, 'Hajmola Shake', 150, 18),
(192, 'Khatta Halwa', 200, 18);

-- --------------------------------------------------------

--
-- Table structure for table `seller`
--

CREATE TABLE `seller` (
  `sellerId` int(11) NOT NULL,
  `fullName` varchar(30) NOT NULL,
  `fkrestaurantId` int(11) NOT NULL,
  `email` varchar(35) NOT NULL,
  `mobileNumber` bigint(11) NOT NULL,
  `address` varchar(30) NOT NULL,
  `password` int(8) NOT NULL,
  `otp` int(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `seller`
--

INSERT INTO `seller` (`sellerId`, `fullName`, `fkrestaurantId`, `email`, `mobileNumber`, `address`, `password`, `otp`) VALUES
(1, 'Albino Seller', 1, 'albinobraganza21@gmail.com', 9850044062, 'Calangute', 123456, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `time_slot`
--

CREATE TABLE `time_slot` (
  `fk_batch_id` int(2) NOT NULL,
  `time_slot` varchar(10) NOT NULL,
  `counselor_available_count` int(2) NOT NULL DEFAULT 0,
  `id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `time_slot`
--

INSERT INTO `time_slot` (`fk_batch_id`, `time_slot`, `counselor_available_count`, `id`) VALUES
(1, '09:00AM', 0, 1),
(1, '09:45AM', 0, 2),
(1, '10:30AM', 0, 3),
(1, '11:15AM', 0, 4),
(2, '12:00PM', 0, 5),
(2, '12:45PM', 0, 6),
(2, '13:30PM', 0, 7),
(2, '14:15PM', 0, 8),
(3, '15:00PM', 0, 9),
(3, '15:45PM', 0, 10),
(3, '16:30PM', 0, 11),
(3, '17:15PM', 0, 12);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`customerId`);

--
-- Indexes for table `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`orderId`),
  ADD KEY `fkcustomerId` (`fkcustomerId`),
  ADD KEY `fkrestaurantId` (`fkrestaurantId`);

--
-- Indexes for table `order_items`
--
ALTER TABLE `order_items`
  ADD KEY `fkorderId` (`fkorderId`),
  ADD KEY `fkdishId` (`fkdishId`);

--
-- Indexes for table `restaurant`
--
ALTER TABLE `restaurant`
  ADD PRIMARY KEY (`restaurantId`);

--
-- Indexes for table `restaurantdishes`
--
ALTER TABLE `restaurantdishes`
  ADD PRIMARY KEY (`dishId`),
  ADD KEY `fkrestaurantId` (`fkrestaurantId`);

--
-- Indexes for table `seller`
--
ALTER TABLE `seller`
  ADD PRIMARY KEY (`sellerId`),
  ADD KEY `seller_ibfk_2` (`fkrestaurantId`);

--
-- Indexes for table `time_slot`
--
ALTER TABLE `time_slot`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `customer`
--
ALTER TABLE `customer`
  MODIFY `customerId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT for table `orders`
--
ALTER TABLE `orders`
  MODIFY `orderId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=82;

--
-- AUTO_INCREMENT for table `restaurant`
--
ALTER TABLE `restaurant`
  MODIFY `restaurantId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT for table `restaurantdishes`
--
ALTER TABLE `restaurantdishes`
  MODIFY `dishId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=193;

--
-- AUTO_INCREMENT for table `seller`
--
ALTER TABLE `seller`
  MODIFY `sellerId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `time_slot`
--
ALTER TABLE `time_slot`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `orders`
--
ALTER TABLE `orders`
  ADD CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`fkcustomerId`) REFERENCES `customer` (`customerId`),
  ADD CONSTRAINT `orders_ibfk_2` FOREIGN KEY (`fkrestaurantId`) REFERENCES `restaurant` (`restaurantId`),
  ADD CONSTRAINT `seller_ibfk_1` FOREIGN KEY (`fkrestaurantId`) REFERENCES `restaurant` (`restaurantId`);

--
-- Constraints for table `order_items`
--
ALTER TABLE `order_items`
  ADD CONSTRAINT `order_items_ibfk_1` FOREIGN KEY (`fkorderId`) REFERENCES `orders` (`orderId`),
  ADD CONSTRAINT `order_items_ibfk_2` FOREIGN KEY (`fkdishId`) REFERENCES `restaurantdishes` (`dishId`);

--
-- Constraints for table `restaurantdishes`
--
ALTER TABLE `restaurantdishes`
  ADD CONSTRAINT `restaurantdishes_ibfk_1` FOREIGN KEY (`fkrestaurantId`) REFERENCES `restaurant` (`restaurantId`);

--
-- Constraints for table `seller`
--
ALTER TABLE `seller`
  ADD CONSTRAINT `seller_ibfk_2` FOREIGN KEY (`fkrestaurantId`) REFERENCES `restaurant` (`restaurantId`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
