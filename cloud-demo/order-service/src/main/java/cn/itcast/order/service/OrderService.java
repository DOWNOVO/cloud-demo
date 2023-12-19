package cn.itcast.order.service;

import cn.itcast.order.client.UserClient;
import cn.itcast.order.mapper.OrderMapper;
import cn.itcast.order.pojo.Order;
import cn.itcast.order.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private UserClient userClient;

    public Order queryOrderById(Long orderId) {
        // 1.查询订单
        Order order = orderMapper.findById(orderId);
//        //定义请求url   用restTemplate
//        String url = "http://userservice/user/" + order.getUserId();
//        //发送请求
//        User forObject = restTemplate.getForObject(url, User.class);
//        order.setUser(forObject);
        //用Feign
        User userClientById = userClient.findById(order.getUserId());
        order.setId(userClientById.getId());
        // 4.返回
        return order;
    }
}
