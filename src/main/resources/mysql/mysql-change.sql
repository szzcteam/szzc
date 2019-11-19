--该表用于记录sql脚本

#2019/11/19 房源管理表增加字段项目编码,用于区分导入房源信息所属项目
ALTER TABLE `r_change`
    ADD COLUMN `item_code` VARCHAR(32) NOT NULL COMMENT '项目编码' AFTER `status`;

#2019/11/19 将之前所有导入的房源归属于明伦街项目
UPDATE r_change SET item_code = 'A001001';