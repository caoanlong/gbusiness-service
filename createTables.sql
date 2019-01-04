/* 创建数据库 */
CREATE DATABASE `edutg`;

/* 创建用户表 */
CREATE TABLE `user` (
  `user_id` INT(11) NOT NULL AUTO_INCREMENT,
  `user_name` VARCHAR(50) NOT NULL,
  `password` VARCHAR(300) NOT NULL,
  `avatar` VARCHAR(300) DEFAULT NULL,
  `mobile` VARCHAR(50) DEFAULT NULL,
  `role_id` INT(11) NOT NULL,
  `create_user_id` INT(11) NOT NULL,
  `update_user_id` INT(11) DEFAULT NULL,
  `create_time` DATETIME NOT NULL,
  `update_time` DATETIME DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

/* 创建角色表 */
CREATE TABLE `role` (
  `role_id` INT(11) NOT NULL AUTO_INCREMENT,
  `role_name` VARCHAR(50) NOT NULL,
  `permissions` VARCHAR(300) DEFAULT NULL,
  `create_user_id` INT(11) NOT NULL,
  `update_user_id` INT(11) DEFAULT NULL,
  `create_time` DATETIME NOT NULL,
  `update_time` DATETIME DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

/* 创建商户报名人员表 */
CREATE TABLE `merchant_member` (
  `merchant_member_id` int(11) NOT NULL AUTO_INCREMENT,
  `merchant_member_name` varchar(50) NOT NULL,
  `mobile` varchar(50) NOT NULL,
  `industry` varchar(50) NOT NULL,
  `is_add_merchant` char(1) NOT NULL DEFAULT 'N',
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`merchant_member_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8

/* 创建商户表 */
CREATE TABLE `merchant` (
  `merchant_id` int(11) NOT NULL AUTO_INCREMENT,
  `merchant_name` varchar(50) NOT NULL,
  `merchant_image` varchar(500) DEFAULT NULL,
  `promotion_count` int(11) DEFAULT '0',
  `sell_count` int(11) DEFAULT '0',
  `merchant_member_id` int(11) NOT NULL,
  `create_user_id` int(11) NOT NULL,
  `update_user_id` int(11) DEFAULT NULL,
  `create_time` datetime NOT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`merchant_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8

/* 创建活动表 */
CREATE TABLE `activity` (
  `activity_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) NOT NULL,
  `banner` varchar(500) NOT NULL,
  `price` float NOT NULL,
  `notes` text NOT NULL,
  `introduction` varchar(300) DEFAULT NULL,
  `end_time` datetime NOT NULL,
  `create_user_id` int(11) NOT NULL,
  `update_user_id` int(11) DEFAULT NULL,
  `create_time` datetime NOT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`activity_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8

/* 创建活动商家关联表 */
CREATE TABLE `activity_merchant` (
  `activity_id` int(11) NOT NULL,
  `merchant_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8

/* 创建会员表 */
CREATE TABLE `member` (
  `member_id` int(11) NOT NULL AUTO_INCREMENT,
  `nick_name` varchar(50) DEFAULT NULL,
  `real_name` varchar(50) DEFAULT NULL,
  `mobile` varchar(50) NOT NULL,
  `sex` char(1) DEFAULT NULL,
  `head_img_url` varchar(300) DEFAULT NULL,
  `open_id` varchar(200) DEFAULT NULL,
  `union_id` varchar(200) DEFAULT NULL,
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`member_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8

/* 创建订单表 */
CREATE TABLE `order` (
  `order_id` int(11) NOT NULL AUTO_INCREMENT,
  `order_no` varchar(200) NOT NULL,
  `member_id` int(11) NOT NULL,
  `activity_id` int(11) NOT NULL,
  `price` float NOT NULL,
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8


