package com.lanjiao.gym.service;

import com.lanjiao.gym.common.Response;
import com.lanjiao.gym.common.ResponseService;
import com.lanjiao.gym.dao.OrderDetailDao;
import com.lanjiao.gym.dao.ReservationDetailsDao;
import com.lanjiao.gym.dao.SubsiteDao;
import com.lanjiao.gym.dao.TimeDao;
import com.lanjiao.gym.entity.OrderDetail;
import com.lanjiao.gym.entity.ReservationDetails;
import com.lanjiao.gym.entity.Subsite;
import com.lanjiao.gym.entity.Time;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class SubsiteService extends ResponseService {
    @Autowired
    private SubsiteDao subsiteDao;

    @Autowired
    private TimeDao timeDao;

    @Autowired
    private OrderDetailDao orderDetailDao;

    public Response querySubsiteDetailBySiteIdAndSportId(@Param("sportId") String sportId, @Param("siteId") String siteId,@Param("date")String date) {
        if (sportId == null || "".equals(sportId) || siteId == null || "".equals(siteId) || date==null || "".equals(date))
            return fail(50000, "参数是空");
        String curDate = "";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try{
            curDate = simpleDateFormat.format(simpleDateFormat.parse(date));
        }catch (Exception e){
            System.out.println("日期格式转化出错");
        }


        List<Subsite> subsiteList = subsiteDao.querySubsiteBySiteIdAndSportId(sportId, siteId);
        if (subsiteList != null){
            for (Subsite subsite:subsiteList){
                String subsiteId = subsite.getSubsiteId();
                List<Time> timeList = timeDao.queryTimes();
                subsite.setTimeList(timeList);
                List<OrderDetail> orderDetailList = orderDetailDao.queryOrderDetailBySubsiteIdAndDetailTime(subsiteId,curDate);
                for(OrderDetail orderDetail :orderDetailList){
                    String timeId = orderDetail.getTimeId();
                    for(Time midtime : subsite.getTimeList()){
                        if(midtime.getTimeId().equals(timeId)){
                            midtime.setFlag(0);
                        }
                    }
                }

                //subsite.setReservationDetailsList(reservationDetailsList);

            }
            return success( subsiteList, "运动场查询成功");
        }

        return fail(50006, "数据库查询失败");

    }

}
