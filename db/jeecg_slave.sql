/*
 Navicat Premium Data Transfer

 Source Server         : mac_mysql
 Source Server Type    : MySQL
 Source Server Version : 50721
 Source Host           : localhost
 Source Database       : jeecg_slave

 Target Server Type    : MySQL
 Target Server Version : 50721
 File Encoding         : utf-8

 Date: 07/22/2020 09:22:01 AM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `cs_teacher`
-- ----------------------------
DROP TABLE IF EXISTS `cs_teacher`;
CREATE TABLE `cs_teacher` (
  `id` varchar(32) NOT NULL COMMENT 'id',
  `teachername` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `classid` varchar(30) DEFAULT NULL COMMENT '手机号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `cs_teacher`
-- ----------------------------
BEGIN;
INSERT INTO `cs_teacher` VALUES ('3f435f4598cf40a3acba045692a4b037', '王老师', '1');
COMMIT;

-- ----------------------------
--  Table structure for `cs_test`
-- ----------------------------
DROP TABLE IF EXISTS `cs_test`;
CREATE TABLE `cs_test` (
  `id` varchar(32) NOT NULL COMMENT 'id',
  `userid` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `score` varchar(30) DEFAULT NULL COMMENT '手机号',
  `classid` varchar(20) DEFAULT NULL COMMENT '办公座机',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_s_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_s_user`;
CREATE TABLE `t_s_user` (
  `id` varchar(32) NOT NULL COMMENT 'id',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `mobilePhone` varchar(30) DEFAULT NULL COMMENT '手机号',
  `officePhone` varchar(20) DEFAULT NULL COMMENT '办公座机',
  `signatureFile` varchar(100) DEFAULT NULL COMMENT '签名文件',
  `update_name` varchar(32) DEFAULT NULL COMMENT '修改人',
  `update_date` datetime DEFAULT NULL COMMENT '修改时间',
  `update_by` varchar(32) DEFAULT NULL COMMENT '修改人id',
  `create_name` varchar(32) DEFAULT NULL COMMENT '创建人',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建人id',
  `portrait` varchar(100) DEFAULT NULL,
  `imsign` varchar(255) DEFAULT NULL,
  `dev_flag` varchar(2) NOT NULL DEFAULT '0' COMMENT '开发权限标志',
  `user_type` varchar(2) DEFAULT NULL COMMENT '用户类型',
  `person_type` varchar(2) DEFAULT NULL COMMENT '人员类型',
  `sex` varchar(2) DEFAULT NULL COMMENT '性别',
  `emp_no` varchar(36) DEFAULT NULL COMMENT '工号',
  `citizen_no` varchar(20) DEFAULT NULL COMMENT '身份证号',
  `fax` varchar(50) DEFAULT NULL COMMENT '传真',
  `address` varchar(1000) DEFAULT NULL COMMENT '联系地址',
  `post` varchar(10) DEFAULT NULL COMMENT '邮编',
  `memo` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `t_s_user`
-- ----------------------------
BEGIN;
INSERT INTO `t_s_user` VALUES ('1', '1', '1', null, null, null, null, null, null, null, null, null, null, '0', null, null, null, null, null, null, null, null, null);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
