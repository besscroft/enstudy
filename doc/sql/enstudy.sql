/*
 Date: 26/08/2023 17:19:24
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for book_dict
-- ----------------------------
DROP TABLE IF EXISTS `book_dict`;
CREATE TABLE `book_dict`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `book_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '词典id',
  `book_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '词典名称',
  `book_size` int NULL DEFAULT NULL COMMENT '词典词数',
  `creator` bigint NULL DEFAULT NULL COMMENT '创建者',
  `updater` bigint NULL DEFAULT NULL COMMENT '更新者',
  `create_time` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `status` tinyint NOT NULL DEFAULT 0 COMMENT '词典启用状态：0->禁用；1->启用',
  `del` tinyint NOT NULL DEFAULT 1 COMMENT '逻辑删除：0->删除状态；1->可用状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of book_dict
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `username` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '账号(用户名)',
  `password` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码',
  `avatar` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '头像(地址)',
  `role` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色',
  `email` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `telephone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '手机',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `creator` bigint NULL DEFAULT NULL COMMENT '创建者',
  `updater` bigint NULL DEFAULT NULL COMMENT '更新者',
  `create_time` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `login_time` datetime NULL DEFAULT NULL COMMENT '最后登录时间',
  `status` tinyint NOT NULL DEFAULT 0 COMMENT '帐号启用状态：0->禁用；1->启用',
  `del` tinyint NOT NULL DEFAULT 1 COMMENT '逻辑删除：0->删除状态；1->可用状态',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `username`(`username` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'enstudy', '3820266cf8c5b51ce2b12b672e69bf76fd2043e6f983fcf41a6a261973dc6afc', 'https://besscroft.com/uploads/avatar.jpeg', 'platform-super-admin', 'admin@qq.com', '旅行者', '13612345678', '只要不失去你的崇高，整个世界都会向你敞开。', 1, NULL, '2023-08-25 04:47:53', '2023-07-12 16:20:19', '2023-08-26 15:58:29', 1, 1);
INSERT INTO `user` VALUES (6, 'view', '94edf28c6d6da38fd35d7ad53e485307f89fbeaf120485c8d17a43f323deee71', 'https://besscroft.com/uploads/avatar.jpeg', 'platform-view', 'test3@qq.com', '观察员', '13612345678', '我超，原...原来你也玩原神', 1, NULL, '2023-08-19 09:13:24', '2023-08-19 17:13:24', '2023-05-07 11:53:48', 1, 1);
INSERT INTO `user` VALUES (7, 'test123', '94edf28c6d6da38fd35d7ad53e485307f89fbeaf120485c8d17a43f323deee71', 'https://besscroft.com/uploads/avatar.jpeg', 'platform-visitor', NULL, '测试', NULL, NULL, NULL, NULL, '2023-04-14 11:52:47', '2023-04-14 19:52:47', NULL, 0, 1);
INSERT INTO `user` VALUES (14, 'option', '94edf28c6d6da38fd35d7ad53e485307f89fbeaf120485c8d17a43f323deee71', NULL, 'platform-visitor', NULL, NULL, NULL, NULL, NULL, NULL, '2023-04-17 14:54:53', '2023-04-17 22:54:40', NULL, 0, 0);
INSERT INTO `user` VALUES (15, 'shishi', '94edf28c6d6da38fd35d7ad53e485307f89fbeaf120485c8d17a43f323deee71', 'rwrwer', 'platform-visitor', 'rewr', '测试更新', NULL, NULL, NULL, NULL, '2023-04-21 05:01:10', '2023-04-21 13:01:10', NULL, 0, 1);
INSERT INTO `user` VALUES (16, 'fsdfasfasdf', '2ed46d7bedc17aba18343eac71e21648b1af50fff732af7e338075cd0ed1567a', NULL, 'platform-visitor', NULL, 'fdsafs', NULL, NULL, NULL, NULL, '2023-04-17 23:46:30', '2023-04-17 23:46:30', NULL, 0, 1);
INSERT INTO `user` VALUES (17, 'sagfdfag', 'd3092fd0ffc90925d922534557a732a5fada5a63714ce7eb13d5498752b97b2e', NULL, 'platform-visitor', NULL, 'gaffddsgsd', NULL, NULL, NULL, NULL, '2023-04-17 23:46:38', '2023-04-17 23:46:38', NULL, 0, 1);
INSERT INTO `user` VALUES (18, 'fasgfdagsd', 'db4f1a4c5dc502b51d62e8175b1f320f94d954f7f46f9adc3b2863e1d1ecc45f', NULL, 'platform-visitor', NULL, 'gdsfgdfgds', NULL, NULL, NULL, NULL, '2023-05-07 03:55:16', '2023-04-17 23:46:44', NULL, 0, 0);
INSERT INTO `user` VALUES (19, 'qrqeqeqw', '45f710caf185a0b240dcdf5b91a111887588c9ea74b099d9fdd18c23bfe36352', NULL, 'platform-visitor', NULL, 'dsfdsgdd', NULL, NULL, NULL, NULL, '2023-04-17 23:46:51', '2023-04-17 23:46:51', NULL, 0, 1);
INSERT INTO `user` VALUES (20, 'fgdfshgdf', '843febe995f3969a78c8dd8fe647a58369804747b5a8b4c1ff4641f5183f1dc4', NULL, 'platform-visitor', NULL, '423sdzb', NULL, NULL, NULL, NULL, '2023-04-17 23:46:59', '2023-04-17 23:46:59', NULL, 0, 1);
INSERT INTO `user` VALUES (21, '56546456', '026c29a697be8ba83b4efcfbbac2804fc9b45501de2ef7ac491b66abe873435a', NULL, 'platform-visitor', NULL, '6546y34', NULL, NULL, NULL, NULL, '2023-04-17 23:48:36', '2023-04-17 23:48:36', NULL, 0, 1);
INSERT INTO `user` VALUES (22, '6453ytrhdf', '1e474b2b590bec6b10d58f1a74069629216b623c0be72214b071956287f2f3dd', NULL, 'platform-visitor', NULL, 'gsfdg3452', NULL, NULL, NULL, NULL, '2023-04-17 23:48:44', '2023-04-17 23:48:44', NULL, 0, 1);

SET FOREIGN_KEY_CHECKS = 1;
