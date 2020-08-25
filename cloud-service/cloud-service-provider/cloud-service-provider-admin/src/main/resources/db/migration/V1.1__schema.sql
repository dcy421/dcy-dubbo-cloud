/*
 Navicat Premium Data Transfer

 Source Server         : 本机
 Source Server Type    : MySQL
 Source Server Version : 50730
 Source Host           : localhost:3306
 Source Schema         : dcy-cloud

 Target Server Type    : MySQL
 Target Server Version : 50730
 File Encoding         : 65001

 Date: 25/08/2020 09:13:26
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for oauth_client_details
-- ----------------------------
DROP TABLE IF EXISTS `oauth_client_details`;
CREATE TABLE `oauth_client_details`  (
  `client_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `resource_ids` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `client_secret` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `scope` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `authorized_grant_types` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `web_server_redirect_uri` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `authorities` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `access_token_validity` int(11) NULL DEFAULT NULL,
  `refresh_token_validity` int(11) NULL DEFAULT NULL,
  `additional_information` varchar(4096) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `autoapprove` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  PRIMARY KEY (`client_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = 'OAuth客户端表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of oauth_client_details
-- ----------------------------
INSERT INTO `oauth_client_details` VALUES ('dcy_admin_client', NULL, '{bcrypt}$2a$10$Z/wu.FZ22lY8TCDkPkdgmekhZn2zKJMxic2P50lf4Aajomxq1NNIa', 'dcy_admin', 'password,authorization_code,refresh_token,implicit', NULL, NULL, NULL, NULL, NULL, 'true');

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict`  (
  `dict_id` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '字典主键',
  `parent_id` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '父级id',
  `parent_ids` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '父级ids',
  `dict_type` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '字典类型',
  `dict_lable` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '字典名称',
  `dict_value` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '字典键值',
  `location` decimal(20, 2) NULL DEFAULT NULL COMMENT '排序',
  `has_children` tinyint(11) NULL DEFAULT NULL COMMENT '子节点',
  `type` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '子类型',
  `create_by` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `create_date` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `update_date` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新时间',
  `del_flag` tinyint(11) NULL DEFAULT 0 COMMENT '删除标识',
  `remark` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`dict_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '字典类型表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
INSERT INTO `sys_dict` VALUES ('1', '0', '0', 'sex_group', '性别', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL);
INSERT INTO `sys_dict` VALUES ('10', '0', '0', 'menu_type_group', '权限类型', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL);
INSERT INTO `sys_dict` VALUES ('11', '10', '0,10', 'menu_type_group', '模块权限', '0', 0.00, NULL, 'menu_type', NULL, NULL, NULL, NULL, 0, NULL);
INSERT INTO `sys_dict` VALUES ('12', '10', '0,10', 'menu_type_group', '菜单权限', '1', 1.00, NULL, 'menu_type', NULL, NULL, NULL, NULL, 0, NULL);
INSERT INTO `sys_dict` VALUES ('13', '0', '0', 'module_type_group', '模块类型', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL);
INSERT INTO `sys_dict` VALUES ('14', '13', '0,13', 'module_type_group', '模块', '0', 0.00, NULL, 'module_type', NULL, NULL, NULL, NULL, 0, NULL);
INSERT INTO `sys_dict` VALUES ('15', '13', '0,13', 'module_type_group', '操作', '1', 1.00, NULL, 'module_type', NULL, NULL, NULL, NULL, 0, NULL);
INSERT INTO `sys_dict` VALUES ('16', '0', '0', 'http_method_group', '请求方式', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL);
INSERT INTO `sys_dict` VALUES ('17', '16', '0,16', 'http_method_group', 'GET', 'GET', 0.00, NULL, 'http_method', NULL, NULL, NULL, NULL, 0, NULL);
INSERT INTO `sys_dict` VALUES ('18', '16', '0,16', 'http_method_group', 'POST', 'POST', 1.00, NULL, 'http_method', NULL, NULL, NULL, NULL, 0, NULL);
INSERT INTO `sys_dict` VALUES ('2', '1', '0,1', 'sex_group', '男', '0', 0.00, NULL, 'sex', NULL, NULL, NULL, NULL, 0, NULL);
INSERT INTO `sys_dict` VALUES ('3', '1', '0,1', 'sex_group', '女', '1', 1.00, NULL, 'sex', NULL, NULL, NULL, NULL, 0, NULL);
INSERT INTO `sys_dict` VALUES ('4', '0', '0', 'status_group', '状态', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL);
INSERT INTO `sys_dict` VALUES ('5', '4', '0,4', 'status_group', '正常', '0', 0.00, NULL, 'status', NULL, NULL, NULL, NULL, 0, NULL);
INSERT INTO `sys_dict` VALUES ('6', '4', '0,4', 'status_group', '禁用', '1', 1.00, NULL, 'status', NULL, NULL, NULL, NULL, 0, NULL);
INSERT INTO `sys_dict` VALUES ('7', '0', '0', 'power_type_group', '菜单类型', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL);
INSERT INTO `sys_dict` VALUES ('8', '7', '0,7', 'power_type_group', '模块', '0', 0.00, NULL, 'power_type', NULL, NULL, NULL, NULL, 0, NULL);
INSERT INTO `sys_dict` VALUES ('9', '7', '0,7', 'power_type_group', '菜单', '1', 1.00, NULL, 'power_type', NULL, NULL, NULL, NULL, 0, NULL);

-- ----------------------------
-- Table structure for sys_resources
-- ----------------------------
DROP TABLE IF EXISTS `sys_resources`;
CREATE TABLE `sys_resources`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键id',
  `parent_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '父级id',
  `parent_ids` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '父级ids',
  `res_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '资源名称',
  `res_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '资源code',
  `res_path` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '资源path',
  `http_method` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '请求方式',
  `res_status` tinyint(1) NULL DEFAULT NULL COMMENT '状态（0、正常；1、禁用）',
  `res_type` tinyint(1) NULL DEFAULT NULL COMMENT '类型（0、模块；1、链接）',
  `res_sort` decimal(10, 2) NULL DEFAULT NULL COMMENT '排序',
  `create_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_date` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `del_flag` tinyint(1) NULL DEFAULT 0 COMMENT '删除标识（0：正常；1：已删除）',
  `remark` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '资源表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_resources
-- ----------------------------
INSERT INTO `sys_resources` VALUES ('1', '0', '0', '用户管理', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL);
INSERT INTO `sys_resources` VALUES ('2', '1', '0,1', '用户查询', 'user:search', '/auth-biz/getUserInfo', 'GET', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL);
INSERT INTO `sys_resources` VALUES ('3', '1', '0,1', '用户添加', 'user:add', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL);
INSERT INTO `sys_resources` VALUES ('4', '1', '0,1', '用户删除', 'user:delete', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL);
INSERT INTO `sys_resources` VALUES ('5', '1', '0,1', '用户修改', 'user:update', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色id',
  `role_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色名称',
  `role_key` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色权限字符串',
  `role_status` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色状态（0、正常；1、禁用）',
  `create_by` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `update_date` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `del_flag` tinyint(11) NULL DEFAULT 0 COMMENT '删除标识',
  `remark` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1171709223680184321', '管理员', 'ROLE_ADMIN', '0', NULL, NULL, '1170896100656156674', NULL, 0, '备注');
INSERT INTO `sys_role` VALUES ('1171953892250918913', '开发组长', 'ROLE_DEVELOP', '0', NULL, NULL, NULL, NULL, 0, NULL);
INSERT INTO `sys_role` VALUES ('1171953965877731330', '测试组长', 'ROLE_TEST', '0', NULL, NULL, NULL, NULL, 0, NULL);
INSERT INTO `sys_role` VALUES ('1171954063797952514', '项目经理', 'ROLE_MANAGE', '1', NULL, NULL, NULL, NULL, 0, NULL);

-- ----------------------------
-- Table structure for sys_role_res
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_res`;
CREATE TABLE `sys_role_res`  (
  `role_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色id',
  `res_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '资源id'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色和资源关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_res
-- ----------------------------
INSERT INTO `sys_role_res` VALUES ('1171709223680184321', '1');
INSERT INTO `sys_role_res` VALUES ('1171709223680184321', '2');
INSERT INTO `sys_role_res` VALUES ('1171709223680184321', '3');
INSERT INTO `sys_role_res` VALUES ('1171709223680184321', '4');
INSERT INTO `sys_role_res` VALUES ('1171709223680184321', '5');

-- ----------------------------
-- Table structure for sys_user_info
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_info`;
CREATE TABLE `sys_user_info`  (
  `id` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户id',
  `username` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `nick_name` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户昵称',
  `user_type` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1' COMMENT '用户类型（0、管理员；1、普通用户）',
  `email` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户邮箱',
  `phone_number` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号码',
  `sex` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '性别（0、男；1、女）',
  `avatar_path` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像',
  `user_status` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '帐号状态（0、正常；1、禁用）',
  `create_by` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `update_date` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `del_flag` tinyint(11) NULL DEFAULT 0 COMMENT '删除标识',
  `remark` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_info
-- ----------------------------
INSERT INTO `sys_user_info` VALUES ('1170896100656156674', 'admin', '{bcrypt}$2a$10$I3nz8bGJfgpKcZbUSJnc8.PQxAYXdJP6r.eLHzdsfBLsCOx8JSB76', '管理员', '0', '13223423@qq.com', '15988888888', '0', NULL, '0', NULL, NULL, NULL, NULL, 0, '管理员');
INSERT INTO `sys_user_info` VALUES ('1171948965562806274', '1234567', '{bcrypt}$2a$10$O2YavjwTheFjryKJSrZGv.aixXnZ1K6GgtCAxEMg5KXSC6gvCDKSy', 'dd', '1', 'dsfa', '112312', '0', NULL, '0', '1170896100656156674', NULL, '1170896100656156674', NULL, 0, 'dd');
INSERT INTO `sys_user_info` VALUES ('1173480636380426241', '123456', '{bcrypt}$2a$10$h9ft5OlLp/4tPpkwbmCCpOwT0.sSbgjULmsK8WkR7DgsxdG6lXlTS', '121', '1', '11114', '121212121', '0', NULL, '0', '1170896100656156674', NULL, '1170896100656156674', NULL, 1, '12121');
INSERT INTO `sys_user_info` VALUES ('1235106131987161090', 'zfling', '{bcrypt}$2a$10$vT26khPHKjrGJDThLKsuF./A0208diONAJIwHiZXZ7hEDPepLGY2K', 'luoluo', '1', NULL, '15505050606', '1', NULL, '0', '1170896100656156674', NULL, NULL, NULL, 1, NULL);
INSERT INTO `sys_user_info` VALUES ('1235107352181497857', 'zfling', '{bcrypt}$2a$10$6QkpHjUCNX3OGOS.IOGnNefns0XxtZyITJecTuXTdHJom22lboDfC', 'aa', '1', NULL, '15544446666', '1', NULL, '0', '1170896100656156674', NULL, NULL, NULL, 1, NULL);
INSERT INTO `sys_user_info` VALUES ('1235107541667569666', 'lilili', '{bcrypt}$2a$10$VaN1kyubG1vCGVTVVbPieuBydVi.nmg4a15NzhummLMDgAHXp0bmm', 'lili', '1', NULL, '13544456666', '1', NULL, '0', '1170896100656156674', NULL, NULL, NULL, 1, NULL);
INSERT INTO `sys_user_info` VALUES ('1235107749109456897', 'ahssadj', '{bcrypt}$2a$10$sjiDwWlW9171ZrjvktI/oOZHi3TihK0KZfP.ivIgxNJJyci55tOcK', 'aa', '1', NULL, '13344456666', '1', NULL, '0', '1170896100656156674', NULL, NULL, NULL, 1, NULL);
INSERT INTO `sys_user_info` VALUES ('1242656635629875202', '111111', '{bcrypt}$2a$10$9oaLnmkTEWX.Hue3.xX6ieh02UAqYDUlu2vJZpPyAP/gu8KrFbEI6', '111', '1', NULL, '1245645646', '0', NULL, '0', '1170896100656156674', NULL, NULL, NULL, 0, NULL);
INSERT INTO `sys_user_info` VALUES ('1247436911227883521', '132435人3', '{bcrypt}$2a$10$2bShe9iUUpJudWNellWcUuA2av57BFmNvgWaXotLJAOLqUJerh0HO', 'dsada', '1', NULL, '17519469258', '0', NULL, '0', NULL, NULL, NULL, NULL, 1, NULL);
INSERT INTO `sys_user_info` VALUES ('1247437269933150210', 'sddfsdfsdfzs', '{bcrypt}$2a$10$ciGEdcKKAOrIFweCODh/a.jVTznwiZkQ7oJC10RtcZ.SiF4QOvIkK', 'asdsadad', '1', NULL, '17519469258', '0', NULL, '0', NULL, NULL, NULL, NULL, 0, NULL);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `user_id` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户id',
  `role_id` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色id'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户角色关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('1170896100656156674', '1171709223680184321');
INSERT INTO `sys_user_role` VALUES ('1171948965562806274', '1171709223680184321');
INSERT INTO `sys_user_role` VALUES ('1171948965562806274', '1171953965877731330');

SET FOREIGN_KEY_CHECKS = 1;
