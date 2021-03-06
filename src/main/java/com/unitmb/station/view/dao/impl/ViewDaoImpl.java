package com.unitmb.station.view.dao.impl;

import com.unitmb.station.common.dao.CommonDao;
import com.unitmb.station.view.dao.ViewDao;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class ViewDaoImpl extends CommonDao implements ViewDao{


    @Override
    public List<Map<String, Object>> init() {
        return  jdbcTemplate.queryForList("select * from head_nav_menu");
    }

    @Override
    public List<Map<String, Object>> getListNews(String scoure){
        return jdbcTemplate.queryForList("select * from news where source = ? LIMIT 15", scoure);
    }

    @Override
    public Map<String, Object> getNewsContent(String id) {
        return jdbcTemplate.queryForMap("select * from news where id = ?", id);
    }

    @Override
    public List<Map<String, Object>> getNewsListByName(String name) {
        return jdbcTemplate.queryForList("select * from news where keyword = ?", name);
    }

    @Override
    public List<Map<String, Object>> getRandNews() {
        return jdbcTemplate.queryForList("SELECT * FROM news ORDER BY random() LIMIT 20");
    }
}
