package com.unitmb.station.view.dao;

import java.util.List;
import java.util.Map;

public interface ViewDao {

    public List<Map<String, Object>> init();

    public List<Map<String,Object>> getListNews(String scoure);

    public Map<String, Object> getNewsContent(String id);

    public List<Map<String,Object>> getNewsListByName(String name);
}
