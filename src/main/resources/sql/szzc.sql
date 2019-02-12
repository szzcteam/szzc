/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50527
Source Host           : 127.0.0.1:3306
Source Database       : szzc

Target Server Type    : MYSQL
Target Server Version : 50527
File Encoding         : 65001

Date: 2019-02-12 18:17:16
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for f_admin
-- ----------------------------
DROP TABLE IF EXISTS `f_admin`;
CREATE TABLE `f_admin` (
  `fid` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `fstatus` int(11) DEFAULT NULL COMMENT '状态：1正常；2禁用',
  `froleid` int(11) DEFAULT NULL COMMENT '角色ID',
  `fname` varchar(32) DEFAULT NULL COMMENT '登录名',
  `fpassword` varchar(32) DEFAULT NULL COMMENT '登录密码',
  `fcreatetime` datetime DEFAULT NULL COMMENT '注册时间',
  `fgoogleauthenticator` varchar(128) DEFAULT NULL COMMENT '谷歌密匙',
  `fgoogleurl` varchar(128) DEFAULT NULL COMMENT '谷歌URL',
  `fgooglebind` tinyint(1) DEFAULT '0' COMMENT '是否谷歌绑定',
  `fopengooglevalidate` tinyint(1) DEFAULT NULL COMMENT '是否谷歌验证',
  `fgooglevalidate` tinyint(1) DEFAULT NULL COMMENT '谷歌绑定时间',
  `version` int(11) DEFAULT NULL COMMENT '乐观锁版本',
  `salt` varchar(255) DEFAULT NULL COMMENT '盐值',
  `ftelephone` varchar(255) DEFAULT NULL COMMENT '电话',
  PRIMARY KEY (`fid`),
  KEY `frole_id` (`froleid`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='管理员';

-- ----------------------------
-- Records of f_admin
-- ----------------------------
INSERT INTO `f_admin` VALUES ('1', '2', '1', 'admin', 'VCn0T7sB2069GfpLtEnzjw==', '2016-10-28 16:50:27', null, null, '0', '0', '0', '11', '26003658957127681', null);
INSERT INTO `f_admin` VALUES ('2', '1', '1', 'goodvalue', 'QCNOdmJjtLxuvg5yci+1Hg==', '2018-06-14 09:36:04', null, null, '0', '0', '0', '1', '26003658957127682', null);
INSERT INTO `f_admin` VALUES ('3', '2', '1', 'ceshi', 'BZ04qMiI1RCfozqYFYZgEw==', '2018-08-01 15:42:45', null, null, '0', '0', '0', '1', '26003658957127683', null);
INSERT INTO `f_admin` VALUES ('4', '1', '1', 'fangxiaoming', 'k2CtWhUmVTHHA0mhtOicPg==', '2018-08-01 16:24:16', null, null, '0', '0', '0', '4', '26003658957127684', null);
INSERT INTO `f_admin` VALUES ('5', '1', '1', 'wangwei', '+Da/2vYMcbg6Kj9SEc3k9Q==', '2018-08-01 16:25:08', null, null, '0', '0', '0', '1', '26003658957127685', null);
INSERT INTO `f_admin` VALUES ('6', '1', '1', 'zhangyingdan', 'cxaqI5B7rxHfXKl3LZ8mBQ==', '2018-08-01 16:25:48', null, null, '0', '0', '0', '2', '26003658957127686', null);
INSERT INTO `f_admin` VALUES ('7', '1', '1', 'taojun', 'xXFQoqWUSpzMgtxghkBgLg==', '2018-08-01 16:26:24', null, null, '0', '0', '0', '0', '26003658957127687', null);
INSERT INTO `f_admin` VALUES ('8', '1', '1', 'xieyongliang', 'EhQZRrTHfusBb+KkZeVVag==', '2018-08-01 16:26:56', null, null, '0', '0', '0', '1', '26003658957127688', null);
INSERT INTO `f_admin` VALUES ('9', '1', '1', 'hujingxi', 'xXFQoqWUSpzMgtxghkBgLg==', '2018-08-01 16:27:16', null, null, '0', '0', '0', '0', '26003658957127689', null);
INSERT INTO `f_admin` VALUES ('10', '1', '1', 'luwen', 'xXFQoqWUSpzMgtxghkBgLg==', '2018-08-01 16:27:38', null, null, '0', '0', '0', '0', '26003658957127690', null);
INSERT INTO `f_admin` VALUES ('11', '1', '1', 'lipeng', '14AXV0Yh+DSSIqJ+n1t8jA==', '2018-08-01 16:28:01', null, null, '0', '0', '0', '1', '26003658957127691', null);
INSERT INTO `f_admin` VALUES ('12', '1', '1', 'liujing', '+0gSg1gtBvBuwWcnAk2nZw==', '2018-08-01 18:03:03', null, null, '0', '0', '0', '1', '26003658957127692', null);
INSERT INTO `f_admin` VALUES ('13', '1', '1', 'jiazhigang', 'zdIJMPJ2WEh8hlAwpppU2g==', '2018-08-07 16:10:06', null, null, '0', '0', '0', '2', '26003658957127693', null);
INSERT INTO `f_admin` VALUES ('14', '1', '1', 'zhangchen', '4QrcOUm6Wau+VuBX8g+IPg==', '2018-08-22 14:42:30', null, null, '0', '0', '0', '0', '26003658957127694', null);
INSERT INTO `f_admin` VALUES ('15', '1', '1', 'luwei', '014a41cc397ac3c2941ab71fc1db7f3b', '2018-09-06 11:18:53', null, null, '0', '0', '0', '0', '26003658957127680', '13072747751');

-- ----------------------------
-- Table structure for f_role
-- ----------------------------
DROP TABLE IF EXISTS `f_role`;
CREATE TABLE `f_role` (
  `fid` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `fdescription` varchar(256) DEFAULT NULL COMMENT '描述',
  `fname` varchar(64) NOT NULL COMMENT '名称',
  `fstatus` tinyint(1) DEFAULT '1' COMMENT '状态',
  PRIMARY KEY (`fid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='角色表';

-- ----------------------------
-- Records of f_role
-- ----------------------------
INSERT INTO `f_role` VALUES ('1', '超级管理员', '超级管理员', '1');

-- ----------------------------
-- Table structure for f_role_security
-- ----------------------------
DROP TABLE IF EXISTS `f_role_security`;
CREATE TABLE `f_role_security` (
  `fid` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `fsecurityid` int(11) DEFAULT NULL COMMENT '权限ID',
  `froleid` int(11) DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`fid`),
  KEY `FK9B6EA402BB04F1F` (`fsecurityid`),
  KEY `FK9B6EA403FFD717F` (`froleid`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=COMPACT COMMENT='角色权限表';

-- ----------------------------
-- Records of f_role_security
-- ----------------------------
INSERT INTO `f_role_security` VALUES ('1', '1', '1');
INSERT INTO `f_role_security` VALUES ('2', '2', '1');
INSERT INTO `f_role_security` VALUES ('3', '3', '1');
INSERT INTO `f_role_security` VALUES ('4', '4', '1');
INSERT INTO `f_role_security` VALUES ('5', '5', '1');
INSERT INTO `f_role_security` VALUES ('6', '6', '1');
INSERT INTO `f_role_security` VALUES ('7', '7', '1');
INSERT INTO `f_role_security` VALUES ('8', '8', '1');
INSERT INTO `f_role_security` VALUES ('9', '9', '1');
INSERT INTO `f_role_security` VALUES ('10', '10', '1');

-- ----------------------------
-- Table structure for f_security
-- ----------------------------
DROP TABLE IF EXISTS `f_security`;
CREATE TABLE `f_security` (
  `fid` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `fdescription` varchar(255) DEFAULT NULL COMMENT '描述',
  `fname` varchar(32) DEFAULT NULL COMMENT '权限名称',
  `fpriority` int(11) DEFAULT NULL COMMENT '排序',
  `fparentid` int(20) DEFAULT NULL COMMENT '父级ID',
  `furl` varchar(200) DEFAULT NULL COMMENT '权限URL',
  PRIMARY KEY (`fid`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=COMPACT COMMENT='权限表';

-- ----------------------------
-- Records of f_security
-- ----------------------------
INSERT INTO `f_security` VALUES ('1', '征收资料管理', '征收资料管理', '1', '0', 'dataManage');
INSERT INTO `f_security` VALUES ('2', '协议列表', '协议列表', '2', '1', '/ssadmin/a.html');
INSERT INTO `f_security` VALUES ('3', '用户管理', '用户管理', '2', '1', '/ssadmin/b.html');
INSERT INTO `f_security` VALUES ('4', '网站信息设置', '网站信息设置', '1', '0', 'webBaseInfo');
INSERT INTO `f_security` VALUES ('5', '系统管理', '系统管理', '1', '0', 'system');
INSERT INTO `f_security` VALUES ('6', '后台操作日志', '后台操作日志', '2', '5', 'ssadmin/sysOperatorlog.html');
INSERT INTO `f_security` VALUES ('7', '管理员列表', '管理员列表', '2', '5', 'ssadmin/adminList.html');
INSERT INTO `f_security` VALUES ('8', '系统参数列表', '系统参数列表', '2', '5', 'ssadmin/systemArgsList.html');
INSERT INTO `f_security` VALUES ('9', '权限列表', '权限列表', '2', '5', 'ssadmin/securityList.html');
INSERT INTO `f_security` VALUES ('10', '角色列表', '角色列表', '2', '5', 'ssadmin/roleList.html');

-- ----------------------------
-- Table structure for f_systemargs
-- ----------------------------
DROP TABLE IF EXISTS `f_systemargs`;
CREATE TABLE `f_systemargs` (
  `fid` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `fkey` varchar(100) DEFAULT NULL COMMENT '唯一key',
  `ftype` int(11) DEFAULT NULL COMMENT '类型（1-网站后台，2-网站前台）',
  `fdescription` varchar(1024) DEFAULT NULL COMMENT '描述',
  `fvalue` varchar(2048) DEFAULT NULL COMMENT 'key值',
  `version` int(11) DEFAULT NULL COMMENT '版本',
  `furl` varchar(100) DEFAULT NULL COMMENT '地址',
  PRIMARY KEY (`fid`),
  UNIQUE KEY `fKey_seq` (`fkey`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=COMPACT COMMENT='系统参数';

-- ----------------------------
-- Records of f_systemargs
-- ----------------------------

-- ----------------------------
-- Table structure for f_system_operator_log
-- ----------------------------
DROP TABLE IF EXISTS `f_system_operator_log`;
CREATE TABLE `f_system_operator_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键自增',
  `user_agent` varchar(255) DEFAULT NULL COMMENT '浏览器信息',
  `module` varchar(255) DEFAULT NULL COMMENT '所属模块',
  `operator_date` datetime DEFAULT NULL COMMENT '操作时间',
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `login_name` varchar(255) DEFAULT NULL COMMENT '登录名称',
  `ip` varchar(255) DEFAULT NULL COMMENT 'ip地址',
  `operator_code` varchar(255) DEFAULT NULL COMMENT '操作code',
  `operator_name` varchar(255) DEFAULT NULL COMMENT '操作简称',
  `class_name` varchar(255) DEFAULT NULL COMMENT '类名',
  `method_name` varchar(255) DEFAULT NULL COMMENT '方法名称',
  `issuccess` tinyint(255) DEFAULT NULL COMMENT '操作是否成功',
  `request_parameters` varchar(255) DEFAULT NULL COMMENT '请求参数',
  `operator_content` varchar(255) DEFAULT NULL COMMENT '操作内容',
  `remart` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of f_system_operator_log
-- ----------------------------

-- ----------------------------
-- Table structure for f_webbaseinfo
-- ----------------------------
DROP TABLE IF EXISTS `f_webbaseinfo`;
CREATE TABLE `f_webbaseinfo` (
  `fid` int(11) NOT NULL AUTO_INCREMENT,
  `ftitle` varchar(255) DEFAULT NULL,
  `fkeywords` varchar(255) DEFAULT NULL,
  `fdescription` varchar(255) DEFAULT NULL,
  `fcopyRights` varchar(255) DEFAULT NULL,
  `fbeianInfo` varchar(255) DEFAULT NULL,
  `fsystemMail` varchar(255) DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  PRIMARY KEY (`fid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of f_webbaseinfo
-- ----------------------------
INSERT INTO `f_webbaseinfo` VALUES ('1', '资料管理', '1', '2', '@2019', '3', '851620009@qq.com', '1');
