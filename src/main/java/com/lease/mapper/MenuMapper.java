package com.lease.mapper;

import com.lease.domain.Menu;

import java.util.List;

public interface MenuMapper {
    int insert(Menu record);

    int insertSelective(Menu record);

    List<Menu> selectById(Integer id);

    List<Menu> selectAll();

    List<Menu> selectByUpId(Integer upId);
}