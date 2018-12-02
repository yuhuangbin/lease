package com.lease.service;

import com.lease.domain.Menu;

import java.util.List;

/**
 * Description:
 * author: yu.hb
 * Date: 2018-11-30
 */
public interface IMenuService {
    List<Menu> selectById(Integer root);

    List<Menu> selectAll();

    List<Menu> selectByUpId(Integer id);
}
