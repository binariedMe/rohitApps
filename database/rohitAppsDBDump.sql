/*
SQLyog Ultimate v9.50 
MySQL - 5.6.15 : Database - chat
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`chat` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `chat`;

/*Table structure for table `address` */

DROP TABLE IF EXISTS `address`;

CREATE TABLE `address` (
  `city` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `zipCode` varchar(255) DEFAULT NULL,
  `landMark` varchar(1000) DEFAULT NULL,
  `addressLine1` varchar(1000) DEFAULT NULL,
  `addressLine2` varchar(1000) DEFAULT NULL,
  `user_email_id` varchar(255) DEFAULT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  KEY `id` (`id`),
  KEY `user_address_key_constraint` (`user_email_id`),
  CONSTRAINT `user_address_key_constraint` FOREIGN KEY (`user_email_id`) REFERENCES `user` (`email_id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `address` */

/*Table structure for table `friend` */

DROP TABLE IF EXISTS `friend`;

CREATE TABLE `friend` (
  `user_email` varchar(255) NOT NULL,
  `friend_email` varchar(255) NOT NULL,
  `friend_name` varchar(255) DEFAULT NULL,
  KEY `FK_friend_list_1` (`user_email`),
  KEY `FK_friend_list_2` (`friend_email`),
  CONSTRAINT `friend_user_email_id` FOREIGN KEY (`user_email`) REFERENCES `user` (`email_id`) ON UPDATE CASCADE,
  CONSTRAINT `friend_friend_email_id` FOREIGN KEY (`friend_email`) REFERENCES `user` (`email_id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `friend` */

insert  into `friend`(`user_email`,`friend_email`,`friend_name`) values ('itsashitforwht@gmail.com','ashittheone@gmail.com','Rohit Kumar'),('ashit@gmail.com','kashak@gmail.com','Himanshu Kumar'),('ashittheone.98@gmail.com','itsashitforwht@gmail.com','Ashit'),('ashittheone.98@gmail.com','kabtak@outlook.com','Rohit'),('ashittheone.98@gmail.com','kashak@gmail.com','Himanshu Kumar');

/*Table structure for table `message` */

DROP TABLE IF EXISTS `message`;

CREATE TABLE `message` (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `sender_email` varchar(255) DEFAULT NULL,
  `recepient_email` varchar(255) DEFAULT NULL,
  `message_text` varchar(1000) DEFAULT NULL,
  `time_stamp` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `messageId` (`id`),
  KEY `FK_message_mapping_1` (`sender_email`),
  KEY `FK_message_mapping_2` (`recepient_email`),
  CONSTRAINT `sender_user_email_constraint` FOREIGN KEY (`sender_email`) REFERENCES `user` (`email_id`) ON UPDATE CASCADE,
  CONSTRAINT `recepient_user_email` FOREIGN KEY (`recepient_email`) REFERENCES `user` (`email_id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `message` */

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `email_id` varchar(255) NOT NULL,
  `Password` varchar(255) NOT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `full_name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`email_id`),
  KEY `index_1` (`email_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `user` */

insert  into `user`(`email_id`,`Password`,`first_name`,`last_name`,`full_name`,`phone`) values ('ashit@gmail.com','thatstheone','Rohit Kumar',NULL,NULL,NULL),('ashittheone.98@gmail.com','thatstheone','Rohit',NULL,NULL,NULL),('ashittheone@gmail.com','thatstheone','Rohit Kumar',NULL,NULL,NULL),('itsashitforwht@gmail.com','ashitforwht','Ashit',NULL,NULL,NULL),('kabtak@outlook.com','love','Rohit',NULL,NULL,NULL),('kashak@gmail.com','love','Himanshu Kumar',NULL,NULL,NULL);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
