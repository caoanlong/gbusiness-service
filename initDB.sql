/* 创建数据库 */
-- CREATE DATABASE `edutg`;

/* 创建用户表 */
CREATE TABLE `user` (
  `user_id` INT(11) NOT NULL AUTO_INCREMENT,
  `user_name` VARCHAR(50) NOT NULL,
  `password` VARCHAR(300) NOT NULL,
  `avatar` VARCHAR(300) DEFAULT NULL,
  `mobile` VARCHAR(50) DEFAULT NULL,
  `role_id` INT(11) NOT NULL,
  `createby` INT(11) NOT NULL,
  `updateby` INT(11) DEFAULT NULL,
  `create_time` DATETIME NOT NULL,
  `update_time` DATETIME DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

/* 创建角色表 */
CREATE TABLE `role` (
  `role_id` INT(11) NOT NULL AUTO_INCREMENT,
  `role_name` VARCHAR(50) NOT NULL,
  `permissions` VARCHAR(300) DEFAULT NULL,
  `createby` INT(11) NOT NULL,
  `updateby` INT(11) DEFAULT NULL,
  `create_time` DATETIME NOT NULL,
  `update_time` DATETIME DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

/* 创建初始用户 */
INSERT INTO `user` (
  `user_name`,
  `password`,
  `avatar`,
  `mobile`,
  `role_id`,
  `createby`,
  `create_time`
) VALUES (
  'admin',
  MD5('123456caonimadebi'),
  'https://gitee.com/uploads/48/1682248_caoanlong.png?1513153951',
  '13049497395',
  1,
  1,
  NOW()
);

/* 创建初始角色 */
INSERT INTO `role` (
  `role_name`,
  `permissions`,
  `createby`,
  `create_time`
) VALUES (
  '超级管理员',
  'home,system',
  1,
  NOW()
);
