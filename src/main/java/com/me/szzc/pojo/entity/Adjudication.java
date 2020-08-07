package com.me.szzc.pojo.entity;

import lombok.Data;

/**
 * 字诀号信息
 * @author luwei
 * @date 2019/11/25
 */

@Data
public class Adjudication {

    /**
     * 政府征诀字：年
     */
    private String govYear;

    /**
     * 政府征诀字：月
     */
    private String govMonth;

    /**
     * 政府征诀字：日
     */
    private String govDay;

    /**
     * 政府征诀字：文件年号
     */
    private String adjuLetter;

    /**
     * 政府征诀字：文件字号
     */
    private String adjuNum;

    /**
     * 政府公告：年
     */
    private String noticeYear;

    /**
     * 政府公告：月
     */
    private String noticeMonth;

    /**
     * 政府公告：日
     */
    private String noticeDay;


    /**
     * 返回默认的决字信息
     * @return
     */
    public static Adjudication getDefaultAdju() {
        Adjudication adjudication = new Adjudication();
        adjudication.setGovYear("2019");
        adjudication.setGovMonth("7");
        adjudication.setGovDay("19");
        adjudication.setAdjuLetter("2019");
        adjudication.setAdjuNum("4");
        adjudication.setNoticeYear("2019");
        adjudication.setNoticeMonth("7");
        adjudication.setNoticeDay("19");
        return adjudication;
    }

}
