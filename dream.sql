/*
 Navicat Premium Data Transfer

 Source Server         : java
 Source Server Type    : MySQL
 Source Server Version : 50730
 Source Host           : localhost:3306
 Source Schema         : dream

 Target Server Type    : MySQL
 Target Server Version : 50730
 File Encoding         : 65001

 Date: 06/12/2022 10:58:25
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for biz_apply
-- ----------------------------
DROP TABLE IF EXISTS `biz_apply`;
CREATE TABLE `biz_apply`  (
  `id` bigint(11) NOT NULL COMMENT 'ID',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '入驻名称',
  `contact` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '联系人',
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '联系电话',
  `license` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '执照',
  `status` int(2) NULL DEFAULT NULL COMMENT '状态',
  `is_delete` int(2) NULL DEFAULT NULL COMMENT '是否删除',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of biz_apply
-- ----------------------------

-- ----------------------------
-- Table structure for biz_case
-- ----------------------------
DROP TABLE IF EXISTS `biz_case`;
CREATE TABLE `biz_case`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `image` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '图片',
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '内容',
  `team_id` bigint(20) NULL DEFAULT NULL COMMENT '团队id',
  `team_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '团队name',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '备注',
  `is_delete` int(2) NOT NULL DEFAULT 0 COMMENT '是否删除',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of biz_case
-- ----------------------------

-- ----------------------------
-- Table structure for biz_goods
-- ----------------------------
DROP TABLE IF EXISTS `biz_goods`;
CREATE TABLE `biz_goods`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '商品编码',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '商品名称',
  `photos` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '商品照片',
  `price` decimal(10, 2) NOT NULL DEFAULT 0.00 COMMENT '商品价格',
  `stock` int(20) NOT NULL DEFAULT 0 COMMENT '库存',
  `type` int(255) NOT NULL DEFAULT 0 COMMENT '类型',
  `is_delete` int(2) NOT NULL DEFAULT 0 COMMENT '是否删除',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of biz_goods
-- ----------------------------
INSERT INTO `biz_goods` VALUES (1, '1', '窗台板', 'http://chos7n1.oss-cn-hangzhou.aliyuncs.com/dream/2022/12/03/dcc6d542-0ea2-4481-85f4-255947ea6d43.jpg', 21.00, 15, 9, 0, '2022-10-16 16:29:07', '2022-12-02 18:08:09');
INSERT INTO `biz_goods` VALUES (3, '2', '水泥', 'http://chos7n1.oss-cn-hangzhou.aliyuncs.com/dream/2022/12/03/55cc3d63-0346-4327-9483-ad1ec53d7667.jpg', 21.00, 0, 1, 0, '2022-10-16 16:43:30', '2022-12-02 18:08:09');
INSERT INTO `biz_goods` VALUES (4, '3', '大理石', 'http://chos7n1.oss-cn-hangzhou.aliyuncs.com/dream/2022/12/04/41ae7810-785b-4333-8298-aa4f2ea2f6a1.jpg', 21.00, 0, 7, 0, '2022-10-16 17:20:21', '2022-12-02 18:08:10');
INSERT INTO `biz_goods` VALUES (5, '4', '石材', 'http://chos7n1.oss-cn-hangzhou.aliyuncs.com/dream/2022/12/04/1fa5c78e-704d-4a36-bc37-bfce99856aa5.jpg', 32.00, 2, 3, 0, '2022-11-28 20:33:49', '2022-12-02 18:08:54');
INSERT INTO `biz_goods` VALUES (7, '6', '玻璃钢', 'http://chos7n1.oss-cn-hangzhou.aliyuncs.com/dream/2022/12/04/aec16f57-45a3-49ff-ac1c-88e739f8bf4d.jpg', 4.00, 4, 8, 0, '2022-11-28 20:46:17', '2022-12-02 18:08:11');
INSERT INTO `biz_goods` VALUES (8, '7', '玻璃钢', 'http://chos7n1.oss-cn-hangzhou.aliyuncs.com/dream/2022/12/04/7f563b05-6575-46a1-b6ce-37d002d908ee.jpg', 2.00, 2, 8, 0, '2022-12-01 16:12:05', '2022-12-02 18:08:13');
INSERT INTO `biz_goods` VALUES (9, '353688940710072320', '地板', 'http://chos7n1.oss-cn-hangzhou.aliyuncs.com/dream/2022/12/04/78aed66f-5f3d-450b-a424-3a8160a9d2cb.jpg', 21.00, 0, 6, 0, '2022-12-02 16:38:03', '2022-12-02 16:38:03');

-- ----------------------------
-- Table structure for biz_goods_order
-- ----------------------------
DROP TABLE IF EXISTS `biz_goods_order`;
CREATE TABLE `biz_goods_order`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '编码',
  `customer_id` bigint(20) NULL DEFAULT NULL COMMENT '客户ID',
  `customer_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '客户名称',
  `customer_phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `customer_address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `goods_id` bigint(20) NULL DEFAULT NULL COMMENT '商品id',
  `goods_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '商品名称',
  `goods_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '订单编号',
  `count` int(20) NULL DEFAULT NULL COMMENT '数量',
  `price` decimal(10, 2) NULL DEFAULT NULL COMMENT '单价',
  `sum` decimal(10, 0) NULL DEFAULT NULL COMMENT '总价',
  `status` int(2) NOT NULL DEFAULT 0 COMMENT '状态',
  `is_delete` int(2) NOT NULL DEFAULT 0 COMMENT '是否删除',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 30 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of biz_goods_order
-- ----------------------------
INSERT INTO `biz_goods_order` VALUES (17, '353710401202032640', 18, '王宇杭', '13185060960', '浙江省衢州市常山县东案乡呈东村', 9, '321', '353688940710072320', 1, 21.00, 21, 2, 0, '2022-12-02 18:03:21', '2022-12-02 18:03:21');
INSERT INTO `biz_goods_order` VALUES (18, '353710684464353280', 18, '王宇杭', '13185060960', '浙江省衢州市常山县东案乡呈东村', 9, '321', '353688940710072320', 1, 21.00, 21, 0, 0, '2022-12-02 18:04:27', '2022-12-02 18:04:27');
INSERT INTO `biz_goods_order` VALUES (19, '353710916405170176', 18, '王宇杭', '13185060960', '浙江省衢州市常山县东案乡呈东村', 3, '水泥', '', 1, 21.00, 21, 2, 0, '2022-12-02 18:05:23', '2022-12-02 18:05:23');
INSERT INTO `biz_goods_order` VALUES (20, '353711747288403968', 18, '王宇杭', '13185060960', '浙江省衢州市常山县东案乡呈东村', 4, '21', '3', 1, 21.00, 21, 0, 0, '2022-12-02 18:08:41', '2022-12-02 18:08:41');
INSERT INTO `biz_goods_order` VALUES (21, '353711829454819328', 18, '王宇杭', '13185060960', '浙江省衢州市常山县东案乡呈东村', 5, '321', '4', 1, 32.00, 32, 0, 0, '2022-12-02 18:09:00', '2022-12-02 18:09:00');
INSERT INTO `biz_goods_order` VALUES (22, '353712052449185792', 18, '王宇杭', '13185060960', '浙江省衢州市常山县东案乡呈东村', 1, '21', '1', 1, 21.00, 21, 0, 0, '2022-12-02 18:09:54', '2022-12-02 18:09:54');
INSERT INTO `biz_goods_order` VALUES (23, '353746160575451136', 24, '王唯楚', '111111111', '杭州市', 1, '21', '1', 1, 21.00, 21, 0, 0, '2022-12-02 20:25:25', '2022-12-02 20:25:25');
INSERT INTO `biz_goods_order` VALUES (24, '353749341225226240', 25, '奶酪', '10086', '地球', 1, '21', '1', 1, 21.00, 34, 0, 0, '2022-12-02 20:38:04', '2022-12-02 20:38:04');
INSERT INTO `biz_goods_order` VALUES (25, '353749731299692544', 25, '奶酪', '10086', '地球', 1, '21', '1', 2, 21.00, 46, 0, 0, '2022-12-02 20:39:37', '2022-12-02 20:39:37');
INSERT INTO `biz_goods_order` VALUES (26, '353749946220023808', 25, '奶酪', '10086', '地球', 1, '21', '1', 6, 21.00, 145, 0, 0, '2022-12-02 20:40:28', '2022-12-02 20:40:28');
INSERT INTO `biz_goods_order` VALUES (27, '353752065157238784', 18, '王宇杭', '13185060960', '浙江省衢州市常山县东案乡呈东村', 1, '21', '1', 1, 21.00, 21, 0, 0, '2022-12-02 20:48:54', '2022-12-02 20:48:54');
INSERT INTO `biz_goods_order` VALUES (28, '353752259005386752', 18, '王宇杭', '13185060960', '浙江省衢州市常山县东案乡呈东村', 1, '21', '1', 1, 21.00, 21, 0, 0, '2022-12-02 20:49:39', '2022-12-02 20:49:39');
INSERT INTO `biz_goods_order` VALUES (29, '354374560298176512', 18, '王宇杭', '13185060960', '浙江省衢州市常山县东案乡呈东村', 9, '地板', '353688940710072320', 2, 21.00, 42, 0, 0, '2022-12-04 14:02:28', '2022-12-04 14:02:28');

-- ----------------------------
-- Table structure for biz_order
-- ----------------------------
DROP TABLE IF EXISTS `biz_order`;
CREATE TABLE `biz_order`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '订单编码',
  `customer_id` bigint(255) NULL DEFAULT NULL COMMENT '客户id',
  `customer_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '客户名称',
  `customer_phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `customer_address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '客户住址',
  `photos` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `status` int(2) NOT NULL DEFAULT 0 COMMENT '状态',
  `is_delete` int(2) NOT NULL DEFAULT 0 COMMENT '是否删除',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of biz_order
-- ----------------------------
INSERT INTO `biz_order` VALUES (11, '353734944775868416', 18, '王宇杭', '13185060960', '浙江省衢州市常山县东案乡呈东村', 'http://chos7n1.oss-cn-hangzhou.aliyuncs.com/dream/2022/11/23/1094ae35-5c57-4745-9484-8fac2a50415a.jpg', 0, 0, '2022-12-02 19:40:51', '2022-12-02 19:40:51');
INSERT INTO `biz_order` VALUES (14, '353735475527290880', 18, '王宇杭', '13185060960', '浙江省衢州市常山县东案乡呈东村', 'http://chos7n1.oss-cn-hangzhou.aliyuncs.com/dream/2022/11/20/0df2fa13-f301-46f3-b3c4-27a8d7dbc9ec.jpg', 0, 0, '2022-12-02 19:42:58', '2022-12-02 19:42:58');
INSERT INTO `biz_order` VALUES (15, '353735483324502016', 18, '王宇杭', '13185060960', '浙江省衢州市常山县东案乡呈东村', 'http://chos7n1.oss-cn-hangzhou.aliyuncs.com/dream/2022/11/30/1aef4b73-9365-44d6-bb04-50f8dc0842fd.jpg', 0, 0, '2022-12-02 19:43:00', '2022-12-02 19:43:00');
INSERT INTO `biz_order` VALUES (16, '353746571990536192', 24, '王唯楚', '111111111', '杭州市', 'http://chos7n1.oss-cn-hangzhou.aliyuncs.com/dream/2022/12/02/ce2c6cac-bcf1-4245-b4a0-0a49857dcccd.jpg', 0, 0, '2022-12-02 20:27:03', '2022-12-02 20:27:03');
INSERT INTO `biz_order` VALUES (17, '353750081389858816', 25, '奶酪', '10086', '地球', 'http://chos7n1.oss-cn-hangzhou.aliyuncs.com/dream/2022/11/20/0df2fa13-f301-46f3-b3c4-27a8d7dbc9ec.jpg', 0, 0, '2022-12-02 20:41:00', '2022-12-02 20:41:00');

-- ----------------------------
-- Table structure for biz_scheme
-- ----------------------------
DROP TABLE IF EXISTS `biz_scheme`;
CREATE TABLE `biz_scheme`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '编码',
  `customer_id` bigint(20) NULL DEFAULT NULL COMMENT '客户id',
  `customer_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '客户名称',
  `customer_address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '客户地址',
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '员工id',
  `user_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '员工名称',
  `scheme` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '方案',
  `budget` decimal(20, 2) NOT NULL DEFAULT 0.00 COMMENT '预算',
  `status` int(2) NOT NULL DEFAULT 0 COMMENT '状态',
  `is_delete` int(2) NOT NULL DEFAULT 0 COMMENT '是否删除',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of biz_scheme
-- ----------------------------
INSERT INTO `biz_scheme` VALUES (1, '353737203786059776', 0, '', '', 0, '', '', 0.00, 0, 0, '2022-12-02 19:49:50', '2022-12-02 19:49:50');

-- ----------------------------
-- Table structure for biz_team
-- ----------------------------
DROP TABLE IF EXISTS `biz_team`;
CREATE TABLE `biz_team`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '团队编码',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '团队名称',
  `contact` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '联系人',
  `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '联系电话',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '地址',
  `license` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '营业执照',
  `satisfied` int(2) NULL DEFAULT 0 COMMENT '满意度',
  `order_sum` int(20) NULL DEFAULT 0 COMMENT '订单数',
  `is_delete` int(2) NOT NULL DEFAULT 0 COMMENT '是否删除',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of biz_team
-- ----------------------------
INSERT INTO `biz_team` VALUES (2, '0', '123', '', '13186950368', '浙江省衢州市常山县东案乡呈东村', 'http://chos7n1.oss-cn-hangzhou.aliyuncs.com/dream/2022/10/21/91e567cc-87bd-4cda-92d2-706485bc7cdb.jpg', 2, 32, 0, '2022-10-21 18:54:18', '2022-10-21 18:57:18');
INSERT INTO `biz_team` VALUES (4, '自动生成待完成', '21', '21', '21', '21', 'http://chos7n1.oss-cn-hangzhou.aliyuncs.com/dream/2022/10/21/52c651d2-b3b5-4e7e-a123-ff6679e10002.jpg', 1, 21, 0, '2022-10-21 19:05:21', '2022-10-21 19:05:21');
INSERT INTO `biz_team` VALUES (10, '自动生成待完成', '12', '21', '21', '21', 'http://chos7n1.oss-cn-hangzhou.aliyuncs.com/dream/2022/11/03/97bbec69-4fbf-40d0-b1d6-25defc4e5a34.jpg', 1, 21, 0, '2022-11-03 19:53:10', '2022-11-03 19:53:10');
INSERT INTO `biz_team` VALUES (11, '自动生成待完成', '321', '', '', '', '', 0, 0, 0, '2022-11-29 22:50:41', '2022-11-29 22:50:41');

-- ----------------------------
-- Table structure for customer_collect
-- ----------------------------
DROP TABLE IF EXISTS `customer_collect`;
CREATE TABLE `customer_collect`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `collecter_id` bigint(20) NULL DEFAULT NULL,
  `content_id` bigint(20) NULL DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 52 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of customer_collect
-- ----------------------------
INSERT INTO `customer_collect` VALUES (28, 17, 7, '2022-11-30 15:01:10', '2022-11-30 15:01:10');
INSERT INTO `customer_collect` VALUES (31, 17, 11, '2022-11-30 20:24:57', '2022-11-30 20:24:57');
INSERT INTO `customer_collect` VALUES (32, 17, 8, '2022-11-30 21:27:13', '2022-11-30 21:27:13');
INSERT INTO `customer_collect` VALUES (39, 24, 13, '2022-12-02 20:23:52', '2022-12-02 20:23:52');
INSERT INTO `customer_collect` VALUES (40, 24, 7, '2022-12-02 20:24:08', '2022-12-02 20:24:08');
INSERT INTO `customer_collect` VALUES (42, 24, 8, '2022-12-02 20:27:55', '2022-12-02 20:27:55');
INSERT INTO `customer_collect` VALUES (44, 25, 7, '2022-12-02 20:40:55', '2022-12-02 20:40:55');
INSERT INTO `customer_collect` VALUES (47, 18, 7, '2022-12-04 13:51:10', '2022-12-04 13:51:10');
INSERT INTO `customer_collect` VALUES (50, 18, 8, '2022-12-04 13:55:32', '2022-12-04 13:55:32');
INSERT INTO `customer_collect` VALUES (51, 18, 13, '2022-12-04 13:58:26', '2022-12-04 13:58:26');

-- ----------------------------
-- Table structure for customer_comment
-- ----------------------------
DROP TABLE IF EXISTS `customer_comment`;
CREATE TABLE `customer_comment`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `content_id` bigint(20) NULL DEFAULT NULL COMMENT '内容id',
  `master_id` bigint(20) NULL DEFAULT NULL,
  `customer_id` bigint(20) NULL DEFAULT NULL COMMENT '客户id',
  `customer_image` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '客户头像',
  `customer_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '客户名称',
  `comment` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '评论',
  `likes` bigint(255) NOT NULL DEFAULT 0 COMMENT '点赞数',
  `is_delete` int(2) NOT NULL DEFAULT 0 COMMENT '是否删除',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 71 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of customer_comment
-- ----------------------------
INSERT INTO `customer_comment` VALUES (57, 14, 0, 25, 'http://chos7n1.oss-cn-hangzhou.aliyuncs.com/dream/2022/12/02/7c3ef735-cf22-4226-b970-cc4fa0665bb9.jpg', '奶酪', '1000住吗', 0, 0, '2022-12-02 20:35:23', '2022-12-02 20:35:23');
INSERT INTO `customer_comment` VALUES (58, 15, 0, 18, 'http://chos7n1.oss-cn-hangzhou.aliyuncs.com/dream/2022/11/03/abd77ab9-79f4-4b2b-adc9-0133f2825648.jpg', '王宇杭', '***万岁', 0, 0, '2022-12-03 14:57:34', '2022-12-03 14:57:34');
INSERT INTO `customer_comment` VALUES (59, 15, 0, 18, 'http://chos7n1.oss-cn-hangzhou.aliyuncs.com/dream/2022/11/03/abd77ab9-79f4-4b2b-adc9-0133f2825648.jpg', '王宇杭', '***傻逼', 0, 0, '2022-12-03 14:58:43', '2022-12-03 14:58:43');
INSERT INTO `customer_comment` VALUES (60, 12, 0, 18, 'http://chos7n1.oss-cn-hangzhou.aliyuncs.com/dream/2022/11/03/abd77ab9-79f4-4b2b-adc9-0133f2825648.jpg', '王宇杭', '321', 0, 0, '2022-12-04 13:49:39', '2022-12-04 13:49:39');
INSERT INTO `customer_comment` VALUES (61, 11, 0, 18, 'http://chos7n1.oss-cn-hangzhou.aliyuncs.com/dream/2022/11/03/abd77ab9-79f4-4b2b-adc9-0133f2825648.jpg', '王宇杭', '好看', 0, 0, '2022-12-04 13:57:58', '2022-12-04 13:57:58');
INSERT INTO `customer_comment` VALUES (62, 7, 0, 18, 'http://chos7n1.oss-cn-hangzhou.aliyuncs.com/dream/2022/11/03/abd77ab9-79f4-4b2b-adc9-0133f2825648.jpg', '王宇杭', '21', 0, 0, '2022-12-04 13:59:03', '2022-12-04 13:59:03');
INSERT INTO `customer_comment` VALUES (63, 10, 0, 18, 'http://chos7n1.oss-cn-hangzhou.aliyuncs.com/dream/2022/11/03/abd77ab9-79f4-4b2b-adc9-0133f2825648.jpg', '王宇杭', '3', 0, 0, '2022-12-04 13:59:10', '2022-12-04 13:59:10');
INSERT INTO `customer_comment` VALUES (70, 8, 0, 18, 'http://chos7n1.oss-cn-hangzhou.aliyuncs.com/dream/2022/11/03/abd77ab9-79f4-4b2b-adc9-0133f2825648.jpg', '王宇杭', '6666***', 0, 0, '2022-12-04 14:09:06', '2022-12-04 14:09:06');

-- ----------------------------
-- Table structure for customer_content
-- ----------------------------
DROP TABLE IF EXISTS `customer_content`;
CREATE TABLE `customer_content`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `customer_id` bigint(20) NULL DEFAULT NULL COMMENT '客户id',
  `customer_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '客户名称',
  `customer_image` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '客户头像',
  `photos` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '图片',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '标题',
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '内容',
  `likes` bigint(255) NOT NULL DEFAULT 0 COMMENT '点赞数',
  `collect` bigint(255) NOT NULL DEFAULT 0 COMMENT '收藏数',
  `tag` int(2) NOT NULL DEFAULT 0 COMMENT '标签',
  `is_delete` int(2) NOT NULL DEFAULT 0 COMMENT '是否删除',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of customer_content
-- ----------------------------
INSERT INTO `customer_content` VALUES (7, 17, 'wyh', 'http://chos7n1.oss-cn-hangzhou.aliyuncs.com/dream/2022/11/19/f10c9149-babf-43d3-86e7-247cae783759.jpg', 'http://chos7n1.oss-cn-hangzhou.aliyuncs.com/dream/2022/11/20/0df2fa13-f301-46f3-b3c4-27a8d7dbc9ec.jpg', '', '好车', 6, 54448, 0, 0, '2022-11-20 18:58:52', '2022-12-04 13:51:10');
INSERT INTO `customer_content` VALUES (8, 18, '王宇杭', 'http://chos7n1.oss-cn-hangzhou.aliyuncs.com/dream/2022/11/03/abd77ab9-79f4-4b2b-adc9-0133f2825648.jpg', 'http://chos7n1.oss-cn-hangzhou.aliyuncs.com/dream/2022/12/03/f8d39b34-20ce-499b-b3c8-4a584ae2f756.jpg', '', '这个真好看***', 7, 27, 0, 0, '2022-11-23 21:13:17', '2022-12-04 13:55:32');
INSERT INTO `customer_content` VALUES (10, 18, '王宇杭', 'http://chos7n1.oss-cn-hangzhou.aliyuncs.com/dream/2022/11/03/abd77ab9-79f4-4b2b-adc9-0133f2825648.jpg', 'http://chos7n1.oss-cn-hangzhou.aliyuncs.com/dream/2022/12/04/d68b7885-1ef4-45f3-9c66-e519e817d9ab.jpg', '', '这样的装修', 0, 5, 0, 0, '2022-11-29 22:26:08', '2022-12-04 13:50:41');
INSERT INTO `customer_content` VALUES (11, 17, 'wyh', 'http://chos7n1.oss-cn-hangzhou.aliyuncs.com/dream/2022/11/19/f10c9149-babf-43d3-86e7-247cae783759.jpg', 'http://chos7n1.oss-cn-hangzhou.aliyuncs.com/dream/2022/11/30/1aef4b73-9365-44d6-bb04-50f8dc0842fd.jpg', '', '艾斯', 0, 1, 0, 0, '2022-11-30 19:24:52', '2022-12-04 13:51:26');
INSERT INTO `customer_content` VALUES (13, 24, '王唯楚', 'https://chos7n1.oss-cn-hangzhou.aliyuncs.com/dream/2022/12/02/9537751b-47ff-494d-b2d6-7d48ff0d4914.jpg', 'http://chos7n1.oss-cn-hangzhou.aliyuncs.com/dream/2022/12/02/ce2c6cac-bcf1-4245-b4a0-0a49857dcccd.jpg', '', '压缩7', 0, 2, 0, 0, '2022-12-02 20:23:31', '2022-12-04 13:58:26');
INSERT INTO `customer_content` VALUES (14, 25, '回个人中心修改昵称', 'https://chos7n1.oss-cn-hangzhou.aliyuncs.com/dream/2022/12/02/9537751b-47ff-494d-b2d6-7d48ff0d4914.jpg', 'http://chos7n1.oss-cn-hangzhou.aliyuncs.com/dream/2022/12/02/3668e716-af07-4f31-9132-f82a725ecf57.jpg', '', '德玛西亚风格 装修', 0, 0, 0, 0, '2022-12-02 20:32:52', '2022-12-02 20:33:31');
INSERT INTO `customer_content` VALUES (15, 18, '王宇杭', 'http://chos7n1.oss-cn-hangzhou.aliyuncs.com/dream/2022/11/03/abd77ab9-79f4-4b2b-adc9-0133f2825648.jpg', 'http://chos7n1.oss-cn-hangzhou.aliyuncs.com/dream/2022/12/03/1fb04c0b-fbb1-4a55-be87-bb51fbb26ad2.jpg', '', '卢锡安***哇哇哇', 0, 0, 0, 0, '2022-12-03 14:57:22', '2022-12-03 14:58:07');

-- ----------------------------
-- Table structure for customer_message
-- ----------------------------
DROP TABLE IF EXISTS `customer_message`;
CREATE TABLE `customer_message`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `master_id` int(11) NULL DEFAULT NULL,
  `customer_id` bigint(20) NULL DEFAULT NULL,
  `customer_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `content_id` bigint(20) NULL DEFAULT NULL,
  `content_photos` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `message` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `type` int(10) NOT NULL DEFAULT 0,
  `is_deleted` int(12) NOT NULL DEFAULT 0,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 52 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of customer_message
-- ----------------------------
INSERT INTO `customer_message` VALUES (44, 0, 0, '后台', 0, 'http://chos7n1.oss-cn-hangzhou.aliyuncs.com/dream/2022/12/04/00d23f33-1d07-41cc-bbf0-adcf2f51c116.jpg', '大甩卖', 1, 0, '2022-12-04 14:03:22', '2022-12-04 14:03:22');
INSERT INTO `customer_message` VALUES (45, 18, 18, '王宇杭', 8, 'http://chos7n1.oss-cn-hangzhou.aliyuncs.com/dream/2022/12/03/f8d39b34-20ce-499b-b3c8-4a584ae2f756.jpg', '评论了你的文章：***', 0, 0, '2022-12-04 14:04:53', '2022-12-04 14:04:53');
INSERT INTO `customer_message` VALUES (46, 17, 18, '王宇杭', 7, 'http://chos7n1.oss-cn-hangzhou.aliyuncs.com/dream/2022/11/20/0df2fa13-f301-46f3-b3c4-27a8d7dbc9ec.jpg', '评论了你的文章：***', 0, 0, '2022-12-04 14:08:05', '2022-12-04 14:08:05');
INSERT INTO `customer_message` VALUES (47, 17, 18, '王宇杭', 7, 'http://chos7n1.oss-cn-hangzhou.aliyuncs.com/dream/2022/11/20/0df2fa13-f301-46f3-b3c4-27a8d7dbc9ec.jpg', '评论了你的文章：王唯楚', 0, 0, '2022-12-04 14:08:10', '2022-12-04 14:08:10');
INSERT INTO `customer_message` VALUES (48, 17, 18, '王宇杭', 7, 'http://chos7n1.oss-cn-hangzhou.aliyuncs.com/dream/2022/11/20/0df2fa13-f301-46f3-b3c4-27a8d7dbc9ec.jpg', '评论了你的文章：***', 0, 0, '2022-12-04 14:08:22', '2022-12-04 14:08:22');
INSERT INTO `customer_message` VALUES (49, 17, 18, '王宇杭', 7, 'http://chos7n1.oss-cn-hangzhou.aliyuncs.com/dream/2022/11/20/0df2fa13-f301-46f3-b3c4-27a8d7dbc9ec.jpg', '评论了你的文章：唯楚', 0, 0, '2022-12-04 14:08:32', '2022-12-04 14:08:32');
INSERT INTO `customer_message` VALUES (50, 17, 18, '王宇杭', 7, 'http://chos7n1.oss-cn-hangzhou.aliyuncs.com/dream/2022/11/20/0df2fa13-f301-46f3-b3c4-27a8d7dbc9ec.jpg', '评论了你的文章：王1唯楚', 0, 0, '2022-12-04 14:08:42', '2022-12-04 14:08:42');
INSERT INTO `customer_message` VALUES (51, 18, 18, '王宇杭', 8, 'http://chos7n1.oss-cn-hangzhou.aliyuncs.com/dream/2022/12/03/f8d39b34-20ce-499b-b3c8-4a584ae2f756.jpg', '评论了你的文章：6666***', 0, 0, '2022-12-04 14:09:06', '2022-12-04 14:09:06');

-- ----------------------------
-- Table structure for customer_user
-- ----------------------------
DROP TABLE IF EXISTS `customer_user`;
CREATE TABLE `customer_user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '昵称',
  `image` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '头像',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '账号',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '密码',
  `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '电话',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '邮箱',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '地址',
  `sex` int(2) NOT NULL DEFAULT 0,
  `role` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '权限',
  `salt` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '盐',
  `is_delete` int(2) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 26 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of customer_user
-- ----------------------------
INSERT INTO `customer_user` VALUES (17, 'wyh', 'http://chos7n1.oss-cn-hangzhou.aliyuncs.com/dream/2022/11/19/f10c9149-babf-43d3-86e7-247cae783759.jpg', '123456', '6e07bc9ae96dce396750988dd1cc6b8d', '13185060960', '1136361325@qq.com', '浙江省衢州市常山县东案乡呈东村', 0, '', 'SDkcMTkNfzSWAiw9n4NxzA==', 0, '2022-11-07 19:58:05', '2022-12-02 17:40:20');
INSERT INTO `customer_user` VALUES (18, '王宇杭', 'http://chos7n1.oss-cn-hangzhou.aliyuncs.com/dream/2022/11/03/abd77ab9-79f4-4b2b-adc9-0133f2825648.jpg', '123', '7b2ebd07ebe3dc8c7919c9bcde07bad1', '13185060960', '1136361325@qq.com', '浙江省衢州市常山县东案乡呈东村', 1, '', '7c8B0IJCKFo/1X11F1t8ww==', 0, '2022-11-08 19:12:39', '2022-11-10 20:00:09');
INSERT INTO `customer_user` VALUES (19, '', '', '999', '6935117e8edc6555ba60b0cbb8764ee4', '', '', '', 0, '', 'kiWCZlgQWxzGS9csDmcoTQ==', 0, '2022-11-09 19:30:31', '2022-11-09 19:30:31');
INSERT INTO `customer_user` VALUES (21, '', '', '1234', '380065377f82e128d966a131f8519ab3', '', '', '', 0, '', 'vFGGuKBETF/V02vL0mMMZw==', 0, '2022-11-19 19:18:00', '2022-11-19 19:18:00');
INSERT INTO `customer_user` VALUES (24, '王唯楚', 'https://chos7n1.oss-cn-hangzhou.aliyuncs.com/dream/2022/12/02/9537751b-47ff-494d-b2d6-7d48ff0d4914.jpg', '777', 'e830fce895616059e36244e802f7e757', '111111111', '1111111111', '杭州市', 1, '', 'TFFFnZxGIpj/8rM1cd+KOA==', 0, '2022-12-02 20:21:28', '2022-12-02 20:21:28');

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept`  (
  `id` bigint(20) NOT NULL COMMENT 'ID',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '部门名称',
  `code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '部门编号',
  `is_delete` int(2) NULL DEFAULT NULL COMMENT '是否删除',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dept
-- ----------------------------

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict`  (
  `id` bigint(20) NOT NULL COMMENT 'ID',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '字典名称',
  `code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '字典编号',
  `is_delete` int(2) NULL DEFAULT NULL COMMENT '是否删除',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dict
-- ----------------------------

-- ----------------------------
-- Table structure for sys_sensitive
-- ----------------------------
DROP TABLE IF EXISTS `sys_sensitive`;
CREATE TABLE `sys_sensitive`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_sensitive
-- ----------------------------
INSERT INTO `sys_sensitive` VALUES (11, '啊啊啊');
INSERT INTO `sys_sensitive` VALUES (12, '王唯楚');
INSERT INTO `sys_sensitive` VALUES (13, '777');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '姓名',
  `image` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '头像',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '账号',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '密码',
  `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '手机号',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '邮箱',
  `birthday` date NULL DEFAULT NULL COMMENT '出生日期',
  `sex` int(2) UNSIGNED NOT NULL DEFAULT 0 COMMENT '性别',
  `role` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '权限',
  `salt` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '盐',
  `is_delete` int(2) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除',
  `sys_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '所属部门',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 43 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (26, '王宇杭', 'http://chos7n1.oss-cn-hangzhou.aliyuncs.com/dream/2022/12/03/433e33ec-5efc-4d35-b6c9-6f1568a41612.jpg', '123', 'bd873efef46007475f270938260db26f', '13185060960', '1136361325@qq.com', '2022-12-27', 0, '', 'xm/TPpeXRPyrPDD0h3+EWw==', 0, '', '2022-11-10 19:38:30', '2022-11-11 16:26:10');
INSERT INTO `sys_user` VALUES (42, '王宇杭', 'http://chos7n1.oss-cn-hangzhou.aliyuncs.com/dream/2022/12/03/5f2e1245-872b-4a1a-9aa4-e298c0bbbb9b.jpg', '999', '14a17f43fc3ed12ea5e85f0d2945bd33', '3123123', '1136361325@qq.com', '2022-12-27', 0, '', 'tCTSscYhHEvZL/rKFVr/Iw==', 0, '', '2022-11-11 16:23:59', '2022-11-11 16:23:59');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(22) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '账号',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '密码',
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '手机',
  `birthday` date NULL DEFAULT NULL COMMENT '出生日期',
  `identity` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '身份证',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '地址',
  `role` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限',
  `salt` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '盐',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 34 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'wyh', 'wyh', '123456', '13185060960', '2022-07-27', '330822200012276018', '浙江杭州', '', NULL, '2022-09-06 15:19:41', '2022-09-06 15:19:56');
INSERT INTO `user` VALUES (2, 'IPE77', 'IPE777', '123456', '13735059496', '2022-07-27', '3308222200012276018', '浙江衢州', '', NULL, '2022-09-06 15:19:41', '2022-09-06 16:19:17');
INSERT INTO `user` VALUES (4, '2', '2', '2', '2', '2022-09-04', '2', '2', '', NULL, '2022-09-06 15:19:41', '2022-09-06 15:19:56');
INSERT INTO `user` VALUES (6, '4', '4', '4', '4', '2022-09-04', '4', '4', '', NULL, '2022-09-06 15:19:41', '2022-09-06 15:19:56');
INSERT INTO `user` VALUES (7, '5', '5', '5', '5', '2022-09-04', '5', '5', '', NULL, '2022-09-06 15:19:41', '2022-09-06 15:19:56');
INSERT INTO `user` VALUES (8, '6', '6', '6', '6', '2022-09-07', '6', '6', '', NULL, '2022-09-06 15:19:41', '2022-09-06 15:19:56');
INSERT INTO `user` VALUES (9, '7', '7', '7', '7', '2022-09-04', '7', '7', '', NULL, '2022-09-06 15:19:41', '2022-10-12 17:41:21');
INSERT INTO `user` VALUES (10, '8', '8', '8', '8', '2022-09-04', '8', '8', '', NULL, '2022-09-06 15:19:41', '2022-10-12 17:41:23');
INSERT INTO `user` VALUES (11, '9', '9', '9', '9', '2022-09-04', '9', '9', '', NULL, '2022-09-06 15:19:41', '2022-10-12 17:41:24');
INSERT INTO `user` VALUES (12, '10', '10', '10', '10', '2022-09-04', '10', '10', '', NULL, '2022-09-06 15:19:41', '2022-10-12 17:41:25');
INSERT INTO `user` VALUES (13, '123', '12345678', '123', '23', '2022-09-08', '123', '123', '', NULL, '2022-09-06 15:19:41', '2022-09-06 15:19:56');
INSERT INTO `user` VALUES (14, '123', '12345678', '123', '23', '2022-09-08', '123', '123', '', NULL, '2022-09-06 15:19:41', '2022-09-06 15:19:56');
INSERT INTO `user` VALUES (15, '123', '12345678', '123', '23', '2022-09-08', '123', '123', '', NULL, '2022-09-06 15:19:41', '2022-09-06 15:19:56');
INSERT INTO `user` VALUES (16, '3333', '333', '33', '3', '2022-09-08', '33', '33', '33', NULL, '2022-09-06 15:19:41', '2022-09-06 15:19:56');
INSERT INTO `user` VALUES (19, 'aaa', 'aa', 'aaa', 'aaa', '2022-08-29', 'aa', 'aa', 'a', NULL, '2022-09-06 15:19:41', '2022-09-06 15:19:56');
INSERT INTO `user` VALUES (20, '321', '321', '321', '321', '2022-09-05', '321', '3123123', '321', NULL, '2022-09-06 15:19:41', '2022-09-06 15:19:56');
INSERT INTO `user` VALUES (21, '3333', '3213', '333', '33', '2022-09-08', '2', '2', '1', NULL, '2022-09-06 15:19:41', '2022-09-06 15:19:56');
INSERT INTO `user` VALUES (22, '321', '321', '321', '321', '2022-09-07', '3', '3', '3', NULL, '2022-09-06 15:19:41', '2022-09-06 15:19:56');
INSERT INTO `user` VALUES (23, '321', '321', '321', '321', '2022-09-07', '3', '3', '3', NULL, '2022-09-06 15:19:41', '2022-09-06 15:19:56');
INSERT INTO `user` VALUES (24, 'wyh', 'wyh', '123456', '123456', '2022-09-05', '123', '1231', '', NULL, '2022-09-06 15:19:41', '2022-09-06 15:19:56');
INSERT INTO `user` VALUES (25, 'wyh', 'wyh', '333', '333', '2022-09-06', '33', '33', '', NULL, '2022-09-06 15:19:41', '2022-09-06 15:19:56');
INSERT INTO `user` VALUES (26, '233', '3', '333', '2', '2022-09-05', '3', '3', '3', NULL, '2022-09-06 15:19:41', '2022-09-06 15:19:56');
INSERT INTO `user` VALUES (27, '232', '123', '123', '123', '2022-09-01', '22', '2', '', NULL, '2022-09-06 15:19:41', '2022-09-06 15:19:56');
INSERT INTO `user` VALUES (28, '王唯楚', '王唯楚', '777', '777', '2022-09-05', '777', '777', '', NULL, '2022-09-06 15:19:41', '2022-09-06 15:19:56');
INSERT INTO `user` VALUES (29, '王唯楚', '王唯楚', '777', '777', '2022-09-05', '777', '777', '', NULL, '2022-09-06 15:19:41', '2022-09-06 15:19:56');
INSERT INTO `user` VALUES (30, '王宇杭', '王唯楚', '12', '13185060960', '2022-03-03', '2', '2', '', NULL, '2022-09-06 15:19:41', '2022-09-06 15:19:56');
INSERT INTO `user` VALUES (31, '王宇杭', '王唯楚', '222', '13185060960', '2022-09-07', '2', '2', '', NULL, '2022-09-06 15:19:41', '2022-09-06 15:19:56');
INSERT INTO `user` VALUES (32, '321', '321', '312', '321', '2022-09-08', '321', '321', '', NULL, '2022-09-06 15:19:41', '2022-09-06 15:19:56');
INSERT INTO `user` VALUES (33, '222', '22', '222', '222', '2022-09-06', '22', '22', '', NULL, '2022-09-06 15:20:27', '2022-09-06 15:20:27');

SET FOREIGN_KEY_CHECKS = 1;
