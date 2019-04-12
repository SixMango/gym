package com.lanjiao.gym.service;

import com.lanjiao.gym.dao.SitesMapper;
import com.lanjiao.gym.dao.SubsiteMapper;
import com.lanjiao.gym.entity.Sites;
import com.lanjiao.gym.entity.Subsite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SitesService {

    @Autowired
    private SitesMapper sitesMapper;

    public List<Sites> findSubsite(){
        List<Sites> list = sitesMapper.findSites();
        return list;
    }
}
