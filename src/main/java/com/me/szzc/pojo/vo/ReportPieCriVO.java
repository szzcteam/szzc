package com.me.szzc.pojo.vo;

import lombok.Data;

import java.util.List;

/**
 * 饼形图条件
 * @author luwei
 * @date 2020/5/2
 */
@Data
public class ReportPieCriVO {

    //片区集合
    private List<Long> areaIdList;

    //开始时间
    private String startDate;

    //结束时间
    private String endDate;
}
