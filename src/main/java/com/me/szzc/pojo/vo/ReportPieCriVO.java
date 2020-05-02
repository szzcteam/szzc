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

    private List<Long> areaIdList;

    private String startDate;

    private String endDate;
}
