/*
SQLyog Ultimate v12.08 (64 bit)
MySQL - 5.5.62 : Database - zhxy_db
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`zhxy_db` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `zhxy_db`;

/*Table structure for table `tb_admin` */

DROP TABLE IF EXISTS `tb_admin`;

CREATE TABLE `tb_admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(15) DEFAULT NULL,
  `gender` char(1) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `telephone` varchar(12) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `portrait_path` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=157 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/*Data for the table `tb_admin` */

insert  into `tb_admin`(`id`,`name`,`gender`,`password`,`email`,`telephone`,`address`,`portrait_path`) values (101,'admin','女','21232f297a57a5a743894a0e4a801fc3','111111@qq.com','13260161111','昌平','upload/f0b73627c2bd4e7e8010b5e3f6498b4b.jpeg'),(102,'admin1','男','21232f297a57a5a743894a0e4a801fc3','512111559@qq.com','13260166090','北京','upload/default.jpg'),(103,'admin2','男','21232f297a57a5a743894a0e4a801fc3','512111559@qq.com','13260166090','北京','upload/default.jpg'),(104,'admin3','男','21232f297a57a5a743894a0e4a801fc3','123456@qq.com','13666666666','宏福苑','upload/1a14a25571f2459f902f5fbf70a36443.jpg');

/*Table structure for table `tb_clazz` */

DROP TABLE IF EXISTS `tb_clazz`;

CREATE TABLE `tb_clazz` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(15) DEFAULT NULL,
  `number` int(3) DEFAULT NULL,
  `introducation` varchar(200) DEFAULT NULL,
  `headmaster` varchar(15) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `telephone` varchar(12) DEFAULT NULL,
  `grade_name` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/*Data for the table `tb_clazz` */

insert  into `tb_clazz`(`id`,`name`,`number`,`introducation`,`headmaster`,`email`,`telephone`,`grade_name`) values (1,'一年一班',30,'大圣的一年一班好','大圣','dasheng@163.com','13260166090','一年级'),(2,'一年二班',28,'小张的一年二班好','小张','xiaozhang@163.com','13260166090','一年级'),(3,'二年一班',35,'小韩的二年一班好','小韩','xiaohan@163.com','13260166090','二年级'),(4,'二年二班',30,'小强的二年二班好','小强','xiaoqiang@163.com','13260166090','二年级'),(5,'三年一班',30,'小花的三年一班好','小花','xiaohua@163.com','13260166090','三年级'),(6,'三年二班',30,'小赵的三年二班好','小赵','xiaozhao@163.com','13260166090','三年级'),(7,'四年一班',30,'小赵的三年二班好','小飞','xiaofei@163.com','13260166090','四年级'),(10,'软工一班',45,'软工一班我的伙伴漂漂亮亮','张曼玉','qq.com1324@qq.com','13133432412','一年级');

/*Table structure for table `tb_grade` */

DROP TABLE IF EXISTS `tb_grade`;

CREATE TABLE `tb_grade` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(15) NOT NULL DEFAULT '',
  `manager` varchar(15) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `telephone` varchar(12) DEFAULT NULL,
  `introducation` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`,`name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/*Data for the table `tb_grade` */

insert  into `tb_grade`(`id`,`name`,`manager`,`email`,`telephone`,`introducation`) values (1,'一年级','大圣','dasheng@163.com','13260166090','大学一年级'),(2,'二年级','小魏','xiaowei@163.com','13260166090','大学二年级'),(3,'三年级','小李','xiaoli@163.com','13666666666','三年级,这个班级的孩子们很有才艺'),(4,'五年级','小丽','li@123.com','13666666666','这个年级的同学多才多活力'),(5,'六年级','小明','xiaoming@666.com','13666666666','这个年级的主任是小明');

/*Table structure for table `tb_student` */

DROP TABLE IF EXISTS `tb_student`;

CREATE TABLE `tb_student` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sno` varchar(20) DEFAULT NULL,
  `name` varchar(15) DEFAULT NULL,
  `gender` char(1) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `telephone` varchar(12) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `introducation` varchar(200) DEFAULT NULL,
  `portrait_path` varchar(200) DEFAULT NULL,
  `clazz_name` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/*Data for the table `tb_student` */

