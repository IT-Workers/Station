package com.unitmb.station.view.service;

import java.util.List;
import java.util.Map;

public interface ViewService {

    public List<Map<String, Object>> init();

    public List<Map<String, Object>> getListNews(String scoure);

    public Map<String, Object> getNewsContent(String id);

    public List<Map<String, Object>> getNewsListByName(String name);
}
