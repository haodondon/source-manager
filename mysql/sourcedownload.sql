/*
SQLyog Ultimate v12.08 (64 bit)
MySQL - 8.0.18 : Database - sourcedownload
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`sourcedownload` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `sourcedownload`;

/*Table structure for table `account` */

DROP TABLE IF EXISTS `account`;

CREATE TABLE `account` (
  `id` varchar(30) NOT NULL COMMENT 'ID',
  `account_name` varchar(30) DEFAULT NULL COMMENT '第三方账号',
  `account_pass_word` varchar(30) DEFAULT NULL COMMENT '第三方密码',
  `account_type` int(3) DEFAULT NULL COMMENT '1-千图网 2-摄图 3-包图 4-千库 5-熊猫 6-昵图',
  `account_token` text COMMENT '第三方token',
  `account_last_login_time` datetime DEFAULT NULL COMMENT '最后一次登录时间',
  `account_create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `account_error_info` varchar(500) DEFAULT NULL COMMENT '错误信息',
  `account_use` int(11) DEFAULT '1' COMMENT '1-可用 2-停用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `account` */

insert  into `account`(`id`,`account_name`,`account_pass_word`,`account_type`,`account_token`,`account_last_login_time`,`account_create_time`,`account_error_info`,`account_use`) values ('1330381310233001986',NULL,NULL,NULL,NULL,NULL,'2020-11-22 13:23:16',NULL,1);

/*Table structure for table `download_detail` */

DROP TABLE IF EXISTS `download_detail`;

CREATE TABLE `download_detail` (
  `id` varchar(30) NOT NULL COMMENT 'id',
  `user_id` varchar(40) DEFAULT NULL COMMENT '用户id',
  `download_time` datetime DEFAULT NULL COMMENT '下载时间',
  `download_name` varchar(500) DEFAULT NULL COMMENT '素材名称',
  `download_url` text COMMENT '素材下载地址',
  `download_status` int(2) DEFAULT NULL COMMENT '1-下载成功 2-下载失败',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `download_detail` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
