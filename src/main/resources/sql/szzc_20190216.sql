/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50527
Source Host           : 127.0.0.1:3306
Source Database       : szzc

Target Server Type    : MYSQL
Target Server Version : 50527
File Encoding         : 65001

Date: 2019-03-17 00:49:54
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
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='管理员';

-- ----------------------------
-- Records of f_admin
-- ----------------------------
INSERT INTO `f_admin` VALUES ('1', '1', '1', 'admin', 'a0581f0b20e4f7efc82e0069e386863a', '2016-10-28 16:50:27', null, null, '0', '0', '0', '11', '26003658957127681', null);
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
INSERT INTO `f_admin` VALUES ('14', '1', '2', 'test', '476f534e0a7d75344487cf363c51e482', '2018-08-22 14:42:30', null, null, '0', '0', '0', '0', '26003658957127694', null);
INSERT INTO `f_admin` VALUES ('15', '1', '1', 'luwei', '014a41cc397ac3c2941ab71fc1db7f3b', '2018-09-06 11:18:53', null, null, '0', '0', '0', '0', '26003658957127680', '13072747751');
INSERT INTO `f_admin` VALUES ('16', '1', '2', 'dev', 'e4588317e11742b7dc80d4db9461fb5c', '2019-02-16 14:09:47', null, null, null, null, null, null, 'e48b0281772c4fdda162a89474e1411e', '13096251487');

