/* 创建初始用户 */
INSERT INTO `user` (
  `user_name`,
  `password`,
  `avatar`,
  `mobile`,
  `role_id`,
  `create_user_id`,
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
  `create_user_id`,
  `create_time`
) VALUES (
  '超级管理员',
  '*',
  1,
  NOW()
);
