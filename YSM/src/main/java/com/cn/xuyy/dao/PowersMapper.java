package com.cn.xuyy.dao;

import com.cn.xuyy.entity.Powers;

public interface PowersMapper {
    int deleteByPrimaryKey(Integer powerId);

    int insert(Powers record);

    int insertSelective(Powers record);

    Powers selectByPrimaryKey(Integer powerId);

    int updateByPrimaryKeySelective(Powers record);

    int updateByPrimaryKey(Powers record);
}