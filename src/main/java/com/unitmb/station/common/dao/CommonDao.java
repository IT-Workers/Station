package com.unitmb.station.common.dao;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

public class CommonDao implements InitializingBean {

    @Resource
    protected JdbcTemplate jdbcTemplate;

    @Override
    public void afterPropertiesSet() {

        /**
         * 头部导航菜单
         */
        String head_nav_menu_sql = "create table if not exists head_nav_menu (id integer primary key not null, name varchar(100), href varchar(200))";
        jdbcTemplate.execute(head_nav_menu_sql);
        /**
         * 关键词
         */
        String keyword_table_sql = "create table if not exists news (id integer primary key not null , source varchar(100), title varchar(1000), keyword text, content text, comment text)";
        jdbcTemplate.execute(keyword_table_sql);

        System.out.println("SQLite init ==========");

    }
}
