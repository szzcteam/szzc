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

#增加系统参数
insert into f_systemargs(fkey, ftype, fdescription, fvalue, version)
values('rmb_after_day_audit',1,'会计机构审核出具报告N天后，甲方付款给乙方','15',0);
