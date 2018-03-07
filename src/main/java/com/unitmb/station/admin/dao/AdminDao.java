package com.unitmb.station.admin.dao;

import com.unitmb.station.admin.model.HeadNavMenu;

public interface AdminDao {

    public int insertHeadNavMenu(HeadNavMenu menu);

    public int delHeadNavMenu(long id);

    public int updateHeadNavMenu(long id, String name);

    public int insertkeyWordContent(long id, String source, String title, String name, String content, String comment);

    public int deleteKetWord(String name);
}