-- ----------------------------
-- Table structure for f_role
-- ----------------------------
DROP TABLE IF EXISTS `f_role`;
CREATE TABLE `f_role` (
  `fid` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `fdescription` varchar(256) DEFAULT NULL COMMENT '描述',
  `fname` varchar(64) NOT NULL COMMENT '名称',
  `fstatus` tinyint(1) DEFAULT '1' COMMENT '状态：0禁用、1启用',
  PRIMARY KEY (`fid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='角色表';

-- ----------------------------
-- Records of f_role
-- ----------------------------
INSERT INTO `f_role` VALUES ('1', '超级管理员', '超级管理员', '1');
INSERT INTO `f_role` VALUES ('2', '操作员', '操作员', '1');

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
) ENGINE=InnoDB AUTO_INCREMENT=74 DEFAULT CHARSET=utf8 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=COMPACT COMMENT='角色权限表';

-- ----------------------------
-- Records of f_role_security
-- ----------------------------
INSERT INTO `f_role_security` VALUES ('23', '2', '2');
INSERT INTO `f_role_security` VALUES ('24', '3', '2');
INSERT INTO `f_role_security` VALUES ('25', '4', '2');
INSERT INTO `f_role_security` VALUES ('48', '1', '1');
INSERT INTO `f_role_security` VALUES ('49', '2', '1');
INSERT INTO `f_role_security` VALUES ('50', '3', '1');
INSERT INTO `f_role_security` VALUES ('51', '4', '1');
INSERT INTO `f_role_security` VALUES ('52', '5', '1');
INSERT INTO `f_role_security` VALUES ('53', '6', '1');
INSERT INTO `f_role_security` VALUES ('54', '7', '1');
INSERT INTO `f_role_security` VALUES ('55', '8', '1');
INSERT INTO `f_role_security` VALUES ('56', '9', '1');
INSERT INTO `f_role_security` VALUES ('57', '10', '1');
INSERT INTO `f_role_security` VALUES ('58', '11', '1');
INSERT INTO `f_role_security` VALUES ('59', '12', '1');
INSERT INTO `f_role_security` VALUES ('60', '13', '1');
INSERT INTO `f_role_security` VALUES ('61', '14', '1');
INSERT INTO `f_role_security` VALUES ('62', '15', '1');
INSERT INTO `f_role_security` VALUES ('63', '16', '1');
INSERT INTO `f_role_security` VALUES ('64', '17', '1');
INSERT INTO `f_role_security` VALUES ('65', '18', '1');
INSERT INTO `f_role_security` VALUES ('66', '19', '1');
INSERT INTO `f_role_security` VALUES ('67', '20', '1');
INSERT INTO `f_role_security` VALUES ('68', '21', '1');
INSERT INTO `f_role_security` VALUES ('69', '22', '1');
INSERT INTO `f_role_security` VALUES ('70', '23', '1');
INSERT INTO `f_role_security` VALUES ('71', '24', '1');
INSERT INTO `f_role_security` VALUES ('72', '25', '1');
INSERT INTO `f_role_security` VALUES ('73', '26', '1');

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
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=COMPACT COMMENT='权限表';

-- ----------------------------
-- Records of f_security
-- ----------------------------
INSERT INTO `f_security` VALUES ('1', '花山权限', '花山权限', '1', '0', 'root');
INSERT INTO `f_security` VALUES ('2', '征收档案管理', '征收档案管理', '2', '1', 'dataManage');
INSERT INTO `f_security` VALUES ('3', '协议列表', '协议列表', '3', '2', '/ssadmin/protocolList.html');
INSERT INTO `f_security` VALUES ('4', '网站信息设置', '网站信息设置', '2', '1', 'webBaseInfo');
INSERT INTO `f_security` VALUES ('5', '系统管理', '系统管理', '2', '1', 'system');
INSERT INTO `f_security` VALUES ('6', '后台操作日志', '后台操作日志', '3', '5', 'ssadmin/sysOperatorlog.html');
INSERT INTO `f_security` VALUES ('7', '管理员列表', '管理员列表', '3', '5', 'ssadmin/adminList.html');
INSERT INTO `f_security` VALUES ('8', '系统参数列表', '系统参数列表', '3', '5', 'ssadmin/systemArgsList.html');
INSERT INTO `f_security` VALUES ('9', '权限列表', '权限列表', '3', '5', 'ssadmin/securityList.html');
INSERT INTO `f_security` VALUES ('10', '角色列表', '角色列表', '3', '5', 'ssadmin/roleList.html');
INSERT INTO `f_security` VALUES ('11', '新增', '新增', '4', '7', 'ssadmin/addAdmin.html');
INSERT INTO `f_security` VALUES ('12', '禁用', '禁用', '4', '7', 'ssadmin/forbbinAdmin.html?status=1');
INSERT INTO `f_security` VALUES ('13', '解除禁用', '解除禁用', '4', '7', 'ssadmin/forbbinAdmin.html?status=2');
INSERT INTO `f_security` VALUES ('14', '修改密码', '修改密码', '4', '7', 'ssadmin/updateAdminPassword.html');
INSERT INTO `f_security` VALUES ('15', '修改角色', '修改角色', '4', '7', 'ssadmin/updateAdminRole.html');
INSERT INTO `f_security` VALUES ('16', '新增', '新增', '4', '9', 'ssadmin/addSecurity.html');
INSERT INTO `f_security` VALUES ('17', '删除', '删除', '4', '9', 'ssadmin/deleteSecurity.html');
INSERT INTO `f_security` VALUES ('18', '修改', '修改', '4', '9', 'ssadmin/updateSecurity.html');
INSERT INTO `f_security` VALUES ('19', '新增', '新增', '4', '10', 'ssadmin/createRole.html');
INSERT INTO `f_security` VALUES ('20', '修改', '修改', '4', '10', 'ssadmin/updateRole.html');
INSERT INTO `f_security` VALUES ('21', '修改', '修改', '4', '8', 'ssadmin/updateSystemArgs.html');
INSERT INTO `f_security` VALUES ('22', '新增', '新增', '4', '8', 'ssadmin/addSystemArgs.html');
INSERT INTO `f_security` VALUES ('23', '新增', '新增', '4', '3', 'ssadmin/addProtocol.html');
INSERT INTO `f_security` VALUES ('24', '修改', '修改', '4', '3', 'ssadmin/updateProtocol.html');
INSERT INTO `f_security` VALUES ('25', '删除', '删除', '4', '3', 'ssadmin/deleteProtocol.html');
INSERT INTO `f_security` VALUES ('26', '导出', '导出', '4', '3', 'ssadmin/addSettleAccounts.html');

-- ----------------------------
-- Table structure for f_systemargs
-- ----------------------------
DROP TABLE IF EXISTS `f_systemargs`;
CREATE TABLE `f_systemargs` (
  `fid` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `fkey` varchar(100) DEFAULT NULL COMMENT '唯一key',
  `ftype` int(11) DEFAULT NULL COMMENT '类型（1-运营参数，2-后台参数）',
  `fdescription` varchar(1024) DEFAULT NULL COMMENT '描述',
  `fvalue` varchar(2048) DEFAULT NULL COMMENT 'key值',
  `version` int(11) DEFAULT NULL COMMENT '版本',
  `furl` varchar(100) DEFAULT NULL COMMENT '地址',
  PRIMARY KEY (`fid`),
  UNIQUE KEY `fKey_seq` (`fkey`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=COMPACT COMMENT='系统参数';

-- ----------------------------
-- Records of f_systemargs
-- ----------------------------
INSERT INTO `f_systemargs` VALUES ('1', 'city', '1', '地区描述', '武昌区', '1', null);
INSERT INTO `f_systemargs` VALUES ('2', 'company', '1', '征收实施单位', '武汉和鑫居房屋征收服务有限公司', '0', '');
INSERT INTO `f_systemargs` VALUES ('3', 'water_meter_main', '1', '水表(主)', '500', '0', null);
INSERT INTO `f_systemargs` VALUES ('4', 'water_meter_sub', '1', '水表(副)', '100', '0', null);
INSERT INTO `f_systemargs` VALUES ('5', 'air_conditioner_shutter', '1', '窗机', '100', '0', null);
INSERT INTO `f_systemargs` VALUES ('6', 'air_conditioner_hang', '1', '挂机', '200', '0', null);
INSERT INTO `f_systemargs` VALUES ('7', 'air_conditioner_cabinet', '1', '柜机', '300', '0', null);
INSERT INTO `f_systemargs` VALUES ('8', 'ammeter_main', '1', '电表(主)', '500', '0', null);
INSERT INTO `f_systemargs` VALUES ('9', 'ammeter_sub', '1', '电表(副)', '100', '0', null);

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
  `request_parameters` varchar(5000) DEFAULT NULL COMMENT '请求参数',
  `operator_content` varchar(255) DEFAULT NULL COMMENT '操作内容',
  `remart` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of f_system_operator_log
-- ----------------------------
INSERT INTO `f_system_operator_log` VALUES ('1', null, '系统管理', '2019-02-15 23:35:16', '15', 'luwei', '0:0:0:0:0:0:0:1', 'system_operation', '修改后台权限', 'com.me.szzc.controller.SecurityController', 'updateSecurity', '1', '', '用户:执行(修改后台权限)操作 修改成功', null);
INSERT INTO `f_system_operator_log` VALUES ('2', null, '系统管理', '2019-02-15 23:35:24', '15', 'luwei', '0:0:0:0:0:0:0:1', 'system_operation', '修改后台权限', 'com.me.szzc.controller.SecurityController', 'updateSecurity', '1', '', '用户:执行(修改后台权限)操作 修改成功', null);
INSERT INTO `f_system_operator_log` VALUES ('3', null, '系统管理', '2019-02-15 23:45:32', '15', 'luwei', '0:0:0:0:0:0:0:1', 'system_operation', '新增角色', 'com.me.szzc.controller.RoleController', 'saveRole', '1', '', '用户:执行(新增角色)操作 保存成功', null);
INSERT INTO `f_system_operator_log` VALUES ('4', null, '系统管理', '2019-02-16 13:56:53', '15', 'luwei', '0:0:0:0:0:0:0:1', 'system_operation', '修改管理员角色', 'com.me.szzc.controller.AdminController', 'updateAdminRole', '1', '', '用户:执行(修改管理员角色)操作 修改成功', null);
INSERT INTO `f_system_operator_log` VALUES ('5', null, '系统管理', '2019-02-16 14:00:09', '15', 'luwei', '0:0:0:0:0:0:0:1', 'system_operation', '修改管理员密码', 'com.me.szzc.controller.AdminController', 'updateAdmin', '1', '', '用户:执行(修改管理员密码)操作 修改密码成功', null);
INSERT INTO `f_system_operator_log` VALUES ('6', null, '系统管理', '2019-02-16 14:00:21', '15', 'luwei', '0:0:0:0:0:0:0:1', 'system_operation', '修改管理员禁用状态', 'com.me.szzc.controller.AdminController', 'forbbinAdmin', '1', '', '用户:执行(修改管理员禁用状态)操作 禁用成功', null);
INSERT INTO `f_system_operator_log` VALUES ('7', null, '系统管理', '2019-02-16 14:00:25', '15', 'luwei', '0:0:0:0:0:0:0:1', 'system_operation', '修改管理员禁用状态', 'com.me.szzc.controller.AdminController', 'forbbinAdmin', '1', '', '用户:执行(修改管理员禁用状态)操作 成功', null);
INSERT INTO `f_system_operator_log` VALUES ('8', null, '系统管理', '2019-02-16 14:08:50', '15', 'luwei', '0:0:0:0:0:0:0:1', 'system_operation', '修改管理员禁用状态', 'com.me.szzc.controller.AdminController', 'forbbinAdmin', '1', '', '用户:执行(修改管理员禁用状态)操作 成功', null);
INSERT INTO `f_system_operator_log` VALUES ('9', null, '系统管理', '2019-02-16 14:09:17', '15', 'luwei', '0:0:0:0:0:0:0:1', 'system_operation', '修改管理员密码', 'com.me.szzc.controller.AdminController', 'updateAdmin', '1', '', '用户:执行(修改管理员密码)操作 修改密码成功', null);
INSERT INTO `f_system_operator_log` VALUES ('10', null, '系统管理', '2019-02-16 14:09:47', '15', 'luwei', '0:0:0:0:0:0:0:1', 'system_operation', '新增管理员', 'com.me.szzc.controller.AdminController', 'saveAdmin', '1', 'dev,dev2019,2,13096251487', '用户:执行(新增管理员)操作 新增成功', null);
INSERT INTO `f_system_operator_log` VALUES ('11', null, '系统管理', '2019-02-16 14:16:36', '15', 'luwei', '0:0:0:0:0:0:0:1', 'system_operation', '修改角色', 'com.me.szzc.controller.RoleController', 'updateRole', '1', '', '用户:执行(修改角色)操作 更新成功', null);
INSERT INTO `f_system_operator_log` VALUES ('12', null, '系统管理', '2019-02-16 15:32:14', '15', 'luwei', '0:0:0:0:0:0:0:1', 'system_operation', '修改角色', 'com.me.szzc.controller.RoleController', 'updateRole', '1', '', '用户:执行(修改角色)操作 更新成功', null);
INSERT INTO `f_system_operator_log` VALUES ('13', null, '系统管理', '2019-02-16 15:52:59', '15', 'luwei', '0:0:0:0:0:0:0:1', 'system_operation', '修改系统参数', 'com.me.szzc.controller.SystemArgsController', 'updateSystemArgs', '1', 'banner_img1,https://luwei0308.oss-cn-shanghai.aliyuncs.com/console/sysargs/201902161542046_70n9T.png,2,测试用,勿删除,www.baidu.com', '用户:执行(修改系统参数)操作 更新成功', null);
INSERT INTO `f_system_operator_log` VALUES ('14', null, '系统管理', '2019-02-16 15:56:06', '15', 'luwei', '0:0:0:0:0:0:0:1', 'system_operation', '修改密码', 'com.me.szzc.controller.AdminController', 'updateAdminPassword', '1', '', '用户:执行(修改密码)操作 修改密码成功', null);
INSERT INTO `f_system_operator_log` VALUES ('15', null, '系统管理', '2019-02-16 21:37:31', '15', 'luwei', '0:0:0:0:0:0:0:1', 'system_operation', '修改角色', 'com.me.szzc.controller.RoleController', 'updateRole', '1', '', '用户:执行(修改角色)操作 更新成功', null);
INSERT INTO `f_system_operator_log` VALUES ('16', null, '协议管理', '2019-02-17 15:27:42', '15', 'luwei', '0:0:0:0:0:0:0:1', 'protocol_operation', '新增产权调换协议', 'com.me.szzc.controller.SwapHouseController', 'addSwapHouse', '1', '{\"address\":\"123\",\"cardNo\":\"123\",\"changeCompensate\":123,\"checkInArea\":123,\"closeBalcony\":123,\"coveredArea\":123,\"decorationCompensate\":123,\"difference\":123,\"floors\":\"123\",\"gasFee\":123,\"hotWaterCompensate\":123,\"houseNumber\":\"123\",\"houseOwner\":\"123\",\"houseOwnerNumber\":\"123\",\"interimFee\":123,\"interimMonth\":123,\"interimPrice\":123,\"lessDifference\":123,\"lifeCompensate\":123,\"mngOffice\":\"123\",\"months\":\"123\",\"moveAirConditioningFee\":123,\"moveAmmeterFee\":123,\"moveHouseFee\":123,\"moveMonth\":123,\"movePhoneFee\":123,\"moveReward\":123,\"moveWaterMeterFee\":123,\"newHouseAddress\":\"123\",\"noCheckArea\":123,\"noCheckCompensate\":123,\"officeArea\":123,\"operateArea\":123,\"otherArea\":123,\"otherFee\":123,\"otherTermsOne\":\"123\",\"price\":123,\"produceArea\":123,\"proportion\":123,\"publicOwnerNumber\":\"123\",\"residenceArea\":123,\"seat\":\"123\",\"structureCompensate\":123,\"sumRbm\":123,\"suspendBusinessFee\":123,\"totalPrice\":123,\"tvFee\":123,\"unit\":123,\"upperDifference\":\"123\",\"upperLessDifference\":\"123\",\"upperRmb\":\"123\",\"upperTotalPrice\":\"123\",\"useing\":\"123\",\"valueCompensate\":123,\"wifiFee\":123,\"years\":\"123\"}', '用户:执行(新增产权调换协议)操作 新增成功', null);
INSERT INTO `f_system_operator_log` VALUES ('17', null, '协议管理', '2019-02-18 21:49:00', '15', 'luwei', '0:0:0:0:0:0:0:1', 'protocol_operation', '新增产权调换协议', 'com.me.szzc.controller.SwapHouseController', 'addSwapHouse', '1', '{\"address\":\"123\",\"cardNo\":\"123\",\"checkInArea\":123,\"coveredArea\":1,\"createDate\":1550497739975,\"createUserId\":15,\"decorationCompensate\":123,\"floors\":\"1\",\"houseNumber\":\"1\",\"houseOwner\":\"123\",\"houseOwnerNumber\":\"123\",\"interimFee\":123,\"mngOffice\":\"123\",\"modifiedDate\":1550497739975,\"modifiedUserId\":15,\"months\":\"1\",\"moveHouseFee\":123,\"moveMonth\":1,\"moveReward\":123,\"newHouseAddress\":\"1\",\"otherTermsOne\":\"\",\"price\":1,\"proportion\":123,\"publicOwnerNumber\":\"123\",\"seat\":\"1\",\"sumRbm\":123,\"totalPrice\":1,\"unit\":1,\"upperDifference\":\"\",\"upperLessDifference\":\"\",\"upperRmb\":\"123\",\"upperTotalPrice\":\"1\",\"useing\":\"123\",\"valueCompensate\":123,\"years\":\"1\"}', '用户:执行(新增产权调换协议)操作 新增成功', null);
INSERT INTO `f_system_operator_log` VALUES ('18', null, '协议管理', '2019-02-20 20:51:36', '15', 'luwei', '0:0:0:0:0:0:0:1', 'protocol_operation', '查询产权调换协议', 'com.me.szzc.controller.SwapHouseController', 'selectSwapHouseByHouseOwner', '1', '张三,ssadmin/updateSwapHouse', '用户:执行(查询产权调换协议)操作 查询成功', null);
INSERT INTO `f_system_operator_log` VALUES ('19', null, '协议管理', '2019-02-20 20:51:58', '15', 'luwei', '0:0:0:0:0:0:0:1', 'protocol_operation', '查询产权调换协议', 'com.me.szzc.controller.SwapHouseController', 'selectSwapHouseByHouseOwner', '1', '张三,ssadmin/updateSwapHouse', '用户:执行(查询产权调换协议)操作 查询成功', null);
INSERT INTO `f_system_operator_log` VALUES ('20', null, '协议管理', '2019-02-20 20:55:41', '15', 'luwei', '0:0:0:0:0:0:0:1', 'protocol_operation', '查询产权调换协议', 'com.me.szzc.controller.SwapHouseController', 'selectSwapHouseByHouseOwner', '1', '张三,ssadmin/updateSwapHouse', '用户:执行(查询产权调换协议)操作 查询成功', null);
INSERT INTO `f_system_operator_log` VALUES ('21', null, '协议管理', '2019-02-20 21:01:46', '15', 'luwei', '0:0:0:0:0:0:0:1', 'protocol_operation', '查询产权调换协议', 'com.me.szzc.controller.SwapHouseController', 'selectSwapHouseByHouseOwner', '1', '张三,ssadmin/updateSwapHouse', '用户:执行(查询产权调换协议)操作 查询成功', null);
INSERT INTO `f_system_operator_log` VALUES ('22', null, '协议管理', '2019-02-20 21:02:14', '15', 'luwei', '0:0:0:0:0:0:0:1', 'protocol_operation', '查询产权调换协议', 'com.me.szzc.controller.SwapHouseController', 'selectSwapHouseByHouseOwner', '1', '张三,ssadmin/updateSwapHouse', '用户:执行(查询产权调换协议)操作 查询成功', null);
INSERT INTO `f_system_operator_log` VALUES ('23', null, '协议管理', '2019-02-20 21:13:36', '15', 'luwei', '0:0:0:0:0:0:0:1', 'protocol_operation', '查询产权调换协议', 'com.me.szzc.controller.SwapHouseController', 'selectSwapHouseByHouseOwner', '1', '张三,ssadmin/updateSwapHouse', '用户:执行(查询产权调换协议)操作 查询成功', null);
INSERT INTO `f_system_operator_log` VALUES ('24', null, '协议管理', '2019-02-20 21:13:47', '15', 'luwei', '0:0:0:0:0:0:0:1', 'protocol_operation', '修改产权调换协议', 'com.me.szzc.controller.SwapHouseController', 'updateSwapHouse', '1', '{\"address\":\"123\",\"cardNo\":\"123\",\"changeCompensate\":123.00,\"checkInArea\":123.00,\"closeBalcony\":123.00,\"coveredArea\":123.00,\"decorationCompensate\":123.00,\"difference\":123.00,\"floors\":\"123\",\"gasFee\":123.00,\"hotWaterCompensate\":123.00,\"houseNumber\":\"123\",\"houseOwner\":\"张三\",\"houseOwnerNumber\":\"123\",\"id\":2,\"interimFee\":123.00,\"interimMonth\":123,\"interimPrice\":123.00,\"lessDifference\":123.00,\"lifeCompensate\":123.00,\"mngOffice\":\"武昌区拆迁办\",\"modifiedDate\":1550668427158,\"modifiedUserId\":15,\"months\":\"123\",\"moveAirConditioningFee\":123.00,\"moveAmmeterFee\":123.00,\"moveHouseFee\":123.00,\"moveMonth\":123,\"movePhoneFee\":123.00,\"moveReward\":123.00,\"moveWaterMeterFee\":123.00,\"newHouseAddress\":\"123\",\"noCheckArea\":123.00,\"noCheckCompensate\":123.00,\"officeArea\":123.00,\"operateArea\":123.00,\"otherArea\":123.00,\"otherFee\":123.00,\"otherTermsOne\":\"123\",\"price\":123.00,\"produceArea\":123.00,\"proportion\":123.00,\"publicOwnerNumber\":\"123\",\"residenceArea\":123.00,\"seat\":\"123\",\"structureCompensate\":123.00,\"sumRbm\":123.00,\"suspendBusinessFee\":123.00,\"totalPrice\":123.00,\"tvFee\":123.00,\"unit\":123,\"upperDifference\":\"123\",\"upperLessDifference\":\"123\",\"upperRmb\":\"123\",\"upperTotalPrice\":\"123\",\"useing\":\"123\",\"valueCompensate\":123.00,\"wifiFee\":123.00,\"years\":\"123\"}', '用户:执行(修改产权调换协议)操作 修改成功', null);
INSERT INTO `f_system_operator_log` VALUES ('25', null, '协议管理', '2019-02-20 21:13:51', '15', 'luwei', '0:0:0:0:0:0:0:1', 'protocol_operation', '查询产权调换协议', 'com.me.szzc.controller.SwapHouseController', 'selectSwapHouseByHouseOwner', '1', '张三,ssadmin/updateSwapHouse', '用户:执行(查询产权调换协议)操作 查询成功', null);
INSERT INTO `f_system_operator_log` VALUES ('26', null, '协议管理', '2019-02-20 21:14:17', '15', 'luwei', '0:0:0:0:0:0:0:1', 'protocol_operation', '修改产权调换协议', 'com.me.szzc.controller.SwapHouseController', 'updateSwapHouse', '1', '{\"address\":\"123\",\"cardNo\":\"123\",\"changeCompensate\":123.00,\"checkInArea\":123.00,\"closeBalcony\":123.00,\"coveredArea\":123.00,\"decorationCompensate\":9000,\"difference\":123.00,\"floors\":\"123\",\"gasFee\":123.00,\"hotWaterCompensate\":123.00,\"houseNumber\":\"123\",\"houseOwner\":\"张三\",\"houseOwnerNumber\":\"123\",\"id\":2,\"interimFee\":123.00,\"interimMonth\":15,\"interimPrice\":123.00,\"lessDifference\":123.00,\"lifeCompensate\":123.00,\"mngOffice\":\"武昌区拆迁办\",\"modifiedDate\":1550668457600,\"modifiedUserId\":15,\"months\":\"123\",\"moveAirConditioningFee\":123.00,\"moveAmmeterFee\":123.00,\"moveHouseFee\":123.00,\"moveMonth\":123,\"movePhoneFee\":123.00,\"moveReward\":123.00,\"moveWaterMeterFee\":123.00,\"newHouseAddress\":\"123\",\"noCheckArea\":123.00,\"noCheckCompensate\":123.00,\"officeArea\":123.00,\"operateArea\":123.00,\"otherArea\":123.00,\"otherFee\":123.00,\"otherTermsOne\":\"123\",\"price\":123.00,\"produceArea\":123.00,\"proportion\":123.00,\"publicOwnerNumber\":\"123\",\"residenceArea\":123.00,\"seat\":\"123\",\"structureCompensate\":123.00,\"subtotal\":98765,\"sumRbm\":123.00,\"suspendBusinessFee\":123.00,\"totalPrice\":123.00,\"tvFee\":123.00,\"unit\":123,\"upperDifference\":\"123\",\"upperLessDifference\":\"123\",\"upperRmb\":\"123\",\"upperTotalPrice\":\"123\",\"useing\":\"123\",\"valueCompensate\":7543215,\"wifiFee\":123.00,\"years\":\"123\"}', '用户:执行(修改产权调换协议)操作 修改成功', null);
INSERT INTO `f_system_operator_log` VALUES ('27', null, '协议管理', '2019-02-20 21:14:20', '15', 'luwei', '0:0:0:0:0:0:0:1', 'protocol_operation', '查询产权调换协议', 'com.me.szzc.controller.SwapHouseController', 'selectSwapHouseByHouseOwner', '1', '张三,ssadmin/updateSwapHouse', '用户:执行(查询产权调换协议)操作 查询成功', null);
INSERT INTO `f_system_operator_log` VALUES ('28', null, '协议管理', '2019-02-20 21:27:45', '15', 'luwei', '0:0:0:0:0:0:0:1', 'protocol_operation', '删除产权调换协议', 'com.me.szzc.controller.SwapHouseController', 'deleteSwapHouse', '1', '王五', '用户:执行(删除产权调换协议)操作 删除成功', null);
INSERT INTO `f_system_operator_log` VALUES ('29', null, '协议管理', '2019-02-20 21:30:25', '15', 'luwei', '0:0:0:0:0:0:0:1', 'protocol_operation', '查询产权调换协议', 'com.me.szzc.controller.SwapHouseController', 'selectSwapHouseByHouseOwner', '1', '王五,ssadmin/updateSwapHouse', '用户:执行(查询产权调换协议)操作 查询成功', null);
INSERT INTO `f_system_operator_log` VALUES ('30', null, '协议管理', '2019-02-20 21:45:41', '15', 'luwei', '0:0:0:0:0:0:0:1', 'protocol_operation', '删除产权调换协议', 'com.me.szzc.controller.SwapHouseController', 'deleteSwapHouse', '0', '王五', '用户:执行(删除产权调换协议)操作 数据已删除，请核查后再操作', null);
INSERT INTO `f_system_operator_log` VALUES ('31', null, '协议管理', '2019-02-20 21:45:46', '15', 'luwei', '0:0:0:0:0:0:0:1', 'protocol_operation', '删除产权调换协议', 'com.me.szzc.controller.SwapHouseController', 'deleteSwapHouse', '0', '王五', '用户:执行(删除产权调换协议)操作 数据已删除，请核查后再操作', null);
INSERT INTO `f_system_operator_log` VALUES ('32', null, '协议管理', '2019-02-20 21:45:53', '15', 'luwei', '0:0:0:0:0:0:0:1', 'protocol_operation', '查询产权调换协议', 'com.me.szzc.controller.SwapHouseController', 'selectSwapHouseByHouseOwner', '1', '王五,ssadmin/updateSwapHouse', '用户:执行(查询产权调换协议)操作 查询成功', null);
INSERT INTO `f_system_operator_log` VALUES ('33', null, '协议管理', '2019-02-21 15:44:13', '15', 'luwei', '0:0:0:0:0:0:0:1', 'protocol_operation', '新增产权调换协议', 'com.me.szzc.controller.SwapHouseController', 'addSwapHouse', '1', '{\"address\":\"123\",\"cardNo\":\"123\",\"checkInArea\":123,\"company\":\"武汉和鑫居房屋征收服务有限公司\",\"coveredArea\":1,\"createDate\":1550735053859,\"createUserId\":15,\"decorationCompensate\":123,\"deleted\":false,\"floors\":\"1\",\"houseNumber\":\"1\",\"houseOwner\":\"123\",\"houseOwnerNumber\":\"123\",\"interimFee\":123,\"mngOffice\":\"123\",\"modifiedDate\":1550735053859,\"modifiedUserId\":15,\"months\":\"1\",\"moveHouseFee\":123,\"moveMonth\":1,\"moveReward\":123,\"newHouseAddress\":\"1\",\"otherTermsOne\":\"\",\"price\":1,\"proportion\":123,\"publicOwnerNumber\":\"123\",\"seat\":\"1\",\"sumRbm\":123,\"totalPrice\":1,\"unit\":1,\"upperDifference\":\"\",\"upperLessDifference\":\"\",\"upperRmb\":\"123\",\"upperTotalPrice\":\"1\",\"useing\":\"123\",\"valueCompensate\":123,\"years\":\"1\"}', '用户:执行(新增产权调换协议)操作 新增成功', null);
INSERT INTO `f_system_operator_log` VALUES ('34', null, '协议管理', '2019-02-21 16:49:13', '15', 'luwei', '0:0:0:0:0:0:0:1', 'protocol_operation', '新增产权调换协议', 'com.me.szzc.controller.SwapHouseController', 'addSwapHouse', '1', '{\"address\":\"123\",\"cardNo\":\"123\",\"checkInArea\":123,\"company\":\"武汉和鑫居房屋征收服务有限公司\",\"coveredArea\":1,\"createDate\":1550738952569,\"createUserId\":15,\"decorationCompensate\":123,\"deleted\":false,\"floors\":\"1\",\"houseNumber\":\"1\",\"houseOwner\":\"张三\",\"houseOwnerNumber\":\"123\",\"interimFee\":123,\"mngOffice\":\"123\",\"modifiedDate\":1550738952569,\"modifiedUserId\":15,\"months\":\"1\",\"moveHouseFee\":123,\"moveMonth\":1,\"moveReward\":123,\"newHouseAddress\":\"1\",\"otherTermsOne\":\"\",\"price\":1,\"proportion\":123,\"publicOwnerNumber\":\"123\",\"seat\":\"1\",\"sumRbm\":123,\"totalPrice\":1,\"unit\":1,\"upperDifference\":\"\",\"upperLessDifference\":\"\",\"upperRmb\":\"123\",\"upperTotalPrice\":\"1\",\"useing\":\"123\",\"valueCompensate\":123,\"years\":\"1\"}', '用户:执行(新增产权调换协议)操作 新增成功', null);
INSERT INTO `f_system_operator_log` VALUES ('35', null, '协议管理', '2019-02-21 16:51:45', '15', 'luwei', '0:0:0:0:0:0:0:1', 'protocol_operation', '新增产权调换协议', 'com.me.szzc.controller.SwapHouseController', 'addSwapHouse', '1', '{\"address\":\"123\",\"cardNo\":\"123\",\"checkInArea\":123.00,\"company\":\"武汉和鑫居房屋征收服务有限公司\",\"coveredArea\":1,\"createDate\":1550739105301,\"createUserId\":15,\"decorationCompensate\":123,\"deleted\":false,\"floors\":\"1\",\"houseNumber\":\"1\",\"houseOwner\":\"123\",\"houseOwnerNumber\":\"123\",\"interimFee\":123.00,\"mngOffice\":\"武昌区拆迁办\",\"modifiedDate\":1550739105301,\"modifiedUserId\":15,\"months\":\"1\",\"moveHouseFee\":123,\"moveMonth\":1,\"moveReward\":123,\"newHouseAddress\":\"1\",\"otherTermsOne\":\"\",\"price\":123.00,\"proportion\":123,\"publicOwnerNumber\":\"123\",\"seat\":\"1\",\"sumRbm\":123,\"totalPrice\":1,\"unit\":1,\"upperDifference\":\"\",\"upperLessDifference\":\"\",\"upperRmb\":\"123\",\"upperTotalPrice\":\"123\",\"useing\":\"123\",\"valueCompensate\":123,\"years\":\"123\"}', '用户:执行(新增产权调换协议)操作 新增成功', null);

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
INSERT INTO `f_webbaseinfo` VALUES ('1', '征收协议管理', '补偿协议', '个人所有', '版权所有2019 武汉太阳城  粤B2-20160116', '粤B2-20190116', '851620009@qq.com', '1');

-- ----------------------------
-- Table structure for t_notice
-- ----------------------------
DROP TABLE IF EXISTS `t_notice`;
CREATE TABLE `t_notice` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `card_no` varchar(255) DEFAULT NULL COMMENT '协议编号',
  `project_name` varchar(255) DEFAULT NULL COMMENT '项目名称',
  `years` varchar(255) DEFAULT NULL COMMENT '年',
  `months` varchar(255) DEFAULT NULL COMMENT '月',
  `days` varchar(255) DEFAULT NULL COMMENT '日',
  `house_owner` varchar(255) DEFAULT NULL COMMENT '被征收人',
  `lessee` varchar(255) DEFAULT NULL COMMENT '公房承租人',
  `phone` varchar(255) DEFAULT NULL COMMENT '被征收人电话',
  `lessee_phone` varchar(255) DEFAULT NULL COMMENT '承租人电话',
  `useing` varchar(255) DEFAULT NULL COMMENT '房屋用途',
  `check_in_area` varchar(255) DEFAULT '' COMMENT '建筑面积',
  `proportion` decimal(10,2) DEFAULT NULL COMMENT '产权比例',
  `assess_price` decimal(10,2) DEFAULT NULL COMMENT '评估单价',
  `identity_no` int(11) DEFAULT NULL COMMENT '个人身份证号或单位账号',
  `address` varchar(255) DEFAULT NULL COMMENT '被征收房屋地址',
  `calc_value_compensate` varchar(255) DEFAULT NULL COMMENT '房屋价值补偿计算公式',
  `value_compensate` decimal(10,2) DEFAULT NULL COMMENT '房屋价值补偿',
  `value_compensate_bz` varchar(255) DEFAULT NULL COMMENT '房屋价值补偿备注',
  `calc_decoration_compensate` varchar(255) DEFAULT NULL COMMENT '室内装修补偿计算公式',
  `decoration_compensate` decimal(10,2) DEFAULT NULL COMMENT '室内装修补偿',
  `decoration_compensate_bz` varchar(255) DEFAULT NULL COMMENT '室内装修补偿备注',
  `calc_structure_compensate` varchar(255) DEFAULT NULL COMMENT '构建物补偿计算公式',
  `structure_compensate` decimal(10,2) DEFAULT NULL COMMENT '构建物补偿',
  `structure_compensate_bz` varchar(255) DEFAULT NULL COMMENT '构建物补偿计算公式',
  `calc_move_ammeter_fee` varchar(255) DEFAULT NULL COMMENT '电表迁移费计算公式',
  `move_ammeter_fee` decimal(10,2) DEFAULT NULL COMMENT '电表迁移费',
  `move_ammeter_fee_bz` varchar(255) DEFAULT NULL COMMENT '电表迁移费备注',
  `calc_move_water_meter_fee` varchar(255) DEFAULT NULL COMMENT '水表迁移费计算公式',
  `move_water_meter_fee` decimal(10,2) DEFAULT NULL COMMENT '水表迁移费',
  `move_water_meter_fee_bz` varchar(255) DEFAULT NULL COMMENT '水表迁移费备注',
  `calc_move_air_conditioning_fee` varchar(255) DEFAULT NULL COMMENT '空调移机费计算公式',
  `move_air_conditioning_fee` decimal(10,2) DEFAULT NULL COMMENT '空调移机费',
  `move_air_conditioning_fee_bz` varchar(255) DEFAULT NULL COMMENT '空调移机费备注',
  `calc_gas_fee` varchar(255) DEFAULT NULL COMMENT '管道煤气拆装补偿计算公式',
  `gas_fee` decimal(10,2) DEFAULT NULL COMMENT '管道煤气拆装补偿',
  `gas_fee_bz` varchar(255) DEFAULT NULL COMMENT '管道燃气拆装补偿备注',
  `calc_hot_water_compensate` varchar(255) DEFAULT NULL COMMENT '热水器拆装补偿',
  `hot_water_compensate` decimal(10,2) DEFAULT NULL COMMENT '热水器拆装补偿',
  `hot_water_compensate_bz` varchar(255) DEFAULT NULL COMMENT '热水器拆装补偿备注',
  `affiliated1_desc` varchar(255) DEFAULT NULL COMMENT '附属1描述',
  `calc_affiliated1_desc` varchar(255) DEFAULT NULL COMMENT '附属1计算公式',
  `affiliated1_rmb` decimal(10,2) DEFAULT NULL COMMENT '附属1金额',
  `affiliated1_bz` varchar(255) DEFAULT NULL COMMENT '附属1备注',
  `affiliated2_desc` varchar(255) DEFAULT NULL COMMENT '附属2描述',
  `calc_affiliated2_desc` varchar(255) DEFAULT NULL COMMENT '附属2计算公式',
  `affiliated2_rmb` decimal(10,2) DEFAULT NULL COMMENT '附属2金额',
  `affiliated2_bz` varchar(255) DEFAULT NULL COMMENT '附属2备注',
  `affiliated3_desc` varchar(255) DEFAULT NULL COMMENT '附属3描述',
  `calc_affiliated3_desc` varchar(255) DEFAULT NULL COMMENT '附属3计算公式',
  `affiliated3_rmb` decimal(10,2) DEFAULT NULL COMMENT '附属3计算金额',
  `affiliated3_bz` varchar(255) DEFAULT NULL COMMENT '附属3备注',
  `calc_move_house_fee` varchar(1255) DEFAULT NULL COMMENT '搬家费计算公式',
  `move_house_fee` decimal(10,2) DEFAULT NULL COMMENT '搬家费',
  `move_house_fee_bz` varchar(255) DEFAULT NULL COMMENT '搬家费备注',
  `calc_interim_fee` varchar(255) DEFAULT NULL COMMENT '临时安置补偿费计算公式 ',
  `interim_fee` decimal(10,2) DEFAULT NULL COMMENT '临时安置补偿费',
  `interim_fee_bz` varchar(255) DEFAULT NULL COMMENT '临时安置补偿费备注',
  `calc_suspend_business_fee` varchar(255) DEFAULT NULL COMMENT '停产停业损失补助',
  `suspend_business_fee` decimal(10,2) DEFAULT NULL COMMENT '停产停业损失补助',
  `suspend_business_fee_bz` varchar(255) DEFAULT NULL COMMENT '停产停业损失补助备注',
  `calc_rmb_compensate` varchar(255) DEFAULT NULL COMMENT '货币补偿补助计算公式',
  `rmb_compensate` decimal(10,2) DEFAULT NULL COMMENT '货币补偿',
  `rmb_compensate_bz` varchar(255) DEFAULT NULL COMMENT '货币补助备注',
  `calc_life_compensate` varchar(255) DEFAULT NULL COMMENT '生活困难补助计算公式',
  `life_compensate` decimal(10,2) DEFAULT NULL COMMENT '生活困难补助',
  `life_compensate_bz` varchar(255) DEFAULT NULL COMMENT '生活困难补助备注',
  `calc_change_compensate` varchar(255) DEFAULT NULL COMMENT '住改商补助计算公式 ',
  `change_compensate` decimal(10,2) DEFAULT NULL COMMENT '住改商补助',
  `change_compensate_bz` varchar(255) DEFAULT NULL COMMENT '住改商补助备注',
  `calc_move_reward` varchar(255) DEFAULT NULL COMMENT '搬迁奖励计算公式',
  `move_reward` decimal(10,2) DEFAULT NULL COMMENT '搬迁奖励',
  `move_reward_bz` varchar(255) DEFAULT NULL COMMENT '搬迁奖励备注',
  `calc_close_balcony` varchar(255) DEFAULT NULL COMMENT '封闭阳台计算公式',
  `close_balcony` decimal(10,2) DEFAULT NULL COMMENT '封闭阳台',
  `close_balcony_bz` varchar(255) DEFAULT NULL COMMENT '封闭阳台备注',
  `calc_no_check_compensate` varchar(255) DEFAULT NULL COMMENT '未登记房屋补偿计算公式',
  `no_check_compensate` decimal(10,2) DEFAULT NULL COMMENT '未登记房屋补偿',
  `no_check_compensate_bz` varchar(255) DEFAULT NULL COMMENT '未登记房屋补偿备注',
  `other_desc` varchar(255) DEFAULT NULL COMMENT '其它文字描述',
  `calc_other` varchar(255) DEFAULT NULL COMMENT '其他计算公式',
  `other_rmb` decimal(10,2) NOT NULL COMMENT '其他补偿rmb金额',
  `other_bz` varchar(255) DEFAULT NULL COMMENT '其他备注',
  `calc_pay_total` varchar(255) DEFAULT NULL COMMENT '应付款合计计算公式',
  `pay_total` decimal(10,2) DEFAULT NULL COMMENT '应付款金额',
  `pay_total_bz` varchar(255) DEFAULT NULL COMMENT '应付款备注',
  `change_area` decimal(10,2) DEFAULT NULL COMMENT '交换建筑面积',
  `calc_change_rmb` varchar(255) DEFAULT NULL COMMENT '交换金额计算公式',
  `change_rmb` decimal(10,2) DEFAULT NULL COMMENT '交换金额',
  `change_rmb_bz` varchar(255) DEFAULT NULL COMMENT '交换金额备注',
  `real_receive_total` decimal(10,2) DEFAULT NULL COMMENT '实际结算收款',
  `real_pay_total` decimal(10,2) DEFAULT NULL COMMENT '实际结算付款',
  `real_pay_money` varchar(255) DEFAULT NULL COMMENT '实际支付大写元',
  `create_date` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建人员id',
  `modified_date` timestamp NULL DEFAULT NULL COMMENT '修改时间',
  `modified_user_id` bigint(20) DEFAULT NULL COMMENT '修改人员id',
  `deleted` tinyint(4) DEFAULT NULL COMMENT '逻辑删除',
  PRIMARY KEY (`id`,`other_rmb`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_notice
-- ----------------------------
INSERT INTO `t_notice` VALUES ('17', '123', '123', null, null, null, '123', '123', '123', '123', '123', '123', '123.00', '123.00', '123', '123', '123', '123.00', '123', '123', '123.00', '123', '123', '123.00', '123', '123', '123.00', '123', '123', '123.00', '123', '123', '123.00', '123', '123', '123.00', '123', '123', '123.00', '123', '123', '123', '123.00', '123', '123', '123', '123.00', '123', '123', '123', '123.00', '123', '123', '123.00', '123', '123', '123.00', '123', '123', '123.00', '123', '123', '123.00', '123', '123', '123.00', '123', '123', '123.00', '123', '123', '123.00', '123', '123', '123.00', '123', '123', '123.00', '123', '123', '123', '123.00', '123', '123', '123.00', '123', '123.00', '123', '123.00', '123', '123.00', '123.00', '123', '2019-02-23 15:06:17', '15', '2019-02-23 15:06:17', '15', '0');
INSERT INTO `t_notice` VALUES ('18', '123', '123', '2019', '2', '23', '张三', '123', '123', '1308737827', '123', '123', '123.00', '23212.00', '123', '123', '123', '123.00', '123', '123', '123.00', '123', '123', '123.00', '123', '123', '123.00', '123', '123', '123.00', '123', '123', '123.00', '123', '123', '123.00', '123', '123', '123.00', '123', '123', '123', '123.00', '123', '123', '123', '123.00', '123', '123', '123', '123.00', '123', '123', '123.00', '123', '123', '123.00', '123', '123', '123.00', '123', '123', '123.00', '123', '123', '123.00', '123', '123', '123.00', '123', '123', '123.00', '123', '123', '123.00', '123', '123', '123.00', '123', '123', '123', '123.00', '123', '123', '123.00', '123', '123.00', '123', '123.00', '123', '123.00', '123.00', '123', '2019-02-23 15:11:49', '15', '2019-02-23 15:11:49', '15', '0');

-- ----------------------------
-- Table structure for t_rmb_recompense
-- ----------------------------
DROP TABLE IF EXISTS `t_rmb_recompense`;
CREATE TABLE `t_rmb_recompense` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `card_no` varchar(100) DEFAULT NULL COMMENT '协议编号',
  `mng_office` varchar(255) DEFAULT NULL COMMENT '征收部门',
  `company` varchar(255) DEFAULT NULL COMMENT '实施公司',
  `house_owner` varchar(255) DEFAULT NULL COMMENT '被征收人',
  `recompense_plan` varchar(255) DEFAULT NULL COMMENT '补偿计划',
  `address` varchar(255) DEFAULT NULL COMMENT '房屋地址',
  `house_owner_number` varchar(255) DEFAULT NULL COMMENT '房屋所属权证号',
  `public_owner_number` varchar(255) DEFAULT NULL COMMENT '国有土地使用权证号',
  `proportion` decimal(5,2) DEFAULT NULL COMMENT '房屋权属份额',
  `useing` varchar(255) DEFAULT NULL COMMENT '房屋用途',
  `check_in_area` decimal(10,2) DEFAULT NULL COMMENT '登记建筑面积',
  `residence_area` decimal(10,2) DEFAULT NULL COMMENT '住宅面积',
  `operate_area` decimal(10,2) DEFAULT NULL COMMENT '经营面积',
  `office_area` decimal(10,2) DEFAULT NULL COMMENT '办公面积',
  `produce_area` decimal(10,2) DEFAULT NULL COMMENT '生产面积',
  `other_area` decimal(10,2) DEFAULT NULL COMMENT '其他面积',
  `no_check_area` decimal(10,2) DEFAULT NULL COMMENT '未登记面积',
  `value_compensate` decimal(10,2) DEFAULT NULL COMMENT '房屋补偿金额',
  `decoration_compensate` decimal(10,2) DEFAULT NULL COMMENT '装饰装修补偿金额',
  `structure_compensate` decimal(10,2) DEFAULT NULL COMMENT '构建物补偿',
  `move_phone_fee` decimal(10,2) DEFAULT NULL COMMENT '电话移机费',
  `tv_fee` decimal(10,2) DEFAULT NULL COMMENT '有线电视复装费',
  `move_ammeter_fee` decimal(10,2) DEFAULT NULL COMMENT '电表迁移费',
  `move_water_meter_fee` decimal(10,2) DEFAULT NULL COMMENT '水表迁移费',
  `wifi_fee` decimal(10,2) DEFAULT NULL COMMENT '宽带网补偿',
  `move_air_conditioning_fee` decimal(10,2) DEFAULT NULL COMMENT '空调移机费',
  `gas_fee` decimal(10,2) DEFAULT NULL COMMENT '管道燃气拆装费',
  `hot_water_compensate` decimal(10,2) DEFAULT NULL COMMENT '热水器拆装补偿',
  `subtotal` decimal(10,2) DEFAULT NULL COMMENT '小计',
  `move_house_fee` decimal(10,2) DEFAULT NULL COMMENT '搬家费',
  `interim_price` decimal(10,2) DEFAULT NULL COMMENT '过渡单价',
  `interim_month` int(10) DEFAULT NULL COMMENT '过渡月数',
  `interim_fee` decimal(10,2) DEFAULT NULL COMMENT '过渡费',
  `suspend_business_fee` decimal(10,2) DEFAULT NULL COMMENT '停产停业损失补偿',
  `rmb_compensate` decimal(10,2) DEFAULT NULL COMMENT '货币补偿补助',
  `life_compensate` decimal(10,2) DEFAULT NULL COMMENT '生活困难补助',
  `change_compensate` decimal(10,2) DEFAULT NULL COMMENT '住改商补助',
  `move_reward` decimal(10,2) DEFAULT NULL COMMENT '搬迁奖励',
  `close_balcony` decimal(10,2) DEFAULT NULL COMMENT '封闭阳台',
  `no_check_compensate` decimal(10,2) DEFAULT NULL COMMENT '未登记建筑',
  `other_fee` decimal(10,2) DEFAULT NULL COMMENT '其他费用',
  `sum_rbm` decimal(10,2) DEFAULT NULL COMMENT '总计人民币',
  `upper_rmb` varchar(255) DEFAULT NULL COMMENT '大写人民币',
  `other_terms_one` varchar(255) DEFAULT NULL COMMENT '其他约定1',
  `other_terms_two` varchar(255) DEFAULT NULL COMMENT '其他约定2',
  `create_date` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建人id',
  `modified_date` timestamp NULL DEFAULT NULL COMMENT '修改时间',
  `modified_user_id` bigint(20) DEFAULT NULL COMMENT '修改人id',
  `deleted` tinyint(1) NOT NULL COMMENT '逻辑删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='货币补偿协议书';

-- ----------------------------
-- Records of t_rmb_recompense
-- ----------------------------
INSERT INTO `t_rmb_recompense` VALUES ('1', '123', '123', null, '123', null, '123', '123', '123', '123.00', '123', '123.00', '123.00', '123.00', '123.00', '123.00', '123.00', '123.00', '123.00', '123.00', '123.00', '123.00', '123.00', '123.00', '123.00', '123.00', '123.00', '123.00', '123.00', '123.00', '123.00', '123.00', '123', '123.00', '123.00', '123.00', '123.00', '123.00', '123.00', '123.00', '123.00', '123.00', '123.00', '123', '123', '123', null, null, null, null, '1');

-- ----------------------------
-- Table structure for t_settle_accounts
-- ----------------------------
DROP TABLE IF EXISTS `t_settle_accounts`;
CREATE TABLE `t_settle_accounts` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `card_no` varchar(255) DEFAULT NULL COMMENT '协议编号',
  `project_name` varchar(255) DEFAULT NULL COMMENT '项目名称',
  `house_owner` varchar(255) DEFAULT NULL COMMENT '被征收人',
  `lessee` varchar(255) DEFAULT NULL COMMENT '承租人',
  `phone` varchar(255) DEFAULT NULL COMMENT '被征收人电话',
  `lessee_phone` varchar(255) DEFAULT NULL COMMENT '承租人电话',
  `useing` varchar(255) DEFAULT NULL COMMENT '用途',
  `check_in_area` decimal(10,2) DEFAULT NULL COMMENT '房屋建筑面积',
  `in_area` decimal(10,2) DEFAULT NULL COMMENT '套内面积',
  `assess_price` decimal(10,2) DEFAULT NULL COMMENT '房屋评估单价',
  `address` varchar(255) DEFAULT NULL COMMENT '被征收房屋地址',
  `calc_value_compensate` varchar(255) DEFAULT NULL COMMENT '被征收房屋价值补偿计算公式',
  `value_compensate` decimal(10,2) DEFAULT NULL COMMENT '被征收房屋价值补偿金额',
  `value_compensate_bz` varchar(255) DEFAULT NULL COMMENT '被征收房屋价值补偿备注',
  `calc_decoration_compensate` varchar(255) DEFAULT NULL COMMENT '装修折旧补偿',
  `decoration_compensate` decimal(10,2) DEFAULT NULL COMMENT '装修折旧补偿金额',
  `decoration_compensate_bz` varchar(255) DEFAULT NULL COMMENT '装修折旧补偿金额备注',
  `calc_move_house_fee` varchar(1255) DEFAULT NULL COMMENT '搬家费计算公式',
  `move_house_fee` decimal(10,2) DEFAULT NULL COMMENT '搬家费',
  `move_house_fee_bz` varchar(255) DEFAULT NULL COMMENT '搬家费备注',
  `calc_interim_fee` varchar(255) DEFAULT NULL COMMENT '临时安置补偿费计算公式 ',
  `interim_fee` decimal(10,2) DEFAULT NULL COMMENT '临时安置补偿费',
  `interim_fee_bz` varchar(255) DEFAULT NULL COMMENT '临时安置补偿费备注',
  `calc_move_water_meter_fee` varchar(255) DEFAULT NULL COMMENT '水表迁移费计算公式',
  `move_water_meter_fee` decimal(10,2) DEFAULT NULL COMMENT '水表迁移费',
  `move_water_meter_fee_bz` varchar(255) DEFAULT NULL COMMENT '水表迁移费备注',
  `calc_move_ammeter_fee` varchar(255) DEFAULT NULL COMMENT '电表迁移费计算公式',
  `move_ammeter_fee` decimal(10,2) DEFAULT NULL COMMENT '电表迁移费',
  `move_ammeter_fee_bz` varchar(255) DEFAULT NULL COMMENT '电表迁移费备注',
  `calc_move_air_conditioning_fee` varchar(255) DEFAULT NULL COMMENT '空调移机费计算公式',
  `move_air_conditioning_fee` decimal(10,2) DEFAULT NULL COMMENT '空调移机费',
  `move_air_conditioning_fee_bz` varchar(255) DEFAULT NULL COMMENT '空调移机费备注',
  `calc_hot_water_compensate` varchar(255) DEFAULT NULL COMMENT '热水器拆装费计算',
  `hot_water_compensate` decimal(10,2) DEFAULT NULL COMMENT '热水器拆装费',
  `hot_water_compensate_bz` varchar(255) DEFAULT NULL COMMENT '热水器拆装费备注',
  `calc_gas_fee` varchar(255) DEFAULT NULL COMMENT '管道燃气拆装费计算公式',
  `gas_fee` decimal(10,2) DEFAULT NULL COMMENT '管道燃气拆装费',
  `gas_fee_bz` varchar(255) DEFAULT NULL COMMENT '管道燃气拆装费备注',
  `calc_structure_compensate` varchar(255) DEFAULT NULL COMMENT '构建物补偿计算公式',
  `structure_compensate` decimal(10,2) DEFAULT NULL COMMENT '构建物补偿费',
  `structure_compensate_bz` varchar(255) DEFAULT NULL COMMENT '构建物补偿费备注',
  `affiliated_other_desc` varchar(255) DEFAULT NULL COMMENT '其他描述',
  `calc_affiliated_other` varchar(255) DEFAULT NULL COMMENT '附属其他计算',
  `affiliated_other` decimal(10,2) DEFAULT NULL COMMENT '附属金额',
  `affiliated_other_bz` varchar(255) DEFAULT NULL COMMENT '附属其他备注',
  `calc_no_check_compensate` varchar(255) DEFAULT NULL COMMENT '未登记房屋补偿计算公式',
  `no_check_compensate` decimal(10,2) DEFAULT NULL COMMENT '未登记房屋补偿',
  `no_check_compensate_bz` varchar(255) DEFAULT NULL COMMENT '未登记房屋补偿备注',
  `calc_rmb_compensate` varchar(255) DEFAULT NULL COMMENT '货币补偿补助计算公式',
  `rmb_compensate` decimal(10,2) DEFAULT NULL COMMENT '货币补偿',
  `rmb_compensate_bz` varchar(255) DEFAULT NULL COMMENT '货币补助备注',
  `calc_life_compensate` varchar(255) DEFAULT NULL COMMENT '生活困难补助计算公式',
  `life_compensate` decimal(10,2) DEFAULT NULL COMMENT '生活困难补助',
  `life_compensate_bz` varchar(255) DEFAULT NULL COMMENT '生活困难补助备注',
  `calc_change_compensate` varchar(255) DEFAULT NULL COMMENT '住改商补助计算公式 ',
  `change_compensate` decimal(10,2) DEFAULT NULL COMMENT '住改商补助',
  `change_compensate_bz` varchar(255) DEFAULT NULL COMMENT '住改商补助备注',
  `calc_building_area_fee` varchar(255) DEFAULT NULL COMMENT '建筑面积补助计算公式',
  `building_area_fee` decimal(10,2) DEFAULT NULL COMMENT '建筑面积补助',
  `building_area_fee_bz` varchar(255) DEFAULT NULL COMMENT '建筑面积补助',
  `calc_suspend_business_fee` varchar(255) DEFAULT NULL COMMENT '停产停业损失补助',
  `suspend_business_fee` decimal(10,2) DEFAULT NULL COMMENT '停产停业损失补助',
  `suspend_business_fee_bz` varchar(255) DEFAULT NULL COMMENT '停产停业损失补助备注',
  `calc_no_move_compensate` varchar(255) DEFAULT NULL COMMENT '不可移动设备设施补助计算公式',
  `no_move_compensate` decimal(10,2) DEFAULT NULL COMMENT '不可移动设备设施补助',
  `no_move_compensate_bz` varchar(255) DEFAULT NULL COMMENT '不可移动设备设施补助备注',
  `calc_move_reward` varchar(255) DEFAULT NULL COMMENT '搬迁奖励计算公式',
  `move_reward` decimal(10,2) DEFAULT NULL COMMENT '搬迁奖励',
  `move_reward_bz` varchar(255) DEFAULT NULL COMMENT '搬迁奖励备注',
  `calc_rmb_move_reward` varchar(255) DEFAULT NULL COMMENT '货币搬迁奖励计算公式',
  `rmb_move_reward` decimal(10,2) DEFAULT NULL COMMENT '货币搬迁奖励',
  `rmb_move_reward_bz` varchar(255) DEFAULT NULL COMMENT '货币搬迁奖励备注',
  `calc_small_area_reward` varchar(255) DEFAULT NULL COMMENT '小面积住房搬迁奖励计算公式',
  `small_area_reward` decimal(10,2) DEFAULT NULL COMMENT '小面积住房搬迁奖励',
  `small_area_reward_bz` varchar(255) DEFAULT NULL COMMENT '小面积住房搬迁奖励备注',
  `other_desc` varchar(255) DEFAULT NULL COMMENT '其他描述',
  `calc_other` varchar(255) DEFAULT NULL COMMENT '其他计算公式',
  `other_rmb` decimal(10,2) DEFAULT NULL COMMENT '其他补偿rmb金额',
  `other_bz` varchar(255) DEFAULT NULL COMMENT '其他备注',
  `calc_sum_compensate` varchar(255) DEFAULT NULL COMMENT '征收房屋补偿合计计算公式',
  `sum_compensate` decimal(10,2) DEFAULT NULL COMMENT '合计补偿',
  `sum_compensate_bz` varchar(255) DEFAULT NULL COMMENT '补偿合计备注',
  `change_area` decimal(10,2) DEFAULT NULL COMMENT '产权调换建筑面积',
  `calc_change_area` varchar(255) DEFAULT NULL COMMENT '产权调换建筑面积计算公式',
  `calcm` decimal(10,2) DEFAULT NULL COMMENT '产权调换，其中参与计算面积',
  `price` decimal(10,2) DEFAULT NULL COMMENT '房屋置换金额',
  `house_money` decimal(10,2) DEFAULT NULL COMMENT '房屋置换金额',
  `calc_deduction` varchar(255) DEFAULT NULL COMMENT '计算已抵扣安置房款',
  `deduction` decimal(10,2) DEFAULT NULL COMMENT '已抵扣安置房款',
  `deduction_bz` varchar(255) DEFAULT '' COMMENT '已抵扣安置房款备注',
  `pay_total` decimal(10,2) DEFAULT NULL COMMENT '应付合计',
  `receive_total` decimal(10,2) DEFAULT NULL COMMENT '应收合计',
  `pay_money` varchar(255) DEFAULT NULL COMMENT '应付合计大写元',
  `receive_money` varchar(255) DEFAULT NULL COMMENT '应收合计大写元',
  `create_date` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建人id',
  `modified_date` timestamp NULL DEFAULT NULL COMMENT '修改时间',
  `modified_user_id` bigint(20) DEFAULT NULL COMMENT '修改人员id',
  `deleted` tinyint(4) DEFAULT NULL COMMENT '逻辑删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_settle_accounts
-- ----------------------------
INSERT INTO `t_settle_accounts` VALUES ('1', '110', null, '张三', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '0');
INSERT INTO `t_settle_accounts` VALUES ('2', '123', '123', '1231', '123', '123', '123', '123', '123.00', null, '123.00', '123', '123', '123.00', '123', '123', '123.00', '123', '123', '123.00', '123', '123', '123.00', '123', '123', '123.00', '123', '123', '123.00', '123', '123', '123.00', '123', '123', '123.00', '123', '123', '123.00', '123', '123', '123.00', '123', null, '123', '123.00', '123', '123', '123.00', '123', '123', '123.00', '123', '123', '123.00', '123', '123', '123.00', '123', '123', '123.00', '123', '123', '123.00', '123', '123', '123.00', '123', '123', '123.00', '123', '123', '123.00', '123', '123', '123.00', '123', '123', '123', '123.00', '123', '123', '123.00', '123', '123.00', '123', '123.00', '123.00', null, '123', '123.00', '123', '123.00', '123.00', '123', '123', '2019-03-01 11:25:05', '15', '2019-03-01 11:25:05', '15', '0');
INSERT INTO `t_settle_accounts` VALUES ('3', '1234', '1234', '1234', '1234', '1234', '1234', '1234', '1234.00', '1234.00', '1234.00', '1234', '1234', '1234.00', '1234', '1234', '1234.00', '1234', '1234', '1234.00', '1234', '1234', '1234.00', '1234', '1234', '1234.00', '1234', '1234', '1234.00', '1234', '1234', '1234.00', '1234', '1234', '1234.00', '1234', '1234', '1234.00', '1234', '1234', '1234.00', '1234', null, '1234', '1234.00', '1234', '1234', '1234.00', '1234', '1234', '1234.00', '1234', '1234', '1234.00', '1234', '1234', '1234.00', '1234', '1234', '1234.00', '1234', '1234', '1234.00', '1234', '1234', '1234.00', '1234', '1234', '1234.00', '1234', '1234', '1234.00', '1234', '1234', '1234.00', '1234', '1234', '1234', '1234.00', '1234', '1234', '1234.00', '1234', '1234.00', '1234', '1234.00', '1234.00', null, '1234', '1234.00', '1234', '1234.00', '1234.00', '1234', '1234', '2019-03-01 11:27:32', '15', '2019-03-01 11:27:32', '15', '0');
INSERT INTO `t_settle_accounts` VALUES ('4', '1', '1', '1', '1', '1', '1', '1', '1.00', '1.00', '1.00', '1', '1', '1.00', '1', '1', '1.00', '1', '1', '1.00', '1', '1', '1.00', '1', '1', '1.00', '1', '1', '1.00', '1', '1', '1.00', '1', '1', '1.00', '1', '1', '1.00', '1', '1', '1.00', '1', '补偿其他描述', '1', '1.00', '1', '1', '1.00', '1', '1', '1.00', '1', '1', '1.00', '1', '1', '1.00', '1', '1', '1.00', '1', '1', '1.00', '1', '1', '1.00', '1', '1', '1.00', '1', '1', '1.00', '1', '1', '1.00', '1', '总的其他描述', '1', '1.00', '1', '1', '1.00', '1', '1.00', '1', '1.00', '1.00', null, '1', '1.00', '1', '1.00', '1.00', '1', '1', '2019-03-01 11:30:20', '15', '2019-03-01 11:30:20', '15', '0');
INSERT INTO `t_settle_accounts` VALUES ('5', '2', '2', '2', '2', '2', '2', '2', '2.00', '2.00', '2.00', '2', '2', '2.00', '2', '2', '2.00', '2', '2', '2.00', '2', '2', '2.00', '2', '2', '2.00', '2', '2', '2.00', '2', '2', '2.00', '2', '2', '2.00', '2', '2', '2.00', '2', '2', '2.00', '2', '2', '2', '2.00', '2', '2', '2.00', '2', '2', '2.00', '2', '2', '2.00', '2', '2', '2.00', '2', '2', '2.00', '2', '2', '2.00', '2', '2', '2.00', '2', '2', '2.00', '2', '2', '2.00', '2', '2', '2.00', '2', '2', '2', '2.00', '2', '2', '2.00', '2', '2.00', '2', '2.00', '2.00', '3333.00', '2', '2.00', '2', '2.00', '2.00', '2', '2', '2019-03-01 11:52:02', '15', '2019-03-01 11:52:02', '15', '0');
INSERT INTO `t_settle_accounts` VALUES ('6', '123', '红岭三期', '123', '123', '123', '123', '住宅', '130.00', '121.00', '23600.00', '123', '123', '123.00', '123', '123', '123.00', '123', '123', '123.00', '123', '123', '123.00', '123', '123', '123.00', '123', '123', '123.00', '123', '123', '123.00', '123', '123', '123.00', '123', '123', '123.00', '123', '123', '123.00', '123', '', '123', '123.00', '123', '123', '123.00', '123', '123', '123.00', '123', '123', '123.00', '123', '123', '123.00', '123', '123', '123.00', '123', '123', '123.00', '123', '123', '123.00', '123', '123', '123.00', '123', '123', '123.00', '123', '123', '123.00', '123', '123', '123', '123.00', '123', '123', '123.00', '123', '86.00', '123', '80.00', '235142.00', '639.00', '123', '123.00', '123', '123.00', '123.00', '陆佰', '玖佰', '2019-03-01 17:09:12', '15', '2019-03-01 17:09:12', '15', '0');
INSERT INTO `t_settle_accounts` VALUES ('7', '20190317A01', '金地太阳城一期改建项目', '孙峰', '', '13072747751', '', '住宅', '177.96', '150.00', '21740.00', '关山大道金地太阳城369号8栋1单元', '177.96*21740*1.12', '4333112.45', '', '177.96*550', '97878.00', '', '', '2000.00', '', '177.96*45*34', '272278.80', '', '0*500+2*100', '200.00', '', '1*500+1*500', '500.00', '', '1*300+4*200+0*100', '1100.00', '', '200', '200.00', '', '', null, '', '', null, '', '', '', null, '', '', null, '', '', null, '', '', null, '', '', null, '', '', null, '', '', null, '', '', null, '', '177.96*21740*0.02', '77377.01', '', '', null, '', '', null, '', '无烟灶台', '1000', '1000.00', '', '', '4785646.26', '', '139.38', '', '50.00', '3000.00', '4201252.00', '', null, '', '584394.26', null, '伍拾捌万肆仟叁佰玖拾肆元贰角伍分', '', '2019-03-17 00:48:05', '15', '2019-03-17 00:48:05', '15', '0');

-- ----------------------------
-- Table structure for t_swap_house
-- ----------------------------
DROP TABLE IF EXISTS `t_swap_house`;
CREATE TABLE `t_swap_house` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `card_no` varchar(100) DEFAULT NULL COMMENT '协议编号',
  `mng_office` varchar(255) DEFAULT NULL COMMENT '征收部门',
  `company` varchar(255) DEFAULT NULL COMMENT '实施公司',
  `house_owner` varchar(255) DEFAULT NULL COMMENT '被征收人',
  `recompense_plan` varchar(255) DEFAULT NULL COMMENT '补偿计划',
  `address` varchar(255) DEFAULT NULL COMMENT '房屋地址',
  `house_owner_number` varchar(255) DEFAULT NULL COMMENT '房屋所属权证号',
  `public_owner_number` varchar(255) DEFAULT NULL COMMENT '国有土地使用权证号',
  `proportion` decimal(5,2) DEFAULT NULL COMMENT '房屋权属份额',
  `useing` varchar(255) DEFAULT NULL COMMENT '房屋用途',
  `check_in_area` decimal(10,2) DEFAULT NULL COMMENT '登记建筑面积',
  `residence_area` decimal(10,2) DEFAULT NULL COMMENT '住宅面积',
  `operate_area` decimal(10,2) DEFAULT NULL COMMENT '经营面积',
  `office_area` decimal(10,2) DEFAULT NULL COMMENT '办公面积',
  `produce_area` decimal(10,2) DEFAULT NULL COMMENT '生产面积',
  `other_area` decimal(10,2) DEFAULT NULL COMMENT '其他面积',
  `no_check_area` decimal(10,2) DEFAULT NULL COMMENT '未登记面积',
  `value_compensate` decimal(10,2) DEFAULT NULL COMMENT '房屋补偿金额',
  `decoration_compensate` decimal(10,2) DEFAULT NULL COMMENT '装饰装修补偿金额',
  `structure_compensate` decimal(10,2) DEFAULT NULL COMMENT '构建物补偿',
  `move_phone_fee` decimal(10,2) DEFAULT NULL COMMENT '电话移机费',
  `tv_fee` decimal(10,2) DEFAULT NULL COMMENT '有线电视复装费',
  `move_ammeter_fee` decimal(10,2) DEFAULT NULL COMMENT '电表迁移费',
  `move_water_meter_fee` decimal(10,2) DEFAULT NULL COMMENT '水表迁移费',
  `wifi_fee` decimal(10,2) DEFAULT NULL COMMENT '宽带网补偿',
  `move_air_conditioning_fee` decimal(10,2) DEFAULT NULL COMMENT '空调移机费',
  `gas_fee` decimal(10,2) DEFAULT NULL COMMENT '管道燃气拆装费',
  `hot_water_compensate` decimal(10,2) DEFAULT NULL COMMENT '热水器拆装补偿',
  `subtotal` decimal(10,2) DEFAULT NULL COMMENT '小计',
  `move_house_fee` decimal(10,2) DEFAULT NULL COMMENT '搬家费',
  `interim_price` decimal(10,2) DEFAULT NULL COMMENT '过渡单价',
  `interim_month` int(10) DEFAULT NULL COMMENT '过渡月数',
  `interim_fee` decimal(10,2) DEFAULT NULL COMMENT '过渡费',
  `suspend_business_fee` decimal(10,2) DEFAULT NULL COMMENT '停产停业损失补偿',
  `life_compensate` decimal(10,2) DEFAULT NULL COMMENT '生活困难补助',
  `change_compensate` decimal(10,2) DEFAULT NULL COMMENT '住改商补助',
  `move_reward` decimal(10,2) DEFAULT NULL COMMENT '搬迁奖励',
  `close_balcony` decimal(10,2) DEFAULT NULL COMMENT '封闭阳台',
  `no_check_compensate` decimal(10,2) DEFAULT NULL COMMENT '未登记建筑',
  `other_fee` decimal(10,2) DEFAULT NULL COMMENT '其他费用',
  `sum_rbm` decimal(10,2) DEFAULT NULL COMMENT '总计人民币',
  `upper_rmb` varchar(255) DEFAULT NULL COMMENT '大写人民币',
  `new_house_address` varchar(255) DEFAULT NULL COMMENT '新房地址',
  `seat` varchar(255) DEFAULT NULL COMMENT '楼栋',
  `unit` int(255) DEFAULT NULL COMMENT '单元',
  `floors` int(255) DEFAULT NULL COMMENT '楼层',
  `house_number` varchar(255) DEFAULT NULL COMMENT '房号',
  `covered_area` decimal(10,2) DEFAULT NULL COMMENT '该房屋建筑面积',
  `price` decimal(10,2) DEFAULT NULL COMMENT '单价',
  `total_price` decimal(10,2) DEFAULT NULL COMMENT '总价',
  `upper_total_price` varchar(255) DEFAULT NULL COMMENT '总价大写',
  `years` varchar(255) DEFAULT NULL COMMENT '交房年份',
  `months` varchar(255) DEFAULT NULL COMMENT '交房月份',
  `difference` decimal(10,2) DEFAULT NULL COMMENT '差额：征收补偿款总额大于预购房款',
  `upper_difference` varchar(255) DEFAULT NULL COMMENT '大写差额',
  `less_difference` decimal(10,2) DEFAULT NULL COMMENT '差额：收补偿总额小于预购房款',
  `upper_less_difference` varchar(255) DEFAULT NULL COMMENT '大写差额',
  `move_month` int(255) DEFAULT NULL COMMENT '过渡几个月',
  `other_terms_one` varchar(255) DEFAULT NULL COMMENT '其他约定1',
  `create_date` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建人id',
  `modified_date` timestamp NULL DEFAULT NULL COMMENT '修改时间',
  `modified_user_id` bigint(20) DEFAULT NULL COMMENT '修改人id',
  `deleted` tinyint(4) DEFAULT NULL COMMENT '逻辑删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='产权调换协议书';

-- ----------------------------
-- Records of t_swap_house
-- ----------------------------
INSERT INTO `t_swap_house` VALUES ('1', '123', '123', '武汉和鑫居房屋征收服务有限公司', '李四', '33', '123', '123', '123', '123.00', '123', '123.00', '123.00', '123.00', '123.00', '123.00', '123.00', '123.00', '123.00', '123.00', '123.00', '123.00', '123.00', '123.00', '123.00', '123.00', '123.00', '123.00', '123.00', null, '123.00', '123.00', '123', '123.00', '123.00', '123.00', '123.00', '123.00', '123.00', '123.00', '123.00', '123.00', '123', '123', '123', '123', '123', '123', '123.00', '123.00', '123.00', '123', '123', '123', '123.00', '123', '123.00', '123', '123', '123', null, null, null, null, '0');
INSERT INTO `t_swap_house` VALUES ('2', '123', '武昌区拆迁办', null, '张三', '22', '123', '123', '123', '123.00', '123', '123.00', '123.00', '123.00', '123.00', '123.00', '123.00', '123.00', '7543215.00', '9000.00', '123.00', '123.00', '123.00', '123.00', '123.00', '123.00', '123.00', '123.00', '123.00', '98765.00', '123.00', '123.00', '15', '123.00', '123.00', '123.00', '123.00', '123.00', '123.00', '123.00', '123.00', '123.00', '123', '123', '123', '123', '123', '123', '123.00', '123.00', '123.00', '123', '123', '123', '123.00', '123', '123.00', '123', '123', '123', null, null, '2019-02-20 21:14:17', '15', '0');
INSERT INTO `t_swap_house` VALUES ('3', '123', '123', '武汉和鑫居房屋征收服务有限公司', '王五', '33', '123', '123', '123', '123.00', '123', '123.00', null, null, null, null, null, null, '123.00', '123.00', null, null, null, null, null, null, null, null, null, null, '123.00', null, null, '123.00', null, null, null, '123.00', null, null, null, '123.00', '123', '1', '1', '1', '1', '1', '1.00', '1.00', '1.00', '1', '1', '1', null, '', null, '', '1', '', '2019-02-18 21:48:59', '15', '2019-02-20 21:27:45', '15', '1');
INSERT INTO `t_swap_house` VALUES ('4', '123', '123', '武汉和鑫居房屋征收服务有限公司', '1234', '44', '123', '123', '123', '123.00', '123', '123.00', null, null, null, null, null, null, '123.00', '123.00', null, null, null, null, null, null, null, null, null, null, '123.00', null, null, '123.00', null, null, null, '123.00', null, null, null, '123.00', '123', '1', '1', '1', '1', '1', '1.00', '1.00', '1.00', '1', '1', '1', null, '', null, '', '1', '', '2019-02-21 15:44:13', '15', '2019-02-21 15:44:13', '15', '0');
INSERT INTO `t_swap_house` VALUES ('5', '123', '123', '武汉和鑫居房屋征收服务有限公司', '张三1', '55', '123', '123', '123', '123.00', '123', '123.00', null, null, null, null, null, null, '123.00', '123.00', null, null, null, null, null, null, null, null, null, null, '123.00', null, null, '123.00', null, null, null, '123.00', null, null, null, '123.00', '123', '1', '1', '1', '1', '1', '1.00', '1.00', '1.00', '1', '1', '1', null, '', null, '', '1', '', '2019-02-21 16:49:12', '15', '2019-02-21 16:49:12', '15', '0');
INSERT INTO `t_swap_house` VALUES ('6', '123', '武昌区拆迁办', '武汉和鑫居房屋征收服务有限公司', '1232', '66', '123', '123', '123', '123.00', '123', '123.00', null, null, null, null, null, null, '123.00', '123.00', null, null, null, null, null, null, null, null, null, null, '123.00', null, null, '123.00', null, null, null, '123.00', null, null, null, '123.00', '123', '1', '1', '1', '1', '1', '1.00', '123.00', '1.00', '123', '123', '1', null, '', null, '', '1', '', '2019-02-21 16:51:45', '15', '2019-02-21 16:51:45', '15', '0');
