package com.unitmb.station.admin.service.impl;

import com.unitmb.station.admin.dao.AdminDao;
import com.unitmb.station.admin.model.HeadNavMenu;
import com.unitmb.station.admin.service.AdminService;
import com.unitmb.station.common.toutiao.Toutiao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class AdminServiceImpl implements AdminService{


    @Resource
    private AdminDao adminDao;

    @Resource
    private Toutiao toutiao;

    @Override
    @Transactional
    public boolean addHeadNavMenu(final HeadNavMenu menu) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                toutiao.getContent(menu.getName());
            }
        }).start();

        return adminDao.insertHeadNavMenu(menu) > 0;
    }

    @Override
    @Transactional
    public boolean delHeadNavMenu(long id, String name) {
        return (adminDao.delHeadNavMenu(id) + adminDao.deleteKetWord(name)) > 0;
    }

    @Override
    @Transactional
    public boolean updateHeadNavMenu(long id, String name) {
        return adminDao.updateHeadNavMenu(id, name) > 0;
    }
}
