/*
SQLyog Professional v12.09 (64 bit)
MySQL - 5.7.23-log : Database - lease
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`lease` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `lease`;

/*Table structure for table `lease_info` */

DROP TABLE IF EXISTS `lease_info`;

CREATE TABLE `lease_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `box_code` varchar(100) DEFAULT NULL COMMENT '集装箱编码',
  `air_code` varchar(100) DEFAULT NULL COMMENT '空调编码',
  `bed_code` varchar(100) DEFAULT NULL COMMENT '活动床编码',
  `lessee_name` varchar(50) DEFAULT NULL COMMENT '承租人姓名',
  `lessee_tel` varchar(15) DEFAULT NULL COMMENT '电话',
  `lessee_company` varchar(50) DEFAULT NULL COMMENT '所属公司',
  `cash_pledge` decimal(18,2) DEFAULT NULL COMMENT '押金',
  `status` int(2) DEFAULT NULL COMMENT '0-出租中 1-已到期 2-已结算',
  `start_date` timestamp NULL DEFAULT NULL COMMENT '开始时间',
  `end_date` timestamp NULL DEFAULT NULL COMMENT '结束时间',
  `create_date` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

/*Data for the table `lease_info` */

insert  into `lease_info`(`id`,`box_code`,`air_code`,`bed_code`,`lessee_name`,`lessee_tel`,`lessee_company`,`cash_pledge`,`status`,`start_date`,`end_date`,`create_date`) values (2,'C0001','A0001','B0001','小A','13299998888','阿里巴巴','100000.00',2,'2018-12-02 00:00:00','2018-12-03 00:00:00','2018-12-03 20:34:10'),(4,'','A0001','','王五','13200000000','阿里巴巴中国','1000.00',2,'2018-12-01 00:00:00','2018-12-05 00:00:00','2018-12-04 10:53:48'),(5,'123','123','123','123','123',NULL,NULL,2,NULL,NULL,NULL),(6,'123','123','123','123','213',NULL,NULL,2,NULL,NULL,NULL),(7,'213','123','123','123',NULL,'213',NULL,2,NULL,NULL,NULL),(8,'3443','434',NULL,'34','34',NULL,NULL,2,NULL,NULL,NULL),(9,'22',NULL,NULL,NULL,NULL,NULL,NULL,2,NULL,NULL,NULL),(10,'33',NULL,NULL,NULL,NULL,NULL,NULL,2,NULL,NULL,NULL),(11,'11',NULL,NULL,NULL,NULL,NULL,NULL,2,NULL,NULL,NULL),(12,'55',NULL,NULL,NULL,NULL,NULL,NULL,2,NULL,NULL,NULL),(13,'66','77',NULL,NULL,NULL,NULL,NULL,2,NULL,NULL,NULL),(14,'12',NULL,NULL,NULL,NULL,NULL,NULL,2,NULL,NULL,NULL),(15,'13',NULL,NULL,NULL,NULL,NULL,NULL,2,NULL,NULL,NULL),(16,'14',NULL,NULL,NULL,NULL,NULL,NULL,2,NULL,NULL,NULL),(17,'15',NULL,NULL,NULL,NULL,NULL,NULL,2,NULL,NULL,NULL);

/*Table structure for table `menu` */

DROP TABLE IF EXISTS `menu`;

CREATE TABLE `menu` (
  `id` int(11) DEFAULT NULL,
  `text` varchar(20) DEFAULT NULL,
  `url` varchar(100) DEFAULT NULL,
  `up_menu_id` int(11) DEFAULT NULL,
  KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `menu` */

insert  into `menu`(`id`,`text`,`url`,`up_menu_id`) values (0,'系统管理',NULL,NULL),(1,'采购管理',NULL,0),(2,'采购列表','/sys/buyList',1),(3,'租赁管理',NULL,0),(4,'租赁列表','/sys/leaseList',3);

/*Table structure for table `product_info` */

DROP TABLE IF EXISTS `product_info`;

CREATE TABLE `product_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `product_code` varchar(25) DEFAULT NULL COMMENT '产品code',
  `product_type` int(2) DEFAULT NULL COMMENT '产品类型（1-集装箱 2-活动床 3-空调）',
  `buy_time` timestamp NULL DEFAULT NULL COMMENT '购买时间',
  `factory_name` varchar(200) DEFAULT NULL COMMENT '制造商',
  `price` decimal(18,2) DEFAULT NULL COMMENT '购买价格',
  `product_high` decimal(18,2) DEFAULT NULL COMMENT '高',
  `product_with` decimal(18,2) DEFAULT NULL COMMENT '宽',
  `product_length` decimal(18,2) DEFAULT NULL COMMENT '长',
  `product_power` varchar(20) DEFAULT NULL COMMENT '功率',
  `status` int(2) DEFAULT NULL COMMENT '状态（0-未出租 1-出租中）',
  `create_date` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8;

/*Data for the table `product_info` */

insert  into `product_info`(`id`,`product_code`,`product_type`,`buy_time`,`factory_name`,`price`,`product_high`,`product_with`,`product_length`,`product_power`,`status`,`create_date`) values (33,'C0001',1,'2018-12-03 00:00:00','池州最火爆集装箱制作公司','1000.00','2.00','10.00','30.00','',0,'2018-12-03 20:30:52'),(34,'B0001',2,'2018-12-03 00:00:00','池州活动床最火爆制作公司','500.00','0.60','1.80','2.00','',0,'2018-12-03 20:31:43'),(35,'A0001',3,'2018-12-03 00:00:00','合肥格力股份有限公司','3999.00',NULL,NULL,NULL,'1200',0,'2018-12-03 20:32:28');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(20) DEFAULT NULL COMMENT '用户名',
  `user_pwd` varchar(200) DEFAULT NULL COMMENT '密码',
  `create_date` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`user_id`,`user_name`,`user_pwd`,`create_date`) values (1,'943695742@qq.com','$2a$10$B9OUpkR5zjyMNOp98hwzou2MPAds6ScZQCmjCK5qK5ekX24GaVXgC','2018-11-30 22:59:59'),(2,'861490024@qq.com','$2a$10$B9OUpkR5zjyMNOp98hwzou2MPAds6ScZQCmjCK5qK5ekX24GaVXgC','2018-12-03 16:00:43'),(3,'3213811631@qq.com','$2a$10$B9OUpkR5zjyMNOp98hwzou2MPAds6ScZQCmjCK5qK5ekX24GaVXgC','2018-12-03 16:23:40'),(5,'yuhuangbin96@163.com','$2a$10$B9OUpkR5zjyMNOp98hwzou2MPAds6ScZQCmjCK5qK5ekX24GaVXgC',NULL);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
