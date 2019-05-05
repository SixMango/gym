package com.lanjiao.gym.service;

import com.lanjiao.gym.common.Response;
import com.lanjiao.gym.common.ResponseService;
import com.lanjiao.gym.dao.OrderDetailDao;
import com.lanjiao.gym.dao.SubsiteDao;
import com.lanjiao.gym.dao.TimeDao;
import com.lanjiao.gym.entity.OrderDetail;
import com.lanjiao.gym.entity.Subsite;
import com.lanjiao.gym.entity.Time;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class SubsiteService extends ResponseService {
    @Autowired
    private SubsiteDao subsiteDao;

    @Autowired
    private TimeDao timeDao;

    @Autowired
    private OrderDetailDao orderDetailDao;

    public Response querySubsiteDetailBySiteIdAndSportId(@Param("sportId") String sportId, @Param("siteId") String siteId, @Param("date") String date) {
        if (sportId == null || "".equals(sportId) || siteId == null || "".equals(siteId) || date == null || "".equals(date))
            return fail(50000, "参数是空");
        Date curDate = new Date();
        //将字符串给是化
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            curDate = simpleDateFormat.parse(date);
        } catch (Exception e) {
            System.out.println("日期格式转化出错");
        }

        //得到所有的子场地
        List<Subsite> subsiteList = subsiteDao.querySubsiteBySiteIdAndSportId(sportId, siteId);
        for (Subsite subsite : subsiteList) {

            List<Time> timeList = timeDao.queryTimes();
            subsite.setTimeList(timeList);
            //根据当前时间和子场地id 查询已订购的场地 该场地订购的时间段
                       OrderDetail tem = new OrderDetail();
            tem.setSubsiteId(subsite.getSubsiteId());
            tem.setDetailTime(curDate);
            List<OrderDetail> orderDetailList = orderDetailDao.queryOrderDetail(tem);
            for (OrderDetail orderDetail : orderDetailList) {
                String timeId = orderDetail.getTimeId();
                for (Time midtime : subsite.getTimeList()) {
                    if (midtime.getTimeId().equals(timeId)) {
                        midtime.setFlag(0);
                    }
                }
            }

        }
        return success(subsiteList, "运动场查询成功");
    }

}
