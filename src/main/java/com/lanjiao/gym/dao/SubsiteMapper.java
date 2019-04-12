package com.lanjiao.gym.dao;


import com.lanjiao.gym.entity.Subsite;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface SubsiteMapper {
   List<Subsite> findSubsite();
}