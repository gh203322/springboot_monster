/*
Navicat MySQL Data Transfer

Source Server         : study
Source Server Version : 50644
Source Host           : localhost:3306
Source Database       : ocean

Target Server Type    : MYSQL
Target Server Version : 50644
File Encoding         : 65001

Date: 2019-06-13 09:16:55
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `car`
-- ----------------------------
DROP TABLE IF EXISTS `car`;
CREATE TABLE `car` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `carNo` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '车牌号',
  `city` varchar(6) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '市编码',
  `latitude` double(9,6) DEFAULT NULL COMMENT '纬度',
  `longitude` double(9,6) DEFAULT NULL COMMENT '经度',
  `province` varchar(6) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '省编码',
  `userId` bigint(20) DEFAULT NULL,
  `signDate` date DEFAULT NULL COMMENT '车辆登记时间',
  PRIMARY KEY (`id`),
  KEY `FK91iver85cam9nynoknr5r7fkb` (`userId`),
  CONSTRAINT `FK91iver85cam9nynoknr5r7fkb` FOREIGN KEY (`userId`) REFERENCES `car_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='车辆表';

-- ----------------------------
-- Records of car
-- ----------------------------
INSERT INTO `car` VALUES ('11', '川A9999', '成都市', null, null, '四川省', '1', '2019-06-06');
INSERT INTO `car` VALUES ('12', '川A6666', '成都市', '1.000000', '1.000000', '四川省', '1', '2019-06-01');
INSERT INTO `car` VALUES ('16', '川A11111', '成都市', null, null, '四川省', '1', '2019-06-30');

-- ----------------------------
-- Table structure for `car_location`
-- ----------------------------
DROP TABLE IF EXISTS `car_location`;
CREATE TABLE `car_location` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `carNo` varchar(10) COLLATE utf8_unicode_ci NOT NULL COMMENT '车牌号',
  `createTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `createTimeStamp` int(11) NOT NULL COMMENT '创建时间时间戳秒',
  `latitude` double(9,6) DEFAULT NULL COMMENT '纬度',
  `longitude` double(9,6) DEFAULT NULL COMMENT '经度',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='车辆轨迹表';

-- ----------------------------
-- Records of car_location
-- ----------------------------

-- ----------------------------
-- Table structure for `car_user`
-- ----------------------------
DROP TABLE IF EXISTS `car_user`;
CREATE TABLE `car_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '用户名',
  `pass` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '密码',
  `phone` varchar(15) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '手机号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='车辆用户表';

-- ----------------------------
-- Records of car_user
-- ----------------------------
INSERT INTO `car_user` VALUES ('1', '唐英俊', '123456', '18700000000');

-- ----------------------------
-- Table structure for `hibernate_sequence`
-- ----------------------------
DROP TABLE IF EXISTS `hibernate_sequence`;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of hibernate_sequence
-- ----------------------------
INSERT INTO `hibernate_sequence` VALUES ('18');
INSERT INTO `hibernate_sequence` VALUES ('18');
INSERT INTO `hibernate_sequence` VALUES ('18');
INSERT INTO `hibernate_sequence` VALUES ('18');
INSERT INTO `hibernate_sequence` VALUES ('18');
INSERT INTO `hibernate_sequence` VALUES ('18');

-- ----------------------------
-- Table structure for `mp_menu_role`
-- ----------------------------
DROP TABLE IF EXISTS `mp_menu_role`;
CREATE TABLE `mp_menu_role` (
  `menuId` bigint(20) NOT NULL,
  `roleId` bigint(20) NOT NULL,
  KEY `FK8etqrdgu0f8m8pu1x92w6jgg6` (`roleId`),
  KEY `FKc0ocgpux4g4tsn6pwnpd1d3ii` (`menuId`),
  CONSTRAINT `FK8etqrdgu0f8m8pu1x92w6jgg6` FOREIGN KEY (`roleId`) REFERENCES `sys_role` (`id`),
  CONSTRAINT `FKc0ocgpux4g4tsn6pwnpd1d3ii` FOREIGN KEY (`menuId`) REFERENCES `sys_menu` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of mp_menu_role
-- ----------------------------

-- ----------------------------
-- Table structure for `mp_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `mp_user_role`;
CREATE TABLE `mp_user_role` (
  `userId` bigint(20) NOT NULL,
  `roleId` bigint(20) NOT NULL,
  KEY `FKirr3f7n3slskqybn572im1pu` (`roleId`),
  KEY `FK1016g1ng85q078wssermch64e` (`userId`),
  CONSTRAINT `FK1016g1ng85q078wssermch64e` FOREIGN KEY (`userId`) REFERENCES `sys_user` (`id`),
  CONSTRAINT `FKirr3f7n3slskqybn572im1pu` FOREIGN KEY (`roleId`) REFERENCES `sys_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of mp_user_role
-- ----------------------------

-- ----------------------------
-- Table structure for `sys_menu`
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `css` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '菜单class样式',
  `href` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '对应的html页面',
  `isLeaf` int(1) DEFAULT '0' COMMENT '是否是叶子节点',
  `isOpen` int(1) DEFAULT '0' COMMENT '是否默认展开',
  `isShow` int(1) DEFAULT '1' COMMENT '是否默认显示',
  `name` varchar(15) COLLATE utf8_unicode_ci NOT NULL COMMENT '菜单名称',
  `sort` int(3) DEFAULT '0' COMMENT '自定义排序',
  `parentId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKon6ax23cdghjmhc9un3oxc9sc` (`parentId`),
  CONSTRAINT `FKon6ax23cdghjmhc9un3oxc9sc` FOREIGN KEY (`parentId`) REFERENCES `sys_menu` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='系统菜单表';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1', 'fa fa-laptop', null, '0', '0', '1', '系统管理', '0', null);
INSERT INTO `sys_menu` VALUES ('2', null, null, '1', '0', '1', '基础设置', '1', '1');
INSERT INTO `sys_menu` VALUES ('3', null, null, '1', '0', '1', '菜单管理', '2', '1');
INSERT INTO `sys_menu` VALUES ('4', null, null, '1', '0', '1', '角色管理', '3', '1');
INSERT INTO `sys_menu` VALUES ('5', null, null, '1', '0', '1', '权限管理', '4', '1');
INSERT INTO `sys_menu` VALUES ('6', null, null, '1', '0', '1', '用户管理', '5', '1');
INSERT INTO `sys_menu` VALUES ('7', null, null, '1', '0', '1', '个性化设置', '17', '1');
INSERT INTO `sys_menu` VALUES ('8', null, null, '0', '1', '1', '二级盒子示例', '7', '1');
INSERT INTO `sys_menu` VALUES ('9', null, null, '1', '0', '1', '二级盒子示例菜单', '8', '8');
INSERT INTO `sys_menu` VALUES ('10', 'fa fa-pie-chart', null, '0', '0', '1', '图表报表', '9', null);
INSERT INTO `sys_menu` VALUES ('11', null, null, '1', '0', '1', '系统日志报表', '10', '10');
INSERT INTO `sys_menu` VALUES ('12', null, null, '1', '0', '1', '系统运行报告', '11', '10');
INSERT INTO `sys_menu` VALUES ('13', null, null, '1', '0', '1', '系统操作记录', '12', '10');
INSERT INTO `sys_menu` VALUES ('14', null, null, '0', '1', '1', '车辆管理', '13', null);
INSERT INTO `sys_menu` VALUES ('15', null, 'car/view', '1', '0', '1', '车辆信息', '14', '14');
INSERT INTO `sys_menu` VALUES ('16', null, null, '1', '0', '1', '车辆用户', '15', '14');
INSERT INTO `sys_menu` VALUES ('17', null, null, '1', '0', '1', '车辆轨迹', '16', '14');
INSERT INTO `sys_menu` VALUES ('18', null, null, '1', '0', '1', '组织管理', '6', '1');
INSERT INTO `sys_menu` VALUES ('19', null, null, '0', '1', '1', '三级盒子示例', '18', '8');
INSERT INTO `sys_menu` VALUES ('20', null, null, '1', '0', '1', '三级盒子示例菜单', '19', '19');

-- ----------------------------
-- Table structure for `sys_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(15) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '角色编码',
  `name` varchar(15) COLLATE utf8_unicode_ci NOT NULL COMMENT '角色名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='系统角色表';

-- ----------------------------
-- Records of sys_role
-- ----------------------------

-- ----------------------------
-- Table structure for `sys_user`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '用户名',
  `pass` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '密码',
  `phone` varchar(15) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '手机号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='系统用户表';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
