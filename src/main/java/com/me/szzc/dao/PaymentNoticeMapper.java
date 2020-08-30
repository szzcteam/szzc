package com.me.szzc.dao;

import com.me.szzc.pojo.vo.PaymentNoticeVO;
import org.apache.ibatis.annotations.Param;

public interface PaymentNoticeMapper {

    PaymentNoticeVO selectOneByChangeId(Long id);

    PaymentNoticeVO selectOneByIdAndChangeId(@Param("id") Long id,
                                             @Param("changeId") Long changeId);

    int addPaymentNotice(PaymentNoticeVO paymentNoticeVO);

    int updatePaymentNotice(PaymentNoticeVO paymentNoticeVO);
}
