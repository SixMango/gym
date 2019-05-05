package com.lanjiao.gym.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Data
public class Order {

    private Integer id;
    private String orderId;
    private String userId;
    private Integer orderPrice;

    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date orderDate;
    //1.待付款 2.已取消 3.未支付(过期）4.已付款
    private Integer orderStatus;
    //1.审核通过 ，2，审核未通过
    private Integer orderCheck;
   // 0 不免费  1免费
    private Integer orderFree;
    private String orderRemake;
    private String orderFaceback;
    private List<OrderDetail> orderDetailList;
    private WxUser wxUser;
    private String sportName;

    //状态的集合，查询时可能会有多个状态
    private List<Integer> listStatus;

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderId='" + orderId + '\'' +
                ", userId='" + userId + '\'' +
                ", orderPrice=" + orderPrice +
                ", orderDate=" + orderDate +
                ", orderStatus=" + orderStatus +
                ", orderCheck=" + orderCheck +
                ", orderFree=" + orderFree +
                ", orderRemake='" + orderRemake + '\'' +
                ", orderFaceback='" + orderFaceback + '\'' +
                ", orderDetailList=" + orderDetailList +
                '}';
    }
}