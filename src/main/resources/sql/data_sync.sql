--1、bbfang 20210616 房源新增字段实测面积、实测总价、身份证号码、审计公司
ALTER TABLE `szzc`.`r_change`
  CHANGE `r_area` `r_area` VARCHAR(20) CHARSET utf8 COLLATE utf8_general_ci NULL COMMENT '预测面积',
  CHANGE `r_total_price` `r_total_price` VARCHAR(20) CHARSET utf8 COLLATE utf8_general_ci NULL COMMENT '预测总价',
  ADD COLUMN `r_real_area` VARCHAR(20) CHARSET utf8 COLLATE utf8_general_ci NULL COMMENT '实测面积' AFTER `r_remark`,
  ADD COLUMN `r_real_total_price` VARCHAR(20) CHARSET utf8 COLLATE utf8_general_ci NULL COMMENT '实测总价' AFTER `r_real_area`,
  ADD COLUMN `r_idcard` VARCHAR(128) CHARSET utf8 COLLATE utf8_general_ci NULL COMMENT '身份证号码' AFTER `r_real_total_price`,
  ADD COLUMN `r_audit_firm` VARCHAR(50) CHARSET utf8 COLLATE utf8_general_ci NULL COMMENT '审计公司' AFTER `r_idcard`;