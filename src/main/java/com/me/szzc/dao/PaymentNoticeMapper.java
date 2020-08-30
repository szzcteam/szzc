package com.me.szzc.dao;

import com.me.szzc.pojo.vo.PaymentNoticeVO;
import org.apache.ibatis.annotations.Param;

public interface PaymentNoticeMapper {

    //根据房源ID查询通知书
    PaymentNoticeVO selectOneByChangeId(Long id);

    PaymentNoticeVO selectOneByIdAndChangeId(@Param("id") Long id,
                                             @Param("changeId") Long changeId);

    int addPaymentNotice(PaymentNoticeVO paymentNoticeVO);

    int updatePaymentNotice(PaymentNoticeVO paymentNoticeVO);
}
