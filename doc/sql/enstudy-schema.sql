/*
 数据库表结构
 Date: 23/09/2023 16:48:50
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for enstudy_book_dict
-- ----------------------------
DROP TABLE IF EXISTS `enstudy_book_dict`;
CREATE TABLE `enstudy_book_dict`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `book_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '词典 id',
  `book_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '词典名称',
  `book_size` int NOT NULL DEFAULT 0 COMMENT '词典词数',
  `creator` bigint NULL DEFAULT NULL COMMENT '创建者',
  `updater` bigint NULL DEFAULT NULL COMMENT '更新者',
  `create_time` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `status` tinyint NOT NULL DEFAULT 0 COMMENT '词典启用状态：0->禁用；1->启用',
  `del` tinyint NOT NULL DEFAULT 1 COMMENT '逻辑删除：0->删除状态；1->可用状态',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_book_id`(`book_id` ASC, `del` ASC) USING BTREE COMMENT '词典 id 唯一索引'
) ENGINE = InnoDB AUTO_INCREMENT = 84 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '词库表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for enstudy_feedback
-- ----------------------------
DROP TABLE IF EXISTS `enstudy_feedback`;
CREATE TABLE `enstudy_feedback`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `word_id` bigint NOT NULL COMMENT '单词 id',
  `user_id` bigint NOT NULL COMMENT '用户 id',
  `book_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '词典 id',
  `content` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '反馈内容',
  `creator` bigint NULL DEFAULT NULL COMMENT '创建者',
  `updater` bigint NULL DEFAULT NULL COMMENT '更新者',
  `create_time` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `del` tinyint NOT NULL DEFAULT 1 COMMENT '逻辑删除：0->删除状态；1->可用状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '反馈表' ROW_FORMAT = Dynamic;

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
-- Table structure for enstudy_user_book_dict
-- ----------------------------
DROP TABLE IF EXISTS `enstudy_user_book_dict`;
CREATE TABLE `enstudy_user_book_dict`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint NOT NULL COMMENT '用户 id',
  `book_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '词典 id',
  `book_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '词典名称',
  `studied` int NOT NULL DEFAULT 0 COMMENT '已学词数',
  `action` tinyint NOT NULL DEFAULT 0 COMMENT '用户使用状态：0->停用状态；1->使用状态',
  `creator` bigint NULL DEFAULT NULL COMMENT '创建者',
  `updater` bigint NULL DEFAULT NULL COMMENT '更新者',
  `create_time` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `del` tinyint NOT NULL DEFAULT 1 COMMENT '逻辑删除：0->删除状态；1->可用状态',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_user_id`(`user_id` ASC, `del` ASC) USING BTREE COMMENT '用户 id 索引'
) ENGINE = InnoDB AUTO_INCREMENT = 102 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户活动词库表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for enstudy_user_work_actions
-- ----------------------------
DROP TABLE IF EXISTS `enstudy_user_work_actions`;
CREATE TABLE `enstudy_user_work_actions`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `word_id` bigint NOT NULL COMMENT '单词 id',
  `user_id` bigint NOT NULL COMMENT '用户 id',
  `book_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '词典 id',
  `state` tinyint NOT NULL DEFAULT 0 COMMENT '学习状态：0->未学；1->已学',
  `creator` bigint NULL DEFAULT NULL COMMENT '创建者',
  `updater` bigint NULL DEFAULT NULL COMMENT '更新者',
  `create_time` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `del` tinyint NOT NULL DEFAULT 1 COMMENT '逻辑删除：0->删除状态；1->可用状态',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `idx_user_id_and_book_id_and_word_id`(`user_id` ASC, `book_id` ASC, `word_id` ASC, `del` ASC) USING BTREE COMMENT '用户 id 和词典 id 和单词 id 索引',
  INDEX `idx_user_id_and_book_id`(`user_id` ASC, `book_id` ASC, `del` ASC) USING BTREE COMMENT '用户 id 和词典 id 索引',
  INDEX `idx_update_time`(`update_time` ASC) USING BTREE COMMENT '更新时间索引'
) ENGINE = InnoDB AUTO_INCREMENT = 185984 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户学习行为表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for enstudy_user_wrong_word
-- ----------------------------
DROP TABLE IF EXISTS `enstudy_user_wrong_word`;
CREATE TABLE `enstudy_user_wrong_word`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `word_id` bigint NOT NULL COMMENT '单词 id',
  `user_id` bigint NOT NULL COMMENT '用户 id',
  `book_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '词典 id',
  `fail_count` int NOT NULL DEFAULT 0 COMMENT '错误次数',
  `creator` bigint NULL DEFAULT NULL COMMENT '创建者',
  `updater` bigint NULL DEFAULT NULL COMMENT '更新者',
  `create_time` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `del` tinyint NOT NULL DEFAULT 1 COMMENT '逻辑删除：0->删除状态；1->可用状态',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `idx_user_id_and_book_id_and_word_id`(`user_id` ASC, `book_id` ASC, `word_id` ASC, `del` ASC) USING BTREE COMMENT '用户 id 和词典 id 和单词 id 索引',
  INDEX `idx_user_id_and_book_id`(`user_id` ASC, `book_id` ASC, `del` ASC) USING BTREE COMMENT '用户 id 和词典 id 索引'
) ENGINE = InnoDB AUTO_INCREMENT = 39 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户错题本' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for enstudy_word
-- ----------------------------
DROP TABLE IF EXISTS `enstudy_word`;
CREATE TABLE `enstudy_word`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `word_json_id` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '单词数据 id',
  `word_rank` int NOT NULL COMMENT '单词序号',
  `head_word` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '单词',
  `book_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '词典 id',
  `creator` bigint NULL DEFAULT NULL COMMENT '创建者',
  `updater` bigint NULL DEFAULT NULL COMMENT '更新者',
  `create_time` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `del` tinyint NOT NULL DEFAULT 1 COMMENT '逻辑删除：0->删除状态；1->可用状态',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_word_json_id`(`word_json_id`, `del`) USING HASH COMMENT '单词数据 id 索引',
  INDEX `idx_book_id`(`book_id` ASC, `del` ASC) USING BTREE COMMENT '词典 id 索引'
) ENGINE = InnoDB AUTO_INCREMENT = 153018 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '单词表' ROW_FORMAT = DYNAMIC;

SET FOREIGN_KEY_CHECKS = 1;
