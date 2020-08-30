package com.me.szzc.pojo.vo;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class PaymentNoticeVO {

    //自增主键ID
    private Long id;

    //房源ID
    private Long changeId;

    /**
     * 房源项目名称
     */

    private String name;

    /**
     * 协议编号
     */
    private String cardNo;

    /**
     * 被征收人
     */
    private String houseOwner;

    /**
     * 身份证号
     */
    private String identityNo;

    /**
     * 片区所属项目
     */
    private String projectCode;

    /**
     * 栋
     */
    private String ridgepole;

    /**
     * 单元
     */
    private String unit;

    /**
     * 楼层
     */
    private String floor;

    /**
     * 号
     */
    private String mark;

    /**
     * 该房屋建筑面积
     */
    private String area;

    /**
     * 总计人民币
     */
    private String sumRbm;

    /**
     * 应付-元
     **/
    private String payParm1;

    /**
     * 应付-拾
     **/
    private String payParm2;

    /**
     * 应付-佰
     **/
    private String payParm3;

    /**
     * 应付-仟
     **/
    private String payParm4;

    /**
     * 应付-万
     **/
    private String payParm5;

    /**
     * 应付-拾万
     **/
    private String payParm6;

    /**
     * 应付-佰万
     **/
    private String payParm7;

    /**
     * 应付-仟万
     **/
    private String payParm8;

    /**
     * 乙方同意将N元由甲方支付给调换房建设单位
     */
    private String transferRmb;

    /**
     * 需补缴房款
     */
    private String difference;

    /**
     * 需退还房款
     */
    private String lessDifference;
}
