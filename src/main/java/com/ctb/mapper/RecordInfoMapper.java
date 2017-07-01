package com.ctb.mapper;

import com.ctb.model.RecordInfo;

public interface RecordInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(RecordInfo record);

    int insertSelective(RecordInfo record);

    RecordInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RecordInfo record);

    int updateByPrimaryKey(RecordInfo record);
}