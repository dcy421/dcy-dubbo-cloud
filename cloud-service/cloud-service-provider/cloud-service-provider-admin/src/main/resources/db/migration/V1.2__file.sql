/*
 Navicat Premium Data Transfer

 Source Server         : 192.168.0.2
 Source Server Type    : MySQL
 Source Server Version : 100311
 Source Host           : 192.168.0.2:3306
 Source Schema         : tsq-microservices

 Target Server Type    : MySQL
 Target Server Version : 100311
 File Encoding         : 65001

 Date: 28/08/2020 08:58:17
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for file_info
-- ----------------------------
DROP TABLE IF EXISTS `file_info`;
CREATE TABLE `file_info`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '文件md5',
  `name` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '文件名称',
  `is_img` tinyint(1) NOT NULL COMMENT '是否是图片类型',
  `content_type` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '文件类型',
  `size` int(11) NOT NULL COMMENT '文件大小',
  `path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '物理路径',
  `url` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'url地址',
  `source` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '来源',
  `create_date` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_create_time`(`create_date`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '文件表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of file_info
-- ----------------------------
INSERT INTO `file_info` VALUES ('2c95b54f4d8356cf8ab40802f496df83', '头像.png', 1, 'image/png', 1290, 'http://pkqtmn0p1.bkt.clouddn.com/头像.png', 'http://pkqtmn0p1.bkt.clouddn.com/头像.png', 'QINIU', '20190108170536');
INSERT INTO `file_info` VALUES ('9650357b6b77eba5db65e7593c75bfc5', '壁纸六.png', 1, 'image/png', 54508, 'http://py9cmj9ml.bkt.clouddn.com//壁纸六.png', 'http://py9cmj9ml.bkt.clouddn.com//壁纸六.png', 'qiniu', '20190923081011960');
INSERT INTO `file_info` VALUES ('d9c81d9c4a45fc58520f14602b5c1687', '旭旭宝宝头像.jpg', 1, 'image/jpeg', 33072, 'group1/M00/00/00/wKi9t13KXoKABjKRAACBMMJ7Ivo638.jpg', 'http://192.168.189.183:8888/group1/M00/00/00/wKi9t13KXoKABjKRAACBMMJ7Ivo638.jpg', 'fastdfs', '20191112152554184');

SET FOREIGN_KEY_CHECKS = 1;
