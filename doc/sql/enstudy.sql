/*
 Date: 31/08/2023 21:43:41
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for enstudy_book_dict
-- ----------------------------
DROP TABLE IF EXISTS `enstudy_book_dict`;
CREATE TABLE `enstudy_book_dict`  (
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
) ENGINE = InnoDB AUTO_INCREMENT = 82 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '词库表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of enstudy_book_dict
-- ----------------------------
INSERT INTO `enstudy_book_dict` VALUES (1, 'CET4luan_1', '四级真题核心词（图片记忆）', 1162, NULL, NULL, '2023-08-29 22:13:13', '2023-08-29 22:13:17', 0, 1);
INSERT INTO `enstudy_book_dict` VALUES (2, 'CET6luan_1', '六级真题核心词（图片记忆）', 1228, NULL, NULL, '2023-08-29 22:13:13', '2023-08-29 22:13:17', 0, 1);
INSERT INTO `enstudy_book_dict` VALUES (3, 'KaoYanluan_1', '考研必考词汇', 1341, NULL, NULL, '2023-08-29 22:13:13', '2023-08-29 22:13:17', 0, 1);
INSERT INTO `enstudy_book_dict` VALUES (4, 'Level4luan_1', '专四真题高频词', 595, NULL, NULL, '2023-08-29 22:13:13', '2023-08-29 22:13:17', 0, 1);
INSERT INTO `enstudy_book_dict` VALUES (5, 'Level8_1', '专八真题高频词', 684, NULL, NULL, '2023-08-29 22:13:13', '2023-08-29 22:13:17', 0, 1);
INSERT INTO `enstudy_book_dict` VALUES (6, 'CET4luan_2', '四级英语词汇', 3739, NULL, NULL, '2023-08-29 22:13:13', '2023-08-29 22:13:17', 0, 1);
INSERT INTO `enstudy_book_dict` VALUES (7, 'CET6_2', '六级英语词汇', 2078, NULL, NULL, '2023-08-29 22:13:13', '2023-08-29 22:13:17', 0, 1);
INSERT INTO `enstudy_book_dict` VALUES (8, 'KaoYan_2', '考研英语词汇', 4533, NULL, NULL, '2023-08-29 22:13:13', '2023-08-29 22:13:17', 0, 1);
INSERT INTO `enstudy_book_dict` VALUES (9, 'Level4luan_2', '专四核心词汇', 4025, NULL, NULL, '2023-08-29 22:13:13', '2023-08-29 22:13:17', 0, 1);
INSERT INTO `enstudy_book_dict` VALUES (10, 'Level8luan_2', '专八核心词汇', 12197, NULL, NULL, '2023-08-29 22:13:13', '2023-08-29 22:13:17', 0, 1);
INSERT INTO `enstudy_book_dict` VALUES (11, 'CET4_3', '新东方四级词汇', 2607, NULL, NULL, '2023-08-29 22:13:13', '2023-08-29 22:13:17', 0, 1);
INSERT INTO `enstudy_book_dict` VALUES (12, 'CET6_3', '新东方六级词汇', 2345, NULL, NULL, '2023-08-29 22:13:13', '2023-08-29 22:13:17', 0, 1);
INSERT INTO `enstudy_book_dict` VALUES (13, 'KaoYan_3', '新东方考研词汇', 3728, NULL, NULL, '2023-08-29 22:13:13', '2023-08-29 22:13:17', 0, 1);
INSERT INTO `enstudy_book_dict` VALUES (14, 'CET4_1', '四级真题核心词（正序版）', 1162, NULL, NULL, '2023-08-29 22:13:13', '2023-08-29 22:13:17', 0, 1);
INSERT INTO `enstudy_book_dict` VALUES (15, 'CET6_1', '六级真题核心词（正序版）', 1228, NULL, NULL, '2023-08-29 22:13:13', '2023-08-29 22:13:17', 0, 1);
INSERT INTO `enstudy_book_dict` VALUES (16, 'KaoYan_1', '考研必考词汇（正序版）', 1341, NULL, NULL, '2023-08-29 22:13:13', '2023-08-29 22:13:17', 0, 1);
INSERT INTO `enstudy_book_dict` VALUES (17, 'Level4_1', '专四真题高频词（正序版）', 595, NULL, NULL, '2023-08-29 22:13:13', '2023-08-29 22:13:17', 0, 1);
INSERT INTO `enstudy_book_dict` VALUES (18, 'CET4_2', '四级英语词汇（正序版）', 3739, NULL, NULL, '2023-08-29 22:13:13', '2023-08-29 22:13:17', 0, 1);
INSERT INTO `enstudy_book_dict` VALUES (19, 'Level4_2', '专四核心词汇（正序版）', 4025, NULL, NULL, '2023-08-29 22:13:13', '2023-08-29 22:13:17', 0, 1);
INSERT INTO `enstudy_book_dict` VALUES (20, 'Level8_2', '专八核心词汇（正序版）', 12197, NULL, NULL, '2023-08-29 22:13:13', '2023-08-29 22:13:17', 0, 1);
INSERT INTO `enstudy_book_dict` VALUES (21, 'IELTSluan_2', '雅思词汇', 3427, NULL, NULL, '2023-08-29 22:13:13', '2023-08-29 22:13:17', 0, 1);
INSERT INTO `enstudy_book_dict` VALUES (22, 'TOEFL_2', 'TOEFL 词汇', 9213, NULL, NULL, '2023-08-29 22:13:13', '2023-08-29 22:13:17', 0, 1);
INSERT INTO `enstudy_book_dict` VALUES (23, 'GRE_2', 'GRE 词汇', 7199, NULL, NULL, '2023-08-29 22:13:13', '2023-08-29 22:13:17', 0, 1);
INSERT INTO `enstudy_book_dict` VALUES (24, 'SAT_2', 'SAT 词汇', 4423, NULL, NULL, '2023-08-29 22:13:13', '2023-08-29 22:13:17', 0, 1);
INSERT INTO `enstudy_book_dict` VALUES (25, 'GMATluan_2', 'GMAT 词汇', 3254, NULL, NULL, '2023-08-29 22:13:13', '2023-08-29 22:13:17', 0, 1);
INSERT INTO `enstudy_book_dict` VALUES (26, 'IELTS_3', '新东方雅思词汇', 3575, NULL, NULL, '2023-08-29 22:13:13', '2023-08-29 22:13:17', 0, 1);
INSERT INTO `enstudy_book_dict` VALUES (27, 'TOEFL_3', '新东方 TOEFL 词汇', 4264, NULL, NULL, '2023-08-29 22:13:13', '2023-08-29 22:13:17', 0, 1);
INSERT INTO `enstudy_book_dict` VALUES (28, 'GRE_3', '新东方 GRE 词汇', 6515, NULL, NULL, '2023-08-29 22:13:13', '2023-08-29 22:13:17', 0, 1);
INSERT INTO `enstudy_book_dict` VALUES (29, 'SAT_3', '新东方 SAT 词汇', 4464, NULL, NULL, '2023-08-29 22:13:13', '2023-08-29 22:13:17', 0, 1);
INSERT INTO `enstudy_book_dict` VALUES (30, 'GMAT_3', '新东方 GMAT 词汇', 3047, NULL, NULL, '2023-08-29 22:13:13', '2023-08-29 22:13:17', 0, 1);
INSERT INTO `enstudy_book_dict` VALUES (31, 'IELTS_2', '雅思词汇（正序版）', 3427, NULL, NULL, '2023-08-29 22:13:13', '2023-08-29 22:13:17', 0, 1);
INSERT INTO `enstudy_book_dict` VALUES (32, 'GMAT_2', 'GMAT 词汇（正序版）', 3254, NULL, NULL, '2023-08-29 22:13:13', '2023-08-29 22:13:17', 0, 1);
INSERT INTO `enstudy_book_dict` VALUES (33, 'ChuZhongluan_2', '中考必备词汇', 1420, NULL, NULL, '2023-08-29 22:13:13', '2023-08-29 22:13:17', 0, 1);
INSERT INTO `enstudy_book_dict` VALUES (34, 'GaoZhongluan_2', '高考必备词汇（图片记忆）', 3668, NULL, NULL, '2023-08-29 22:13:13', '2023-08-29 22:13:17', 0, 1);
INSERT INTO `enstudy_book_dict` VALUES (35, 'ChuZhong_3', '新东方初中词汇', 1803, NULL, NULL, '2023-08-29 22:13:13', '2023-08-29 22:13:17', 0, 1);
INSERT INTO `enstudy_book_dict` VALUES (36, 'GaoZhong_3', '新东方高中词汇', 2340, NULL, NULL, '2023-08-29 22:13:13', '2023-08-29 22:13:17', 0, 1);
INSERT INTO `enstudy_book_dict` VALUES (37, 'PEPXiaoXue3_1', '人教版小学英语-三年级上册', 64, NULL, NULL, '2023-08-29 22:13:13', '2023-08-29 22:13:17', 0, 1);
INSERT INTO `enstudy_book_dict` VALUES (38, 'PEPXiaoXue3_2', '人教版小学英语-三年级下册', 72, NULL, NULL, '2023-08-29 22:13:13', '2023-08-29 22:13:17', 0, 1);
INSERT INTO `enstudy_book_dict` VALUES (39, 'PEPXiaoXue4_1', '人教版小学英语-四年级上册', 84, NULL, NULL, '2023-08-29 22:13:13', '2023-08-29 22:13:17', 0, 1);
INSERT INTO `enstudy_book_dict` VALUES (40, 'PEPXiaoXue4_2', '人教版小学英语-四年级下册', 104, NULL, NULL, '2023-08-29 22:13:13', '2023-08-29 22:13:17', 0, 1);
INSERT INTO `enstudy_book_dict` VALUES (41, 'PEPXiaoXue5_1', '人教版小学英语-五年级上册', 131, NULL, NULL, '2023-08-29 22:13:13', '2023-08-29 22:13:17', 0, 1);
INSERT INTO `enstudy_book_dict` VALUES (42, 'PEPXiaoXue5_2', '人教版小学英语-五年级下册', 156, NULL, NULL, '2023-08-29 22:13:13', '2023-08-29 22:13:17', 0, 1);
INSERT INTO `enstudy_book_dict` VALUES (43, 'PEPXiaoXue6_1', '人教版小学英语-六年级上册', 130, NULL, NULL, '2023-08-29 22:13:13', '2023-08-29 22:13:17', 0, 1);
INSERT INTO `enstudy_book_dict` VALUES (44, 'PEPXiaoXue6_2', '人教版小学英语-六年级下册', 108, NULL, NULL, '2023-08-29 22:13:13', '2023-08-29 22:13:17', 0, 1);
INSERT INTO `enstudy_book_dict` VALUES (45, 'PEPChuZhong7_1', '人教版初中英语-七年级上册', 392, NULL, NULL, '2023-08-29 22:13:13', '2023-08-29 22:13:17', 0, 1);
INSERT INTO `enstudy_book_dict` VALUES (46, 'PEPChuZhong7_2', '人教版初中英语-七年级下册', 492, NULL, NULL, '2023-08-29 22:13:13', '2023-08-29 22:13:17', 0, 1);
INSERT INTO `enstudy_book_dict` VALUES (47, 'PEPChuZhong8_1', '人教版初中英语-八年级上册', 419, NULL, NULL, '2023-08-29 22:13:13', '2023-08-29 22:13:17', 0, 1);
INSERT INTO `enstudy_book_dict` VALUES (48, 'PEPChuZhong8_2', '人教版初中英语-八年级下册', 466, NULL, NULL, '2023-08-29 22:13:13', '2023-08-29 22:13:17', 0, 1);
INSERT INTO `enstudy_book_dict` VALUES (49, 'PEPChuZhong9_1', '人教版初中英语-九年级全册', 551, NULL, NULL, '2023-08-29 22:13:13', '2023-08-29 22:13:17', 0, 1);
INSERT INTO `enstudy_book_dict` VALUES (50, 'WaiYanSheChuZhong_1', '外研社版初中英语-七年级上册', 629, NULL, NULL, '2023-08-29 22:13:13', '2023-08-29 22:13:17', 0, 1);
INSERT INTO `enstudy_book_dict` VALUES (51, 'WaiYanSheChuZhong_2', '外研社版初中英语-七年级下册', 438, NULL, NULL, '2023-08-29 22:13:13', '2023-08-29 22:13:17', 0, 1);
INSERT INTO `enstudy_book_dict` VALUES (52, 'WaiYanSheChuZhong_3', '外研社版初中英语-八年级上册', 320, NULL, NULL, '2023-08-29 22:13:13', '2023-08-29 22:13:17', 0, 1);
INSERT INTO `enstudy_book_dict` VALUES (53, 'WaiYanSheChuZhong_4', '外研社版初中英语-八年级下册', 266, NULL, NULL, '2023-08-29 22:13:13', '2023-08-29 22:13:17', 0, 1);
INSERT INTO `enstudy_book_dict` VALUES (54, 'WaiYanSheChuZhong_5', '外研社版初中英语-九年级上册', 381, NULL, NULL, '2023-08-29 22:13:13', '2023-08-29 22:13:17', 0, 1);
INSERT INTO `enstudy_book_dict` VALUES (55, 'WaiYanSheChuZhong_6', '外研社版初中英语-九年级下册', 128, NULL, NULL, '2023-08-29 22:13:13', '2023-08-29 22:13:17', 0, 1);
INSERT INTO `enstudy_book_dict` VALUES (56, 'PEPGaoZhong_1', '人教版高中英语-必修 1', 311, NULL, NULL, '2023-08-29 22:13:13', '2023-08-29 22:13:17', 0, 1);
INSERT INTO `enstudy_book_dict` VALUES (57, 'PEPGaoZhong_2', '人教版高中英语-必修 2', 319, NULL, NULL, '2023-08-29 22:13:13', '2023-08-29 22:13:17', 0, 1);
INSERT INTO `enstudy_book_dict` VALUES (58, 'PEPGaoZhong_3', '人教版高中英语-必修 3', 366, NULL, NULL, '2023-08-29 22:13:13', '2023-08-29 22:13:17', 0, 1);
INSERT INTO `enstudy_book_dict` VALUES (59, 'PEPGaoZhong_4', '人教版高中英语-必修 4', 307, NULL, NULL, '2023-08-29 22:13:13', '2023-08-29 22:13:17', 0, 1);
INSERT INTO `enstudy_book_dict` VALUES (60, 'PEPGaoZhong_5', '人教版高中英语-必修 5', 357, NULL, NULL, '2023-08-29 22:13:13', '2023-08-29 22:13:17', 0, 1);
INSERT INTO `enstudy_book_dict` VALUES (61, 'PEPGaoZhong_6', '人教版高中英语-选修 6', 391, NULL, NULL, '2023-08-29 22:13:13', '2023-08-29 22:13:17', 0, 1);
INSERT INTO `enstudy_book_dict` VALUES (62, 'PEPGaoZhong_7', '人教版高中英语-选修 7', 384, NULL, NULL, '2023-08-29 22:13:13', '2023-08-29 22:13:17', 0, 1);
INSERT INTO `enstudy_book_dict` VALUES (63, 'PEPGaoZhong_8', '人教版高中英语-选修 8', 420, NULL, NULL, '2023-08-29 22:13:13', '2023-08-29 22:13:17', 0, 1);
INSERT INTO `enstudy_book_dict` VALUES (64, 'PEPGaoZhong_9', '人教版高中英语-选修 9', 352, NULL, NULL, '2023-08-29 22:13:13', '2023-08-29 22:13:17', 0, 1);
INSERT INTO `enstudy_book_dict` VALUES (65, 'PEPGaoZhong_10', '人教版高中英语-选修 10', 361, NULL, NULL, '2023-08-29 22:13:13', '2023-08-29 22:13:17', 0, 1);
INSERT INTO `enstudy_book_dict` VALUES (66, 'PEPGaoZhong_11', '人教版高中英语-选修 11', 309, NULL, NULL, '2023-08-29 22:13:13', '2023-08-29 22:13:17', 0, 1);
INSERT INTO `enstudy_book_dict` VALUES (67, 'ChuZhong_2', '初中英语词汇（正序版）', 1420, NULL, NULL, '2023-08-29 22:13:13', '2023-08-29 22:13:17', 0, 1);
INSERT INTO `enstudy_book_dict` VALUES (68, 'GaoZhong_2', '高中英语词汇（正序版）', 3668, NULL, NULL, '2023-08-29 22:13:13', '2023-08-29 22:13:17', 0, 1);
INSERT INTO `enstudy_book_dict` VALUES (69, 'BEC_2', '商务英语词汇', 2753, NULL, NULL, '2023-08-29 22:13:13', '2023-08-29 22:13:17', 0, 1);
INSERT INTO `enstudy_book_dict` VALUES (70, 'BEC_3', '新东方 BEC 词汇', 2825, NULL, NULL, '2023-08-29 22:13:13', '2023-08-29 22:13:17', 0, 1);
INSERT INTO `enstudy_book_dict` VALUES (71, 'BeiShiGaoZhong_1', '北师大版高中必修一', 226, NULL, NULL, '2023-08-29 22:13:13', '2023-08-29 22:13:17', 0, 1);
INSERT INTO `enstudy_book_dict` VALUES (72, 'BeiShiGaoZhong_2', '北师大版高中必修二', 244, NULL, NULL, '2023-08-29 22:13:13', '2023-08-29 22:13:17', 0, 1);
INSERT INTO `enstudy_book_dict` VALUES (73, 'BeiShiGaoZhong_3', '北师大版高中必修三', 295, NULL, NULL, '2023-08-29 22:13:13', '2023-08-29 22:13:17', 0, 1);
INSERT INTO `enstudy_book_dict` VALUES (74, 'BeiShiGaoZhong_4', '北师大版高中必修四', 336, NULL, NULL, '2023-08-29 22:13:13', '2023-08-29 22:13:17', 0, 1);
INSERT INTO `enstudy_book_dict` VALUES (75, 'BeiShiGaoZhong_5', '北师大版高中必修五', 327, NULL, NULL, '2023-08-29 22:13:13', '2023-08-29 22:13:17', 0, 1);
INSERT INTO `enstudy_book_dict` VALUES (76, 'BeiShiGaoZhong_6', '北师大版高中选修六', 271, NULL, NULL, '2023-08-29 22:13:13', '2023-08-29 22:13:17', 0, 1);
INSERT INTO `enstudy_book_dict` VALUES (77, 'BeiShiGaoZhong_7', '北师大版高中选修七', 334, NULL, NULL, '2023-08-29 22:13:13', '2023-08-29 22:13:17', 0, 1);
INSERT INTO `enstudy_book_dict` VALUES (78, 'BeiShiGaoZhong_8', '北师大版高中选修八', 364, NULL, NULL, '2023-08-29 22:13:13', '2023-08-29 22:13:17', 0, 1);
INSERT INTO `enstudy_book_dict` VALUES (79, 'BeiShiGaoZhong_9', '北师大版高中选修九', 299, NULL, NULL, '2023-08-29 22:13:13', '2023-08-29 22:13:17', 0, 1);
INSERT INTO `enstudy_book_dict` VALUES (80, 'BeiShiGaoZhong_10', '北师大版高中选修十', 267, NULL, NULL, '2023-08-29 22:13:13', '2023-08-29 22:13:17', 0, 1);
INSERT INTO `enstudy_book_dict` VALUES (81, 'BeiShiGaoZhong_11', '北师大版高中选修十一', 330, NULL, NULL, '2023-08-29 22:13:13', '2023-08-29 22:13:17', 0, 1);

-- ----------------------------
-- Table structure for enstudy_user
-- ----------------------------
DROP TABLE IF EXISTS `enstudy_user`;
CREATE TABLE `enstudy_user`  (
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
-- Records of enstudy_user
-- ----------------------------
INSERT INTO `enstudy_user` VALUES (1, 'enstudy', '3820266cf8c5b51ce2b12b672e69bf76fd2043e6f983fcf41a6a261973dc6afc', 'https://besscroft.com/uploads/avatar.jpeg', 'platform-super-admin', 'admin@qq.com', '旅行者', '13612345678', '只要不失去你的崇高，整个世界都会向你敞开。', 1, NULL, '2023-08-25 04:47:53', '2023-07-12 16:20:19', '2023-08-26 17:55:41', 1, 1);
INSERT INTO `enstudy_user` VALUES (6, 'view', '94edf28c6d6da38fd35d7ad53e485307f89fbeaf120485c8d17a43f323deee71', 'https://besscroft.com/uploads/avatar.jpeg', 'platform-view', 'test3@qq.com', '观察员', '13612345678', '我超，原...原来你也玩原神', 1, NULL, '2023-08-19 09:13:24', '2023-08-19 17:13:24', '2023-08-30 16:24:52', 1, 1);
INSERT INTO `enstudy_user` VALUES (7, 'test123', '94edf28c6d6da38fd35d7ad53e485307f89fbeaf120485c8d17a43f323deee71', 'https://besscroft.com/uploads/avatar.jpeg', 'platform-visitor', NULL, '测试', NULL, NULL, NULL, NULL, '2023-04-14 11:52:47', '2023-04-14 19:52:47', NULL, 0, 1);
INSERT INTO `enstudy_user` VALUES (14, 'option', '94edf28c6d6da38fd35d7ad53e485307f89fbeaf120485c8d17a43f323deee71', NULL, 'platform-visitor', NULL, NULL, NULL, NULL, NULL, NULL, '2023-08-29 15:02:27', '2023-04-17 22:54:40', NULL, 0, 1);
INSERT INTO `enstudy_user` VALUES (15, 'shishi', '94edf28c6d6da38fd35d7ad53e485307f89fbeaf120485c8d17a43f323deee71', 'rwrwer', 'platform-visitor', 'rewr', '测试更新', NULL, NULL, NULL, NULL, '2023-04-21 05:01:10', '2023-04-21 13:01:10', NULL, 0, 1);
INSERT INTO `enstudy_user` VALUES (16, 'fsdfasfasdf', '2ed46d7bedc17aba18343eac71e21648b1af50fff732af7e338075cd0ed1567a', NULL, 'platform-visitor', NULL, 'fdsafs', NULL, NULL, NULL, NULL, '2023-04-17 23:46:30', '2023-04-17 23:46:30', NULL, 0, 1);
INSERT INTO `enstudy_user` VALUES (17, 'sagfdfag', 'd3092fd0ffc90925d922534557a732a5fada5a63714ce7eb13d5498752b97b2e', NULL, 'platform-visitor', NULL, 'gaffddsgsd', NULL, NULL, NULL, NULL, '2023-04-17 23:46:38', '2023-04-17 23:46:38', NULL, 0, 1);
INSERT INTO `enstudy_user` VALUES (18, 'fasgfdagsd', 'db4f1a4c5dc502b51d62e8175b1f320f94d954f7f46f9adc3b2863e1d1ecc45f', NULL, 'platform-visitor', NULL, 'gdsfgdfgds', NULL, NULL, NULL, NULL, '2023-08-29 15:02:28', '2023-04-17 23:46:44', NULL, 0, 1);
INSERT INTO `enstudy_user` VALUES (19, 'qrqeqeqw', '45f710caf185a0b240dcdf5b91a111887588c9ea74b099d9fdd18c23bfe36352', NULL, 'platform-visitor', NULL, 'dsfdsgdd', NULL, NULL, NULL, NULL, '2023-04-17 23:46:51', '2023-04-17 23:46:51', NULL, 0, 1);
INSERT INTO `enstudy_user` VALUES (20, 'fgdfshgdf', '843febe995f3969a78c8dd8fe647a58369804747b5a8b4c1ff4641f5183f1dc4', NULL, 'platform-visitor', NULL, '423sdzb', NULL, NULL, NULL, NULL, '2023-04-17 23:46:59', '2023-04-17 23:46:59', NULL, 0, 1);
INSERT INTO `enstudy_user` VALUES (21, '56546456', '026c29a697be8ba83b4efcfbbac2804fc9b45501de2ef7ac491b66abe873435a', NULL, 'platform-visitor', NULL, '6546y34', NULL, NULL, NULL, NULL, '2023-04-17 23:48:36', '2023-04-17 23:48:36', NULL, 0, 1);
INSERT INTO `enstudy_user` VALUES (22, '6453ytrhdf', '1e474b2b590bec6b10d58f1a74069629216b623c0be72214b071956287f2f3dd', NULL, 'platform-visitor', NULL, 'gsfdg3452', NULL, NULL, NULL, NULL, '2023-04-17 23:48:44', '2023-04-17 23:48:44', NULL, 0, 1);

-- ----------------------------
-- Table structure for enstudy_user_book_dict
-- ----------------------------
DROP TABLE IF EXISTS `enstudy_user_book_dict`;
CREATE TABLE `enstudy_user_book_dict`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint NOT NULL COMMENT '用户 id',
  `book_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '词典 id',
  `book_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '词典名称',
  `studied` int NULL DEFAULT NULL COMMENT '已学词数',
  `action` tinyint NOT NULL DEFAULT 0 COMMENT '用户使用状态：0->停用状态；1->使用状态',
  `creator` bigint NULL DEFAULT NULL COMMENT '创建者',
  `updater` bigint NULL DEFAULT NULL COMMENT '更新者',
  `create_time` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `del` tinyint NOT NULL DEFAULT 1 COMMENT '逻辑删除：0->删除状态；1->可用状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户活动词库表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of enstudy_user_book_dict
-- ----------------------------

-- ----------------------------
-- Table structure for enstudy_user_work_actions
-- ----------------------------
DROP TABLE IF EXISTS `enstudy_user_work_actions`;
CREATE TABLE `enstudy_user_work_actions`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `word_id` bigint NOT NULL COMMENT '单词 id',
  `user_id` bigint NOT NULL COMMENT '用户 id',
  `book_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '词典 id',
  `state` tinyint NULL DEFAULT 0 COMMENT '学习状态：0->未学；1->已学',
  `creator` bigint NULL DEFAULT NULL COMMENT '创建者',
  `updater` bigint NULL DEFAULT NULL COMMENT '更新者',
  `create_time` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `del` tinyint NOT NULL DEFAULT 1 COMMENT '逻辑删除：0->删除状态；1->可用状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户学习行为表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of enstudy_user_work_actions
-- ----------------------------

-- ----------------------------
-- Table structure for enstudy_user_wrong_word
-- ----------------------------
DROP TABLE IF EXISTS `enstudy_user_wrong_word`;
CREATE TABLE `enstudy_user_wrong_word`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `word_id` bigint NOT NULL COMMENT '单词id',
  `user_id` bigint NOT NULL COMMENT '用户 id',
  `book_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '词典 id',
  `fail_count` int NULL DEFAULT 0 COMMENT '错误次数',
  `creator` bigint NULL DEFAULT NULL COMMENT '创建者',
  `updater` bigint NULL DEFAULT NULL COMMENT '更新者',
  `create_time` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `del` tinyint NOT NULL DEFAULT 1 COMMENT '逻辑删除：0->删除状态；1->可用状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户错题本' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of enstudy_user_wrong_word
-- ----------------------------

-- ----------------------------
-- Table structure for enstudy_word
-- ----------------------------
DROP TABLE IF EXISTS `enstudy_word`;
CREATE TABLE `enstudy_word`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `word_json_id` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '单词数据id',
  `word_rank` int NOT NULL COMMENT '单词序号',
  `head_word` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '单词',
  `book_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '词典 id',
  `creator` bigint NULL DEFAULT NULL COMMENT '创建者',
  `updater` bigint NULL DEFAULT NULL COMMENT '更新者',
  `create_time` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `del` tinyint NOT NULL DEFAULT 1 COMMENT '逻辑删除：0->删除状态；1->可用状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '单词表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of enstudy_word
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
