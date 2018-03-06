package com.unitmb.station.admin.service;


import com.unitmb.station.admin.model.HeadNavMenu;

public interface AdminService {

    public boolean addHeadNavMenu (HeadNavMenu menu);

    public boolean delHeadNavMenu(long id, String name);

    public boolean updateHeadNavMenu(long id, String name);

}
