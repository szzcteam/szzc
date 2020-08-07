--该表用于记录sql脚本

#2019/11/19 房源管理表增加字段项目编码,用于区分导入房源信息所属项目
ALTER TABLE `r_change`
    ADD COLUMN `item_code` VARCHAR(32) NOT NULL COMMENT '项目编码' AFTER `status`;

#2019/11/19 将之前所有导入的房源归属于明伦街项目
UPDATE r_change SET item_code = 'A001001';

#2019-11-19 片区管理增加管辖项目
ALTER TABLE `t_area`
ADD COLUMN `project_code`  varchar(50) NULL COMMENT '管辖项目';

#设置当前片区为明伦街
update t_area set project_code = 'A001001';

#2019/11/25 打印坐标表增加字段项目编码,并维护明伦街项目编码。
ALTER TABLE `field_coordinate`
    ADD COLUMN `item_code` VARCHAR(32) NOT NULL COMMENT '项目编码' AFTER `del`;

UPDATE field_coordinate SET item_code = 'A001001';

#结算单增加新房名称
ALTER TABLE `t_settle_accounts`
ADD COLUMN `new_house_name`  varchar(500) NULL COMMENT '新房名称' AFTER `address`;

#产权调换增加 承租人标识
ALTER TABLE `t_swap_house`
ADD COLUMN `is_lessee_flag`  tinyint NULL COMMENT '是否是承租人，0否，1是，默认是0' AFTER `other_terms_one`,
ADD COLUMN `adjudication_json`  varchar(1000) NULL COMMENT '字诀信息，json串格式' AFTER `is_lessee_flag`;

#货币补偿增加 承租人标识
ALTER TABLE `t_rmb_recompense`
ADD COLUMN `is_lessee_flag`  tinyint NULL COMMENT '是否是承租人，0否，1是，默认是0' AFTER `other_terms_two`,
ADD COLUMN `adjudication_json`  varchar(1000) NULL COMMENT '字诀信息，json串格式' AFTER `is_lessee_flag`;

#将历史数据统一设置为 被征收人
update t_rmb_recompense set is_lessee_flag = false where is_lessee_flag is null;
update t_swap_house set is_lessee_flag = false where is_lessee_flag is null;


#货币补偿增加标识，审计N天后，甲方付款给乙方
ALTER TABLE `t_rmb_recompense`
ADD COLUMN `after_day_audit`  int NULL COMMENT '审计N天后，甲方付款给乙方' AFTER `upper_rmb`;

#将以前的历史记录，明伦街的天数固定为15
update t_rmb_recompense set after_day_audit = 15 where after_day_audit is null;

#产权调换增加标识，审计N天后，甲方付款给乙方
ALTER TABLE `t_swap_house`
ADD COLUMN `after_day_audit`  int NULL COMMENT '审计N天后，甲方付款给乙方' AFTER `upper_rmb`;

#将以前的历史记录，明伦街的天数固定为15
update t_swap_house set after_day_audit = 15 where after_day_audit is null;

#增加系统参数
insert into f_systemargs(fkey, ftype, fdescription, fvalue, version)
values('rmb_after_day_audit',1,'会计机构审核出具报告N天后，甲方付款给乙方','15',0);


#菜单修改
update f_security set fdescription = '明伦街新增', fname='明伦街新增' where furl = 'ssadmin/addProtocol.html';

-- 插入紫阳村的菜单信息
insert into f_security(fdescription,fname, fpriority, fparentid, furl)
values('紫阳村新增','紫阳村新增',4,3,'ssadmin/addProtocol.html?projectCode=B001001');


-- 插入按钮权限
insert into f_role_security(fsecurityid, froleid)
values(41,1);

