package com.unitmb.station.view.service.impl;

import com.unitmb.station.view.dao.ViewDao;
import com.unitmb.station.view.service.ViewService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class ViewServiceImpl implements ViewService {

    @Resource
    private ViewDao viewDao;

    @Override
    public List<Map<String, Object>> init() {
        return viewDao.init();
    }

    @Override
    public List<Map<String, Object>> getListNews(String scoure) {
        return viewDao.getListNews(scoure);
    }

    @Override
    public Map<String, Object> getNewsContent(String id) {
        return viewDao.getNewsContent(id);
    }

    @Override
    public List<Map<String, Object>> getNewsListByName(String name) {
        return viewDao.getNewsListByName(name);
    }

    @Override
    public List<Map<String, Object>> getRandNews() {
        return viewDao.getRandNews();
    }
}
