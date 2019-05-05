package com.lanjiao.gym.service;

import com.lanjiao.gym.common.Response;
import com.lanjiao.gym.common.ResponseService;
import com.lanjiao.gym.dao.OrderDetailDao;
import com.lanjiao.gym.entity.OrderDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailService extends ResponseService {

    @Autowired
    OrderDetailDao orderDetailDao;

    public Response insertOrderDetail(OrderDetail orderDetail){

        System.out.println(orderDetail);
        int flag = orderDetailDao.insertOrderDetail(orderDetail);
        if(flag ==0){
            return fail(50008,"数据库插入数据失败");
        }
        return success(orderDetail.getOrderId(),"订单细节生成成功");
    }
}