insert  into `tb_student`(`id`,`sno`,`name`,`gender`,`password`,`email`,`telephone`,`address`,`introducation`,`portrait_path`,`clazz_name`) values (1,'1001','张小明','男','e10adc3949ba59abbe56e057f20f883e','yinyufei@163.com','13260166090','北京天通苑','这个学生学习好','upload/default.jpg','一年一班'),(2,'1002','郭建超','男','e10adc3949ba59abbe56e057f20f883e','guojianchao@163.com','13260166090','北京昌平','这个学生会功夫','upload/default.jpg','一年一班'),(3,'1003','史汶鑫','男','e10adc3949ba59abbe56e057f20f883e','shiwenxin@163.com','13260166090','北京昌平','这个学生酒量好','upload/default.jpg','二年一班'),(4,'1004','高建军','男','e10adc3949ba59abbe56e057f20f883e','gaojianjun@163.com','13260166090','北京昌平','这个学生会做饭','upload/default.jpg','二年一班'),(5,'1005','邹伟斌','男','e10adc3949ba59abbe56e057f20f883e','zouweibin@163.com','13260166090','北京昌平','这个学生能吃辣','upload/default.jpg','三年一班'),(6,'1006','刘路','男','e10adc3949ba59abbe56e057f20f883e','liulu@163.com','13260166090','北京昌平','这个学生是学霸','upload/default.jpg','三年二班'),(7,'1007','庞家仨','女','e10adc3949ba59abbe56e057f20f883e','pangjiasan@163.com','13260166090','北京昌平','这个学生海拔高','upload/default.jpg','三年二班'),(8,'1008','谭帅','男','e10adc3949ba59abbe56e057f20f883e','tanshuai@163.com','13260166090','北京昌平','这个学生想考研','upload/2c05836439db4b338a71c0350f8a47f4.jpeg','四年一班');

/*Table structure for table `tb_teacher` */

DROP TABLE IF EXISTS `tb_teacher`;

CREATE TABLE `tb_teacher` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tno` varchar(20) DEFAULT NULL,
  `name` varchar(15) DEFAULT NULL,
  `gender` char(1) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `telephone` varchar(12) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `portrait_path` varchar(200) DEFAULT NULL,
  `clazz_name` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/*Data for the table `tb_teacher` */

insert  into `tb_teacher`(`id`,`tno`,`name`,`gender`,`password`,`email`,`telephone`,`address`,`portrait_path`,`clazz_name`) values (1,'101','大圣','女','123456','dasheng@163.com','13260166090','北京昌平','upload/default.jpg','一年一班'),(2,'102','小张','男','e10adc3949ba59abbe56e057f20f883e','xiaozhang@163.com','13260166090','北京海淀','upload/default.jpg','一年二班'),(3,'103','小韩','男','e10adc3949ba59abbe56e057f20f883e','xiaohan@163.com','13260166090','北京朝阳','upload/default.jpg','二年一班'),(4,'104','小强','男','e10adc3949ba59abbe56e057f20f883e','xiaoqiang@163.com','13260166090','北京通州','upload/default.jpg','二年二班'),(5,'105','小花','男','e10adc3949ba59abbe56e057f20f883e','xiaohua@163.com','13260166090','北京顺义','upload/default.jpg','三年一班'),(6,'106','小赵','男','e10adc3949ba59abbe56e057f20f883e','xiaozhao@163.com','13260166090','北京东城','upload/7112257a777d4841b3a7525382723b62.jpeg','三年二班'),(7,'107','小飞','男','e10adc3949ba59abbe56e057f20f883e','xiaofei@163.com','13260166090','北京西城','upload/9ba421afe32f481fa4cf98ae9acaf5cb.jpg','四年一班'),(8,'108','秀秀','女','e10adc3949ba59abbe56e057f20f883e','123456@123.com','13855555555','海淀','upload/19c092f147eb46c6b52abaed62adcd54.jpeg','一年一班');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