--坐标
DROP TABLE IF EXISTS `field_coordinate`;
CREATE TABLE `field_coordinate` (
  `id` double NOT NULL AUTO_INCREMENT,
  `order` double NOT NULL,
  `table_name` varchar(384) NOT NULL,
  `code` varchar(180) DEFAULT NULL,
  `code_name` varchar(384) DEFAULT NULL,
  `abscissa` double NOT NULL,
  `ordinate` double NOT NULL,
  `width` double NOT NULL DEFAULT '300',
  `height` double NOT NULL DEFAULT '26',
  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `update_date` timestamp NULL DEFAULT NULL,
  `font_name` varchar(180) NOT NULL DEFAULT '新宋体',
  `font_size` double NOT NULL DEFAULT '12',
  `del` tinyint(1) NOT NULL DEFAULT '1',
  `item_code` varchar(32) NOT NULL DEFAULT 'B001001' COMMENT '项目编码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=577 DEFAULT CHARSET=utf8;

#2019/12/17 bbfang 新增字段备注
ALTER TABLE `szzc`.`r_change`
   ADD COLUMN `r_remark` VARCHAR(2000) NULL COMMENT '备注' AFTER `item_code`;

-- 2019-12-17 增加编辑房源按钮
insert into f_security(fdescription,fname, fpriority, fparentid, furl)
values('编辑备注','编辑备注',4,27,'ssadmin/roomChange/updateRemark.html');


insert into f_role_security(fsecurityid, froleid)
values(42,1);

-- 操作内容加大
ALTER TABLE `f_system_operator_log`
MODIFY COLUMN `operator_content`  varchar(5000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作内容' AFTER `request_parameters`;


-- 新增紫阳村的太阳能热水器补偿
insert into f_systemargs(fkey, ftype, fdescription, fvalue, version)
values('zyc_solar_water_heaters', '1', '紫阳村太阳能热水器补偿价格', 2000, 0);



-- 紫阳村空调价格
insert into f_systemargs(fkey, ftype, fdescription, fvalue, version)
values('zyc_air_conditioner_shutter', '1', '紫阳村窗机(单元：元)', 200, 0);

insert into f_systemargs(fkey, ftype, fdescription, fvalue, version)
values('zyc_air_conditioner_hang', '1', '紫阳村挂机(单元：元)', 300, 0);

insert into f_systemargs(fkey, ftype, fdescription, fvalue, version)
values('zyc_air_conditioner_cabinet', '1', '紫阳村柜机(单元：元)', 500, 0);

insert into f_systemargs(fkey, ftype, fdescription, fvalue, version)
values('zyc_water_heater', '1', '紫阳村电热水器迁移费', 300, 0);


-- 2019-12-19 之前脚本 分割线 -----------------------------------------------------------------------------

-- 结算单增加签约时间字段
ALTER TABLE `t_settle_accounts`
ADD COLUMN `signing_date`  timestamp NULL COMMENT '签约时间' AFTER `signing_status`;

-- 与之前的数据保持一直，签约时间 = 创建时间
update t_settle_accounts set signing_date = create_date;

-- 增加签约时间按钮
insert into f_security(fdescription,fname, fpriority, fparentid, furl)
values('修改签约时间','修改签约时间',4,3,'ssadmin/settleAccounts/update-sign-date.html');


insert into f_role_security(fsecurityid, froleid)
values(43,1);


-------------- 12.28号脚本

-- 插入西城壕的菜单信息
insert into f_security(fdescription,fname, fpriority, fparentid, furl)
values('西城壕新增','西城壕新增',4,3,'ssadmin/addProtocol.html?projectCode=B001002');



-- 插入按钮权限
insert into f_role_security(fsecurityid, froleid)
values(44,1);




-- 插入电二南侧的菜单信息
insert into f_security(fdescription,fname, fpriority, fparentid, furl)
values('电二南侧新增','电二南侧新增',4,3,'ssadmin/addProtocol.html?projectCode=C001001');



-- 插入按钮权限
insert into f_role_security(fsecurityid, froleid)
values(45,1);



-- 给系统参数加备注
update f_systemargs set fdescription = concat(fdescription, "，明伦街、电车二公司南侧共用") where fkey  = 'air_conditioner_shutter';

update f_systemargs set fdescription = concat(fdescription, "，明伦街、电车二公司南侧共用") where fkey  = 'air_conditioner_hang';

update f_systemargs set fdescription = concat(fdescription, "，明伦街、电车二公司南侧共用") where fkey  = 'air_conditioner_cabinet';

update f_systemargs set fdescription = concat(fdescription, "，明伦街、电车二公司南侧共用") where fkey  = 'solar_water_heaters';





ALTER TABLE `t_rmb_recompense`
ADD COLUMN `other_fee_detail`  varchar(1000) NULL COMMENT '其他费用信息描述' AFTER `other_fee`,
ADD COLUMN `consign_name`  varchar(255) NULL COMMENT '委托人姓名' AFTER `deleted`,
ADD COLUMN `consign_identity_no`  varchar(255) NULL COMMENT '委托人身份证号' AFTER `consign_name`,
ADD COLUMN `is_trade_house`  tinyint(255) NULL COMMENT '是否申购交换新房,1是，0否' AFTER `consign_identity_no`,
ADD COLUMN `trade_house_json`  varchar(3000) NULL COMMENT '申购新房的相关信息' AFTER `is_trade_house`;



