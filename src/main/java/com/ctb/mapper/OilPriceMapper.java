package com.ctb.mapper;

import com.ctb.model.OilPrice;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

public interface OilPriceMapper {
    int deleteByPrimaryKey(Integer priceId);

    int insert(OilPrice record);

    int insertSelective(OilPrice record);

    OilPrice selectByPrimaryKey(Integer priceId);

    int updateByPrimaryKeySelective(OilPrice record);

    OilPrice getNowPriceByCityCode(@Param("priceCity")String priceCity,@Param("priceInsertTime")Date priceInsertTime);

    int updateByPrimaryKey(OilPrice record);
}