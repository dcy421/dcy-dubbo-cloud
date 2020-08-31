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

 Date: 26/08/2020 15:49:41
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
  `id` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '字典主键',
  `parent_id` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '父级id',
  `parent_ids` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '父级ids',
  `dict_type` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '字典类型',
  `dict_label` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '字典名称',
  `dict_value` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '字典键值',
  `dict_sort` decimal(20, 2) NULL DEFAULT NULL COMMENT '排序',
  `type` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '子类型',
  `create_by` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `create_date` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `update_date` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新时间',
  `del_flag` tinyint(11) NULL DEFAULT 0 COMMENT '删除标识',
  `remark` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '字典类型表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
INSERT INTO `sys_dict` VALUES ('1', '0', '0', 'sex_group', '性别', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL);
INSERT INTO `sys_dict` VALUES ('10', '0', '0', 'menu_type_group', '权限类型', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL);
INSERT INTO `sys_dict` VALUES ('11', '10', '0,10', 'menu_type_group', '模块权限', '0', 0.00, 'menu_type', NULL, NULL, NULL, NULL, 0, NULL);
INSERT INTO `sys_dict` VALUES ('12', '10', '0,10', 'menu_type_group', '菜单权限', '1', 1.00, 'menu_type', NULL, NULL, NULL, NULL, 0, NULL);
INSERT INTO `sys_dict` VALUES ('13', '0', '0', 'module_type_group', '模块类型', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL);
INSERT INTO `sys_dict` VALUES ('14', '13', '0,13', 'module_type_group', '模块', '0', 0.00, 'module_type', NULL, NULL, NULL, NULL, 0, NULL);
INSERT INTO `sys_dict` VALUES ('15', '13', '0,13', 'module_type_group', '操作', '1', 1.00, 'module_type', NULL, NULL, NULL, NULL, 0, NULL);
INSERT INTO `sys_dict` VALUES ('16', '0', '0', 'http_method_group', '请求方式', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL);
INSERT INTO `sys_dict` VALUES ('17', '16', '0,16', 'http_method_group', 'GET', 'GET', 0.00, 'http_method', NULL, NULL, NULL, NULL, 0, NULL);
INSERT INTO `sys_dict` VALUES ('18', '16', '0,16', 'http_method_group', 'POST', 'POST', 1.00, 'http_method', NULL, NULL, NULL, NULL, 0, NULL);
INSERT INTO `sys_dict` VALUES ('2', '1', '0,1', 'sex_group', '男', '0', 0.00, 'sex', NULL, NULL, NULL, NULL, 0, NULL);
INSERT INTO `sys_dict` VALUES ('3', '1', '0,1', 'sex_group', '女', '1', 1.00, 'sex', NULL, NULL, NULL, NULL, 0, NULL);
INSERT INTO `sys_dict` VALUES ('4', '0', '0', 'status_group', '状态', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL);
INSERT INTO `sys_dict` VALUES ('5', '4', '0,4', 'status_group', '正常', '0', 0.00, 'status', NULL, NULL, NULL, NULL, 0, NULL);
INSERT INTO `sys_dict` VALUES ('6', '4', '0,4', 'status_group', '禁用', '1', 1.00, 'status', NULL, NULL, NULL, NULL, 0, NULL);
INSERT INTO `sys_dict` VALUES ('7', '0', '0', 'power_type_group', '菜单类型', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL);
INSERT INTO `sys_dict` VALUES ('8', '7', '0,7', 'power_type_group', '模块', '0', 0.00, 'power_type', NULL, NULL, NULL, NULL, 0, NULL);
INSERT INTO `sys_dict` VALUES ('9', '7', '0,7', 'power_type_group', '菜单', '1', 1.00, 'power_type', NULL, NULL, NULL, NULL, 0, NULL);

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
INSERT INTO `sys_resources` VALUES ('1', '0', '0', '全部', 'ALL', NULL, NULL, 0, 0, NULL, NULL, NULL, NULL, NULL, 0, NULL);
INSERT INTO `sys_resources` VALUES ('1173416959543459841', '1', '0,1', '系统模块', NULL, NULL, NULL, 0, 0, 100.00, NULL, NULL, NULL, NULL, 0, NULL);
INSERT INTO `sys_resources` VALUES ('1173417638056017922', '1173416959543459841', '0,1,1173416959543459841', '系统管理', NULL, NULL, NULL, 0, 0, 100.00, NULL, NULL, NULL, NULL, 0, NULL);
INSERT INTO `sys_resources` VALUES ('1173417760584220674', '1173417638056017922', '0,1,1173416959543459841,1173417638056017922', '用户管理', NULL, NULL, NULL, 0, 0, 10.00, NULL, NULL, NULL, NULL, 0, NULL);
INSERT INTO `sys_resources` VALUES ('1173494683788189698', '1173417760584220674', '0,1,1173416959543459841,1173417638056017922,1173417760584220674', '添加用户', 'user:save', '/admin-center/user/save', 'POST', 0, 1, 2.00, NULL, NULL, NULL, NULL, 0, NULL);
INSERT INTO `sys_resources` VALUES ('1173519815223037953', '1173417760584220674', '0,1,1173416959543459841,1173417638056017922,1173417760584220674', '修改用户', 'user:update', '/admin-center/user/update', 'POST', 0, 1, 5.00, NULL, NULL, NULL, NULL, 0, NULL);
INSERT INTO `sys_resources` VALUES ('1173519895434907649', '1173417760584220674', '0,1,1173416959543459841,1173417638056017922,1173417760584220674', '删除用户', 'user:delete', '/admin-center/user/delete', 'POST', 0, 1, 7.00, NULL, NULL, NULL, NULL, 0, NULL);
INSERT INTO `sys_resources` VALUES ('1173519984786165761', '1173417760584220674', '0,1,1173416959543459841,1173417638056017922,1173417760584220674', '批量删除用户', 'user:batch:delete', '/admin-center/user/deleteBatch', 'POST', 0, 1, 12.00, NULL, NULL, NULL, NULL, 0, NULL);
INSERT INTO `sys_resources` VALUES ('1173520061013446658', '1173417760584220674', '0,1,1173416959543459841,1173417638056017922,1173417760584220674', '重置密码', 'user:reset:pass', '/admin-center/user/resetPassword', 'POST', 0, 1, 13.00, NULL, NULL, NULL, NULL, 0, NULL);
INSERT INTO `sys_resources` VALUES ('1173520174125436930', '1173417760584220674', '0,1,1173416959543459841,1173417638056017922,1173417760584220674', '根据用户id查询已授权的角色列表', 'user:auth:role', '/admin-center/user/getAuthRoleListByUserId', 'GET', 0, 1, 15.00, NULL, NULL, NULL, NULL, 0, NULL);
INSERT INTO `sys_resources` VALUES ('1173520251158024193', '1173417760584220674', '0,1,1173416959543459841,1173417638056017922,1173417760584220674', '保存授权角色', 'user:auth:role', '/admin-center/user/saveAuthRole', 'POST', 0, 1, 15.00, NULL, NULL, NULL, NULL, 0, NULL);
INSERT INTO `sys_resources` VALUES ('1173520583535644673', '1173417760584220674', '0,1,1173416959543459841,1173417638056017922,1173417760584220674', '根据用户名获取用户信息', 'user:save', '/admin-center/user/getUserInfoByUsername', 'GET', 0, 1, 23.00, NULL, NULL, NULL, NULL, 0, NULL);
INSERT INTO `sys_resources` VALUES ('1173521725967654914', '1173417760584220674', '0,1,1173416959543459841,1173417638056017922,1173417760584220674', '获取用户分页信息', 'user:list', '/admin-center/user/page', 'GET', 0, 1, 1.00, NULL, NULL, NULL, NULL, 0, NULL);
INSERT INTO `sys_resources` VALUES ('1173786240433037313', '1173417638056017922', '0,1,1173416959543459841,1173417638056017922', '角色管理', NULL, NULL, NULL, 0, 0, 20.00, NULL, NULL, NULL, NULL, 0, NULL);
INSERT INTO `sys_resources` VALUES ('1173786513016659970', '1173786240433037313', '0,1,1173416959543459841,1173417638056017922,1173786240433037313', '查询所有角色', 'role:list', '/admin-center/role/all', 'GET', 0, 1, 5.00, NULL, NULL, NULL, NULL, 0, NULL);
INSERT INTO `sys_resources` VALUES ('1173786896229244930', '1173786240433037313', '0,1,1173416959543459841,1173417638056017922,1173786240433037313', '获取角色分页信息', 'role:list', '/admin-center/role/page', 'GET', 0, 1, 3.00, NULL, NULL, NULL, NULL, 0, NULL);
INSERT INTO `sys_resources` VALUES ('1173787141281456130', '1173786240433037313', '0,1,1173416959543459841,1173417638056017922,1173786240433037313', '添加角色', 'role:save', '/admin-center/role/save', 'POST', 0, 1, 7.00, NULL, NULL, NULL, NULL, 0, NULL);
INSERT INTO `sys_resources` VALUES ('1173787273578192898', '1173786240433037313', '0,1,1173416959543459841,1173417638056017922,1173786240433037313', '修改角色', 'role:update', '/admin-center/role/update', 'POST', 0, 1, 10.00, NULL, NULL, NULL, NULL, 0, NULL);
INSERT INTO `sys_resources` VALUES ('1173787371326447617', '1173786240433037313', '0,1,1173416959543459841,1173417638056017922,1173786240433037313', '删除角色', 'role:delete', '/admin-center/role/delete', 'POST', 0, 1, 15.00, NULL, NULL, NULL, NULL, 0, NULL);
INSERT INTO `sys_resources` VALUES ('1173787467954823170', '1173786240433037313', '0,1,1173416959543459841,1173417638056017922,1173786240433037313', '批量删除角色', 'role:batch:delete', '/admin-center/role/deleteBatch', 'POST', 0, 1, 17.00, NULL, NULL, NULL, NULL, 0, NULL);
INSERT INTO `sys_resources` VALUES ('1173787618282872833', '1173786240433037313', '0,1,1173416959543459841,1173417638056017922,1173786240433037313', '根据角色id查询已授权的权限列表', 'role:auth:resource', '/admin-center/role/getAuthResourceListByRoleId', 'GET', 0, 1, 20.00, NULL, NULL, NULL, NULL, 0, NULL);
INSERT INTO `sys_resources` VALUES ('1173787686142517250', '1173786240433037313', '0,1,1173416959543459841,1173417638056017922,1173786240433037313', '保存授权权限', 'role:auth:resource', '/admin-center/role/saveAuthResource', 'POST', 0, 1, 25.00, NULL, NULL, NULL, NULL, 0, NULL);
INSERT INTO `sys_resources` VALUES ('1173792738055856130', '1173417638056017922', '0,1,1173416959543459841,1173417638056017922', '资源管理', NULL, NULL, NULL, 0, 0, 60.00, NULL, NULL, NULL, NULL, 0, NULL);
INSERT INTO `sys_resources` VALUES ('1173793141136859137', '1173792738055856130', '0,1,1173416959543459841,1173417638056017922,1173792738055856130', '添加资源', 'module:add', '/admin-center/resources/save', 'POST', 0, 1, 7.00, NULL, NULL, NULL, NULL, 0, NULL);
INSERT INTO `sys_resources` VALUES ('1173793218580488194', '1173792738055856130', '0,1,1173416959543459841,1173417638056017922,1173792738055856130', '修改资源', 'module:update', '/admin-center/resources/update', 'POST', 0, 1, 9.00, NULL, NULL, NULL, NULL, 0, NULL);
INSERT INTO `sys_resources` VALUES ('1173793287136387073', '1173792738055856130', '0,1,1173416959543459841,1173417638056017922,1173792738055856130', '删除资源', 'module:delete', '/admin-center/resources/delete', 'POST', 0, 1, 11.00, NULL, NULL, NULL, NULL, 0, NULL);
INSERT INTO `sys_resources` VALUES ('1173793449908936705', '1173792738055856130', '0,1,1173416959543459841,1173417638056017922,1173792738055856130', '获取资源tree-table数据', 'module:list', '/admin-center/resources/getResourceTreeTableList', 'GET', 0, 1, 15.00, NULL, NULL, NULL, NULL, 0, NULL);
INSERT INTO `sys_resources` VALUES ('1173793663180906497', '1173792738055856130', '0,1,1173416959543459841,1173417638056017922,1173792738055856130', '根据权限id，查询资源tree列表数据', 'module:list', '/admin-center/resources/getResourceTreeListByRoleId', 'GET', 0, 1, 18.00, NULL, NULL, NULL, NULL, 0, NULL);
INSERT INTO `sys_resources` VALUES ('1208938290946772993', '1173417638056017922', '0,1,1173416959543459841,1173417638056017922', '字典管理', NULL, NULL, NULL, 0, 0, 70.00, NULL, NULL, NULL, NULL, 0, NULL);
INSERT INTO `sys_resources` VALUES ('1208938414578077698', '1208938290946772993', '0,1,1173416959543459841,1173417638056017922,1208938290946772993', '获取tree-table字典列表数据', 'dict:list', '/admin-center/dict/getDictTreeTableList', 'GET', 0, 1, 5.00, NULL, NULL, NULL, NULL, 0, NULL);
INSERT INTO `sys_resources` VALUES ('1208938659106000810', '1208938290946772993', '0,1,1173416959543459841,1173417638056017922,1208938290946772993', '修改字典', 'dict:update', '/admin-center/dict/update', 'POST', 0, 1, 15.00, NULL, NULL, NULL, NULL, 0, NULL);
INSERT INTO `sys_resources` VALUES ('1208938659106000811', '1208938290946772993', '0,1,1173416959543459841,1173417638056017922,1208938290946772993', '删除字典', 'dict:delete', '/admin-center/dict/delete', 'POST', 0, 1, 20.00, NULL, NULL, NULL, NULL, 0, NULL);
INSERT INTO `sys_resources` VALUES ('1208938659106000898', '1208938290946772993', '0,1,1173416959543459841,1173417638056017922,1208938290946772993', '添加字典', 'dict:save', '/admin-center/dict/save', 'POST', 0, 1, 10.00, NULL, NULL, NULL, NULL, 0, NULL);

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
INSERT INTO `sys_role_res` VALUES ('1171709223680184321', '1173416959543459841');
INSERT INTO `sys_role_res` VALUES ('1171709223680184321', '1173417638056017922');
INSERT INTO `sys_role_res` VALUES ('1171709223680184321', '1173417760584220674');
INSERT INTO `sys_role_res` VALUES ('1171709223680184321', '1173521725967654914');
INSERT INTO `sys_role_res` VALUES ('1171709223680184321', '1173494683788189698');
INSERT INTO `sys_role_res` VALUES ('1171709223680184321', '1173519815223037953');
INSERT INTO `sys_role_res` VALUES ('1171709223680184321', '1173519895434907649');
INSERT INTO `sys_role_res` VALUES ('1171709223680184321', '1173519984786165761');
INSERT INTO `sys_role_res` VALUES ('1171709223680184321', '1173520061013446658');
INSERT INTO `sys_role_res` VALUES ('1171709223680184321', '1173520174125436930');
INSERT INTO `sys_role_res` VALUES ('1171709223680184321', '1173520251158024193');
INSERT INTO `sys_role_res` VALUES ('1171709223680184321', '1173520583535644673');
INSERT INTO `sys_role_res` VALUES ('1171709223680184321', '1173786240433037313');
INSERT INTO `sys_role_res` VALUES ('1171709223680184321', '1173786896229244930');
INSERT INTO `sys_role_res` VALUES ('1171709223680184321', '1173786513016659970');
INSERT INTO `sys_role_res` VALUES ('1171709223680184321', '1173787141281456130');
INSERT INTO `sys_role_res` VALUES ('1171709223680184321', '1173787273578192898');
INSERT INTO `sys_role_res` VALUES ('1171709223680184321', '1173787371326447617');
INSERT INTO `sys_role_res` VALUES ('1171709223680184321', '1173787467954823170');
INSERT INTO `sys_role_res` VALUES ('1171709223680184321', '1173787618282872833');
INSERT INTO `sys_role_res` VALUES ('1171709223680184321', '1173787686142517250');
INSERT INTO `sys_role_res` VALUES ('1171709223680184321', '1173792738055856130');
INSERT INTO `sys_role_res` VALUES ('1171709223680184321', '1173793141136859137');
INSERT INTO `sys_role_res` VALUES ('1171709223680184321', '1173793218580488194');
INSERT INTO `sys_role_res` VALUES ('1171709223680184321', '1173793287136387073');
INSERT INTO `sys_role_res` VALUES ('1171709223680184321', '1173793449908936705');
INSERT INTO `sys_role_res` VALUES ('1171709223680184321', '1173793663180906497');
INSERT INTO `sys_role_res` VALUES ('1171709223680184321', '1208938290946772993');
INSERT INTO `sys_role_res` VALUES ('1171709223680184321', '1208938414578077698');
INSERT INTO `sys_role_res` VALUES ('1171709223680184321', '1208938659106000898');
INSERT INTO `sys_role_res` VALUES ('1171709223680184321', '1208938659106000810');
INSERT INTO `sys_role_res` VALUES ('1171709223680184321', '1208938659106000811');

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
INSERT INTO `sys_user_info` VALUES ('1298494547157360642', '121212121', '{bcrypt}$2a$10$XryvgFQCGnLn88wV13lm.ezWYcxlPd8VsRWdYa98lqCLwLCya4Aaa', '千千万万1', '1', 'ddasfsda@qq', '154123123213', '0', NULL, '0', NULL, '2020-08-25 13:36:40', NULL, NULL, 0, 'ddd');

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
