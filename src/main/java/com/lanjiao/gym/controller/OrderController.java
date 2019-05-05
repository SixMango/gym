package com.lanjiao.gym.controller;


import com.lanjiao.gym.common.Response;
import com.lanjiao.gym.entity.Order;
import com.lanjiao.gym.entity.WxUser;
import com.lanjiao.gym.service.ErrorService;
import com.lanjiao.gym.service.OrderService;
import com.lanjiao.gym.wechat.utils.WxUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    ErrorService errorService;

    @Autowired
    WxUserUtil wxUserUtil;

    @Autowired
    OrderService orderService;

    @PostMapping("/createOrder")
    public Response createOrder(@RequestHeader(value = "Authorization") String token,
                                @RequestBody Order order) {

        //如果未登录，返回

        WxUser wxUser = wxUserUtil.getWxUserByToken(token);

        order.setUserId(wxUser.getOpenid());
        Response response = orderService.insertOrder(order);
        return response;
    }

    @GetMapping("/queryOrderByOrderId")
    public Response queryOrderByOrderId(@RequestHeader(value = "Authorization", required = false) String token,
                                        @RequestParam(value = "orderId") String orderId) {

        WxUser wxUser = wxUserUtil.getWxUserByToken(token);
        Response response = orderService.queryOrderByOrderId(orderId, wxUser);
        return response;
    }

    @GetMapping("/queryOrdersByUser")
    public Response queryOrdersByUser(@RequestHeader(value = "Authorization", required = false) String token){
        WxUser wxUser = wxUserUtil.getWxUserByToken(token);
        Response response = orderService.queryOrder(wxUser);

        return response;
    }

    @PostMapping("/queryOrdersByStatus")
    public Response queryOrderByOrderStatus(@RequestHeader(value = "Authorization", required = false) String token,
          @RequestBody Map<String,List<Integer>> listmap){
        //根据状态查询用户所有的订单
        List<Integer> listStatus = listmap.get("status");
        WxUser wxUser = wxUserUtil.getWxUserByToken(token);
        Response response = orderService.queryOrderByOrderStatus(listStatus,wxUser);
        return response;
    }


    //取消订单状态
    @PostMapping("/cancelOrder")
    public Response cancelOrder(@RequestBody Order order){

        Response response = orderService.cancelOrder(order);

        return response;
    }
    //确认订单状态
    @PostMapping("/confirmOrder")
    public  Response  confirmOrder(@RequestBody Order order){

        Response response  = orderService.confirmOrder(order);
        return response;
    }
}
