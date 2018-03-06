package com.unitmb.station.admin.dao.impl;

import com.unitmb.station.admin.dao.AdminDao;
import com.unitmb.station.admin.model.HeadNavMenu;
import com.unitmb.station.common.dao.CommonDao;
import org.springframework.stereotype.Repository;

@Repository
public class AdminDaoImpl extends CommonDao implements AdminDao{

    @Override
    public int insertHeadNavMenu(HeadNavMenu menu) {
        return jdbcTemplate.update("INSERT INTO head_nav_menu(name, href) VALUES(?, ?)", menu.getName(), menu.getHref());
    }

    @Override
    public int delHeadNavMenu(long id) {
        return jdbcTemplate.update("DELETE from head_nav_menu where id = ?", id);
    }

    @Override
    public int updateHeadNavMenu(long id, String name) {
        return jdbcTemplate.update("UPDATE head_nav_menu SET name = ? where id = ?", name, id);
    }

    @Override
    public int insertkeyWordContent(long id, String source, String title, String name, String conent, String comment) {

        String insert = "INSERT INTO keyword (id, source, title, name, conent, comment)  VALUES(?, ?, ?, ?, ?, ?)";

        return jdbcTemplate.update(insert, id, source, title, name, conent, comment);
    }

    @Override
    public int deleteKetWord(String name) {
        return jdbcTemplate.update("DELETE from keyword where name = ?", name);
    }
}
