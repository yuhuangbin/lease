package com.lease.service.impl;

import com.lease.domain.Menu;
import com.lease.mapper.MenuMapper;
import com.lease.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Description:
 * author: yu.hb
 * Date: 2018-11-30
 */
@Service
public class MenuService implements IMenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<Menu> selectById(Integer id) {
        return menuMapper.selectById(id);
    }

    @Override
    public List<Menu> selectAll() {
        return menuMapper.selectAll();
    }

    @Override
    public List<Menu> selectByUpId(Integer upId) {
        return menuMapper.selectByUpId(upId);
    }
}
