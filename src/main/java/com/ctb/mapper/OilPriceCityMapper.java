package com.ctb.mapper;

import com.ctb.model.OilPriceCity;

import java.util.List;

public interface OilPriceCityMapper {
    int deleteByPrimaryKey(Integer priceCityId);

    int insert(OilPriceCity record);

    int insertSelective(OilPriceCity record);

    OilPriceCity selectByPrimaryKey(Integer priceCityId);

    List<OilPriceCity> getAllCitys();

    int updateByPrimaryKeySelective(OilPriceCity record);

    int updateByPrimaryKey(OilPriceCity record);
}