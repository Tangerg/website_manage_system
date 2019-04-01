/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 50722
 Source Host           : localhost:3306
 Source Schema         : website_manage_system

 Target Server Type    : MySQL
 Target Server Version : 50722
 File Encoding         : 65001

 Date: 01/04/2019 21:36:09
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for application
-- ----------------------------
DROP TABLE IF EXISTS `application`;
CREATE TABLE `application`  (
  `application_id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '申请表唯一标识',
  `application_department_id` int(5) UNSIGNED NOT NULL COMMENT '申请部门编号，与部门表相关联',
  `application_record_operate` tinyint(4) UNSIGNED NOT NULL DEFAULT 1 COMMENT '备案的操作（1：网站申请； 2：网站取消 ；3：网站变更 ；4：域名申请 ；5：域名取消 ；6：域名变更 ；7：端口开放 ；8：端口关闭）',
  `application_department_director` int(5) UNSIGNED NOT NULL COMMENT '部门负责人工号',
  `application_office_phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '办公电话',
  `application_website_director_num` int(5) UNSIGNED NOT NULL COMMENT '网站负责人工号',
  `application_website_director_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '网站负责人姓名',
  `application_website_director_phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '网站负责人手机号',
  `application_website_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '网站名称',
  `application_website_name_old` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '网站之前的名称',
  `application_website_domain_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '网站域名',
  `application_website_domain_name_old` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '网站之前的域名',
  `application_website_ip` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '网站IP',
  `application_website_ip_old` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '网站之前的IP',
  `application_visit_range` tinyint(1) UNSIGNED NOT NULL DEFAULT 1 COMMENT '访问范围 1：校内 0：校外',
  `application_service_content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '网站服务内容',
  `application_website_space_type` tinyint(1) NOT NULL COMMENT 'type :  1:自有服务器 ；2：网络中心分配 ；3：其他要求',
  `application_website_space_campus` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '服务器校区',
  `application_website_space_building` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '楼',
  `application_website_space_room` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '房间',
  `application_website_port_in` int(5) UNSIGNED NOT NULL DEFAULT 80 COMMENT '开放端口 校内默认80',
  `application_website_port_out` int(5) UNSIGNED NOT NULL DEFAULT 0 COMMENT '校外端口',
  `application_website_type` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '1' COMMENT '1 静态html；2：php；3：asp；4：jsp；5：其他',
  `application_website_database` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '1' COMMENT '1:无；2：MySQL；3：access；4：MSSQL；5:oracle;6:其他',
  `application_interactive_column` tinyint(1) UNSIGNED NOT NULL DEFAULT 1 COMMENT '交互栏目 1：无；2：用户实名注册 3：24小时监控',
  `application_safety_audit` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '安全审核 1：true 0:false',
  `application_submit_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '申请提交日期',
  `application_state` int(3) UNSIGNED NOT NULL COMMENT '申请状态：10草稿状态，申请人可修改和提交；20提交后审核，申请人只读取；30审核未通过 ，申请人可修改和重新提交；40审核通过，申请人只读',
  PRIMARY KEY (`application_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for count
-- ----------------------------
DROP TABLE IF EXISTS `count`;
CREATE TABLE `count`  (
  `count_id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `count_website` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '网站总数',
  `count_lan_html` int(10) UNSIGNED NOT NULL DEFAULT 0,
  `count_lan_php` int(10) UNSIGNED NOT NULL DEFAULT 0,
  `count_lan_asp` int(10) UNSIGNED NOT NULL DEFAULT 0,
  `count_lan_jsp` int(10) UNSIGNED NOT NULL DEFAULT 0,
  `count_lan_other` int(10) UNSIGNED NOT NULL DEFAULT 0,
  `count_db_no` int(10) UNSIGNED NOT NULL DEFAULT 0,
  `count_db_mysql` int(10) UNSIGNED NOT NULL DEFAULT 0,
  `count_db_access` int(10) UNSIGNED NOT NULL DEFAULT 0,
  `count_db_mssql` int(10) UNSIGNED NOT NULL DEFAULT 0,
  `count_db_oracle` int(10) UNSIGNED NOT NULL DEFAULT 0,
  `count_db_other` int(10) UNSIGNED NOT NULL DEFAULT 0,
  `count_bug_sql` int(10) UNSIGNED NOT NULL DEFAULT 0,
  `count_bug_xss` int(10) UNSIGNED NOT NULL DEFAULT 0,
  `count_bug_csrf` int(10) UNSIGNED NOT NULL DEFAULT 0,
  `count_bug_file_upload` int(10) UNSIGNED NOT NULL DEFAULT 0,
  `count_bug_jurisdiction` int(10) UNSIGNED NOT NULL DEFAULT 0,
  `count_bug_other` int(10) UNSIGNED NOT NULL DEFAULT 0,
  `count_department` int(10) UNSIGNED NOT NULL DEFAULT 0,
  `count_update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`count_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for department
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department`  (
  `department_id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '部门表唯一标识',
  `department_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '部门名称',
  `department_director` int(5) UNSIGNED NOT NULL COMMENT '部门负责人工号',
  `department_office_phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '部门办公电话',
  `department_total_people` int(5) UNSIGNED NOT NULL DEFAULT 0 COMMENT '部门总人数',
  `department_total_website` int(5) UNSIGNED NOT NULL DEFAULT 0 COMMENT '部门总网站数',
  PRIMARY KEY (`department_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for log
-- ----------------------------
DROP TABLE IF EXISTS `log`;
CREATE TABLE `log`  (
  `log_id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '日志id',
  `log_job_num` int(5) UNSIGNED NOT NULL COMMENT '用户工号',
  `log_ip` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '操作ip',
  `log_operate` tinyint(1) UNSIGNED NOT NULL COMMENT '操作类型1：登录 2：提交申请 3：审核通过 4：审核未通过',
  `log_additional` int(10) UNSIGNED NULL DEFAULT NULL COMMENT '如果是申请相关的则附加申请表id',
  `log_create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '日志创建时间',
  PRIMARY KEY (`log_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message`  (
  `message_id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '消息表id',
  `message_sender` int(5) UNSIGNED NOT NULL COMMENT '发送者工号',
  `message_receiver` int(5) UNSIGNED NOT NULL COMMENT '接收者工号',
  `message_type` tinyint(1) UNSIGNED NOT NULL DEFAULT 1 COMMENT '消息类型 1：提交申请；2：审核未通过；3：审核通过',
  `message_application` int(10) UNSIGNED NOT NULL COMMENT '申请表id',
  `message_additional` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '消息附加内容',
  `message_state` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '消息状态1：已读  0：未读',
  `message_is_del` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否软删除 1：是  0：否',
  `message_create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '消息创建时间',
  PRIMARY KEY (`message_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `user_id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '用户表唯一标识',
  `user_job_num` int(5) UNSIGNED NOT NULL COMMENT '用户工号',
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名称',
  `user_password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '123456' COMMENT '用户密码',
  `user_gender` tinyint(1) UNSIGNED NULL DEFAULT 1 COMMENT '性别 1：男 0:女',
  `user_department_id` int(5) UNSIGNED NULL DEFAULT NULL COMMENT '用户所属部门，与部门表相关联',
  `user_office_phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '办公电话',
  `user_tel` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机电话',
  `user_is_quit` tinyint(1) UNSIGNED NULL DEFAULT 0 COMMENT '是否离职 1：true 0:false',
  `user_roles` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '权限 0：普通用户；1：管理员',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for website
-- ----------------------------
DROP TABLE IF EXISTS `website`;
CREATE TABLE `website`  (
  `website_id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '网站唯一标识',
  `website_department_id` int(5) UNSIGNED NOT NULL COMMENT '网站所属，与部门表相关联',
  `website_director_num` int(5) UNSIGNED NOT NULL COMMENT '网站负责人工号',
  `website_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '网站名称',
  `website_domain_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '网站域名',
  `website_ip` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '网站IP',
  `website_visit_range` tinyint(1) UNSIGNED NOT NULL DEFAULT 1 COMMENT '访问范围 1：校内 0：校外',
  `website_service_content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '网站服务内容',
  `website_space_type` tinyint(1) NOT NULL COMMENT 'type :  1:自有服务器 ；2：网络中心分配 ；3：其他要求',
  `website_space_campus` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `website_space_building` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `website_space_room` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `website_port_in` int(5) NOT NULL DEFAULT 80 COMMENT '开放端口 in:校内默认80 ；',
  `website_port_out` int(5) NOT NULL DEFAULT 0 COMMENT 'out :校外，不开放',
  `website_type` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '1' COMMENT '1 静态html；2：php；3：asp；4：jsp；5：其他',
  `website_database` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '1' COMMENT '1:无；2：MySQL；3：access；4：MSSQL；5:oracle;6:其他',
  `website_interactive_column` tinyint(1) UNSIGNED NOT NULL DEFAULT 1 COMMENT '交互栏目 1：无；2：用户实名注册 3：24小时监控',
  `website_safety_audit` tinyint(1) UNSIGNED NOT NULL DEFAULT 1 COMMENT '安全审核 1：true 0:false',
  `website_is_open` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否开放 1：true 0:false',
  `website_is_rectify` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否整改 1：true 0:false',
  `website_is_notice` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否安全通报 1：true 0:false',
  `website_notice_type` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '1 SQL注入攻击；2：XSS攻击；3：CSRF攻击；4：文件上传漏洞；5：访问控制；6：其他',
  `website_create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`website_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
