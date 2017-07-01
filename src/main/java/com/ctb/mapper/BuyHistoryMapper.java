package com.ctb.mapper;

import com.ctb.model.BuyHistory;

import java.util.List;

public interface BuyHistoryMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BuyHistory record);

    int insertSelective(BuyHistory record);

    BuyHistory selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BuyHistory record);

    int updateByPrimaryKey(BuyHistory record);

    List<BuyHistory> failedOrders();
}