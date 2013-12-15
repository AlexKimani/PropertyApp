-- phpMyAdmin SQL Dump
-- version 4.0.4.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Dec 15, 2013 at 03:06 AM
-- Server version: 5.5.34-0ubuntu0.13.10.1
-- PHP Version: 5.5.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `PropertyApp`
--
CREATE DATABASE IF NOT EXISTS `PropertyApp` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `PropertyApp`;

-- --------------------------------------------------------

--
-- Table structure for table `COMMERCIAL_TB`
--

CREATE TABLE IF NOT EXISTS `COMMERCIAL_TB` (
  `com_id` int(11) NOT NULL AUTO_INCREMENT,
  `commercial_bnum` varchar(45) NOT NULL,
  `commercial_type` varchar(20) NOT NULL,
  `property_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`com_id`),
  KEY `property_id` (`property_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

-- --------------------------------------------------------

--
-- Table structure for table `LAND_TB`
--

CREATE TABLE IF NOT EXISTS `LAND_TB` (
  `land_id` int(11) NOT NULL AUTO_INCREMENT,
  `land_title` varchar(45) NOT NULL,
  `land_type` varchar(20) NOT NULL,
  `property_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`land_id`),
  KEY `property_id` (`property_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=40 ;

-- --------------------------------------------------------

--
-- Table structure for table `PROPERTY_TB`
--

CREATE TABLE IF NOT EXISTS `PROPERTY_TB` (
  `property_id` int(11) NOT NULL AUTO_INCREMENT,
  `property_name` varchar(60) NOT NULL,
  `property_size` varchar(45) NOT NULL,
  `property_type` varchar(45) NOT NULL,
  `property_location` varchar(60) NOT NULL,
  `property_price` varchar(30) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`property_id`),
  KEY `user_id` (`user_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=102 ;

-- --------------------------------------------------------

--
-- Table structure for table `RESIDENTIAL_TB`
--

CREATE TABLE IF NOT EXISTS `RESIDENTIAL_TB` (
  `residential_id` int(11) NOT NULL AUTO_INCREMENT,
  `residential_nor` varchar(45) NOT NULL,
  `residential_type` varchar(20) NOT NULL,
  `property_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`residential_id`),
  KEY `property_id` (`property_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

-- --------------------------------------------------------

--
-- Table structure for table `USER_TB`
--

CREATE TABLE IF NOT EXISTS `USER_TB` (
  `user_fname` varchar(45) NOT NULL,
  `user_sname` varchar(45) NOT NULL,
  `user_username` varchar(20) NOT NULL,
  `user_password` varchar(30) NOT NULL,
  `user_occupation` varchar(60) NOT NULL,
  `user_gender` varchar(10) NOT NULL,
  `user_address` varchar(60) NOT NULL,
  `user_email` varchar(60) NOT NULL,
  `user_phonenumber` varchar(20) NOT NULL,
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `COMMERCIAL_TB`
--
ALTER TABLE `COMMERCIAL_TB`
  ADD CONSTRAINT `COMMERCIAL_TB_ibfk_1` FOREIGN KEY (`property_id`) REFERENCES `PROPERTY_TB` (`property_id`);

--
-- Constraints for table `LAND_TB`
--
ALTER TABLE `LAND_TB`
  ADD CONSTRAINT `LAND_TB_ibfk_1` FOREIGN KEY (`property_id`) REFERENCES `PROPERTY_TB` (`property_id`);

--
-- Constraints for table `PROPERTY_TB`
--
ALTER TABLE `PROPERTY_TB`
  ADD CONSTRAINT `PROPERTY_TB_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `USER_TB` (`user_id`);

--
-- Constraints for table `RESIDENTIAL_TB`
--
ALTER TABLE `RESIDENTIAL_TB`
  ADD CONSTRAINT `RESIDENTIAL_TB_ibfk_1` FOREIGN KEY (`property_id`) REFERENCES `PROPERTY_TB` (`property_id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
